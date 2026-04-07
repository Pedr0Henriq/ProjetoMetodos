package br.com.floricultura.repositorio;

/**
 * Factory concreta que cria repositórios em memória RAM.
 * Os dados são perdidos ao encerrar o sistema.
 */
public class RepositorioMemoriaFactory extends RepositorioFactory {

    @Override
    public RepositorioUsuario criarRepositorioUsuario() {
        return new RepositorioUsuarioMemoria();
    }

    @Override
    public RepositorioProduto criarRepositorioProduto() {
        return new RepositorioProdutoMemoria();
    }
}
