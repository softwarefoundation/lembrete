package com.agenda.service;

import com.agenda.dto.AnotacaoDto;
import com.agenda.entity.Anotacao;

import java.time.LocalDateTime;
import java.util.List;

public interface AnotacaoService {

    Anotacao salvar(Anotacao anotacao);

    Anotacao pesquisar(Long id);

    List<Anotacao> pesquisarPorDataDoEvento(LocalDateTime dataInicio, LocalDateTime dataFim);

    Anotacao atualizar(AnotacaoDto anotacaoDto);

    Anotacao atualizarDataEvento(Long id, Anotacao anotacao);

    List<Anotacao> listarTodos();

    void deletar(Long id);

}
