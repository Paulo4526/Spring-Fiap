package br.com.fiap.contatosTelefone.repository_03;

import br.com.fiap.contatosTelefone.model_01.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


//na interface devemos extender a nossa interface no Spring JPA passando Contato como tipo, e Long como id
public interface ContatoRepository extends JpaRepository<Contato, Long> {

    public Optional<Contato> findByNome(String nome);

    public List<Contato> findByDtNascimentoBetween(LocalDate dataInicial, LocalDate dataFinal);

    @Query("SELECT c FROM Contato c WHERE c.email = :email")
    public Optional<Contato> buscarPeloEmail(@Param("email") String email);




}


