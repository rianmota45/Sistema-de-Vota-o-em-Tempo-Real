package org.example.sistemadevotacaoemtemporeal.pool;

import lombok.AllArgsConstructor;
import org.example.sistemadevotacaoemtemporeal.pool.domain.PoolEntity;
import org.example.sistemadevotacaoemtemporeal.pool.dto.PoolRequestDTO;
import org.example.sistemadevotacaoemtemporeal.pool.dto.PoolResponseDTO;
import org.example.sistemadevotacaoemtemporeal.user.domain.UserEntity;

@AllArgsConstructor
public class PoolFactory {

    private PoolOptionFactory poolOptionFactory;

    public PoolEntity createPool(PoolRequestDTO poolRequestDTO) {
        return new PoolEntity(
                poolRequestDTO.getCloseDate(),
                poolRequestDTO.getPoolTitle(),
                poolRequestDTO.getPoolQuestion(),
                poolOptionFactory.parseToPoolOption(poolRequestDTO.getPoolOptions())
        );
    }


    public PoolResponseDTO createPoolResponseDTO(PoolEntity poolEntity, UserEntity user){
        return new PoolResponseDTO(
                user.getUserName(),
                poolEntity.getPoolID(),
                poolEntity.getCreationDate(),
                poolEntity.getCloseDate(),
                poolEntity.getPoolTitle(),
                poolEntity.getPoolQuestion(),
                poolEntity.getPoolStatus(),
                poolOptionFactory
                        .parseToPoolOptionResDTO(poolEntity.getPoolOptions())
        );
    }
}