package br.com.floricultura.entidade;


import br.com.floricultura.excecao.NomeInvalidoException;
import br.com.floricultura.excecao.PrecoInvalidoException;
import br.com.floricultura.excecao.QuantidadeEstoqueInvalidaException;

import java.io.Serializable;

public class Produto implements Serializable {
    private int id;
    private String nome;
    private double preco;
    private int quantidadeEstoque;

    public Produto(int id, String nome, double preco, int quantidadeEstoque) throws NomeInvalidoException, PrecoInvalidoException, QuantidadeEstoqueInvalidaException {
        this.setId(id);
        this.setNome(nome);
        this.setPreco(preco);
        this.setQuantidadeEstoque(quantidadeEstoque);
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public double getPreco() { return preco; }
    public int getQuantidadeEstoque() { return quantidadeEstoque; }

    public void setId(int id) { this.id = id; }

    public void setNome(String nome) throws NomeInvalidoException {
        if (nome == null || nome.trim().isEmpty()) {
            throw new NomeInvalidoException("O nome não pode ser vazio.");
        }
        this.nome = nome;
    }

    public void setPreco(double preco) throws PrecoInvalidoException {
        if (preco <= 0) {
            throw new PrecoInvalidoException("O preço deve ser maior que zero.");
        }
        this.preco = preco;
    }
    public void setQuantidadeEstoque(int quantidadeEstoque) throws QuantidadeEstoqueInvalidaException {
        if (quantidadeEstoque < 0) {
            throw new QuantidadeEstoqueInvalidaException("A quantidade em estoque não pode ser negativa.");
        }
        this.quantidadeEstoque = quantidadeEstoque;
    }
}