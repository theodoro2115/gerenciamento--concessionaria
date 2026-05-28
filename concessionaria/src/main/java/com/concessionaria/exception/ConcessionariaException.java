package com.manus.concessionaria.exception;

public class ConcessionariaException extends RuntimeException {
    public ConcessionariaException(String message) {
        super(message);
    }
}

class AnoInvalidoException extends ConcessionariaException {
    public AnoInvalidoException() {
        super("Ano de fabricação não pode ser superior a 2027.");
    }
}

class CPFInvalidoException extends ConcessionariaException {
    public CPFInvalidoException() {
        super("CPF deve conter 11 dígitos.");
    }
}
