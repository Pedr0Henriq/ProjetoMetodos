package br.com.floricultura.log;

/**
 * Interface de log que a camada de negócio conhece.
 * É o "Target" do padrão Adapter: o sistema depende apenas desta abstração,
 * sem acoplamento a nenhuma biblioteca externa.
 */
public interface Logger {
    void info(String mensagem);
    void aviso(String mensagem);
    void erro(String mensagem);
}
