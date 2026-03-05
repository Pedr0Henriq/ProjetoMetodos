package br.com.floricultura.repositorio;

import br.com.floricultura.entidade.Produto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioProdutoBDR implements RepositorioProduto {

    private static final String NOME_ARQUIVO = "produtos.bin";

    @Override
    public void salvar(Produto produto) throws IOException {
        List<Produto> produtos = buscarTodos();
        produtos.add(produto);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(NOME_ARQUIVO))) {
            oos.writeObject(produtos);
        }
    }

    @Override
    public void atualizar(Produto produto) throws IOException {
        List<Produto> produtos = buscarTodos();
        boolean encontrado = false;

        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getId() == produto.getId()) {
                produtos.set(i, produto);
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            throw new IllegalArgumentException("Produto com id " + produto.getId() + " não encontrado.");
        }

        salvarLista(produtos);
    }

    @Override
    public void remover(int id) throws IOException {
        List<Produto> produtos = buscarTodos();
        boolean removido = produtos.removeIf(produto -> produto.getId() == id);

        if (!removido) {
            throw new IllegalArgumentException("Produto com id " + id + " não encontrado.");
        }

        salvarLista(produtos);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Produto> buscarTodos() throws IOException {
        File arquivo = new File(NOME_ARQUIVO);

        if (!arquivo.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (List<Produto>) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new IOException("Erro ao ler os produtos do arquivo.", e);
        }
    }

    private void salvarLista(List<Produto> produtos) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(NOME_ARQUIVO))) {
            oos.writeObject(produtos);
        }
    }
}