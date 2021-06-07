package com.lembrete.resources;

import com.lembrete.dto.lembrete.LembretePersistDto;
import com.lembrete.entity.Lembrete;
import com.lembrete.exceptions.RegistroNaoEncotradoException;
import com.lembrete.service.LembreteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/lembrete")
public class LembreteResource {

    private LembreteService lembreteService;

    @Autowired
    public LembreteResource(LembreteService lembreteService) {
        this.lembreteService = lembreteService;
    }

    @PostMapping
    public ResponseEntity<?> salvarLembrete(@RequestBody LembretePersistDto lembreteDto){
        Lembrete lembrete = lembreteService.salvar(lembreteDto.toEntity());
        return ResponseEntity.status(HttpStatus.CREATED).body(lembrete);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> pesquisarLembretePorId(@PathVariable Long id) {
        Optional<Lembrete> lembreteOptional = lembreteService.pesquisarPorId(id);
        if(lembreteOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(lembreteOptional.get());
        }
        throw new RegistroNaoEncotradoException(id);
    }

    @PutMapping
    public ResponseEntity<?> atualizarLembrete(@RequestBody LembretePersistDto lembreteDto){
        Lembrete lembrete = lembreteService.atualizar(lembreteDto);
        return ResponseEntity.status(HttpStatus.OK).body(lembrete);
    }

    @PatchMapping
    public ResponseEntity<?> atualizarTituloLembrete(@RequestBody LembretePersistDto lembreteDto){
        Lembrete lembrete = lembreteService.atualizar(lembreteDto);
        return ResponseEntity.status(HttpStatus.OK).body(lembrete);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deletarLembrete(@PathVariable(name = "id") Long id) {
        lembreteService.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
