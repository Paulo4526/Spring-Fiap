package br.com.fiap.contatosTelefone.controller_05;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


// Esta classe é absoleta foi criada somente para teste e não está sendo usada para molde
// da nossa API a clase sendo usada é "ContatoController".
@RestController
@RequestMapping("/api")
public class HelloControler {

    @GetMapping("/hello")
    public String hello(){
        return "Hello World";
    }

    @GetMapping("/ola")
    public String ola(){
        return "Olá Mundo";
    }

}
