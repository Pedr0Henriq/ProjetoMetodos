package br.com.floricultura.repositorio;

import br.com.floricultura.entidade.Produto;
import java.util.ArrayList;
import java.util.List;

public class RepositorioProdutoMemoria implements RepositorioProduto {

    private final List<Produto> produtos;

    public RepositorioProdutoMemoria() {
        this.produtos = new ArrayList<>();
    }

    @Override
    public void salvar(Produto produto) {
        produtos.add(produto);
    }

    @Override
    public void atualizar(Produto produto) {
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getId() == produto.getId()) {
                produtos.set(i, produto);
                return;
            }
        }
        throw new IllegalArgumentException("Produto com id " + produto.getId() + " não encontrado.");
    }

    @Override
    public void remover(int id) {
        boolean removido = produtos.removeIf(produto -> produto.getId() == id);
        if (!removido) {
            throw new IllegalArgumentException("Produto com id " + id + " não encontrado.");
        }
    }

    @Override
    public List<Produto> buscarTodos() {
        return new ArrayList<>(produtos);
    }
}