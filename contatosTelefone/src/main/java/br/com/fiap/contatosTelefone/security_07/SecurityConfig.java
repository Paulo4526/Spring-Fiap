package br.com.fiap.contatosTelefone.security_07;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private VerificarToken verificarToken;

    //Aqui criamos o metodo para definição da nossa segurança para STATELESS(Sem estado) 4 filtro de segurança.
    @Bean
    public SecurityFilterChain filtarSeguranca(HttpSecurity httpSecurity) throws Exception{
        //Esse metodo com o csrf desabilitado, desabilita logins falsos vindo de outros sites quando
        //há a integração entre os sites.
        return httpSecurity.csrf(csrf -> csrf.disable())
                //Aqui criamos as permissões mediante as autenticações, caso o usuario não esteja
                //autenticado será bloqueado
                .sessionManagement(
                        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/contatos/listarTodos").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.PUT, "/api/contatos").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/contatos").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/contatos").hasRole("ADMIN")
                        .anyRequest()
                        .authenticated())
                .addFilterBefore(
                    verificarToken, UsernamePasswordAuthenticationFilter.class
                )
                .build();
    }

    @Bean
    //Aqui usamos a validação do nosso login passados pela classe AuthController e fará a autenticação da nossa requisilção HTTP
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }


    //PasswordEncoder já cria a criptografia da nossa aplicação criptografando a senha que o usuario passar.
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
