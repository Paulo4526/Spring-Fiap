package br.com.fiap.contatosTelefone.controller_05;


import br.com.fiap.contatosTelefone.dto_02.ContatoCadastroDTO;
import br.com.fiap.contatosTelefone.dto_02.ContatoExibicaoDTO;
import br.com.fiap.contatosTelefone.model_01.Contato;
import br.com.fiap.contatosTelefone.service_04.ContatoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ContatoController {

    //Aqui usamos o nosso atributo e inicializamos ele com a anotação Autowired
    //que irá passar os valores necessários para o nosso ContatoService.
    @Autowired
    private ContatoService service;


    //Aqui criamos o metodo gravar, além d anotação PostMapping, devemos dentro do metodo
    //informar para o spring que o contato será enviado para o banco de dados usando a
    // anotação @RequestBody, que irá converter nosso arquivo em Json e irá atribuir a cada
    //atributo do banco de dados seu respectivo valor.
    @PostMapping("/contatos")
    @ResponseStatus(HttpStatus.CREATED)
    //AQUI USAMOS A ANOTAÇÃO @VALID PARA APLICAR AS VALIDAÇÕES SOLICITADAS NO NOSSO ContatoCadastroDTO.
    public ContatoExibicaoDTO gravar(@RequestBody @Valid ContatoCadastroDTO contatoCadastroDTO){
        return service.gravar(contatoCadastroDTO);
    }


    //Aqui confirmamos no controler o uso da Page com o tipo Pageable.
    @GetMapping("/contatos/listarTodos")
    @ResponseStatus(HttpStatus.OK)
    public Page<ContatoExibicaoDTO> listarTodosOsContatos(Pageable paginacao){
        return service.listarTodos(paginacao);
    }


    //Aqui na deleção criamos o metodo de exclusão, porém, devemos passar um id para exclusão.
    //PAra isso dentro do nosso endereço Path usamos o "{id}", e para saber qual id deve ser
    //utilizado dentro do metodo usamos o @PathVariable que irá informar que aquele Long id
    // representa o "{id}" e ira enviar os parametros para o path
    @DeleteMapping("/contatos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id){
        service.excluir(id);
    }

    @PutMapping("/contatos")
    @ResponseStatus(HttpStatus.OK)
    public Contato atualizar (@RequestBody Contato contato){
        return service.atualizar(contato);
    }


    //Abaixo criamos controladores com parametros
    @GetMapping(value = "/contatos", params = "nome")
    @ResponseStatus(HttpStatus.OK)
    public ContatoExibicaoDTO buscarPeloNome(@RequestParam String nome){
        return service.buscarPeloNome(nome);
    }

    @GetMapping(value = "/contatos", params = "email")
    @ResponseStatus(HttpStatus.OK)
    public ContatoExibicaoDTO buscarPeloEmail(@RequestParam String email){
        return service.buscarPeloEmail(email);
    }

    @GetMapping(value = "/contatos",params = "id")
    @ResponseStatus(HttpStatus.OK)
    public ContatoExibicaoDTO buscarPeloId(@RequestParam Long id){
        return service.buscarId(id);
    }

    @GetMapping(value = "/contatos", params = {"dtInicial", "dtFinal"})
    @ResponseStatus(HttpStatus.OK)
        public List<ContatoExibicaoDTO> mostrarAniversario(@RequestParam LocalDate dtInicial, @RequestParam LocalDate dtFinal){
        return service.mostrarAniversariantes(dtInicial, dtFinal);
    }
}
