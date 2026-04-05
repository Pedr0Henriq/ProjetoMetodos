package br.com.floricultura.repositorio;

/**
 * Factory concreta que cria repositórios persistidos em arquivo binário (BDR).
 * Os dados sobrevivem ao encerramento do sistema.
 */
public class RepositorioBDRFactory extends RepositorioFactory {

    @Override
    public RepositorioUsuario criarRepositorioUsuario() {
        return new RepositorioUsuarioBDR();
    }

    @Override
    public RepositorioProduto criarRepositorioProduto() {
        return new RepositorioProdutoBDR();
    }
}
