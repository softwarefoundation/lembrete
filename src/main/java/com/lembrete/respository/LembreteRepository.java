package com.lembrete.respository;

import com.lembrete.entity.Lembrete;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LembreteRepository extends JpaRepository<Lembrete,Long> {
}
