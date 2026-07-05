package org.example.sistemadevotacaoemtemporeal.Infrastructure.Entity.Pool;

import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "pool")
@Getter
@Setter
@NoArgsConstructor
public class PoolEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer poolID;
    @Column(nullable = false)
    private LocalDate creationDate;
    @Column
    private LocalDate closeDate;
    @Column(nullable = false)
    private String poolTitle;
    @Column(nullable = false)
    private String poolQuestion;
    @Column(nullable = false)
    private PoolStatus poolStatus;
    @OneToMany(mappedBy = "poolEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PoolOption> poolOptions;

    public PoolEntity(LocalDate closeDate, String poolTitle, String poolQuestion, List<PoolOption> poolOptionList) {
        this.creationDate = LocalDate.now();
        this.closeDate = closeDate;
        this.poolTitle = poolTitle;
        this.poolQuestion = poolQuestion;
        this.poolStatus = PoolStatus.OPEN;
        this.poolOptions = poolOptionList;
    }

    public void closePool() {
        this.poolStatus = PoolStatus.CLOSED;
    }

    public void cancellPool() {
        this.poolStatus = PoolStatus.CANCELLED;
    }

    public String getPoolOptionById(Integer optionID) {
        for (PoolOption poolOption : poolOptions) {
            if (poolOption.getOptionID().equals(optionID)) {
                return poolOptions.get(optionID).toString();
            }
        }
        throw new RuntimeException("Option not found");
    }
}
