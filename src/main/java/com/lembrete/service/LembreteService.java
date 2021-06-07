package com.lembrete.service;

import com.lembrete.dto.lembrete.LembretePersistDto;
import com.lembrete.entity.Lembrete;

import java.util.List;
import java.util.Optional;

public interface LembreteService {

    Lembrete salvar(Lembrete lembrete);

    Optional<Lembrete> pesquisarPorId(Long id);

    Lembrete atualizar(LembretePersistDto lembreteDto);

    Lembrete atualizarTitulo(LembretePersistDto lembreteDto);

    List<Lembrete> listarLembreteTodos();

    void deletar(Long id);

}
