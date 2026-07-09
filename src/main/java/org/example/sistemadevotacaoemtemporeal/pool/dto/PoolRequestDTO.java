package org.example.sistemadevotacaoemtemporeal.pool.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class PoolRequestDTO {
    private LocalDate closeDate;
    private String poolTitle;
    private String poolQuestion;
    private List<PoolOptionDTO> poolOptions;
}
