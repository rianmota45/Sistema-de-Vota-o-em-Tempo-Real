package org.example.sistemadevotacaoemtemporeal.Infrastructure.Functions;

import lombok.AllArgsConstructor;
import org.example.sistemadevotacaoemtemporeal.Infrastructure.Entity.User.UserEntity;
import org.example.sistemadevotacaoemtemporeal.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
public class Verification {

    @Autowired
    private final UserRepository userRepository;

    public boolean userExists(UUID userid){
        Optional<UserEntity> user = userRepository.findByuserid(userid);
        return user.isPresent();
    }
}
