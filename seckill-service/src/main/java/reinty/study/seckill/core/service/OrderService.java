package reinty.study.seckill.core.service;

import com.alibaba.fastjson.JSON;
import com.revinate.guava.util.concurrent.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reinty.study.seckill.core.common.CheckUtils;
import reinty.study.seckill.core.common.WebResult;
import reinty.study.seckill.core.common.exception.ExceptionCodeEnum;
import reinty.study.seckill.core.common.exception.SecKillBusinessException;
import reinty.study.seckill.core.entity.GoodsEntity;
import reinty.study.seckill.core.entity.PromotionActivityEntity;
import reinty.study.seckill.core.kafka.MyKafkaProducer;
import reinty.study.seckill.core.repository.OrderRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);

    private static final int MAX_REQUESTS_PER_MINUTE = 20;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private PromotionActivityService promotionActivityService;
    @Autowired
    private MyKafkaProducer kafkaProducer;

    private final RateLimiter rateLimiter = RateLimiter.create(300.0);


    public WebResult<String> createOrder(Long promoteId, Long goodsId, Long userId){

        try{

            checkParameter(promoteId, goodsId);

            // 本地库存校验
            CheckUtils.checkIfTrue( goodsService.isSaleOff(goodsId), "本地库存标记售完", ExceptionCodeEnum.STOCK_NOT_ENOUGH);

            // 用户请求数量校验
            CheckUtils.checkIfTrue( !userRequestCount(userId), "用户请求过多", ExceptionCodeEnum.SYS_EXCEPTION);

            // 系统接口访问量控制
            CheckUtils.checkIfTrue( !rateLimiter.tryAcquire(), "请求数量达到上线", ExceptionCodeEnum.SYS_EXCEPTION);

            // redis库存控制
            long redisStock = redisService.decr( GoodsService.getStockKey(goodsId));
            CheckUtils.checkIfTrue( redisStock < 0, "缓存库存扣减完毕", ExceptionCodeEnum.STOCK_NOT_ENOUGH);

            String orderNo = createOrderNo();

            Map<String, Object> map = new HashMap<>(6);
            map.put("orderNo", orderNo);
            map.put("promoteId", promoteId);
            map.put("goodsId", goodsId);
            map.put("userId", userId);

            // 订单消费队列
            kafkaProducer.sendMsg("order-create", JSON.toJSONString(map));

            return WebResult.successResult(orderNo);

        }catch (SecKillBusinessException ex){
            LOGGER.warn("下单接口逻辑处理失败，promoteId:{}, goodsId:{}, msg:{}", promoteId, goodsId, ex.getMessage());
            return WebResult.failResult(ex.getCode(), ex.getMessage(), null);
        }

    }

    public void setFailedOrder(String orderNo){
        redisService.set("succ_"+orderNo, "F");
    }

    public boolean createFailed(String orderNo){
        String result = redisService.get("succ_"+orderNo);
        return "F".equals(result);
    }

    private void checkParameter( Long promoteId, Long goodsId){
        PromotionActivityEntity promotionActivityEntity = promotionActivityService.get(promoteId);
        CheckUtils.checkIfNull( promotionActivityEntity, "无效的活动信息");

        long currTime = System.currentTimeMillis();
        CheckUtils.checkIfTrue( promotionActivityEntity.getStartTime() > currTime, "活动尚未开始");
        CheckUtils.checkIfTrue( promotionActivityEntity.getEndTime() < currTime, "活动已结束");

        GoodsEntity goodsEntity = goodsService.get(goodsId);
        CheckUtils.checkIfNull( goodsEntity, "无效的商品信息");
    }

    private boolean userRequestCount( long userId){
        String key = "user_req_"+userId;
        Long req = redisService.incr( key);
        if(req == 1){
            redisService.expire(key, 60);
        }
        return req > 0 && req < MAX_REQUESTS_PER_MINUTE;
    }


    private String createOrderNo(){
        return UUID.randomUUID().toString().replace("-", "");
    }
}
