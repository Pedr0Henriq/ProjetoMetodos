package br.com.floricultura.repositorio;

import br.com.floricultura.controle.GerenciamentoDeAcesso;
import br.com.floricultura.entidade.Produto;

import java.io.IOException;
import java.util.List;

/**
 * Proxy para controlar acesso às operações de escrita no repositório de produtos.
 * Neste exemplo simples, permite operações apenas se houver pelo menos um registro
 * de acesso (usuário 'logado') registrado em GerenciamentoDeAcesso.
 */
public class RepositorioProdutoProxy implements RepositorioProduto {

    private final RepositorioProduto real;

    public RepositorioProdutoProxy(RepositorioProduto real) {
        this.real = real;
    }

    private void verificarAutenticacao() {
        if (GerenciamentoDeAcesso.getInstance().getRegistros().isEmpty()) {
            throw new SecurityException("Operação negada: usuário não autenticado.");
        }
    }

    @Override
    public void salvar(Produto produto) throws IOException {
        verificarAutenticacao();
        real.salvar(produto);
    }

    @Override
    public void atualizar(Produto produto) throws IOException {
        verificarAutenticacao();
        real.atualizar(produto);
    }

    @Override
    public void remover(int id) throws IOException {
        verificarAutenticacao();
        real.remover(id);
    }

    @Override
    public List<Produto> buscarTodos() throws IOException {
        return real.buscarTodos();
    }
}
