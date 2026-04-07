package br.com.floricultura.relatorio;

import br.com.floricultura.entidade.RegistroAcesso;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Template Method para geração de relatórios de acesso.
 *
 * Define o esqueleto do algoritmo de geração de relatório:
 *   1. abrirDocumento()   — setup inicial (ex: tags HTML abertas, cabeçalho PDF)
 *   2. escreverCabecalho() — título e data de geração
 *   3. escreverEstatisticas() — dados calculados (delegado a métodos primitivos)
 *   4. fecharDocumento()   — finalização (ex: tags HTML fechadas)
 *
 * As subclasses implementam apenas os métodos primitivos, sem alterar o fluxo.
 */
public abstract class GeradorRelatorioAcesso {

    // ── Template Method (final: o algoritmo não pode ser sobrescrito) ───────
    public final String gerar(List<RegistroAcesso> registros) {
        Map<String, Long> acessosPorUsuario = contarAcessosPorUsuario(registros);

        StringBuilder sb = new StringBuilder();
        abrirDocumento(sb);
        escreverCabecalho(sb);
        escreverEstatisticas(sb, registros, acessosPorUsuario);
        fecharDocumento(sb);
        return sb.toString();
    }

    // ── Métodos primitivos (obrigatórios nas subclasses) ────────────────────
    protected abstract void abrirDocumento(StringBuilder sb);
    protected abstract void fecharDocumento(StringBuilder sb);
    protected abstract void escreverTitulo(StringBuilder sb, String titulo);
    protected abstract void escreverLinha(StringBuilder sb, String chave, String valor);
    protected abstract void escreverSeparador(StringBuilder sb);
    protected abstract void escreverItemUsuario(StringBuilder sb, String login, long quantidade);

    // ── Método gancho (hook) — subclasses podem sobrescrever se quiserem ────
    protected String nomeTipoRelatorio() {
        return "Relatório";
    }

    // ── Passos internos do template ─────────────────────────────────────────
    private void escreverCabecalho(StringBuilder sb) {
        escreverTitulo(sb, "Relatório de Acessos — Floricultura");
        escreverLinha(sb, "Gerado em", java.time.LocalDateTime.now()
                .format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
        escreverLinha(sb, "Tipo", nomeTipoRelatorio());
        escreverSeparador(sb);
    }

    private void escreverEstatisticas(StringBuilder sb,
                                      List<RegistroAcesso> registros,
                                      Map<String, Long> acessosPorUsuario) {
        escreverLinha(sb, "Total de acessos", String.valueOf(registros.size()));
        escreverLinha(sb, "Usuários distintos", String.valueOf(acessosPorUsuario.size()));
        escreverSeparador(sb);
        escreverTitulo(sb, "Acessos por usuário");

        acessosPorUsuario.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .forEach(e -> escreverItemUsuario(sb, e.getKey(), e.getValue()));
    }

    private Map<String, Long> contarAcessosPorUsuario(List<RegistroAcesso> registros) {
        return registros.stream()
                .collect(Collectors.groupingBy(RegistroAcesso::getLoginUsuario, Collectors.counting()));
    }
}
