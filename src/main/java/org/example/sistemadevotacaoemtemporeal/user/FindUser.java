package org.example.sistemadevotacaoemtemporeal.user;

import lombok.NoArgsConstructor;
import org.example.sistemadevotacaoemtemporeal.user.domain.UserEntity;
import org.example.sistemadevotacaoemtemporeal.user.exception.UserNotFoundException;
import org.example.sistemadevotacaoemtemporeal.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.UUID;

@NoArgsConstructor(force = true)
public class FindUser {
    @Autowired
    private final UserRepository userRepository;

    public UserEntity findAndReturnUser(UUID uuid){
        assert userRepository != null;
        Optional<UserEntity> userFound = userRepository.findByuserid(uuid);
        return userFound.orElseThrow(() -> new UserNotFoundException("User not found!"));
    }

    public String findAndDeleteById(UUID uuid){
        assert userRepository != null;
        Optional<UserEntity> userToDelete = userRepository.findByuserid(uuid);
        UserEntity user = userToDelete.orElseThrow(()
                -> new UserNotFoundException("This user does´t exists")
        );

        String name = user.getUserName();
        userRepository.deleteById(uuid);
        return "User '" + name + "' Deleted!";
    }
}
