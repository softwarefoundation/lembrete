package com.lembrete.exceptions;

import javax.persistence.NoResultException;
import java.text.MessageFormat;

public class RegistroNaoEncotradoException extends NoResultException {

    public RegistroNaoEncotradoException(Long id) {
        super(MessageFormat.format("Registro não encontrado: {0}",id));
    }
}
