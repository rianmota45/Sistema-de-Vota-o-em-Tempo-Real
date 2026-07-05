package org.example.sistemadevotacaoemtemporeal.Infrastructure.Entity;

import java.time.LocalDate;
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

public class VoteEntity {
    private final Integer poolID;
    private final Integer optionID;
    private final UUID userID;
    private final LocalDate transactionDate;

    public VoteEntity(Integer poolID, Integer optionID, UUID userID) {
        this.poolID = poolID;
        this.optionID = optionID;
        this.userID = userID;
        this.transactionDate = LocalDate.now();
    }


    @Override
    public String toString() {
        return "VoteEntity{" +
                "poolID=" + poolID +
                ", optionID=" + optionID +
                ", userID=" + userID +
                ", transactionDate=" + transactionDate +
                '}';
    }
}
