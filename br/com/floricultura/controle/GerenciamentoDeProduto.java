package br.com.floricultura.controle;

import br.com.floricultura.entidade.Produto;
import br.com.floricultura.log.Logger;
import br.com.floricultura.log.LoggerAdapter;
import br.com.floricultura.observer.ObservadorEstoque;
import br.com.floricultura.repositorio.RepositorioProduto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Subject (Observable) do padrão Observer.
 * Gerencia operações sobre produtos e notifica os observadores registrados
 * sempre que ocorre um evento relevante (cadastro, atualização, remoção).
 */
public class GerenciamentoDeProduto {

    private final RepositorioProduto repositorio;
    private final Logger logger;

    // ── Padrão Observer (Subject) ─────────────────────────────────────────────
    private final List<ObservadorEstoque> observadores;

    public GerenciamentoDeProduto(RepositorioProduto repositorio) {
        this.repositorio = repositorio;
        this.logger = new LoggerAdapter();
        this.observadores = new ArrayList<>();
    }

    public void adicionarObservador(ObservadorEstoque observador) {
        observadores.add(observador);
    }

    public void removerObservador(ObservadorEstoque observador) {
        observadores.remove(observador);
    }

    private void notificarObservadores(String evento, Produto produto) {
        for (ObservadorEstoque obs : observadores) {
            obs.atualizar(evento, produto);
        }
    }
    // ──────────────────────────────────────────────────────────────────────────

    public void cadastrarProduto(int id, String nome, double preco, int quantidadeEstoque) throws IOException {
        Produto produto = new Produto(id, nome, preco, quantidadeEstoque);
        repositorio.salvar(produto);
        logger.info("Produto cadastrado: " + produto);
        notificarObservadores("CADASTRO", produto);
    }

    public void atualizarProduto(int id, String nome, double preco, int quantidadeEstoque) throws IOException {
        Produto produto = new Produto(id, nome, preco, quantidadeEstoque);
        repositorio.atualizar(produto);
        logger.info("Produto atualizado: " + produto);
        notificarObservadores("ATUALIZACAO", produto);
    }

    public void removerProduto(int id) throws IOException {
        Produto produto = new Produto(id, "removido", 0, 0);
        repositorio.remover(id);
        logger.aviso("Produto removido. ID: " + id);
        notificarObservadores("REMOCAO", produto);
    }

    public List<Produto> listarProdutos() throws IOException {
        List<Produto> produtos = repositorio.buscarTodos();
        logger.info("Listagem de produtos solicitada. Total: " + produtos.size());
        return produtos;
    }
}