package com.lembrete.dto.lembrete;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lembrete.entity.Lembrete;
import com.lembrete.util.conveter.DtoToEntityConverter;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class LembreteDto implements DtoToEntityConverter<Lembrete> {

    @NotNull(message = "O título do lembrete é obrigatório")
    @Length(min = 10, max = 30, message = "O título deve ter no mínimo {min} e no máximo {max} caracteres")
    private String titulo;

    @NotNull(message = "O texto do lembrete é obrigatório")
    @Length(min = 10, max = 50, message = "O texto deve ter no mínimo {min} e no máximo {max} caracteres")
    private String texto;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm", locale = "pt-BR", timezone = "America/Araguaina")
    @Future(message = "A data do evento deve ser posterior a data atual")
    private LocalDateTime dataEvento;

    @Override
    public Lembrete toEntity() {
        return new ModelMapper().map(this, Lembrete.class);
    }
}
