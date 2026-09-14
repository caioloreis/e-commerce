package devcaio.ecommerce.repository;

import devcaio.ecommerce.entity.OrderItemEntity;
import devcaio.ecommerce.entity.OrderItemId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItemEntity, OrderItemId> {
}
