package org.example.sistemadevotacaoemtemporeal.Infrastructure.Entity.Pool;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Objeto PoolOption representa uma opção de votação em uma enquete (pool). Cada opção possui um identificador único, um texto descritivo e um contador de votos.
 * <p>
 * Atributos:
 * - {@code Integer optionID}: Identificador único da opção de votação.
 * - {@code String optionText}: Texto descritivo da opção de votação.
 * - {@code Integer numberOfVotes}: Contador de votos recebidos pela opção.
 * <p>
 * Exemplo de uso:
 * <blockquote><pre>
 * PoolOption option = new PoolOption("Opção A");
 * option.setNumberOfVotes(10);
 * System.out.println(option.getOptionText()); // Saída: Opção A
 * System.out.println(option.getNumberOfVotes()); // Saída: 10
 * </pre></blockquote>
 */

@Getter
@Setter
@Entity
@Table(name = "option")
@NoArgsConstructor
public class PoolOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer optionID;
    @Column(nullable = false)
    private String optionText;
    @Column(name = "number_of_votes", nullable = false)
    private Integer numberOfVotes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pool_id", nullable = false)
    private PoolEntity poolEntity;

    public PoolOption(String optionText) {
        this.optionText = optionText;
        this.numberOfVotes = 0;
    }

    public void voteOnOption(){
        numberOfVotes++;
    }
}
