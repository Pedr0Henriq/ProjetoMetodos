package br.com.floricultura.observer;

import br.com.floricultura.entidade.Produto;
import br.com.floricultura.log.Logger;
import br.com.floricultura.log.LoggerAdapter;

/**
 * Observer concreto que registra no log toda alteração de estoque.
 */
public class LogEstoqueObserver implements ObservadorEstoque {

    private final Logger logger;

    public LogEstoqueObserver() {
        this.logger = new LoggerAdapter();
    }

    @Override
    public void atualizar(String evento, Produto produto) {
        logger.info("[Observer] Evento: " + evento + " | Produto: " + produto);
    }
}
