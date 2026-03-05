package br.com.floricultura.controle;

import br.com.floricultura.entidade.Produto;
import br.com.floricultura.entidade.Usuario;
import br.com.floricultura.excecao.LoginInvalidoException;
import br.com.floricultura.excecao.SenhaInvalidaException;
import br.com.floricultura.repositorio.RepositorioProduto;
import br.com.floricultura.repositorio.RepositorioProdutoBDR;
import br.com.floricultura.repositorio.RepositorioProdutoMemoria;
import br.com.floricultura.repositorio.RepositorioUsuario;
import br.com.floricultura.repositorio.RepositorioUsuarioBDR;
import br.com.floricultura.repositorio.RepositorioUsuarioMemoria;

import java.io.IOException;
import java.util.List;

public class FacadeSingletonController {

    private static FacadeSingletonController instancia;

    private GerenciamentoDeUsuario gerUsuario;
    private GerenciamentoDeProduto gerProduto;

    private FacadeSingletonController() {
    }

    public static FacadeSingletonController getInstance() {
        if (instancia == null) {
            instancia = new FacadeSingletonController();
        }
        return instancia;
    }

    public void inicializarRepositorios(boolean usarMemoria) {
        RepositorioUsuario repositorioUsuario;
        RepositorioProduto repositorioProduto;

        if (usarMemoria) {
            repositorioUsuario = new RepositorioUsuarioMemoria();
            repositorioProduto = new RepositorioProdutoMemoria();
        } else {
            repositorioUsuario = new RepositorioUsuarioBDR();
            repositorioProduto = new RepositorioProdutoBDR();
        }

        gerUsuario = new GerenciamentoDeUsuario(repositorioUsuario);
        gerProduto = new GerenciamentoDeProduto(repositorioProduto);
    }

    public void cadastrarUsuario(String login, String senha)
            throws LoginInvalidoException, SenhaInvalidaException, IOException {

        verificarInicializacao();
        gerUsuario.cadastrar(login, senha);
    }

    public void cadastrarProduto(int id, String nome, double preco, int quantidadeEstoque) throws IOException {
        verificarInicializacao();
        gerProduto.cadastrarProduto(id, nome, preco, quantidadeEstoque);
    }

    public void atualizarProduto(int id, String nome, double preco, int quantidadeEstoque) throws IOException {
        verificarInicializacao();
        gerProduto.atualizarProduto(id, nome, preco, quantidadeEstoque);
    }

    public void removerProduto(int id) throws IOException {
        verificarInicializacao();
        gerProduto.removerProduto(id);
    }

    public List<Usuario> listarUsuario() throws IOException {
        verificarInicializacao();
        return gerUsuario.listarTodos();
    }

    public List<Produto> listarProdutos() throws IOException {
        verificarInicializacao();
        return gerProduto.listarProdutos();
    }

    public int getQuantidadeEntidades() throws IOException {
        verificarInicializacao();
        return listarUsuario().size() + listarProdutos().size();
    }

    private void verificarInicializacao() {
        if (gerUsuario == null || gerProduto == null) {
            throw new IllegalStateException("Os repositórios ainda não foram inicializados.");
        }
    }
}