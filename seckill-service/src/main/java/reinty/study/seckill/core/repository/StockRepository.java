package reinty.study.seckill.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import reinty.study.seckill.core.entity.StockEntity;

public interface StockRepository extends JpaRepository<StockEntity, Long> {

    /**
     * 扣减数据库库存
     * @param sku_id 商品Id，唯一索引（ 此处简单处理， goodsId 看作 skuId, 事实中goods可能包含多个 sku）
     * @return 扣减成功 返回 1， 失败返回 0；
     */
    @Query(nativeQuery = true, value = "update t_stock set stock = stock - 1 where sku_id = :sku_id and stock > 0")
    int decrementStock(@Param("sku_id") long sku_id);

    /**
     * 增加数据库库存
     * @param sku_id 商品Id，唯一索引（ 此处简单处理， goodsId 看作 skuId, 事实中goods可能包含多个 sku）
     * @return 成功 返回 1， 失败返回 0；
     */
    @Query(nativeQuery = true, value = "update t_stock set stock = stock + 1 where sku_id = :sku_id")
    int incrementStock(@Param("sku_id") long sku_id);

}