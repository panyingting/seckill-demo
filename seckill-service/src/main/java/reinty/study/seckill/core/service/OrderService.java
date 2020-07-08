package reinty.study.seckill.core.service;

import com.revinate.guava.util.concurrent.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reinty.study.seckill.core.common.CheckUtils;
import reinty.study.seckill.core.common.WebResult;
import reinty.study.seckill.core.common.exception.ExceptionCodeEnum;
import reinty.study.seckill.core.common.exception.SecKillBusinessException;
import reinty.study.seckill.core.repository.OrderRepository;

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

    private final RateLimiter rateLimiter = RateLimiter.create(300.0);


    public WebResult<Long> createOrder(Long promoteId, Long goodsId, Long userId){

        try{

            // 本地库存校验
            CheckUtils.checkIfTrue( goodsService.isSaleOff(goodsId), "本地库存标记售完", ExceptionCodeEnum.STOCK_NOT_ENOUGH);

            // 用户请求数量校验
            CheckUtils.checkIfTrue( !userRequestCount(userId), "用户请求过多", ExceptionCodeEnum.SYS_EXCEPTION);

            // 系统接口访问量控制
            CheckUtils.checkIfTrue( !rateLimiter.tryAcquire(), "请求数量达到上线", ExceptionCodeEnum.SYS_EXCEPTION);

            // redis库存控制
            long redisStock = redisService.decr( goodsService.getStockKey(goodsId));
            CheckUtils.checkIfTrue( redisStock < 0, "缓存库存扣减完毕", ExceptionCodeEnum.STOCK_NOT_ENOUGH);


            return null;




        }catch (SecKillBusinessException ex){
            LOGGER.warn("下单接口逻辑处理失败，promoteId:{}, goodsId:{}, msg:{}", promoteId, goodsId, ex.getMessage());
            return WebResult.failResult(ex.getCode(), ex.getMessage(), 0L);
        }

    }

    private boolean userRequestCount( long userId){
        String key = "user_req_"+userId;
        Long req = redisService.incr( key);
        if(req == 1){
            redisService.expire(key, 60);
        }
        return req > 0 && req < MAX_REQUESTS_PER_MINUTE;
    }
}
