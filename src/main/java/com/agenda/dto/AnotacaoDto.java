package com.agenda.dto;

import com.agenda.entity.Anotacao;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.agenda.util.conveter.DtoToEntityConverter;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class AnotacaoDto implements DtoToEntityConverter<Anotacao> {

    @NotNull
    @Length(min = 10, max = 30)
    private String titulo;

    @NotNull
    @Length(min = 10, max = 50)
    private String texto;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    @Future
    private LocalDateTime dataEvento;

    @Override
    public Anotacao toEntity() {
        return new ModelMapper().map(this, Anotacao.class);
    }
}
