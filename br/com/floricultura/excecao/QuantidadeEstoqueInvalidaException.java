package br.com.floricultura.excecao;

public class QuantidadeEstoqueInvalidaException extends Exception {
    public QuantidadeEstoqueInvalidaException(String message) {
        super(message);
    }
}
