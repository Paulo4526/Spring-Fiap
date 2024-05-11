package br.com.fiap.contatosTelefone.dto_02;

import br.com.fiap.contatosTelefone.model_01.Contato;

import java.time.LocalDate;


// Aqui nossa classe de registro DTO cria registro para recebimento de dados antes de enviar ao usuario,
// ou seja quando uma api estiver sendo consumida, nossa senha não aparecerá nem para o programador.
//Dentro dos parametros da classe DTO colocamos quais valores queremos receber.
public record ContatoExibicaoDTO(
   Long id,
   String nome,
   String email,
   LocalDate dtNascimento

) {
    //Aqui criamos um metodo com a classe DTO com o parametro da classe onde queremos buscar os valores,
    // assim mostrará somente os valores desejados, e não mostrará o indesejado.
    public ContatoExibicaoDTO(Contato contato){
        this(contato.getId(), contato.getNome(), contato.getEmail(), contato.getDtNascimento());
    }
}
