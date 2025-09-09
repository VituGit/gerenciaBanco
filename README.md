# GerenciaBanco — Relatório do Projeto (Aluno)

> **Disciplina:** Linguagem Orientada a Objetos  
> **Tema da prática:** Aplicação simples de gerenciamento bancário em Java (POO)  
> **Autor(a):** _Victor Pereira Gurgel_ — _38820904_

---

## 1) O que eu fiz e por quê

Eu desenvolvi uma aplicação de console que simula operações básicas de uma conta bancária: **consultar saldo, depositar e sacar**. O objetivo foi colocar em prática os conceitos centrais de **Programação Orientada a Objetos (POO)**:

- definição de **classes** e **objetos**;  
- **encapsulamento** (atributos privados e métodos públicos de acesso);  
- uso de **métodos** e **construtor**;  
- organização em **pacotes**;  
- **estruturas de repetição** (`do...while`) e de **decisão** (`switch...case`).

A aplicação atende ao roteiro da atividade: coleta **nome, sobrenome e CPF**, cria a conta com **saldo inicial 0** e oferece um **menu** que se repete até o usuário escolher **encerrar**.

---

## 2) Como eu construí (passo a passo)

### 2.1 Ambiente
- **IDE:** NetBeans  
- **JDK:** 17 (ou compatível)  
- **Build:** Maven (projeto “Java with Maven > Java Application”)
- **Pacote do projeto:** `com.mycompany.gerenciabanco`

### 2.2 Criação do projeto no NetBeans
1. `File > New Project` → **Java with Maven > Java Application**.  
2. Nomeei como **gerenciaBanco** (groupId `com.mycompany`, artifactId `gerenciaBanco`).  
3. O NetBeans inicializou o repositório Git local e depois fiz push para o GitHub.

### 2.3 Organização do código
Eu criei **duas classes** dentro do pacote `com.mycompany.gerenciabanco`:

```
src/main/java/com/mycompany/gerenciabanco/
 ├─ ContaBancaria.java   (regras da conta)
 └─ GerenciaBanco.java   (interface de console e método main)
```

### 2.4 Decisões de design (POO)
- **Responsabilidades separadas:**  
  - `ContaBancaria` concentra **regras de negócio** (validar depósito/saque, manter saldo).  
  - `GerenciaBanco` cuida apenas da **interação com o usuário** (menu, leitura com `Scanner`).  
- **Encapsulamento:** atributos (`nome`, `sobrenome`, `cpf`, `saldo`) são **privados**; o acesso é via **métodos públicos**.  
- **Validação simples:** depósitos/saques só aceitam valores **maiores que zero**; saque confere **saldo suficiente**.  
- **Laço de execução:** `do...while` mantém o menu ativo até a opção **4 (sair)**.  
- **Escolhas do usuário:** `switch...case` organiza cada ação.  

---

## 3) Explicação das classes e métodos

### 3.1 `ContaBancaria`
```java
class ContaBancaria {
    private String nome;
    private String sobrenome;
    private String cpf;
    private double saldo;

    public ContaBancaria(String nome, String sobrenome, String cpf) { ... }

    public double consultarSaldo() { ... }         // retorna o saldo atual
    public void depositar(double valor) { ... }    // valida > 0 e soma ao saldo
    public void sacar(double valor) { ... }        // valida > 0 e saldo suficiente
}
```
- **Construtor:** recebe os dados pessoais e inicia `saldo = 0.0`.
- **`consultarSaldo()`**: retorna o saldo atual.
- **`depositar(valor)`**: só aceita **valor > 0**. Atualiza saldo e informa sucesso/erro.
- **`sacar(valor)`**: exige **valor > 0** e **saldo suficiente**. Atualiza e informa resultado.

> Observação: usei `double` por simplicidade, ciente de que em projetos reais de finanças o ideal é `BigDecimal` (evita imprecisão de ponto flutuante).

### 3.2 `GerenciaBanco` (classe principal)
```java
public class GerenciaBanco {
    public static void exibirMenu() { ... }  // imprime as opções de operação
    public static void main(String[] args) { ... } // fluxo da aplicação
}
```
- **Coleta dados pessoais** com `Scanner` (nome, sobrenome e CPF).  
- **Cria** uma `ContaBancaria` com saldo 0.  
- **Menu** com `do...while` e `switch...case`:  
  1. Consultar saldo  
  2. Realizar depósito  
  3. Realizar saque  
  4. Encerrar aplicação  

---

## 4) Fluxo de uso (exemplo real de execução)

```text
Informe seu nome: Ana
Informe seu sobrenome: Silva
Informe seu CPF: 123.456.789-00

=== Menu de Operações ===
1. Consultar saldo
2. Realizar depósito
3. Realizar saque
4. Encerrar aplicação
Escolha uma opção: 2
Informe o valor para depósito: 150
Depósito de R$ 150.0 realizado com sucesso.

=== Menu de Operações ===
1. Consultar saldo
2. Realizar depósito
3. Realizar saque
4. Encerrar aplicação
Escolha uma opção: 1
Saldo atual: R$ 150.0

=== Menu de Operações ===
1. Consultar saldo
2. Realizar depósito
3. Realizar saque
4. Encerrar aplicação
Escolha uma opção: 3
Informe o valor para saque: 200
Saldo insuficiente para realizar o saque.

=== Menu de Operações ===
1. Consultar saldo
2. Realizar depósito
3. Realizar saque
4. Encerrar aplicação
Escolha uma opção: 4
Encerrando aplicação... Obrigado por utilizar nosso banco, Ana!
```

---

## 5) Como executar

### Opção A — Pelo NetBeans
1. Abra o projeto.  
2. Clique com o botão direito na classe `GerenciaBanco` → **Run File** (ou **Run Project** se a `main` já estiver configurada no Maven/IDE).

### Opção B — Linha de comando (Maven)
```bash
# dentro da pasta do projeto
mvn -q -DskipTests package
java -cp target/classes com.mycompany.gerenciabanco.GerenciaBanco
```
> Se houver plugin para empacotamento/execução via `exec:java`, também é possível rodar com `mvn exec:java` configurando a `mainClass`.

---

## 6) Testes e verificações que eu fiz

- **Depósito com valor negativo ou zero** → mensagem de erro, saldo inalterado.  
- **Saque com valor negativo ou zero** → erro, saldo inalterado.  
- **Saque maior que o saldo** → mensagem “Saldo insuficiente”, saldo inalterado.  
- **Caminho feliz**: uma sequência de depósitos e saques válidos atualiza corretamente o saldo.  
- **Laço de repetição**: o menu só termina quando o usuário escolhe **opção 4**.

Checklist rápido (manual):
- [x] Criar conta (nome/sobrenome/CPF).  
- [x] Depositar 100 → saldo 100.  
- [x] Sacar 30 → saldo 70.  
- [x] Sacar 100 → erro (saldo insuficiente).  
- [x] Encerrar.

---

## 7) Limitações conhecidas e melhorias futuras

- **Precisão monetária:** trocar `double` por `BigDecimal` e formatar saída com `NumberFormat` (ex.: `R$ 10,00`).  
- **Validação de CPF:** hoje o CPF é apenas armazenado; posso implementar verificação de formato e dígitos.  
- **Persistência:** atualmente os dados vivem só durante a execução (memória). Uma melhoria seria salvar em **arquivo** ou **banco de dados**.  
- **Múltiplas contas/usuários:** permitir cadastro e login de várias contas.  
- **Tratamento de erros de entrada:** capturar `InputMismatchException` quando o usuário digitar letras no lugar de números.  
- **Interface:** evoluir de console para **Swing** ou **JavaFX**.

---

## 8) Estrutura do repositório (Git) e comprovação de autoria

- O projeto está versionado em Git e publicado no GitHub:  
  **Repositório:** `https://github.com/VituGit/gerenciaBanco` _(ajuste se o link for diferente no seu caso)_  
- Evidências de autoria:  
  - Histórico de commits com meu usuário.  
  - Datas/horários dos pushes.  
  - Código comentado por mim.  
  - (Opcional) Vídeo curto mostrando eu abrindo o projeto no NetBeans, executando e navegando pelas opções.

Exemplo de comandos Git que usei localmente:
```bash
git init
git add .
git commit -m "Commit inicial: estrutura do projeto e classes ContaBancaria/GerenciaBanco"
git remote add origin https://github.com/VituGit/gerenciaBanco.git
git push -u origin master   # ou main, conforme configuração
```

---

## 9) Diagrama simples (visão geral)

```text
+--------------------+           usa            +-------------------+
|  GerenciaBanco     |------------------------->|  ContaBancaria    |
|  - main()          |                         |  - nome           |
|  - exibirMenu()    |                         |  - sobrenome      |
|  (console + menu)  |                         |  - cpf            |
|                    |                         |  - saldo          |
|  Lê entrada, chama |                         |  + consultarSaldo |
|  depositar/sacar   |                         |  + depositar      |
|  e imprime saída   |                         |  + sacar          |
+--------------------+                         +-------------------+
```

---

## 10) Conclusão

O projeto cumpriu os objetivos da prática: **apliquei POO em Java** usando classes, métodos, encapsulamento e estruturas de controle, entregando uma aplicação funcional de console. Além disso, deixei **documentado** o processo de desenvolvimento e **versionado** o código no GitHub para comprovar a autoria.

> _Ass.:_ **Victor Pereira Gurgel** — **09/09/2025**
