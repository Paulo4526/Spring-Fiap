package br.com.fiap.contatosTelefone.controller_05;

import br.com.fiap.contatosTelefone.dto_02.LoginDTO;
import br.com.fiap.contatosTelefone.dto_02.TokenDTO;
import br.com.fiap.contatosTelefone.dto_02.UsuarioCadastroDTO;
import br.com.fiap.contatosTelefone.dto_02.UsuarioExibicaoDTO;
import br.com.fiap.contatosTelefone.model_01.Usuario;
import br.com.fiap.contatosTelefone.security_07.TokenService;
import br.com.fiap.contatosTelefone.service_04.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    //Aqui estamos criando a validação de login e senha passando como parametros email e senha.
    public ResponseEntity login(@RequestBody @Valid LoginDTO loginDTO){
        UsernamePasswordAuthenticationToken userPassword = new UsernamePasswordAuthenticationToken(
                loginDTO.email(),
                loginDTO.senha()
        );
        // Aqui criamos uma variavel do spring auth para receber os valores passados pela autenticação.
        // Ou seja o email e senha passados pelo metodo userPassword na entidade login será passada como parametro.
        Authentication auth = authenticationManager.authenticate(userPassword);

        String token = tokenService.gerarToken((Usuario) auth.getPrincipal());

        //Caso a nossa validação de login e senha esteja feita com sucesso, o metodo irá retornar um OK informando uma requisição
        //200 como resposta.
        return ResponseEntity.ok(new TokenDTO(token));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioExibicaoDTO registrarNosoUsuario(@RequestBody @Valid UsuarioCadastroDTO usuarioCadastroDTO){
        UsuarioExibicaoDTO usuarioSalvo = null;

        usuarioSalvo = usuarioService.gravarUsuario(usuarioCadastroDTO);
        return usuarioSalvo;
    }

}
