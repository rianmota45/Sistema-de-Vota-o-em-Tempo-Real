package org.example.sistemadevotacaoemtemporeal.Service;

import DTO.PoolDTO.PoolOptionResponseDTO;
import DTO.PoolDTO.PoolRequestDTO;
import DTO.PoolDTO.PoolResponseDTO;
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

@Service
@RequiredArgsConstructor
@Component
public class PoolService {


    @Autowired
    private final UserRepository userRepository;
    @Autowired
    public final PoolRepository poolRepository;


    public PoolResponseDTO createPool(PoolRequestDTO poolRequestDTO) {

        List<PoolOption> poolOptions = poolRequestDTO.getPoolOptions().stream()
                .map(dto -> new PoolOption(dto.getOptionText()))
                .toList();

        PoolEntity pool = new PoolEntity(
                poolRequestDTO.getCloseDate(),
                poolRequestDTO.getPoolTitle(),
                poolRequestDTO.getPoolQuestion(),
                poolOptions
        );

        poolOptions.forEach(option -> option.setPoolEntity(pool));
        poolRepository.save(pool);

        List<PoolOptionResponseDTO> responseOptions = pool.getPoolOptions().stream()
                .map(opt -> new PoolOptionResponseDTO(
                        opt.getOptionID(),
                        opt.getOptionText(),
                        opt.getNumberOfVotes()
                ))
                .toList();

        return new PoolResponseDTO(
                pool.getPoolID(),
                pool.getCreationDate(),
                pool.getCloseDate(),
                pool.getPoolTitle(),
                pool.getPoolQuestion(),
                pool.getPoolStatus(),
                responseOptions
        );
    }


    public List<PoolEntity> getAllPools() {
        return poolRepository.findAll();
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