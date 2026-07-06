package org.example.sistemadevotacaoemtemporeal.Service;

import DTO.PoolDTO.PoolOptionResponseDTO;
import DTO.PoolDTO.PoolRequestDTO;
import DTO.PoolDTO.PoolResponseDTO;
import org.example.sistemadevotacaoemtemporeal.Exception.UserNotFoundException;
import org.example.sistemadevotacaoemtemporeal.Infrastructure.Entity.Pool.PoolEntity;
import org.example.sistemadevotacaoemtemporeal.Infrastructure.Entity.Pool.PoolOption;
import org.example.sistemadevotacaoemtemporeal.Infrastructure.Entity.Pool.VoteRequest;
import org.example.sistemadevotacaoemtemporeal.Infrastructure.Entity.Pool.VoteResponse;
import org.example.sistemadevotacaoemtemporeal.Infrastructure.Entity.User.UserEntity;
import org.example.sistemadevotacaoemtemporeal.Repository.PoolRepository;
import org.example.sistemadevotacaoemtemporeal.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Component
public class PoolService {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    public final PoolRepository poolRepository;


    public PoolResponseDTO createPool(PoolRequestDTO poolRequestDTO, UUID userid) {

        if(!(userRepository.existsById(userid))){
            throw new UserNotFoundException("User dont exists!");
        }

        Optional<UserEntity> userr = userRepository.findByuserid(userid);
        UserEntity user = userr.orElseThrow(() -> new RuntimeException("Problem on <PoolService>"));

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
                user.getUserName(),
                pool.getPoolID(),
                pool.getCreationDate(),
                pool.getCloseDate(),
                pool.getPoolTitle(),
                pool.getPoolQuestion(),
                pool.getPoolStatus(),
                responseOptions
        );
    }

    public PoolResponseDTO getPool(Integer poolid, UUID userid) {

        Optional<UserEntity> userr = userRepository.findByuserid(userid);
        UserEntity user = userr.orElseThrow(() -> new RuntimeException("Problem on <PoolService>"));



        Optional<PoolEntity> poolFound = poolRepository.findById(poolid);

        PoolEntity poolEntityFound = poolFound.orElseThrow(() -> new RuntimeException("Pool not found"));

        List<PoolOptionResponseDTO> poolOptions = new ArrayList<>();
        for (PoolOption poolOption : poolEntityFound.getPoolOptions()) {
            poolOptions.add(
                    new PoolOptionResponseDTO(
                            poolOption.getOptionID(),
                            poolOption.getOptionText(),
                            poolOption.getNumberOfVotes()
                    )
            );
        }

        return new PoolResponseDTO(
                user.getUserName(),
                poolEntityFound.getPoolID(),
                poolEntityFound.getCreationDate(),
                poolEntityFound.getCloseDate(),
                poolEntityFound.getPoolTitle(),
                poolEntityFound.getPoolQuestion(),
                poolEntityFound.getPoolStatus(),
                poolOptions
        );
    }

    public VoteResponse vote(VoteRequest voteRequest) {
        Optional<UserEntity> userFind = userRepository.findById(voteRequest.getUserid());
        Optional<PoolEntity> poolFind = poolRepository.findById(voteRequest.getPoolid());


        UserEntity userEntity = userFind.orElseThrow(() -> new UserNotFoundException("You are not logged! Please, create another account! ( ﾉ ﾟｰﾟ)ﾉ"));
        PoolEntity poolEntity = poolFind.orElseThrow(() -> new RuntimeException("Some error happen, try later! it seems like this pool don't exists!"));


        List<PoolOption> poolOptions = poolEntity.getPoolOptions();
        for (PoolOption poolOption : poolOptions) {
            if (poolOption.getOptionID().equals(voteRequest.getOptionid())) {
                poolOption.voteOnOption();
                poolRepository.save(poolEntity);
                return new VoteResponse(userEntity.getUserName(), poolEntity.getPoolTitle(), poolOption.getOptionText());
            }
        }
        throw new RuntimeException("Error");
    }

}