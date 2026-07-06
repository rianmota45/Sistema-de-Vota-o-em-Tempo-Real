package org.example.sistemadevotacaoemtemporeal.Repository;

import org.example.sistemadevotacaoemtemporeal.Infrastructure.Entity.User.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByuserid(UUID userid);
}
