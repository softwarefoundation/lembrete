package com.lembrete.exceptions;

import javax.persistence.NoResultException;
import java.text.MessageFormat;

public class RegistroNaoEncotradoException extends NoResultException {

    public RegistroNaoEncotradoException(Long id) {
        super(MessageFormat.format("Registro com código: {0} não foi localizado",id));
    }

    public RegistroNaoEncotradoException(String mensagem) {
        super(mensagem);
    }

    public RegistroNaoEncotradoException() {
        super("Nenhum registro localizado");
    }
}
