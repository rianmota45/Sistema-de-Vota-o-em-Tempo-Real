package Repository;

import Infrastructure.Entity.Pool.PoolEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PoolRepository extends JpaRepository<PoolEntity, Integer> {
}
