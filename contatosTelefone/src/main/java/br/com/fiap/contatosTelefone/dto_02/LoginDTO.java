package br.com.fiap.contatosTelefone.dto_02;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginDTO(
        @NotBlank(message = "Email obrigatório!")
        @Email(message = "Email deve ter o formado 'aaaaa@hotmail.com'")
        String email,

        @NotBlank(message = "Senha obrigatória!")
        @Size(min = 6, max = 20, message = "A senha deve conter no mínimo 6 caracteres e máximo 20 caracteres")
        String senha
) {
}
