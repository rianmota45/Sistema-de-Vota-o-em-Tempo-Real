package Infrastructure.Entity.Pool;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class PoolOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer optionID;
    private String optionText;
    private Integer numberOfVotes;

    public PoolOption(String optionText) {
        this.optionText = optionText;
    }
}
