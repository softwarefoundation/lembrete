package com.agenda.entity;

import com.agenda.dto.AnotacaoDto;
import com.agenda.util.conveter.EntityToDtoConverter;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@DynamicUpdate
@Entity
@Table(name = "tb01_anotacao")
public class Anotacao implements EntityToDtoConverter<AnotacaoDto> {

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
    public AnotacaoDto toDto() {
        return new ModelMapper().map(this, AnotacaoDto.class);
    }
}
