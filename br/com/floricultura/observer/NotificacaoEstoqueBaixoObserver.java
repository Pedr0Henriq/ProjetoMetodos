package br.com.floricultura.observer;

import br.com.floricultura.entidade.Produto;

/**
 * Observer concreto que emite um alerta quando o estoque de um produto
 * fica abaixo de um limite mínimo.
 */
public class NotificacaoEstoqueBaixoObserver implements ObservadorEstoque {

    private static final int LIMITE_ESTOQUE_BAIXO = 5;

    @Override
    public void atualizar(String evento, Produto produto) {
        if (produto.getQuantidadeEstoque() <= LIMITE_ESTOQUE_BAIXO) {
            System.out.println("[ALERTA] Estoque baixo para o produto '"
                    + produto.getNome() + "' (quantidade: "
                    + produto.getQuantidadeEstoque() + ")");
        }
    }
}
