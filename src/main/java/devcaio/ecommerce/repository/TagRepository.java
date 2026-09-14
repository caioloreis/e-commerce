package devcaio.ecommerce.repository;

import devcaio.ecommerce.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<TagEntity, Long> {
}
