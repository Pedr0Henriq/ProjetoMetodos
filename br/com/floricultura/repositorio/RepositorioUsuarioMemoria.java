package br.com.floricultura.repositorio;

import br.com.floricultura.entidade.Usuario;
import java.util.ArrayList;
import java.util.List;

public class RepositorioUsuarioMemoria implements RepositorioUsuario {

    private final List<Usuario> usuarios;

    public RepositorioUsuarioMemoria() {
        this.usuarios = new ArrayList<>();
    }

    @Override
    public void salvar(Usuario usuario) {
        usuarios.add(usuario);
    }

    @Override
    public List<Usuario> buscarTodos() {
        return new ArrayList<>(usuarios);
    }
}