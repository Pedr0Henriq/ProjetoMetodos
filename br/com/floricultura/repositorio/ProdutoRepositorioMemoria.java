package br.com.floricultura.repositorio;

import br.com.floricultura.entidade.Produto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoRepositorioMemoria implements ProdutoRepositorio{

    private final List<Produto> produtos;

    public ProdutoRepositorioMemoria() {
        this.produtos = new ArrayList<>();
    }

    @Override
    public void salvar(Produto produto) throws IOException {
        produtos.add(produto);
    }

    @Override
    public List<Produto> buscarTodos() throws IOException {
        return new ArrayList<>(produtos);
    }
}
