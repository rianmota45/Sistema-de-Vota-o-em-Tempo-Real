package Service;

import Infrastructure.Entity.Pool.PoolEntity;
import Infrastructure.Entity.Pool.PoolOption;
import Infrastructure.Entity.User.UserEntity;
import Infrastructure.Entity.VoteEntity;
import Repository.PoolRepository;
import Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor

// por enquanto tá tudo no mesmo service, mas eu vou mudar isso depois

public class VoteService {

    private final VoteEntity vote;
    private final UserEntity userEntity;
    private final PoolEntity poolEntity;
    private final UserRepository userRepository;
    public final PoolRepository poolRepository;

    public UserEntity createuser() {
        UserEntity user = new UserEntity("nomezinho", "emailzinho@gmail.com");
        userRepository.save(user);
        return user;
    }

    public PoolEntity createPool() {
        PoolEntity pool = new PoolEntity(
                "Minha Enquete",
                "Qual peixe é melhor de comer com farinha??",
                List.of(
                        new PoolOption("Bagre"),
                        new PoolOption("Tilapia"),
                        new PoolOption("Alambari")
                )
        );
        poolRepository.save(pool);
        return pool;
    }

    public List<PoolEntity> getAllPools() {
        return poolRepository.findAll();
    }

    public VoteEntity voteIN(Integer poolID, Integer optionID, UUID userID) {
        Optional<PoolEntity> pool = poolRepository.findById(poolID);
        if (pool.isEmpty()) {
            throw new RuntimeException("Pool not found");
        }
        Optional<UserEntity> user = userRepository.findById(userID);
        if (user.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        PoolEntity poolEntity1 = pool.get();
        UserEntity userEntity1 = user.get();
        PoolOption poolOption = poolEntity1.getPoolOptions().stream()
                .filter(option
                        -> option.getOptionID().equals(optionID))
                .findFirst()
                .orElseThrow(()
                        -> new RuntimeException("Option not found"));

        return new VoteEntity(poolID, optionID, userID);
    }
}