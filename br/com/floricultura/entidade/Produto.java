package br.com.floricultura.entidade;

import java.io.Serializable;

public class Produto implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String nome;
    private double preco;
    private int quantidadeEstoque;

    public Produto(int id, String nome, double preco, int quantidadeEstoque) {
        setId(id);
        setNome(nome);
        setPreco(preco);
        setQuantidadeEstoque(quantidadeEstoque);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("O id deve ser maior que zero.");
        }
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do produto não pode ser vazio.");
        }
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        if (preco < 0) {
            throw new IllegalArgumentException("O preço não pode ser negativo.");
        }
        this.preco = preco;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        if (quantidadeEstoque < 0) {
            throw new IllegalArgumentException("A quantidade em estoque não pode ser negativa.");
        }
        this.quantidadeEstoque = quantidadeEstoque;
    }

    @Override
    public String toString() {
        return "Produto{id=" + id +
                ", nome='" + nome + '\'' +
                ", preco=" + preco +
                ", quantidadeEstoque=" + quantidadeEstoque + '}';
    }
    // ── Padrão Memento (Originator) ───────────────────────────────────────────

    /**
     * Cria um memento com o estado atual do Produto antes de ser alterado.
     */
    public ProdutoMemento salvarEstado() {
        return new ProdutoMemento(this.id, this.nome, this.preco, this.quantidadeEstoque);
    }

    /**
     * Restaura o estado do Produto a partir de um memento guardado.
     */
    public void restaurarEstado(ProdutoMemento memento) {
        this.id = memento.getId();
        this.nome = memento.getNome();
        this.preco = memento.getPreco();
        this.quantidadeEstoque = memento.getQuantidadeEstoque();
    }
}