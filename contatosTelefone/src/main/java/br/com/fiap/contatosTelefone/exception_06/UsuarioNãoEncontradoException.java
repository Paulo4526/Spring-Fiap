package br.com.fiap.contatosTelefone.exception_06;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UsuarioNãoEncontradoException extends RuntimeException {

    public UsuarioNãoEncontradoException(String mensagem){
        super(mensagem);
    }

}
