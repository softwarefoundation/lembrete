package com.agenda.respository;

import com.agenda.entity.Anotacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AnotacaoRepository extends JpaRepository<Anotacao,Long> {

    List<Anotacao> findByDataEventoBetween(LocalDateTime dataInicio, LocalDateTime dataFim);

}
