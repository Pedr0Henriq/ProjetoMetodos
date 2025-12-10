package br.com.floricultura.ui;

import br.com.floricultura.controle.GerenciamentoUsuario;
import br.com.floricultura.entidade.Usuario;

import java.util.List;
import java.util.Scanner;

public class AdminUI {

    private GerenciamentoUsuario controlador;
    private Scanner scanner;

    public AdminUI(GerenciamentoUsuario controlador) {
        this.controlador = controlador;
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        while (true) {
            System.out.println("\n===== Sistema de Usuários da Floricultura =====");
            System.out.println("1 - Cadastrar usuário");
            System.out.println("2 - Listar usuários");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    cadastrarUsuario();
                    break;
                case "2":
                    listarUsuarios();
                    break;
                case "0":
                    System.out.println("Encerrando...");
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private void cadastrarUsuario() {
        System.out.print("Login do usuário: ");
        String login = scanner.nextLine();

        System.out.print("Senha do usuário: ");
        String senha = scanner.nextLine();

        controlador.adicionarUsuario(login, senha);
        System.out.println("Usuário cadastrado com sucesso!");
    }

    private void listarUsuarios() {
        List<Usuario> usuarios = controlador.listarUsuarios();
        System.out.println("\n--- Lista de Usuários ---");
        if (usuarios.isEmpty()) {
            System.out.println("(Nenhum usuário cadastrado)");
        } else {
            for (Usuario u : usuarios) {
                System.out.println(u);
            }
        }
    }
}
