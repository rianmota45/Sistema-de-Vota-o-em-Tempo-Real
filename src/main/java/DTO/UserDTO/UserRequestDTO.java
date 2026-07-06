package DTO.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * {@code UserRequestDTO}: É só para transferir os dados para a classe {@code User}.<br>
 * O controller recebe um json com os parametros e transforma nesse dto, e depois passa para o Service
 * criando uma classe {@code User} apartir desse {@code UserRequestDTO} e então realizando a operação.
 */

@Getter
@Setter
@AllArgsConstructor
public class UserRequestDTO {
    private String userDTOName;
    private String userDTOEmail;
}
