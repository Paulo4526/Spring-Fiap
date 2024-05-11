package br.com.fiap.contatosTelefone.service_04;

import br.com.fiap.contatosTelefone.dto_02.UsuarioCadastroDTO;
import br.com.fiap.contatosTelefone.dto_02.UsuarioExibicaoDTO;
import br.com.fiap.contatosTelefone.model_01.Usuario;
import br.com.fiap.contatosTelefone.repository_03.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    public UsuarioRepository usuarioRepository;


    public UsuarioExibicaoDTO gravarUsuario(UsuarioCadastroDTO usuarioCadastroDTO){
        String criptografia = new BCryptPasswordEncoder().encode(usuarioCadastroDTO.senha());

        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(usuarioCadastroDTO, usuario);
        usuario.setSenha(criptografia);

        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        return new UsuarioExibicaoDTO(usuarioSalvo);
    }
}
