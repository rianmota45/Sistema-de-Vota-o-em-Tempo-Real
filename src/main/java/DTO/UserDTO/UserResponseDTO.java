package DTO.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

/**
 * {@code UserResponseDTO} É só para retornar os dados da classe {@code User}<br>
 * Ele vai pegar com {@code get} os dados necessários da classe {@code User} para<br>
 * se formar e depois ser retornado.
 */
@Getter
@Setter
@AllArgsConstructor
@ToString
public class UserResponseDTO {
    private String userDTOName;
    private String userDTOEmail;
    private UUID userDTOID;
}
