package Infrastructure.Entity.User;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * @Note Classe {@code UserEntity} Atributos:
 * @Params {@code String} userName
 * @Params {@code String} userEmail
 * @Params {@code UUID} userID (Gerado Automaticamente)
 * @Params {@code String} userPassword <- Removido por hora, tenho que pensar mais em como implementár.
 * <blockquote><pre>
 * User user = new User (
 *      "Jhon Roblox",
 *      "jhon@roblox.com",
 *  );
 * </pre></blockquote><p>
 */
@Getter
@Setter
@Entity
public class UserEntity {
    @Column(nullable = false)
    private String userName;
    @Column(unique = true)
    private String userEmail;
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userID;

    public UserEntity(String userName, String userEmail) {
        this.userName = userName;
        if (userName == null || userName.isEmpty()) {
            this.userName = ("User" + (Math.round(Math.random() * 1000)));
        }
        this.userEmail = userEmail;
        this.userID = UUID.randomUUID();
    }

    @Override
    public String toString() {
        return "User{" +
                "\n\tuserName='" + userName + '\'' +
                ",\n\tuserEmail='" + userEmail + '\'' +
                ",\n\tuserID=" + userID +
                "\n}";
    }

    public static void main(String[] args) {
        UserEntity usuario = new UserEntity("", "email@gamil");

        System.out.println(usuario);
        System.out.println(usuario.getUserID());
    }
}
