package br.com.floricultura.repositorio;

import br.com.floricultura.entidade.Produto;
import br.com.floricultura.observer.Observer;
import br.com.floricultura.observer.Subject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class RepositorioProdutoMemoria implements RepositorioProduto, Subject {

    private final List<Produto> produtos;
    // Observers usam thread-safe list para evitar ConcurrentModification
    private final List<Observer> observers = new CopyOnWriteArrayList<>();

    public RepositorioProdutoMemoria() {
        this.produtos = new ArrayList<>();
    }

    @Override
    public void salvar(Produto produto) {
        produtos.add(produto);
        notificar();
    }

    @Override
    public void atualizar(Produto produto) {
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getId() == produto.getId()) {
                produtos.set(i, produto);
                notificar();
                return;
            }
        }
        throw new IllegalArgumentException("Produto com id " + produto.getId() + " não encontrado.");
    }

    @Override
    public void remover(int id) {
        boolean removido = produtos.removeIf(produto -> produto.getId() == id);
        if (!removido) {
            throw new IllegalArgumentException("Produto com id " + id + " não encontrado.");
        }
        notificar();
    }

    @Override
    public List<Produto> buscarTodos() {
        return new ArrayList<>(produtos);
    }

    // Subject (Observer pattern) ─────────────────────────────────────
    @Override
    public void registrar(Observer o) {
        if (o != null) observers.add(o);
    }

    @Override
    public void remover(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notificar() {
        List<Produto> copia = buscarTodos();
        for (Observer o : observers) {
            o.atualizar(copia);
        }
    }
}