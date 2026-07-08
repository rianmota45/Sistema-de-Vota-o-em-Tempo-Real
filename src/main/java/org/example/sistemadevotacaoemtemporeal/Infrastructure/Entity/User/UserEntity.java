package org.example.sistemadevotacaoemtemporeal.Infrastructure.Entity.User;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
 * );
 * </pre></blockquote><p>
 */
@Getter
@Setter
@Entity
@Table(name = "user_entity")
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "userid")
    private UUID userid;
    @Column(nullable = false)
    private String userName;
    @Column(unique = true)
    private String userEmail;

    public UserEntity(String userName, String userEmail) {
        this.userName = userName;
        if (userName == null || userName.isEmpty()) {
            this.userName = ("User" + (Math.round(Math.random() * 1000)));
        }
        this.userEmail = userEmail;
    }

    @Override
    public String toString() {
        return "User{" +
                "\n\tuserName='" + userName + '\'' +
                ",\n\tuserEmail='" + userEmail + '\'' +
                ",\n\tuserID=" + userid +
                "\n}";
    }
}
