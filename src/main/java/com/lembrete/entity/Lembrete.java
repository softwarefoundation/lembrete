package com.lembrete.entity;

import com.lembrete.dto.lembrete.LembretePersistDto;
import com.lembrete.util.conveter.EntityToDtoConverter;
import lombok.Data;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tb01_lembrete")
public class Lembrete implements EntityToDtoConverter<LembretePersistDto> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "texto")
    private String texto;

    @Column(name = "data_cadastro")
    private LocalDateTime dataCadastro;

    @Override
    public LembretePersistDto toDto() {
        return new ModelMapper().map(this, LembretePersistDto.class);
    }
}
