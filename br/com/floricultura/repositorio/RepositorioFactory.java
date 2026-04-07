package br.com.floricultura.repositorio;

/**
 * Factory Method abstrata para criação dos repositórios.
 *
 * Cada subclasse concreta decide qual implementação de repositório
 * instanciar (memória ou arquivo), desacoplando a camada de controle
 * da infraestrutura de persistência.
 */
public abstract class RepositorioFactory {

    // ── Factory Methods (contrato para as subclasses) ──────────────────────
    public abstract RepositorioUsuario criarRepositorioUsuario();
    public abstract RepositorioProduto criarRepositorioProduto();

    // ── Método estático que seleciona a factory concreta ───────────────────
    /**
     * Retorna a factory adequada ao modo desejado.
     *
     * @param usarMemoria true → dados em memória RAM; false → dados em arquivo binário
     */
    public static RepositorioFactory obter(boolean usarMemoria) {
        if (usarMemoria) {
            return new RepositorioMemoriaFactory();
        } else {
            return new RepositorioBDRFactory();
        }
    }
}
