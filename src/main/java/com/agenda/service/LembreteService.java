package com.agenda.service;

import com.agenda.dto.LembreteDto;
import com.agenda.entity.Lembrete;

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
