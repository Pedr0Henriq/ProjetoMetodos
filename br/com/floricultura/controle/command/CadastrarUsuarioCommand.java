package br.com.floricultura.controle.command;

import br.com.floricultura.controle.GerenciamentoDeUsuario;

public class CadastrarUsuarioCommand implements Comando {
    private final GerenciamentoDeUsuario gerUsuario; // Receiver
    private final String login;
    private final String senha;

    public CadastrarUsuarioCommand(GerenciamentoDeUsuario gerUsuario, String login, String senha) {
        this.gerUsuario = gerUsuario;
        this.login = login;
        this.senha = senha;
    }

    @Override
    public void executar() throws Exception {
        gerUsuario.cadastrar(login, senha);
    }
}