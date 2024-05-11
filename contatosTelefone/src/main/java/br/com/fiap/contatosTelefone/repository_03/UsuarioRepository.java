package br.com.fiap.contatosTelefone.repository_03;

import br.com.fiap.contatosTelefone.model_01.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    UserDetails findByEmail(String email);

}
