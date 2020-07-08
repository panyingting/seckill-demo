package reinty.study.seckill.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import reinty.study.seckill.core.entity.StockEntity;
import reinty.study.seckill.core.repository.StockRepository;

public class StockService {

    @Autowired
    private RedisService redisService;

    private StockRepository stockRepository;


    public void intStock(long goodsId, int num){
        StockEntity stockEntity = new StockEntity();
        stockEntity.setSkuId(goodsId);
        stockEntity.setStock(num);
        String key = GoodsService.getStockKey(goodsId);
        int redisStock = (int)Math.ceil(num * 1.2);
        redisService.set(key, String.valueOf(redisStock));
        stockRepository.save(stockEntity);
    }

    public boolean decrement(long goodsId){
        int row = stockRepository.decrementStock(goodsId);
        return row > 0;
    }

    public boolean increment(long goodsId){
        int row = stockRepository.decrementStock(goodsId);
        return row > 0;
    }
}
