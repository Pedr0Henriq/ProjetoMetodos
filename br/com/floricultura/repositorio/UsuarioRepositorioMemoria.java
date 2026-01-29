package br.com.floricultura.repositorio;

import br.com.floricultura.entidade.Usuario;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepositorioMemoria implements UsuarioRepositorio {

    private List<Usuario> usuarios;

    public UsuarioRepositorioMemoria() {
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