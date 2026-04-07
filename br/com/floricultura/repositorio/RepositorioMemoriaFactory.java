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
        // Envolve o repositório real com um proxy que controla acesso
        return new RepositorioProdutoProxy(new RepositorioProdutoMemoria());
    }
}
