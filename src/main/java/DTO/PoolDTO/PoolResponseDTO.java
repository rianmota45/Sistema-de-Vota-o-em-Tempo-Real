package DTO.PoolDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.sistemadevotacaoemtemporeal.Infrastructure.Entity.Pool.PoolStatus;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PoolResponseDTO {
    private String userName;
    private Integer poolID;
    private LocalDate creationDate;
    private LocalDate closeDate;
    private String poolTitle;
    private String poolQuestion;
    private PoolStatus poolStatus;
    private List<PoolOptionResponseDTO> poolOptions;
}
