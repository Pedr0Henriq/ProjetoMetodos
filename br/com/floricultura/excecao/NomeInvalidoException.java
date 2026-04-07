package br.com.floricultura.excecao;

public class NomeInvalidoException extends Exception {
    public NomeInvalidoException(String mensagem) {
        super(mensagem);
    }
}
