package br.com.fiap.contatosTelefone.service_04;

import br.com.fiap.contatosTelefone.dto_02.ContatoCadastroDTO;
import br.com.fiap.contatosTelefone.dto_02.ContatoExibicaoDTO;
import br.com.fiap.contatosTelefone.exception_06.UsuarioNãoEncontradoException;
import br.com.fiap.contatosTelefone.model_01.Contato;
import br.com.fiap.contatosTelefone.repository_03.ContatoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


//Anotação service indica que estamos iniciando uma aplicação service.
@Service
public class ContatoService {


    @Autowired
    private ContatoRepository contatoRepository;

    public ContatoExibicaoDTO gravar(ContatoCadastroDTO contatoCadastroDTO){
            Contato contato = new Contato();
            BeanUtils.copyProperties(contatoCadastroDTO, contato);
            return new ContatoExibicaoDTO(contatoRepository.save(contato));
    }

    //Aqui criamos um metodo buscar pelo id.
    public ContatoExibicaoDTO buscarId(Long id){

        Optional<Contato> contatoOptional = contatoRepository.findById(id);

        if(contatoOptional.isPresent()){
            return new ContatoExibicaoDTO(contatoOptional.get());
        }else {
            throw new UsuarioNãoEncontradoException("Contato não encontrado");
        }
    }

    //Aqui criamos um metodo para buscar todos.
    //Aqui usamos o Page ao invés de List para adicionar paginação na busca e dentro
    //do Atributo Colcoamos o tipo Pegeable com um nome de variavel.
    public Page<ContatoExibicaoDTO> listarTodos(Pageable paginacao){
        return contatoRepository.findAll(paginacao) .map(ContatoExibicaoDTO::new);
    }


    //Aqui criamos a função de esclusão pelo id
    public void excluir (Long id){
        Optional<Contato> contatoOptional = contatoRepository.findById(id);

        if(contatoOptional.isPresent()){
            contatoRepository.delete(contatoOptional.get());
        }else{
            throw new UsuarioNãoEncontradoException("Contato não encontrado!");
        }
    }


    //Aqui criamos um metodo para busca de aniversário entre períodos
    public List<ContatoExibicaoDTO> mostrarAniversariantes(LocalDate dataInicial, LocalDate dataFinal) throws UsuarioNãoEncontradoException {
        List<Contato> contatos = contatoRepository.findByDtNascimentoBetween(dataInicial, dataFinal);

        if (contatos.isEmpty()) {
            throw new UsuarioNãoEncontradoException("Nenhum contato encontrado para os aniversariantes neste período.");
        }else{
            return contatos.stream()
                    .map(ContatoExibicaoDTO::new)
                    .toList();
        }

    }


    //Aqui criamos um metodo para atualizar um registro.
    public Contato atualizar (Contato contato){
        Optional<Contato> contatoOptional = contatoRepository.findById(contato.getId());

        if (contatoOptional.isPresent()){
            return contatoRepository.save(contato);
        }else{
            throw new UsuarioNãoEncontradoException("Contato não existe");
        }

    }

    public ContatoExibicaoDTO buscarPeloNome(String nome){
        Optional<Contato> contatoOptional = contatoRepository.findByNome(nome);
        if (contatoOptional.isPresent()){
            return new  ContatoExibicaoDTO(contatoOptional.get());
        }else{
            throw new UsuarioNãoEncontradoException("Contato não existe");
        }
    }

    public ContatoExibicaoDTO buscarPeloEmail(String email){
        Optional<Contato> contatoOptional = contatoRepository.buscarPeloEmail(email);
        if (contatoOptional.isPresent()){
            return new  ContatoExibicaoDTO(contatoOptional.get());
        }else{
            throw new UsuarioNãoEncontradoException("Email não existe");
        }
    }




}
