package Infrastructure.Entity.Pool;

import Infrastructure.Entity.VoteEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "pool")
public class PoolEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer poolID;
    private LocalDate creationDate;
    private LocalDate closeDate;
    private String poolTitle;
    private String poolQuestion;
    private PoolStatus poolStatus;
    private List<PoolOption> poolOptions;

    public PoolEntity(String poolTitle, String poolQuestion, List<PoolOption> poolOptionList) {
        this.poolID ++;
        this.poolTitle = poolTitle;
        this.poolQuestion = poolQuestion;
        this.poolStatus = PoolStatus.OPEN;
        this.poolOptions = poolOptionList;
    }

    public void closePool(){
        this.poolStatus = PoolStatus.CLOSED;
    }
    public void cancellPool(){
        this.poolStatus = PoolStatus.CANCELLED;
    }

    public VoteEntity vote(Integer poolID, Integer optionID, UUID userID){
        VoteEntity vote = new VoteEntity(poolID, optionID, userID);
        return vote;
    }
}
