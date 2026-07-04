package dto.user_dto;

import lombok.Getter;

/**
 * {@code UserResponseDTO} É só para retornar os dados da classe {@code User}<br>
 * Ele vai pegar com {@code get} os dados necessários da classe {@code User} para<br>
 * se formar e depois ser retornado.
 */
@Getter
public class UserResponseDTO {
    private String userDTOName;
    private String userDTOEmail;
}
