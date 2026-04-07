package br.com.floricultura.repositorio;

import br.com.floricultura.entidade.Produto;
import java.io.IOException;
import java.util.List;

public interface RepositorioProduto {
    void salvar(Produto produto) throws IOException;
    void atualizar(Produto produto) throws IOException;
    void remover(int id) throws IOException;
    List<Produto> buscarTodos() throws IOException;
}