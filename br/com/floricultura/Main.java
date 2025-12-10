package br.com.floricultura;

import br.com.floricultura.repositorio.UsuarioRepositorio;
import br.com.floricultura.controle.GerenciamentoUsuario;
import br.com.floricultura.ui.AdminUI;

public class Main {
    public static void main(String[] args) {
        UsuarioRepositorio repositorio = new UsuarioRepositorio();
        GerenciamentoUsuario controlador = new GerenciamentoUsuario(repositorio);
        AdminUI ui = new AdminUI(controlador);

        ui.iniciar();
    }
}
