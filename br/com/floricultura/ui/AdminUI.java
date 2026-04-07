package br.com.floricultura.ui;

import br.com.floricultura.controle.FacadeSingletonController;
import br.com.floricultura.controle.command.*;
import br.com.floricultura.entidade.Produto;
import br.com.floricultura.entidade.RegistroAcesso;
import br.com.floricultura.entidade.Usuario;

import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class AdminUI {

    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    private final FacadeSingletonController controller;
    private final Scanner scanner;

    public AdminUI(FacadeSingletonController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        // Simula um "login" de sessão para registrar o acesso
        System.out.print("Informe seu login para registrar o acesso: ");
        String loginSessao = scanner.nextLine().trim();
        if (!loginSessao.isEmpty()) {
            controller.registrarAcesso(loginSessao);
            System.out.println("Acesso registrado para: " + loginSessao);
        }

        int opcao = -1;
        while (opcao != 0) {
            exibirMenu();
            try {
                opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1  -> cadastrarUsuario();
                    case 2  -> listarUsuarios();
                    case 3  -> cadastrarProduto();
                    case 4  -> atualizarProduto();
                    case 5  -> removerProduto();
                    case 6  -> listarProdutos();
                    case 7  -> mostrarQuantidadeEntidades();
                    case 8  -> listarAcessos();
                    case 9  -> gerarRelatorio();
                    case 10 -> desfazerAtualizacao();
                    case 0  -> System.out.println("Encerrando o sistema...");
                    default -> System.out.println("Opcao invalida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Digite um numero valido.");
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }

            System.out.println();
        }
    }

    private void exibirMenu() {
        System.out.println("===== FLORICULTURA =====");
        System.out.println("1 - Cadastrar usuario");
        System.out.println("2 - Listar usuarios");
        System.out.println("3 - Cadastrar produto");
        System.out.println("4 - Atualizar produto");
        System.out.println("5 - Remover produto");
        System.out.println("6 - Listar produtos");
        System.out.println("7 - Quantidade total de entidades");
        System.out.println("8 - Listar acessos registrados");
        System.out.println("9 - Gerar relatorio de acessos");
        System.out.println("10 - Desfazer última atualização de produto");
        System.out.println("0 - Sair");
        System.out.print("Escolha: ");
    }

    private void cadastrarUsuario() throws Exception {
        System.out.print("Login: ");
        String login = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        
        // Padrão Command: Encapsula a requisição como um objeto
        Comando comando = new CadastrarUsuarioCommand(controller.getGerUsuario(), login, senha);
        controller.executarComando(comando);
        
        System.out.println("Usuario cadastrado com sucesso.");
    }

    private void listarUsuarios() throws IOException {
        List<Usuario> usuarios = controller.listarUsuario();
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuario cadastrado.");
            return;
        }
        System.out.println("=== USUARIOS ===");
        for (Usuario u : usuarios) {
            System.out.println(u);
        }
    }

    private void cadastrarProduto() throws Exception {
        System.out.print("Id: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Preco: ");
        double preco = Double.parseDouble(scanner.nextLine());
        System.out.print("Quantidade em estoque: ");
        int quantidade = Integer.parseInt(scanner.nextLine());
        
        // Padrão Command: Encapsula a requisição como um objeto
        Comando comando = new CadastrarProdutoCommand(controller.getGerProduto(), id, nome, preco, quantidade);
        controller.executarComando(comando);
        
        System.out.println("Produto cadastrado com sucesso.");
    }

    private void atualizarProduto() throws Exception {
        System.out.print("Id do produto: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Novo nome: ");
        String nome = scanner.nextLine();
        System.out.print("Novo preco: ");
        double preco = Double.parseDouble(scanner.nextLine());
        System.out.print("Nova quantidade em estoque: ");
        int quantidade = Integer.parseInt(scanner.nextLine());
        
        // Padrão Command: Encapsula a requisição como um objeto
        Comando comando = new AtualizarProdutoCommand(controller.getGerProduto(), id, nome, preco, quantidade);
        controller.executarComando(comando);
        
        System.out.println("Produto atualizado com sucesso.");
    }

    private void removerProduto() throws Exception {
        System.out.print("Id do produto a remover: ");
        int id = Integer.parseInt(scanner.nextLine());
        
        // Padrão Command: Encapsula a requisição como um objeto
        Comando comando = new RemoverProdutoCommand(controller.getGerProduto(), id);
        controller.executarComando(comando);
        
        System.out.println("Produto removido com sucesso.");
    }

    private void listarProdutos() throws IOException {
        List<Produto> produtos = controller.listarProdutos();
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }
        System.out.println("=== PRODUTOS ===");
        for (Produto p : produtos) {
            System.out.println(p);
        }
    }

    private void mostrarQuantidadeEntidades() throws IOException {
        int total = controller.getQuantidadeEntidades();
        System.out.println("Quantidade total de entidades: " + total);
    }

    private void listarAcessos() {
        List<RegistroAcesso> acessos = controller.listarAcessos();
        if (acessos.isEmpty()) {
            System.out.println("Nenhum acesso registrado nesta sessao.");
            return;
        }
        System.out.println("=== ACESSOS REGISTRADOS ===");
        for (RegistroAcesso ra : acessos) {
            System.out.printf("  %-15s em %s%n",
                    ra.getLoginUsuario(),
                    ra.getDataHora().format(FMT));
        }
    }

    private void gerarRelatorio() {
        System.out.println("Formato do relatorio:");
        System.out.println("  1 - TXT  (exibe no console)");
        System.out.println("  2 - HTML (salva em relatorio_acessos.html)");
        System.out.print("Escolha: ");

        String opcao = scanner.nextLine().trim();

        switch (opcao) {
            case "1" -> {
                String relatorio = controller.gerarRelatorioAcessos("TXT");
                System.out.println();
                System.out.println(relatorio);
            }
            case "2" -> {
                String relatorio = controller.gerarRelatorioAcessos("HTML");
                String nomeArquivo = "relatorio_acessos.html";
                try (FileWriter fw = new FileWriter(nomeArquivo)) {
                    fw.write(relatorio);
                    System.out.println("Relatorio HTML salvo em: " + nomeArquivo);
                } catch (IOException e) {
                    System.out.println("Erro ao salvar o arquivo: " + e.getMessage());
                }
            }
            default -> System.out.println("Opcao invalida.");
        }
    }
    private void desfazerAtualizacao() {
        try {
            controller.desfazerUltimaAtualizacao();
            System.out.println("Sucesso! A ultima atualizacao foi desfeita. O produto voltou ao estado anterior.");
        } catch (Exception e) {
            System.out.println("Erro ao desfazer: " + e.getMessage());
        }
    }
}