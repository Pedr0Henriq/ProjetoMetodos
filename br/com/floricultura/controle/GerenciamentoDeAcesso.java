package br.com.floricultura.controle;

import br.com.floricultura.entidade.RegistroAcesso;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Registra os acessos dos usuários durante a sessão.
 * Singleton para que qualquer parte do sistema possa registrar acessos.
 */
public class GerenciamentoDeAcesso {

    private static GerenciamentoDeAcesso instancia;
    private final List<RegistroAcesso> registros;

    private GerenciamentoDeAcesso() {
        this.registros = new ArrayList<>();
    }

    public static GerenciamentoDeAcesso getInstance() {
        if (instancia == null) {
            instancia = new GerenciamentoDeAcesso();
        }
        return instancia;
    }

    /** Registra um acesso do usuário com o timestamp atual. */
    public void registrarAcesso(String loginUsuario) {
        registros.add(new RegistroAcesso(loginUsuario, LocalDateTime.now()));
    }

    /** Retorna cópia imutável da lista de registros. */
    public List<RegistroAcesso> getRegistros() {
        return Collections.unmodifiableList(registros);
    }
}
