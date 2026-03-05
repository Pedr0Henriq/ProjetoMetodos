package br.com.floricultura.ui;

import br.com.floricultura.controle.FacadeSingletonController;
import br.com.floricultura.entidade.Produto;
import br.com.floricultura.entidade.Usuario;
import br.com.floricultura.excecao.LoginInvalidoException;
import br.com.floricultura.excecao.SenhaInvalidaException;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class AdminUI {

    private final FacadeSingletonController controller;
    private final Scanner scanner;

    public AdminUI(FacadeSingletonController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        int opcao = -1;

        while (opcao != 0) {
            exibirMenu();
            try {
                opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1:
                        cadastrarUsuario();
                        break;
                    case 2:
                        listarUsuarios();
                        break;
                    case 3:
                        cadastrarProduto();
                        break;
                    case 4:
                        atualizarProduto();
                        break;
                    case 5:
                        removerProduto();
                        break;
                    case 6:
                        listarProdutos();
                        break;
                    case 7:
                        mostrarQuantidadeEntidades();
                        break;
                    case 0:
                        System.out.println("Encerrando o sistema...");
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido.");
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }

            System.out.println();
        }
    }

    private void exibirMenu() {
        System.out.println("===== FLORICULTURA =====");
        System.out.println("1 - Cadastrar usuário");
        System.out.println("2 - Listar usuários");
        System.out.println("3 - Cadastrar produto");
        System.out.println("4 - Atualizar produto");
        System.out.println("5 - Remover produto");
        System.out.println("6 - Listar produtos");
        System.out.println("7 - Quantidade total de entidades");
        System.out.println("0 - Sair");
        System.out.print("Escolha: ");
    }

    private void cadastrarUsuario() throws IOException, LoginInvalidoException, SenhaInvalidaException {
        System.out.print("Login: ");
        String login = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        controller.cadastrarUsuario(login, senha);
        System.out.println("Usuário cadastrado com sucesso.");
    }

    private void listarUsuarios() throws IOException {
        List<Usuario> usuarios = controller.listarUsuario();

        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
            return;
        }

        System.out.println("=== USUÁRIOS ===");
        for (Usuario usuario : usuarios) {
            System.out.println(usuario);
        }
    }

    private void cadastrarProduto() throws IOException {
        System.out.print("Id: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Preço: ");
        double preco = Double.parseDouble(scanner.nextLine());

        System.out.print("Quantidade em estoque: ");
        int quantidade = Integer.parseInt(scanner.nextLine());

        controller.cadastrarProduto(id, nome, preco, quantidade);
        System.out.println("Produto cadastrado com sucesso.");
    }

    private void atualizarProduto() throws IOException {
        System.out.print("Id do produto: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Novo nome: ");
        String nome = scanner.nextLine();

        System.out.print("Novo preço: ");
        double preco = Double.parseDouble(scanner.nextLine());

        System.out.print("Nova quantidade em estoque: ");
        int quantidade = Integer.parseInt(scanner.nextLine());

        controller.atualizarProduto(id, nome, preco, quantidade);
        System.out.println("Produto atualizado com sucesso.");
    }

    private void removerProduto() throws IOException {
        System.out.print("Id do produto a remover: ");
        int id = Integer.parseInt(scanner.nextLine());

        controller.removerProduto(id);
        System.out.println("Produto removido com sucesso.");
    }

    private void listarProdutos() throws IOException {
        List<Produto> produtos = controller.listarProdutos();

        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        System.out.println("=== PRODUTOS ===");
        for (Produto produto : produtos) {
            System.out.println(produto);
        }
    }

    private void mostrarQuantidadeEntidades() throws IOException {
        int total = controller.getQuantidadeEntidades();
        System.out.println("Quantidade total de entidades: " + total);
    }
}