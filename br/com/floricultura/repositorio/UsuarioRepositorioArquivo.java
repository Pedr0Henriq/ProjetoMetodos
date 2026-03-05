package br.com.floricultura.repositorio;

import br.com.floricultura.entidade.Usuario;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepositorioArquivo implements UsuarioRepositorio {

    private static final String NOME_ARQUIVO = "usuarios.bin";

    @Override
    public void salvar(Usuario usuario) throws IOException {
        List<Usuario> listaAtual = buscarTodos();
        listaAtual.add(usuario);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(NOME_ARQUIVO))) {
            oos.writeObject(listaAtual);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Usuario> buscarTodos() throws IOException {
        File arquivo = new File(NOME_ARQUIVO);
        
        if (!arquivo.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (List<Usuario>) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new IOException("Erro: Classe Usuário não encontrada.", e);
        }
    }
}