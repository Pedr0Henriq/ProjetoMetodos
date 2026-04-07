package br.com.floricultura.relatorio;

/**
 * Gerador concreto: produz o relatório em texto puro (.txt).
 * Implementa os métodos primitivos definidos no Template Method.
 */
public class GeradorRelatorioTXT extends GeradorRelatorioAcesso {

    @Override
    protected String nomeTipoRelatorio() {
        return "Texto Puro (TXT)";
    }

    @Override
    protected void abrirDocumento(StringBuilder sb) {
        sb.append("=".repeat(50)).append("\n");
    }

    @Override
    protected void fecharDocumento(StringBuilder sb) {
        sb.append("=".repeat(50)).append("\n");
    }

    @Override
    protected void escreverTitulo(StringBuilder sb, String titulo) {
        sb.append("  ").append(titulo.toUpperCase()).append("\n");
    }

    @Override
    protected void escreverLinha(StringBuilder sb, String chave, String valor) {
        sb.append(String.format("  %-25s: %s%n", chave, valor));
    }

    @Override
    protected void escreverSeparador(StringBuilder sb) {
        sb.append("-".repeat(50)).append("\n");
    }

    @Override
    protected void escreverItemUsuario(StringBuilder sb, String login, long quantidade) {
        sb.append(String.format("  %-20s → %d acesso(s)%n", login, quantidade));
    }
}
