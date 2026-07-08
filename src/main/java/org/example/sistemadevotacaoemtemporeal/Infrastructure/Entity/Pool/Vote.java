package org.example.sistemadevotacaoemtemporeal.Infrastructure.Entity.Pool;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.sistemadevotacaoemtemporeal.Infrastructure.Entity.User.UserEntity;

import java.time.LocalDate;


@Entity
@Table(name = "vote")
@Getter
@Setter
@NoArgsConstructor
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long voteId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid", nullable = false, referencedColumnName = "userid")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pool_id", nullable = false)
    private PoolEntity pool;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "option_id", nullable = false)
    private PoolOption selectedOption;

    @Column
    private LocalDate createdAt;

    public Vote(UserEntity user, PoolEntity pool, PoolOption selectedOption) {
        this.user = user;
        this.pool = pool;
        this.selectedOption = selectedOption;
        this.createdAt = LocalDate.now();
    }
}