# ProjetoMetodos

Projeto desenvolvido para a disciplina "Métodos de Projeto de Software" (2025.2).
Implementa um sistema simples de gerenciamento para uma floricultura, cobrindo
controle de usuários, produtos, repositórios em memória/BDR, padrão Command,
Observer, Memento e geração de relatórios.

## Sumário
- **Descrição**
- **Estrutura do repositório**
- **Requisitos**
- **Como compilar e executar**
- **Principais componentes**
- **Como contribuir**

## Descrição

O sistema demonstra conceitos de projeto e padrões de software aplicados a um
caso realista: uma floricultura. Serve como trabalho acadêmico e base para
estudos sobre arquitetura, padrões e testes básicos.

## Estrutura do repositório

O código-fonte está dentro do pacote `br.com.floricultura`. A árvore principal:

- `br/com/floricultura/` — ponto de entrada e pacote principal
	- `controle/` — controladores, fachada e comandos (padrão Command)
	- `entidade/` — classes de domínio (Produto, Usuario, Memento, etc.)
	- `excecao/` — exceções customizadas
	- `log/` — adaptadores de logging
	- `observer/` — interfaces de Observer/Subject
	- `relatorio/` — geradores de relatórios (TXT, HTML, etc.)
	- `repositorio/` — factories e implementações de repositório (memória, BDR, proxy)
	- `ui/` — interfaces de usuário (simples)

Também há um `rodar.bat` em `br/com/floricultura/` para execução rápida em Windows.

## Requisitos

- Java 11+ (ou versão compatível com compilação do projeto)
- JDK instalado e `javac`/`java` no PATH

## Como compilar e executar

Opção recomendada (Windows): execute o `rodar.bat` localizado em
[br/com/floricultura/Main.java](br/com/floricultura/Main.java) (o `bat` compila e
executa a aplicação):

```powershell
cd "c:\Users\luisa\Downloads\ProjetoMetodos"
.
br\com\floricultura\rodar.bat
```

Compilação manual e execução (cross-platform):

```powershell
# Compila todos os arquivos .java para a pasta out
javac -d out $(Get-ChildItem -Recurse -Filter "*.java" -Name | ForEach-Object { "br/com/floricultura/$_" })
# Execute a aplicação
java -cp out br.com.floricultura.Main
```

Observação: ajuste os comandos conforme seu shell (os exemplos acima assumem
PowerShell no Windows). Se preferir, abra sua IDE (IntelliJ/NetBeans/Eclipse)
e importe o projeto como um projeto Java simples.

## Principais componentes

- `FacadeSingletonController` — fachada central que orquestra casos de uso.
- Comandos em `controle/command` — `CadastrarProdutoCommand`, `RemoverProdutoCommand`, etc.
- Repositórios em `repositorio/` — implementações em memória e BDR, além de proxy.
- Memento (`ProdutoMemento`) — exemplo de salvamento/restauração de estado.
- Observer — fluxo para notificações e logs.
- Geradores de relatório em `relatorio/` — saída TXT e HTML.

## Como usar (fluxo básico)

1. Inicie a aplicação via `rodar.bat` ou execute `Main` pela IDE.
2. Utilize a interface fornecida em `ui/` para cadastrar usuários e produtos.
3. Os comandos e repositórios simulam persistência; revise `RepositorioProdutoMemoria`
	 para entender o comportamento em memória.