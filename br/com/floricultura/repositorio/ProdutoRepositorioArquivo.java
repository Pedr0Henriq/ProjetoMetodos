package br.com.floricultura.repositorio;

import br.com.floricultura.entidade.Produto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoRepositorioArquivo implements ProdutoRepositorio {

    private static final String NOME_ARQUIVO = "produtos.bin";
    @Override
    public void salvar(Produto produto) throws IOException {
        List<Produto> listaAtual = buscarTodos();
        listaAtual.add(produto);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(NOME_ARQUIVO))) {
            oos.writeObject(listaAtual);
        }
    }

    @Override
    public List<Produto> buscarTodos() throws IOException {
        File arquivo = new File(NOME_ARQUIVO);

        if (!arquivo.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (List<Produto>) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new IOException("Erro: Classe Produto não encontrada.", e);
        }
    }
}
