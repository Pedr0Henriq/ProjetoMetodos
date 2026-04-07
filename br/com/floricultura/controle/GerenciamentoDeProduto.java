package br.com.floricultura.controle;

import br.com.floricultura.entidade.Produto;
import br.com.floricultura.log.Logger;
import br.com.floricultura.log.LoggerAdapter;
import br.com.floricultura.repositorio.RepositorioProduto;

import java.io.IOException;
import java.util.List;

public class GerenciamentoDeProduto {

    private final RepositorioProduto repositorio;
    private final Logger logger;

    public GerenciamentoDeProduto(RepositorioProduto repositorio) {
        this.repositorio = repositorio;
        this.logger = new LoggerAdapter(); // usa o Adapter — não conhece a biblioteca externa
    }

    /** Expõe o repositório subjacente para permitir registros de Observer/integração. */
    public RepositorioProduto getRepositorio() {
        return repositorio;
    }

    public void cadastrarProduto(int id, String nome, double preco, int quantidadeEstoque) throws IOException {
        Produto produto = new Produto(id, nome, preco, quantidadeEstoque);
        repositorio.salvar(produto);
        logger.info("Produto cadastrado: " + produto);
    }

    public void atualizarProduto(int id, String nome, double preco, int quantidadeEstoque) throws IOException {
        Produto produto = new Produto(id, nome, preco, quantidadeEstoque);
        repositorio.atualizar(produto);
        logger.info("Produto atualizado: " + produto);
    }

    public void removerProduto(int id) throws IOException {
        repositorio.remover(id);
        logger.aviso("Produto removido. ID: " + id);
    }

    public List<Produto> listarProdutos() throws IOException {
        List<Produto> produtos = repositorio.buscarTodos();
        logger.info("Listagem de produtos solicitada. Total: " + produtos.size());
        return produtos;
    }
}