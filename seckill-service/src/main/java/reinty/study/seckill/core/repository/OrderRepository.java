package reinty.study.seckill.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reinty.study.seckill.core.entity.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
