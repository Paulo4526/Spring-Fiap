package br.com.fiap.contatosTelefone.dto_02;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record ContatoCadastroDTO(
        //NOTE QUE NÃO USAMOS NENHUMA ANOTAÇÃO PARA VALIDAÇÃO DO ID, POIS O MESMO JÁ POSSUI VALIDAÇÃO PROPRIA NA CLASSE E BANCO DE DADOS.
        Long id,

        //A ANOTAÇÃO @NOTBLANK É PARA INFORMAR QUE O CAMPO NÃO PODE ESTAR EM BRANCOOU NULO.
        @NotBlank(message = "Nome do contato é obrigatório!")
        String nome,

        @NotBlank(message = "O e-mail é obrigatório!")
        //A ANOTAÇÃO @EMAIL INFORMA QUE O FORMATO DA ESCRITA TEM QUE HAVER AS COMPOSIÇÕES DE UM E-MAIL.
        @Email(message = "O e-mail está no formato inválido!")
        String email,

        @NotBlank(message = "A senha é obrigatória!")
        //A ANOTAÇÃO @SIZE INFORMA O MÁXIMO E MÍNIMO DE CARACTERES A SER RECEBIDOS PELA API.
        @Size(min = 6, max = 20, message = "A senha deve conter entre 6 e 15 caracteres!")
        String senha,

        // AQUI USAMOS O @NOTNULL PARA DATAS POR QUE NÃO É POSSÍVEL USAR A ANOTAÇÃO @NOTBLANK PARA E-MAILS.
        @NotNull(message = "A data é orbigatório!")
        LocalDate dtNascimento

) {
}
