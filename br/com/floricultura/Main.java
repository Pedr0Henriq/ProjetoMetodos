package br.com.floricultura;

import br.com.floricultura.repositorio.UsuarioRepositorio;
import br.com.floricultura.controle.GerenciamentoUsuario;
import br.com.floricultura.ui.FloriculturaUI;

public class Main {
    public static void main(String[] args) {
        UsuarioRepositorio repositorio = new UsuarioRepositorio();
        GerenciamentoUsuario controlador = new GerenciamentoUsuario(repositorio);
        FloriculturaUI ui = new FloriculturaUI(controlador);

        ui.iniciar();
    }
}
