package com.lembrete.controller;

import com.lembrete.dto.lembrete.LembreteDto;
import com.lembrete.entity.Lembrete;
import com.lembrete.service.LembreteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/lembrete")
public class LembreteController {

    private LembreteService lembreteService;

    @Autowired
    public LembreteController(LembreteService lembreteService) {
        this.lembreteService = lembreteService;
    }

    @PostMapping
    public ResponseEntity<?> salvarLembrete(@Valid @RequestBody LembreteDto lembreteDto) {
        Lembrete lembrete = lembreteService.salvar(lembreteDto.toEntity());
        return ResponseEntity.status(HttpStatus.CREATED).body(lembrete);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> pesquisarLembretePorId(@PathVariable Long id) {
        Lembrete lembrete = lembreteService.pesquisar(id);
        return ResponseEntity.status(HttpStatus.OK).body(lembrete);
    }

    @GetMapping(value = "/data-evento")
    public ResponseEntity<?> pesquisarLembretePorDataDoEvento(
            @RequestParam("dataInicio") @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm") LocalDateTime dataInicio,
            @RequestParam("dataFim") @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm") LocalDateTime dataFim){
            List<Lembrete> lembretes = lembreteService.pesquisarLembretePorDataDoEvento(dataInicio,dataFim);
            return ResponseEntity.ok(lembretes);
    }

    @PutMapping
    public ResponseEntity<?> atualizarLembrete(@RequestBody LembreteDto lembreteDto) {
        Lembrete lembrete = lembreteService.atualizar(lembreteDto);
        return ResponseEntity.status(HttpStatus.OK).body(lembrete);
    }

    @PatchMapping("/{id}/titulo")
    public ResponseEntity<?> atualizarTituloLembrete(@PathVariable Long id, @RequestBody LembreteDto lembreteDto) {
        Lembrete lembrete = lembreteService.atualizarTitulo(id, lembreteDto.toEntity());
        return ResponseEntity.status(HttpStatus.OK).body(lembrete);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deletarLembrete(@PathVariable(name = "id") Long id) {
        lembreteService.deletar(id);
        return ResponseEntity.noContent().build();
    }


}
