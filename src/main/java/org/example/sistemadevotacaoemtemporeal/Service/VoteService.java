package org.example.sistemadevotacaoemtemporeal.Service;

import dto.user_dto.UserRequestDTO;
import dto.user_dto.UserResponseDTO;
import org.example.sistemadevotacaoemtemporeal.Infrastructure.Entity.Pool.PoolEntity;
import org.example.sistemadevotacaoemtemporeal.Infrastructure.Entity.Pool.PoolOption;
import org.example.sistemadevotacaoemtemporeal.Infrastructure.Entity.User.UserEntity;
import org.example.sistemadevotacaoemtemporeal.Repository.PoolRepository;
import org.example.sistemadevotacaoemtemporeal.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

// por enquanto tá tudo no mesmo service, mas eu vou mudar isso depois
@Service
@RequiredArgsConstructor
@Component
public class VoteService {


    @Autowired
    private final UserRepository userRepository;
    @Autowired
    public final PoolRepository poolRepository;


    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        UserEntity user = new UserEntity(
                userRequestDTO.getUserDTOName(),
                userRequestDTO.getUserDTOEmail()
        );

        userRepository.save(user);

        return new UserResponseDTO(
                user.getUserName(),
                user.getUserEmail(),
                user.getUserID()
        );
    }


    public PoolEntity createPool() {
        PoolEntity pool = new PoolEntity(
                "Minha Enquete",
                "Qual peixe é melhor de comer com farinha??",
                List.of(
                        new PoolOption("Bagre"),
                        new PoolOption("Tilapia"),
                        new PoolOption("Alambari")
                )
        );
        poolRepository.save(pool);
        return pool;
    }


    public List<PoolEntity> getAllPools() {
        return poolRepository.findAll();
    }


    public String findByUuid(UUID userID){ // find user by uuid
        Optional<UserEntity> userFound = userRepository.findByuserID(userID);

        UserEntity user = userFound.orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        return "Usuário referente ao id:\n Nome:" + user.getUserName() +
                "\nEmail: " + user.getUserEmail();
    }


    public String deleteByUuid(UUID userID){// delete user by uuid
        Optional<UserEntity> userToDelete = userRepository.findByuserID(userID);
        UserEntity user = userToDelete.orElseThrow(() -> new RuntimeException("This user doesn´t exists"));
        String name = user.getUserName();
        userRepository.deleteById(userID);
        return "User '" + name + "' Deleted!";
    }


    public String voteIN(Integer poolID, Integer optionID, UUID userID) {

        Optional<PoolEntity> optionalPool = poolRepository.findById(poolID);
        Optional<UserEntity> optionalUser = userRepository.findById(userID);

        PoolEntity poolEntity1 = optionalPool.orElseThrow(() -> new RuntimeException("Pool not found!"));
        UserEntity userEntity1 = optionalUser.orElseThrow(() -> new RuntimeException("User not found!"));

        List<PoolOption> poolOptions = poolEntity1.getPoolOptions();

        for (PoolOption poolOption : poolOptions) {
            if (Objects.equals(poolOption.getOptionID(), optionID)) {
                Integer numOfVotes = poolOption.getNumberOfVotes();
                poolOption.setNumberOfVotes(numOfVotes + 1);
            }
            //throw new RuntimeException("Error: This option doesn't exist!");
        }
        //return new VoteEntity(poolID, optionID, userID);
        return "The user " + userEntity1.getUserName() +
                "\nHas voted on the pool: " + poolEntity1.getPoolTitle() +
                "\nWith the question: " + poolEntity1.getPoolQuestion() +
                "\nAnd the options " + poolEntity1.getPoolOptions() +
                "\nHe choose the option: " + poolEntity1.getPoolOptionById(optionID) +
                "\nDate of the operation: " + LocalDate.now();
    }
}