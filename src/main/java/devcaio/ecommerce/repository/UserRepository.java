package devcaio.ecommerce.repository;

import devcaio.ecommerce.entity.UserEntity;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID>{
}
