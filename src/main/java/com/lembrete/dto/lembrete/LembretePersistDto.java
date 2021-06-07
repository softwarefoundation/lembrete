package com.lembrete.dto.lembrete;

import com.lembrete.entity.Lembrete;
import com.lembrete.util.conveter.DtoToEntityConverter;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

@Data
public class LembretePersistDto implements DtoToEntityConverter<Lembrete> {

    private Long id;
    private String titulo;
    private String texto;

    @Override
    public Lembrete toEntity() {
        return new ModelMapper().map(this, Lembrete.class);
    }
}
