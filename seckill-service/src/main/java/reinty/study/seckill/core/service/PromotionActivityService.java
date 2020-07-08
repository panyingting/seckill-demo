package reinty.study.seckill.core.service;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reinty.study.seckill.core.common.Const;
import reinty.study.seckill.core.entity.PromotionActivityEntity;
import reinty.study.seckill.core.repository.PromotionActivityRepository;

@Service
public class PromotionActivityService {

    @Autowired
    private RedisService redisService;
    @Autowired
    private PromotionActivityRepository promotionActivityRepository;

    public PromotionActivityEntity get(long id){
        String key = getIdKey(id);
        String entityJson = redisService.get(key);
        if(entityJson == null){
            PromotionActivityEntity entity = promotionActivityRepository.getOne(id);
            String jsonStr = entity == null ? Const.CHACHE_NULL : JSON.toJSONString(entity);
            redisService.set(key, jsonStr);
            return entity;
        }
        if(entityJson.equals(Const.CHACHE_NULL)){
            return null;
        }
        return JSON.parseObject(entityJson, PromotionActivityEntity.class);
    }



    private String getIdKey(long id){
        return "prom_"+id;
    }
}
