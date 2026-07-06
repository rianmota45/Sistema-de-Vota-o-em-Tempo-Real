package org.example.sistemadevotacaoemtemporeal.Infrastructure.Entity.Pool;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class VoteResponse {
    String nameOfVoter;
    String poolTitle;
    String optionText;
    LocalDate dateOfTransaction;

    public VoteResponse(String nameOfVoter, String poolTitle, String optionText) {
        this.nameOfVoter = nameOfVoter;
        this.poolTitle = poolTitle;
        this.optionText = optionText;
        this.dateOfTransaction = LocalDate.now();
    }
}
