package org.example.sistemadevotacaoemtemporeal.Repository;

import org.example.sistemadevotacaoemtemporeal.Infrastructure.Entity.Pool.PoolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoolRepository extends JpaRepository<PoolEntity, Integer> {
}
