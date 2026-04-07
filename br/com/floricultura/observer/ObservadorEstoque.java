package br.com.floricultura.observer;

import br.com.floricultura.entidade.Produto;

/**
 * Interface Observer do padrão Observer.
 * Define o contrato para objetos que desejam ser notificados
 * sobre eventos relacionados ao estoque de produtos.
 */
public interface ObservadorEstoque {
    void atualizar(String evento, Produto produto);
}
