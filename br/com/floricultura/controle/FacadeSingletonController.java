package br.com.floricultura.controle;

import br.com.floricultura.entidade.Produto;
import br.com.floricultura.entidade.Usuario;
import br.com.floricultura.excecao.LoginInvalidoException;
import br.com.floricultura.excecao.SenhaInvalidaException;
import br.com.floricultura.entidade.RegistroAcesso;
import br.com.floricultura.relatorio.GeradorRelatorioHTML;
import br.com.floricultura.relatorio.GeradorRelatorioTXT;
import br.com.floricultura.repositorio.RepositorioFactory;

import java.io.IOException;
import java.util.List;

public class FacadeSingletonController {

    private static FacadeSingletonController instancia;

    private GerenciamentoDeUsuario  gerUsuario;
    private GerenciamentoDeProduto  gerProduto;
    private GerenciamentoDeAcesso   gerAcesso;

    private FacadeSingletonController() {
    }

    public static FacadeSingletonController getInstance() {
        if (instancia == null) {
            instancia = new FacadeSingletonController();
        }
        return instancia;
    }

    /**
     * Inicializa os repositórios usando a Factory adequada ao modo escolhido.
     * O controller não precisa mais conhecer as classes concretas de repositório.
     *
     * @param usarMemoria true → memória RAM; false → arquivo binário
     */
    public void inicializarRepositorios(boolean usarMemoria) {
        RepositorioFactory factory = RepositorioFactory.obter(usarMemoria);
        gerUsuario = new GerenciamentoDeUsuario(factory.criarRepositorioUsuario());
        gerProduto = new GerenciamentoDeProduto(factory.criarRepositorioProduto());
        gerAcesso  = GerenciamentoDeAcesso.getInstance();
    }

    // ── Usuários ─────────────────────────────────────────────────────────────

    public void cadastrarUsuario(String login, String senha)
            throws LoginInvalidoException, SenhaInvalidaException, IOException {
        verificarInicializacao();
        gerUsuario.cadastrar(login, senha);
    }

    public List<Usuario> listarUsuario() throws IOException {
        verificarInicializacao();
        return gerUsuario.listarTodos();
    }

    // ── Produtos ──────────────────────────────────────────────────────────────

    public void cadastrarProduto(int id, String nome, double preco, int quantidadeEstoque) throws IOException {
        verificarInicializacao();
        gerProduto.cadastrarProduto(id, nome, preco, quantidadeEstoque);
    }

    public void atualizarProduto(int id, String nome, double preco, int quantidadeEstoque) throws IOException {
        verificarInicializacao();
        gerProduto.atualizarProduto(id, nome, preco, quantidadeEstoque);
    }

    public void removerProduto(int id) throws IOException {
        verificarInicializacao();
        gerProduto.removerProduto(id);
    }

    public List<Produto> listarProdutos() throws IOException {
        verificarInicializacao();
        return gerProduto.listarProdutos();
    }

    // ── Estatísticas ──────────────────────────────────────────────────────────

    public int getQuantidadeEntidades() throws IOException {
        verificarInicializacao();
        return listarUsuario().size() + listarProdutos().size();
    }

    // ── Acessos ───────────────────────────────────────────────────────────────

    /**
     * Registra um acesso do usuário (chamado ao fazer login ou ao entrar no menu).
     */
    public void registrarAcesso(String loginUsuario) {
        verificarInicializacao();
        gerAcesso.registrarAcesso(loginUsuario);
    }

    public List<RegistroAcesso> listarAcessos() {
        verificarInicializacao();
        return gerAcesso.getRegistros();
    }

    // ── Relatórios (Template Method) ──────────────────────────────────────────

    /**
     * Gera o relatório de acessos no formato escolhido.
     *
     * @param tipo "TXT" ou "HTML"
     * @return conteúdo do relatório como String
     */
    public String gerarRelatorioAcessos(String tipo) {
        verificarInicializacao();
        List<RegistroAcesso> registros = gerAcesso.getRegistros();

        return switch (tipo.toUpperCase()) {
            case "HTML" -> new GeradorRelatorioHTML().gerar(registros);
            default     -> new GeradorRelatorioTXT().gerar(registros);
        };
    }

    // ── Auxiliar ──────────────────────────────────────────────────────────────

    private void verificarInicializacao() {
        if (gerUsuario == null || gerProduto == null) {
            throw new IllegalStateException("Os repositórios ainda não foram inicializados.");
        }
    }
}