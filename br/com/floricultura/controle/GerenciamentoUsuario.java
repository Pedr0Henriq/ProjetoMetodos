package br.com.floricultura.controle;

import br.com.floricultura.entidade.Usuario;
import br.com.floricultura.excecao.LoginInvalidoException;
import br.com.floricultura.excecao.SenhaInvalidaException;
import br.com.floricultura.repositorio.UsuarioRepositorio;

import java.io.IOException;
import java.util.List;

public class GerenciamentoUsuario {

    private UsuarioRepositorio repositorio;

    public GerenciamentoUsuario(UsuarioRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void adicionarUsuario(String login, String senha)
            throws LoginInvalidoException, SenhaInvalidaException, IOException {

        Usuario novo = new Usuario(login, senha);

        repositorio.salvar(novo);
    }

    public List<Usuario> listarUsuarios() throws IOException {
        return repositorio.buscarTodos();
    }
}
