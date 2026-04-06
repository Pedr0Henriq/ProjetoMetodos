package br.com.floricultura.controle.command;

import br.com.floricultura.controle.GerenciamentoDeProduto;

public class RemoverProdutoCommand implements Comando {
    private final GerenciamentoDeProduto gerProduto; // Receiver
    private final int id;

    public RemoverProdutoCommand(GerenciamentoDeProduto gerProduto, int id) {
        this.gerProduto = gerProduto;
        this.id = id;
    }

    @Override
    public void executar() throws Exception {
        gerProduto.removerProduto(id);
    }
}