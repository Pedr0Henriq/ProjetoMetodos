package br.com.floricultura.controle.command;

// Interface base do Padrão Command
public interface Comando {
    // Usamos 'throws Exception' para englobar IOException, LoginInvalidoException, etc.
    void executar() throws Exception;
    
    // Método default: se o comando não tiver como desfazer, ele avisa.
    default void desfazer() throws Exception {
        throw new UnsupportedOperationException("Este comando não suporta a ação de desfazer.");
    }
}