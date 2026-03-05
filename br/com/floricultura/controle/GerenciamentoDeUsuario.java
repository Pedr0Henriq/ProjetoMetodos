package br.com.floricultura.controle;

import br.com.floricultura.entidade.Usuario;
import br.com.floricultura.excecao.LoginInvalidoException;
import br.com.floricultura.excecao.SenhaInvalidaException;
import br.com.floricultura.repositorio.RepositorioUsuario;

import java.io.IOException;
import java.util.List;

public class GerenciamentoDeUsuario {

    private final RepositorioUsuario repositorio;

    public GerenciamentoDeUsuario(RepositorioUsuario repositorio) {
        this.repositorio = repositorio;
    }

    public void cadastrar(String login, String senha)
            throws LoginInvalidoException, SenhaInvalidaException, IOException {

        Usuario usuario = new Usuario(login, senha);
        repositorio.salvar(usuario);
    }

    public List<Usuario> listarTodos() throws IOException {
        return repositorio.buscarTodos();
    }
}