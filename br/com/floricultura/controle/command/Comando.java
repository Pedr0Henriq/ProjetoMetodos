package br.com.floricultura.controle.command;

// Interface base do Padrão Command
public interface Comando {
    // Usamos 'throws Exception' para englobar IOException, LoginInvalidoException, etc.
    void executar() throws Exception;
}