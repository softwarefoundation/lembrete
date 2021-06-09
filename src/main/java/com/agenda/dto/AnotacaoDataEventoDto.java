package com.agenda.dto;

import com.agenda.entity.Anotacao;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.agenda.util.conveter.DtoToEntityConverter;
import lombok.Data;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.Future;
import java.time.LocalDateTime;

@Data
public class AnotacaoDataEventoDto implements DtoToEntityConverter<Anotacao> {

    @Future
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataEvento;

    @Override
    public Anotacao toEntity() {
        return new ModelMapper().map(this, Anotacao.class);
    }
}
