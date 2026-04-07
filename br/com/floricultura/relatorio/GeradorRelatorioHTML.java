package br.com.floricultura.relatorio;

/**
 * Gerador concreto: produz o relatório em HTML.
 * Implementa os métodos primitivos definidos no Template Method.
 */
public class GeradorRelatorioHTML extends GeradorRelatorioAcesso {

    @Override
    protected String nomeTipoRelatorio() {
        return "HTML";
    }

    @Override
    protected void abrirDocumento(StringBuilder sb) {
        sb.append("<!DOCTYPE html>\n<html lang=\"pt-BR\">\n<head>\n")
          .append("  <meta charset=\"UTF-8\">\n")
          .append("  <title>Relatório de Acessos</title>\n")
          .append("  <style>\n")
          .append("    body { font-family: Arial, sans-serif; margin: 32px; color: #333; }\n")
          .append("    h1 { color: #2e7d32; }\n")
          .append("    h2 { color: #388e3c; font-size: 1em; margin-top: 16px; }\n")
          .append("    table { border-collapse: collapse; width: 100%; max-width: 500px; }\n")
          .append("    th, td { border: 1px solid #ccc; padding: 8px 12px; text-align: left; }\n")
          .append("    th { background-color: #c8e6c9; }\n")
          .append("    tr:nth-child(even) { background-color: #f1f8e9; }\n")
          .append("  </style>\n</head>\n<body>\n");
    }

    @Override
    protected void escreverTitulo(StringBuilder sb, String titulo) {
        sb.append("  <h1>").append(escaparHtml(titulo)).append("</h1>\n");
    }

    @Override
    protected void escreverLinha(StringBuilder sb, String chave, String valor) {
        sb.append(String.format("  <p><strong>%s:</strong> %s</p>%n",
                escaparHtml(chave), escaparHtml(valor)));
    }

    @Override
    protected void escreverSeparador(StringBuilder sb) {
        sb.append("  <hr/>\n");
    }

    @Override
    protected void escreverItemUsuario(StringBuilder sb, String login, long quantidade) {
        // Primeiro item: abre a tabela
        if (!sb.toString().contains("<table>")) {
            sb.append("  <table>\n")
              .append("    <tr><th>Usuário</th><th>Acessos</th></tr>\n");
        }
        sb.append(String.format("    <tr><td>%s</td><td>%d</td></tr>%n",
                escaparHtml(login), quantidade));
    }

    @Override
    protected void fecharDocumento(StringBuilder sb) {
        // Fecha a tabela se ela foi aberta
        if (sb.toString().contains("<table>")) {
            sb.append("  </table>\n");
        }
        sb.append("</body>\n</html>\n");
    }

    private String escaparHtml(String texto) {
        return texto.replace("&", "&amp;")
                    .replace("<", "&lt;")
                    .replace(">", "&gt;");
    }
}
