package com.lembrete.service;

import com.lembrete.dto.LembreteDto;
import com.lembrete.entity.Lembrete;

import java.time.LocalDateTime;
import java.util.List;

public interface LembreteService {

    Lembrete salvar(Lembrete lembrete);

    Lembrete pesquisar(Long id);

    List<Lembrete> pesquisarLembretePorDataDoEvento(LocalDateTime dataInicio, LocalDateTime dataFim);

    Lembrete atualizar(LembreteDto lembreteDto);

    Lembrete atualizarDataEvento(Long id, Lembrete lembrete);

    List<Lembrete> listarLembreteTodos();

    void deletar(Long id);

}
