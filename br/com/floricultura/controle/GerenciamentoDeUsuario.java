package br.com.floricultura.controle;

import br.com.floricultura.entidade.Usuario;
import br.com.floricultura.excecao.LoginInvalidoException;
import br.com.floricultura.excecao.SenhaInvalidaException;
import br.com.floricultura.log.Logger;
import br.com.floricultura.log.LoggerAdapter;
import br.com.floricultura.repositorio.RepositorioUsuario;

import java.io.IOException;
import java.util.List;

public class GerenciamentoDeUsuario {

    private final RepositorioUsuario repositorio;
    private final Logger logger;

    public GerenciamentoDeUsuario(RepositorioUsuario repositorio) {
        this.repositorio = repositorio;
        this.logger = new LoggerAdapter(); // usa o Adapter — não conhece a biblioteca externa
    }

    public void cadastrar(String login, String senha)
            throws LoginInvalidoException, SenhaInvalidaException, IOException {

        Usuario usuario = new Usuario(login, senha);
        repositorio.salvar(usuario);
        logger.info("Usuário cadastrado: " + usuario);
    }

    public List<Usuario> listarTodos() throws IOException {
        List<Usuario> usuarios = repositorio.buscarTodos();
        logger.info("Listagem de usuários solicitada. Total: " + usuarios.size());
        return usuarios;
    }
}