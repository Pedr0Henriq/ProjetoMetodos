package br.com.floricultura.repositorio;

import br.com.floricultura.entidade.Usuario;
import java.io.IOException;
import java.util.List;

public interface RepositorioUsuario {
    void salvar(Usuario usuario) throws IOException;
    List<Usuario> buscarTodos() throws IOException;
}