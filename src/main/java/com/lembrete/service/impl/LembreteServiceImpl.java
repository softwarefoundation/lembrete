package com.lembrete.service.impl;

import com.lembrete.dto.LembreteDto;
import com.lembrete.entity.Lembrete;
import com.lembrete.exceptions.RegistroNaoEncotradoException;
import com.lembrete.respository.LembreteRepository;
import com.lembrete.service.LembreteService;
import com.lembrete.util.validador.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LembreteServiceImpl implements LembreteService {

    @Autowired
    private LembreteRepository lembreteRepository;


    @Override
    public Lembrete salvar(Lembrete lembrete) {
        Lembrete lembreteRetorno = lembreteRepository.save(lembrete);
        return lembreteRetorno;
    }

    @Override
    public Lembrete pesquisar(Long id) {
        return lembreteRepository.findById(id).orElseThrow(() -> new RegistroNaoEncotradoException(id));
    }

    @Override
    public List<Lembrete> pesquisarLembretePorDataDoEvento(LocalDateTime dataInicio, LocalDateTime dataFim) {
        List<Lembrete> lembretes = lembreteRepository.findByDataEventoBetween(dataInicio,dataFim);
        String msg = MessageFormat.format("Nenhum registro localizado para as datas informadas, inicio: {0} e  fim: {1}", dataInicio, dataFim);
        Assert.isNotEmpty(lembretes, () -> new RegistroNaoEncotradoException(msg));
        return lembretes;
    }

    @Override
    public Lembrete atualizar(LembreteDto lembreteDto) {
        return lembreteRepository.save(lembreteDto.toEntity());
    }

    @Override
    public Lembrete atualizarDataEvento(Long id, Lembrete lembrete) {
        Lembrete lembreteRetorno = pesquisar(id);
        lembreteRetorno.setDataEvento(lembrete.getDataEvento());
        return lembreteRepository.save(lembreteRetorno);
    }

    @Override
    public List<Lembrete> listarLembreteTodos() {
        List<Lembrete> lembretes = lembreteRepository.findAll();
        return lembretes;
    }

    @Override
    public void deletar(Long id) {
        Lembrete lembrete = pesquisar(id);
        lembreteRepository.delete(lembrete);
    }
}
