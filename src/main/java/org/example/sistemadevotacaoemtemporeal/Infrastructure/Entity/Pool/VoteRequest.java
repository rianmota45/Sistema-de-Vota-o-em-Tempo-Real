package org.example.sistemadevotacaoemtemporeal.Infrastructure.Entity.Pool;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * Propósito do {@code VoteEntity}:<br>
 * Representa o voto de um usuário numa determinada opção de uma enquete (pool).<br>
 * Contém informações sobre a enquete, a opção escolhida, o usuário que votou e a data da transação.<br>
 * <p>
 * Atributos:<br>
 * - {@code Integer poolID}: Identificador da enquete (pool) em que o voto foi registrado.<br>
 * - {@code Integer optionID}: Identificador da opção escolhida pelo usuário na enquete.<br>
 * - {@code UUID userID}: Identificador único do usuário que realizou o voto.<br>
 * - {@code LocalDate transactionDate}: Data em que o voto foi registrado.<br>
 * <p>
 */

@Getter
@Setter
@AllArgsConstructor
public class VoteRequest {
    UUID userid;
    Integer poolid;
    Integer optionid;
}
