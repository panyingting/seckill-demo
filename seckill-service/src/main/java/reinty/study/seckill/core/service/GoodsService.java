package reinty.study.seckill.core.service;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reinty.study.seckill.core.common.Const;
import reinty.study.seckill.core.entity.GoodsEntity;
import reinty.study.seckill.core.repository.GoodsRepository;
import reinty.study.seckill.core.repository.PromotionActivityRepository;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class GoodsService {

    private final ConcurrentHashMap<Long, Long> EXPIRE_GOODS = new ConcurrentHashMap<>();

    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private RedisService redisService;

    public boolean isSaleOff(long goodsId){
        return EXPIRE_GOODS.get(goodsId) != null;
    }

    public void setSaleOff(long goodsId){
        EXPIRE_GOODS.put(goodsId, System.currentTimeMillis());
    }


    public GoodsEntity getGoodsInfo(long goodsId){
        return goodsRepository.getOne(goodsId);
    }

    public static String getStockKey(long goodsId){
        return "stock_"+goodsId;
    }


    public GoodsEntity get(long id){
        String key = getIdKey(id);
        String entityJson = redisService.get(key);
        if(entityJson == null){
            GoodsEntity entity = goodsRepository.getOne(id);
            String jsonStr = entity == null ? Const.CHACHE_NULL : JSON.toJSONString(entity);
            redisService.set(key, jsonStr);
            return entity;
        }
        if(entityJson.equals(Const.CHACHE_NULL)){
            return null;
        }
        return JSON.parseObject(entityJson, GoodsEntity.class);
    }


    private String getIdKey(long id){
        return "goods_"+id;
    }
}
