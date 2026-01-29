package br.com.floricultura.ui;

import br.com.floricultura.controle.GerenciamentoUsuario;
import br.com.floricultura.entidade.Usuario;
import br.com.floricultura.excecao.LoginInvalidoException;
import br.com.floricultura.excecao.SenhaInvalidaException;

import java.io.IOException;
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
        System.out.println("\n--- Novo Cadastro ---");
        System.out.print("Login: ");
        String login = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        try {
            controlador.adicionarUsuario(login, senha);
            System.out.println("✅ Sucesso: Usuário cadastrado!");
            
        } catch (LoginInvalidoException e) {
            System.out.println("❌ Erro de Login: " + e.getMessage());
            
        } catch (SenhaInvalidaException e) {
            System.out.println("❌ Erro de Senha: " + e.getMessage());
            System.out.println("   (Dica: Use maiúsculas, minúsculas, números e símbolos)");
            
        } catch (IOException e) {
            System.out.println("❌ Erro no Disco: Não foi possível salvar o arquivo.");
            e.printStackTrace();
        }
    }

    private void listarUsuarios() {
        try {
            List<Usuario> usuarios = controlador.listarUsuarios();
            System.out.println("\n--- Lista de Usuários ---");
            
            if (usuarios.isEmpty()) {
                System.out.println("(Nenhum usuário cadastrado)");
            } else {
                for (Usuario u : usuarios) {
                    System.out.println(u);
                }
            }
        } catch (IOException e) {
            System.out.println("❌ Erro ao ler os dados: " + e.getMessage());
        }
    }
}
