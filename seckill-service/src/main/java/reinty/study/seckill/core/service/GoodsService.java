package reinty.study.seckill.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reinty.study.seckill.core.entity.GoodsEntity;
import reinty.study.seckill.core.repository.GoodsRepository;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class GoodsService {

    private final ConcurrentHashMap<Long, Long> EXPIRE_GOODS = new ConcurrentHashMap<>();

    @Autowired
    private GoodsRepository goodsRepository;


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


}
