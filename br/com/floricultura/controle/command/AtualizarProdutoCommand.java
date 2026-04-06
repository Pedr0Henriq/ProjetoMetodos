package br.com.floricultura.controle.command;

import br.com.floricultura.controle.GerenciamentoDeProduto;

public class AtualizarProdutoCommand implements Comando {
    private final GerenciamentoDeProduto gerProduto; // Receiver
    private final int id;
    private final String nome;
    private final double preco;
    private final int quantidadeEstoque;

    public AtualizarProdutoCommand(GerenciamentoDeProduto gerProduto, int id, String nome, double preco, int quantidadeEstoque) {
        this.gerProduto = gerProduto;
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    @Override
    public void executar() throws Exception {
        gerProduto.atualizarProduto(id, nome, preco, quantidadeEstoque);
    }
}