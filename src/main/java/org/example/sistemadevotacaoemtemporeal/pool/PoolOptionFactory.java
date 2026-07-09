package org.example.sistemadevotacaoemtemporeal.pool;

import org.example.sistemadevotacaoemtemporeal.pool.domain.PoolOption;
import org.example.sistemadevotacaoemtemporeal.pool.dto.PoolOptionDTO;
import org.example.sistemadevotacaoemtemporeal.pool.dto.PoolOptionResponseDTO;

import java.util.List;

public class PoolOptionFactory {

    /**
     * Parse from {@code List<PoolOptionDTO>} to {@code List<PoolOption>}
     */
    public List<PoolOption> parseToPoolOption(List<PoolOptionDTO> poolOptionDTOS) {
        return poolOptionDTOS.stream()
                .map(option -> new PoolOption(
                                option.getOptionText()
                        )
                ).toList();
    }

    public List<PoolOptionResponseDTO> parseToPoolOptionResDTO(List<PoolOption> poolOptionList) {
        return poolOptionList.stream()
                .map(poolOption -> new PoolOptionResponseDTO(
                                poolOption.getOptionID(),
                                poolOption.getOptionText(),
                                poolOption.getNumberOfVotes()
                        )
                ).toList();
    }
}