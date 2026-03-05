package br.com.floricultura.controle;

import br.com.floricultura.entidade.Produto;
import br.com.floricultura.repositorio.RepositorioProduto;

import java.io.IOException;
import java.util.List;

public class GerenciamentoDeProduto {

    private final RepositorioProduto repositorio;

    public GerenciamentoDeProduto(RepositorioProduto repositorio) {
        this.repositorio = repositorio;
    }

    public void cadastrarProduto(int id, String nome, double preco, int quantidadeEstoque) throws IOException {
        Produto produto = new Produto(id, nome, preco, quantidadeEstoque);
        repositorio.salvar(produto);
    }

    public void atualizarProduto(int id, String nome, double preco, int quantidadeEstoque) throws IOException {
        Produto produto = new Produto(id, nome, preco, quantidadeEstoque);
        repositorio.atualizar(produto);
    }

    public void removerProduto(int id) throws IOException {
        repositorio.remover(id);
    }

    public List<Produto> listarProdutos() throws IOException {
        return repositorio.buscarTodos();
    }
}