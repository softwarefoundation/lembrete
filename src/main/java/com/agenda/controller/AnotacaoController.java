package com.agenda.controller;

import com.agenda.dto.AnotacaoDto;
import com.agenda.dto.AnotacaoDataEventoDto;
import com.agenda.entity.Anotacao;
import com.agenda.service.AnotacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(path = "/anotacao")
public class AnotacaoController {

    private AnotacaoService anotacaoService;

    @Autowired
    public AnotacaoController(AnotacaoService anotacaoService) {
        this.anotacaoService = anotacaoService;
    }

    @PostMapping
    public ResponseEntity<?> salvar(@Valid @RequestBody AnotacaoDto anotacaoDto) {
        Anotacao anotacao = anotacaoService.salvar(anotacaoDto.toEntity());
        return ResponseEntity.status(HttpStatus.CREATED).body(anotacao);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> pesquisarPorId(@PathVariable Long id) {
        Anotacao anotacao = anotacaoService.pesquisar(id);
        return ResponseEntity.status(HttpStatus.OK).body(anotacao);
    }

    @GetMapping(value = "/data-evento")
    public ResponseEntity<?> pesquisarPorDataDoEvento(
            @RequestParam("dataInicio") @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm") LocalDateTime dataInicio,
            @RequestParam("dataFim") @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm") LocalDateTime dataFim){
            List<Anotacao> anotacoes = anotacaoService.pesquisarPorDataDoEvento(dataInicio,dataFim);
            return ResponseEntity.ok(anotacoes);
    }

    @PutMapping
    public ResponseEntity<?> atualizar(@RequestBody AnotacaoDto anotacaoDto) {
        Anotacao anotacao = anotacaoService.atualizar(anotacaoDto);
        return ResponseEntity.status(HttpStatus.OK).body(anotacao);
    }

    @PatchMapping("/{id}/data-evento")
    public ResponseEntity<?> atualizarDataEvento(@PathVariable Long id, @RequestBody AnotacaoDataEventoDto anotacaoDto) {
        Anotacao anotacao = anotacaoService.atualizarDataEvento(id, anotacaoDto.toEntity());
        return ResponseEntity.status(HttpStatus.OK).body(anotacao);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deletar(@PathVariable(name = "id") Long id) {
        anotacaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }


}
