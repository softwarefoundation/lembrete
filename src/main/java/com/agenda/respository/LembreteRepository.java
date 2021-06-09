package com.agenda.respository;

import com.agenda.entity.Lembrete;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface LembreteRepository extends JpaRepository<Lembrete,Long> {

    List<Lembrete> findByDataEventoBetween(LocalDateTime dataInicio, LocalDateTime dataFim);

}
