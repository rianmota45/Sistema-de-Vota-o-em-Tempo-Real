package org.example.sistemadevotacaoemtemporeal.user;

import lombok.NoArgsConstructor;
import org.example.sistemadevotacaoemtemporeal.user.domain.UserEntity;
import org.example.sistemadevotacaoemtemporeal.user.dto.UserRequestDTO;
import org.example.sistemadevotacaoemtemporeal.user.dto.UserResponseDTO;
import org.example.sistemadevotacaoemtemporeal.user.exception.EmailAlreadyExistsException;
import org.example.sistemadevotacaoemtemporeal.user.repository.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.UUID;

@NoArgsConstructor(force = true)
public class CreateUser {

    private final FindUser findUser;
    private final UserRepository userRepository;

    public UserResponseDTO createAndSaveUserEntity(UserRequestDTO userRequestDTO){
        UserEntity user = new UserEntity(
                userRequestDTO.getUserDTOName(),
                userRequestDTO.getUserDTOEmail()
        );
        try {
            assert userRepository != null;
            userRepository.save(user);
        } catch (DataIntegrityViolationException e){
            throw new EmailAlreadyExistsException("This Email is Already Registered!");
        }
        return createUserResDTOByEntity(user);
    }

    public UserResponseDTO createUserResDTOByEntity(UserEntity user){
        return new UserResponseDTO(
                user.getUserName(),
                user.getUserEmail(),
                user.getUserid()
        );
    }

    public UserResponseDTO createUserResDTOById(UUID uuid){
        assert findUser != null;
        UserEntity user = findUser.findAndReturnUser(uuid);

        return new UserResponseDTO(
                user.getUserName(),
                user.getUserEmail(),
                user.getUserid()
        );
    }


}
