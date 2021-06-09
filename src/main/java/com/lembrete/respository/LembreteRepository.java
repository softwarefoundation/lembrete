package com.lembrete.respository;

import com.lembrete.entity.Lembrete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface LembreteRepository extends JpaRepository<Lembrete,Long> {

    List<Lembrete> findByDataEventoGreaterThanEqualAndDataEventoLessThanEqual(LocalDateTime dataInicio, LocalDateTime dataFim);

}
