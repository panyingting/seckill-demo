package reinty.study.seckill.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_stock")
public class StockEntity extends BaseAutoIdEntity {
    /**
     * skuId
     */
    private long skuId;
    /**
     * 库存数
     */
    private long stock;
    /**
     * 拓展配置
     */
    private String conf;

    @Column(name = "sku_id")
    public long getSkuId() {
        return skuId;
    }

    public void setSkuId(long skuId) {
        this.skuId = skuId;
    }

    @Column(name = "stock")
    public long getStock() {
        return stock;
    }

    public void setStock(long stock) {
        this.stock = stock;
    }

    @Column(name = "conf")
    public String getConf() {
        return conf;
    }

    public void setConf(String conf) {
        this.conf = conf;
    }
}
