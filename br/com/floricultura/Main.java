package br.com.floricultura;

import br.com.floricultura.repositorio.UsuarioRepositorio;
import br.com.floricultura.repositorio.UsuarioRepositorioArquivo;
import br.com.floricultura.repositorio.UsuarioRepositorioMemoria;

import java.util.Scanner;

import br.com.floricultura.controle.GerenciamentoUsuario;
import br.com.floricultura.ui.AdminUI;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UsuarioRepositorio repositorio = null;
        while (repositorio == null) {
            System.out.println("Como deseja iniciar o sistema?");
            System.out.println("1 - Memória RAM (Dados somem ao fechar)");
            System.out.println("2 - Arquivo (Dados persistentes)");
            System.out.print("Escolha: ");
            String opcao = scanner.nextLine();

            if (opcao.equals("1")) {
                repositorio = new UsuarioRepositorioMemoria();
                System.out.println("-> Iniciado em modo MEMÓRIA.");
            } else if (opcao.equals("2")) {
                repositorio = new UsuarioRepositorioArquivo();
                System.out.println("-> Iniciado em modo ARQUIVO.");
            } else {
                System.out.println("Opção inválida.");
            }
        }

        // Injeção de dependência: O controlador não sabe qual tipo escolhemos!
        GerenciamentoUsuario controlador = new GerenciamentoUsuario(repositorio);
        AdminUI ui = new AdminUI(controlador);

        ui.iniciar();
    }
}
