package br.com.floricultura.repositorio;

import br.com.floricultura.entidade.Produto;

import java.io.IOException;
import java.util.List;

public interface ProdutoRepositorio {

    void salvar(Produto produto) throws IOException;

    List<Produto> buscarTodos() throws IOException;
}
