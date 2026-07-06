package org.example.sistemadevotacaoemtemporeal.Service;

import DTO.UserDTO.UserRequestDTO;
import DTO.UserDTO.UserResponseDTO;
import lombok.RequiredArgsConstructor;
import org.example.sistemadevotacaoemtemporeal.Exception.EmailAlreadyExistsException;
import org.example.sistemadevotacaoemtemporeal.Exception.UserNotFoundException;
import org.example.sistemadevotacaoemtemporeal.Infrastructure.Entity.User.UserEntity;
import org.example.sistemadevotacaoemtemporeal.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Component
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        UserEntity user = new UserEntity(
                userRequestDTO.getUserDTOName(),
                userRequestDTO.getUserDTOEmail()
        );
        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException e){
            throw new EmailAlreadyExistsException("This Email is already registered! ", e);
        }

        return new UserResponseDTO(
                user.getUserName(),
                user.getUserEmail(),
                user.getUserid()
        );
    }

    public String findByUuid(UUID userid) {
        Optional<UserEntity> userFound = userRepository.findByuserid(userid);

        UserEntity user = userFound.orElseThrow(() -> new UserNotFoundException("User not found!"));

        return "Usuário referente ao id:\n Nome:" + user.getUserName() +
                "\nEmail: " + user.getUserEmail();
    }

    public String deleteByUuid(UUID userID) {
        Optional<UserEntity> userToDelete = userRepository.findByuserid(userID);
        UserEntity user = userToDelete.orElseThrow(()
                -> new UserNotFoundException("This user does´t exists")
        );

        String name = user.getUserName();
        userRepository.deleteById(userID);
        return "User '" + name + "' Deleted!";
    }
}
