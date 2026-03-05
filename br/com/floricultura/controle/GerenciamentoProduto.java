package br.com.floricultura.controle;

import br.com.floricultura.entidade.Produto;
import br.com.floricultura.repositorio.ProdutoRepositorio;
import br.com.floricultura.excecao.NomeInvalidoException;
import br.com.floricultura.excecao.PrecoInvalidoException;
import br.com.floricultura.excecao.QuantidadeEstoqueInvalidaException;

import java.io.IOException;
import java.util.List;

public class GerenciamentoProduto {

    private final ProdutoRepositorio repositorio;

    public GerenciamentoProduto(ProdutoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void cadastrarProduto(int id, String nome, double preco, int quantidadeEstoque)
            throws NomeInvalidoException, PrecoInvalidoException, QuantidadeEstoqueInvalidaException, IOException {

        Produto novo = new Produto(id, nome, preco, quantidadeEstoque);
        repositorio.salvar(novo);
    }

    public List<Produto> listarProdutos() throws IOException {
        return repositorio.buscarTodos();
    }
}