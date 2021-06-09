package com.agenda.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.agenda.entity.Lembrete;
import com.agenda.util.conveter.DtoToEntityConverter;
import lombok.Data;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.Future;
import java.time.LocalDateTime;

@Data
public class LembreteTituloDto implements DtoToEntityConverter<Lembrete> {

    @Future
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataEvento;

    @Override
    public Lembrete toEntity() {
        return new ModelMapper().map(this, Lembrete.class);
    }
}
