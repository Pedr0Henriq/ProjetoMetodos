package br.com.floricultura.repositorio;

import br.com.floricultura.entidade.Usuario;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepositorio {

    // coleção em memória RAM
    private List<Usuario> usuarios;

    public UsuarioRepositorio() {
        this.usuarios = new ArrayList<>();
    }

    public void salvar(Usuario usuario) {
        usuarios.add(usuario);
    }

    public List<Usuario> buscarTodos() {
        return usuarios;
    }
}
