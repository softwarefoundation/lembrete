package com.lembrete.entity;

import com.lembrete.dto.LembreteDto;
import com.lembrete.util.conveter.EntityToDtoConverter;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@DynamicUpdate
@Entity
@Table(name = "tb01_lembrete")
public class Lembrete implements EntityToDtoConverter<LembreteDto> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "texto")
    private String texto;

    @Column(name = "data_evento")
    private LocalDateTime dataEvento;

    @Column(name = "data_cadastro", updatable = false)
    private LocalDateTime dataCadastro;

    @PrePersist
    public void prePersist(){
        setDataCadastro(LocalDateTime.now());
    }

    @Override
    public LembreteDto toDto() {
        return new ModelMapper().map(this, LembreteDto.class);
    }
}
