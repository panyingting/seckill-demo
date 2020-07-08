package reinty.study.seckill.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import reinty.study.seckill.core.entity.GoodsEntity;

public interface GoodsRepository  extends JpaRepository<GoodsEntity, Long> {

    @Query(nativeQuery = true, value = "update t_stock set stock = stock - 1 where sku_id = :sku_id and stock > 0")
    int decrementStock(@Param("sku_id") long sku_id);
}
