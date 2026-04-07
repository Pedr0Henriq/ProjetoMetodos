package br.com.floricultura.controle.command;

import br.com.floricultura.controle.GerenciamentoDeProduto;
import br.com.floricultura.entidade.Produto;
import br.com.floricultura.entidade.ProdutoMemento;

public class AtualizarProdutoCommand implements Comando {
    private final GerenciamentoDeProduto gerProduto; // Receiver
    private final int id;
    private final String nome;
    private final double preco;
    private final int quantidadeEstoque;
    
    // O Caretaker do Memento: guarda o estado antigo do produto
    private ProdutoMemento mementoAnterior;

    public AtualizarProdutoCommand(GerenciamentoDeProduto gerProduto, int id, String nome, double preco, int quantidadeEstoque) {
        this.gerProduto = gerProduto;
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    @Override
    public void executar() throws Exception {
        // 1. Antes de atualizar, busca o produto como ele está agora
        Produto produtoAntigo = gerProduto.listarProdutos().stream()
                .filter(p -> p.getId() == this.id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado para atualização."));

        // 2. Tira a foto do estado antigo e guarda no Memento
        this.mementoAnterior = produtoAntigo.salvarEstado();

        // 3. Executa a atualização normalmente
        gerProduto.atualizarProduto(id, nome, preco, quantidadeEstoque);
    }

    @Override
    public void desfazer() throws Exception {
        if (mementoAnterior != null) {
            // Usa os dados do Memento para mandar o banco sobrescrever a atualização 
            // e voltar ao que era antes.
            gerProduto.atualizarProduto(
                    mementoAnterior.getId(),
                    mementoAnterior.getNome(),
                    mementoAnterior.getPreco(),
                    mementoAnterior.getQuantidadeEstoque()
            );
        } else {
            throw new IllegalStateException("Não há atualização anterior para desfazer.");
        }
    }
}