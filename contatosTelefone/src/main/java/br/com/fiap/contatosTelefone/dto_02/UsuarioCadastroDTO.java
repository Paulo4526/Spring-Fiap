package br.com.fiap.contatosTelefone.dto_02;

import br.com.fiap.contatosTelefone.model_01.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioCadastroDTO(
        Long usuario,
        @NotBlank(message = "O nome de usuário é obrigatorio!")
        String nome,
        @NotBlank(message = "O email de usuário é obrigatorio!")
        @Email(message = "O email deve ser no formado aaaaaa@aaa.com...")
        String email,
        @NotBlank(message = "A senha é obrigatoria!")
        @Size(min = 6, max = 20, message = "A senha deve conter entre 6 e 20 caracteres!")
        String senha,


        UserRole role
){

}
