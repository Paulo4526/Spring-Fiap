package br.com.fiap.contatosTelefone.security_07;

import br.com.fiap.contatosTelefone.model_01.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    //Aqui geramos nosso atributo palavaSecreta para gerarmos o nosso token
    //Toda vez que nosso metodo for chamado ele irá receber o email so usuario e irá converter para
    //algoritimo.
    @Value("${minha.chave.secreta}")
    private String palavaSecreta;

    public String gerarToken(Usuario usuario){
        try{
            //A variavel algo recebe o valor do token gerado pelo atributo palavraSecreta e cria um algoritimo.
            Algorithm algo = Algorithm.HMAC256(palavaSecreta);
            String token = JWT
                    //Comando create para iniciar a geração do token
                    .create()
                    //Comando withIssuer para gerar o token com o assunto contatos.
                    .withIssuer("contatos")
                    //Comando withSubject para utilizar o email da entidade usuario.
                    .withSubject(usuario.getEmail())
                    //comando withExpiresAt para gerar um tempo de expiração que está definido pelo metodo geraDataexpiracao
                    .withExpiresAt(gerarDataExpiracao())
                    //Comando sign para pegar o valor já codificado pelo algo e irá atribuir a ele valores como
                    //Email, Assunto e expiração.
                    .sign(algo);
            return token;
            //Aqui usamos a exeption JWTCreating para pegar algum erro de criação do token
        }catch(JWTCreationException erro){
            throw  new RuntimeException("Erro de criação de token");
        }
    }

    public String validarToken(String token){
        try{
            Algorithm algo = Algorithm.HMAC256(palavaSecreta);
            return JWT
                    //Comando require para validação do algoritimo contigo na palavraSecreta
                    .require(algo)
                    //Comando withIssuer para validar o assunto condificado no algoritimo.
                    .withIssuer("contatos")
                    //Comando build para criação da validação.
                    .build()
                    .verify(token)
                    .getSubject();
        }catch (JWTVerificationException erro){
            throw new RuntimeException("Não foi possível validar o token");
        }
    }


    //Aqui usamos o metodo gerarData para definir um horario de expiração sendo:
    //LocalDateTime.now() --> para referenciar o dia e a hora de agora.
    //plusHours() --> para adicionar horas as horas atuais dando um tempo de validade.
    //.toInstant() --> aqui é colocado o instante em que começa a contar a validação.
    public Instant gerarDataExpiracao (){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
