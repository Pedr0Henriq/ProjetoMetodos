package br.com.floricultura.log;

/**
 * Adapter: converte a interface da {@link BibliotecaLogExterna} (Adaptee)
 * para a interface {@link Logger} (Target) que o sistema utiliza.
 *
 * Desta forma, podemos trocar a biblioteca de log a qualquer momento
 * criando um novo Adapter, sem alterar nenhuma linha do código de negócio.
 */
public class LoggerAdapter implements Logger {

    private static final String TAG = "Floricultura";

    private final BibliotecaLogExterna bibliotecaExterna;

    public LoggerAdapter() {
        this.bibliotecaExterna = new BibliotecaLogExterna();
    }

    @Override
    public void info(String mensagem) {
        // Traduz: info() → writeInfo()
        bibliotecaExterna.writeInfo(TAG, mensagem);
    }

    @Override
    public void aviso(String mensagem) {
        // Traduz: aviso() → writeWarn()
        bibliotecaExterna.writeWarn(TAG, mensagem);
    }

    @Override
    public void erro(String mensagem) {
        // Traduz: erro() → writeError()
        bibliotecaExterna.writeError(TAG, mensagem);
    }
}
