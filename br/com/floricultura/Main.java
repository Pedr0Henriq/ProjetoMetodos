package br.com.floricultura;

import br.com.floricultura.controle.FacadeSingletonController;
import br.com.floricultura.ui.AdminUI;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FacadeSingletonController controller = FacadeSingletonController.getInstance();

        while (true) {
            System.out.println("Como deseja iniciar o sistema?");
            System.out.println("1 - Memória RAM (dados somem ao fechar)");
            System.out.println("2 - Arquivo binário (persistente)");
            System.out.print("Escolha: ");

            String opcao = scanner.nextLine();

            if ("1".equals(opcao)) {
                controller.inicializarRepositorios(true);
                System.out.println("Sistema iniciado em modo MEMÓRIA.");
                break;
            } else if ("2".equals(opcao)) {
                controller.inicializarRepositorios(false);
                System.out.println("Sistema iniciado em modo ARQUIVO.");
                break;
            } else {
                System.out.println("Opção inválida.");
            }
        }

        AdminUI ui = new AdminUI(controller);
        ui.iniciar();
    }
}