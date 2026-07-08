package org.example.sistemadevotacaoemtemporeal.Repository;

import jakarta.persistence.Id;
import org.example.sistemadevotacaoemtemporeal.Infrastructure.Entity.Pool.PoolEntity;
import org.example.sistemadevotacaoemtemporeal.Infrastructure.Entity.Pool.Vote;
import org.example.sistemadevotacaoemtemporeal.Infrastructure.Entity.User.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    boolean existsByUserAndPool(UserEntity user, PoolEntity pool);
}
