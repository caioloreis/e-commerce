package devcaio.ecommerce.repository;

import devcaio.ecommerce.entity.BillingAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingAddressRepository extends JpaRepository<BillingAddressEntity, Long> {
}