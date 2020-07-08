package reinty.study.seckill.core.service;

import org.springframework.beans.factory.annotation.Autowired;

public class StockService {

    @Autowired
    private RedisService redisService;


    public void intStock(long goodsId, int num){
        String key = GoodsService.getStockKey(goodsId);
        int redisStock = (int)Math.ceil(num * 1.2);
        redisService.set(key, String.valueOf(redisStock));
    }
}
