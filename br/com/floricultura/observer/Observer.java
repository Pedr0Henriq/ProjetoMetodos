package br.com.floricultura.observer;

import br.com.floricultura.entidade.Produto;
import java.util.List;

public interface Observer {
    void atualizar(List<Produto> produtos);
}
