package br.com.floricultura.entidade;

/**
 * Padrão Memento: Armazena o estado interno de um Produto (Originator)
 * em um momento específico para poder restaurá-lo depois.
 */
public class ProdutoMemento {
    private final int id;
    private final String nome;
    private final double preco;
    private final int quantidadeEstoque;

    public ProdutoMemento(int id, String nome, double preco, int quantidadeEstoque) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public double getPreco() { return preco; }
    public int getQuantidadeEstoque() { return quantidadeEstoque; }
}