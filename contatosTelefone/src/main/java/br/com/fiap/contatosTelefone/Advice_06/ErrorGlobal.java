package br.com.fiap.contatosTelefone.Advice_06;


import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ErrorGlobal{

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> manusearArgumentosInvalidos(MethodArgumentNotValidException erro){
        Map<String, String> errorMap = new HashMap<>();
        List<FieldError> campos = erro.getBindingResult().getFieldErrors();
        for(FieldError campo : campos){
            errorMap.put(campo.getField(), campo.getDefaultMessage());
        }
        return errorMap;
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public Map<String, String> erroEmailExistente (){
        Map<String, String> mapaErro = new HashMap<>();
        mapaErro.put("erro", "Usuário já existe!");
        return mapaErro;
    }
}
