package com.agenda.service.impl;

import com.agenda.dto.AnotacaoDto;
import com.agenda.entity.Anotacao;
import com.agenda.exceptions.RegistroNaoEncotradoException;
import com.agenda.respository.AnotacaoRepository;
import com.agenda.service.AnotacaoService;
import com.agenda.util.validador.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AnotacaoServiceImpl implements AnotacaoService {

    @Autowired
    private AnotacaoRepository anotacaoRepository;


    @Override
    public Anotacao salvar(Anotacao anotacao) {
        Anotacao anotacaoRetorno = anotacaoRepository.save(anotacao);
        return anotacaoRetorno;
    }

    @Override
    public Anotacao pesquisar(Long id) {
        return anotacaoRepository.findById(id).orElseThrow(() -> new RegistroNaoEncotradoException(id));
    }

    @Override
    public List<Anotacao> pesquisarPorDataDoEvento(LocalDateTime dataInicio, LocalDateTime dataFim) {
        List<Anotacao> anotacoes = anotacaoRepository.findByDataEventoBetween(dataInicio,dataFim);
        String msg = MessageFormat.format("Nenhum registro localizado para as datas informadas, inicio: {0} e  fim: {1}", dataInicio, dataFim);
        Assert.isNotEmpty(anotacoes, () -> new RegistroNaoEncotradoException(msg));
        return anotacoes;
    }

    @Override
    public Anotacao atualizar(AnotacaoDto anotacaoDto) {
        return anotacaoRepository.save(anotacaoDto.toEntity());
    }

    @Override
    public Anotacao atualizarDataEvento(Long id, Anotacao anotacao) {
        Anotacao anotacaoRetorno = pesquisar(id);
        anotacaoRetorno.setDataEvento(anotacao.getDataEvento());
        return anotacaoRepository.save(anotacaoRetorno);
    }

    @Override
    public List<Anotacao> listarTodos() {
        List<Anotacao> anotacoes = anotacaoRepository.findAll();
        return anotacoes;
    }

    @Override
    public void deletar(Long id) {
        Anotacao anotacao = pesquisar(id);
        anotacaoRepository.delete(anotacao);
    }
}
