package br.com.fiap.contatosTelefone.dto_02;

import br.com.fiap.contatosTelefone.model_01.UserRole;
import br.com.fiap.contatosTelefone.model_01.Usuario;

public record UsuarioExibicaoDTO(
        Long usuario,
        String nome,
        String email,
        UserRole role
) {


    public UsuarioExibicaoDTO(Usuario usuarioSalvo) {
        this(usuarioSalvo.getUsuario(), usuarioSalvo.getNome(), usuarioSalvo.getEmail(), usuarioSalvo.getRole());
    }
}
