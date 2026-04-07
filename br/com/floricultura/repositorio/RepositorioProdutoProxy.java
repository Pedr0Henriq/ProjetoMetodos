package br.com.floricultura.repositorio;

import br.com.floricultura.controle.GerenciamentoDeAcesso;
import br.com.floricultura.entidade.Produto;

import java.io.IOException;
import java.util.List;

/**
 * Proxy de proteção do padrão Proxy.
 * Controla o acesso ao repositório real de produtos, verificando
 * se existe um usuário autenticado na sessão antes de permitir
 * qualquer operação de persistência.
 */
public class RepositorioProdutoProxy implements RepositorioProduto {

    private final RepositorioProduto repositorioReal;

    public RepositorioProdutoProxy(RepositorioProduto repositorioReal) {
        this.repositorioReal = repositorioReal;
    }

    private void verificarAcesso() {
        GerenciamentoDeAcesso acesso = GerenciamentoDeAcesso.getInstance();
        if (acesso.getRegistros().isEmpty()) {
            throw new SecurityException("Acesso negado: nenhum usuário autenticado na sessão.");
        }
    }

    @Override
    public void salvar(Produto produto) throws IOException {
        verificarAcesso();
        repositorioReal.salvar(produto);
    }

    @Override
    public void atualizar(Produto produto) throws IOException {
        verificarAcesso();
        repositorioReal.atualizar(produto);
    }

    @Override
    public void remover(int id) throws IOException {
        verificarAcesso();
        repositorioReal.remover(id);
    }

    @Override
    public List<Produto> buscarTodos() throws IOException {
        verificarAcesso();
        return repositorioReal.buscarTodos();
    }
}
