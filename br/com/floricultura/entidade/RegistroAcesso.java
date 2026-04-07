package br.com.floricultura.entidade;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Registra cada acesso (login) de um usuário ao sistema.
 * Usado para gerar os relatórios de estatísticas.
 */
public class RegistroAcesso implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String loginUsuario;
    private final LocalDateTime dataHora;

    public RegistroAcesso(String loginUsuario, LocalDateTime dataHora) {
        this.loginUsuario = loginUsuario;
        this.dataHora = dataHora;
    }

    public String getLoginUsuario() {
        return loginUsuario;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    @Override
    public String toString() {
        return "RegistroAcesso{login='" + loginUsuario + "', dataHora=" + dataHora + "}";
    }
}
