package br.com.floricultura.entidade;

import java.io.Serializable;
import br.com.floricultura.excecao.LoginInvalidoException;
import br.com.floricultura.excecao.SenhaInvalidaException;

public class Usuario implements Serializable {

    private String login;
    private String senha;

    public Usuario(String login, String senha) throws LoginInvalidoException, SenhaInvalidaException {
        this.setLogin(login);
        this.setSenha(senha);
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public void setLogin(String login) throws LoginInvalidoException {
        if (login == null || login.trim().isEmpty()) {
            throw new LoginInvalidoException("O login não pode ser vazio.");
        }
        if (login.length() > 12) {
            throw new LoginInvalidoException("O login deve ter no máximo 12 caracteres.");
        }
        if (login.matches(".*\\d.*")) {
            throw new LoginInvalidoException("O login não pode conter números.");
        }
        this.login = login;
    }

    public void setSenha(String senha) throws SenhaInvalidaException {
        if (senha == null || senha.length() < 8) {
            throw new SenhaInvalidaException("A senha deve ter no mínimo 8 caracteres.");
        }
        
        boolean temNumero = senha.matches(".*\\d.*");
        boolean temMaiuscula = !senha.equals(senha.toLowerCase());
        boolean temMinuscula = !senha.equals(senha.toUpperCase());
        boolean temEspecial = senha.matches(".*[!@#$%^&*(),.?\":{}|<>].*");

        if (!temNumero || !temMaiuscula || !temMinuscula || !temEspecial) {
            throw new SenhaInvalidaException(
                "A senha deve conter letras maiúsculas, minúsculas, números e símbolos."
            );
        }

        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Usuário{login='" + login + "'}";
    }
}
