package com.lembrete.service.impl;

import com.lembrete.dto.lembrete.LembretePersistDto;
import com.lembrete.entity.Lembrete;
import com.lembrete.exceptions.RegistroNaoEncotradoException;
import com.lembrete.respository.LembreteRepository;
import com.lembrete.service.LembreteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class LembreteServiceImpl implements LembreteService {

    @Autowired
    private LembreteRepository lembreteRepository;


    @Override
    public Lembrete salvar(Lembrete lembrete) {
        lembrete.setDataCadastro(LocalDateTime.now());
        Lembrete lembreteRetorno = lembreteRepository.save(lembrete);
        return lembreteRetorno;
    }

    @Override
    public Optional<Lembrete> pesquisarPorId(Long id) {
       Optional<Lembrete> lembrete = lembreteRepository.findById(id);
       return lembrete;
    }

    @Override
    public Lembrete atualizar(LembretePersistDto lembreteDto) {
        Lembrete lembrete = lembreteRepository.save(lembreteDto.toEntity());
        return lembrete;
    }

    @Override
    public Lembrete atualizarTitulo(LembretePersistDto lembreteDto) {
       Optional<Lembrete> lembreteOptional = lembreteRepository.findById(lembreteDto.getId());
       if(lembreteOptional.isPresent()){
           Lembrete lembrete = lembreteOptional.get();
           lembrete.setTitulo(lembreteDto.getTitulo());
          return lembreteRepository.save(lembrete);
       }
       throw new RegistroNaoEncotradoException(lembreteDto.getId());
    }

    @Override
    public List<Lembrete> listarLembreteTodos() {
        List<Lembrete> lembretes = lembreteRepository.findAll();
        return lembretes;
    }

    @Override
    public void deletar(Long id) {
        if(!lembreteRepository.existsById(id)){
            throw new RegistroNaoEncotradoException(id);
        }
        lembreteRepository.deleteById(id);
    }
}
