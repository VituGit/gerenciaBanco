package com.mycompany.gerenciabanco;

import java.util.Scanner;

/**
 * Classe que representa a conta bancária de um usuário, armazenando dados pessoais
 * e permitindo operações de saldo, depósito e saque.
 */
class ContaBancaria {
    // Atributos do titular da conta (dados pessoais e saldo)
    private String nome;
    private String sobrenome;
    private String cpf;
    private double saldo;

    // Construtor para inicializar a conta com dados do usuário e saldo inicial zero
    public ContaBancaria(String nome, String sobrenome, String cpf) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.saldo = 0.0;
    }

    // Método para consultar o saldo atual da conta
    public double consultarSaldo() {
        return this.saldo;
    }

    // Método para depositar um valor na conta
    public void depositar(double valor) {
        if (valor > 0) {
            this.saldo += valor;
            System.out.println("Depósito de R$ " + valor + " realizado com sucesso.");
        } else {
            System.out.println("Valor de depósito inválido.");
        }
    }

    // Método para sacar um valor da conta
    public void sacar(double valor) {
        if (valor > 0) {
            if (valor <= this.saldo) {
                this.saldo -= valor;
                System.out.println("Saque de R$ " + valor + " realizado com sucesso.");
            } else {
                System.out.println("Saldo insuficiente para realizar o saque.");
            }
        } else {
            System.out.println("Valor de saque inválido.");
        }
    }
}

/**
 * Classe principal da aplicação de gerenciamento bancário.
 * Contém o método main que inicia a aplicação e gerencia a interação com o usuário.
 */
public class GerenciaBanco {

    // Método responsável por exibir o menu de opções na tela
    public static void exibirMenu() {
        System.out.println("\n=== Menu de Operações ===");
        System.out.println("1. Consultar saldo");
        System.out.println("2. Realizar depósito");
        System.out.println("3. Realizar saque");
        System.out.println("4. Encerrar aplicação");
        System.out.print("Escolha uma opção: ");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Solicita os dados pessoais do usuário
        System.out.print("Informe seu nome: ");
        String nome = scanner.nextLine();

        System.out.print("Informe seu sobrenome: ");
        String sobrenome = scanner.nextLine();

        System.out.print("Informe seu CPF: ");
        String cpf = scanner.nextLine();

        // Cria uma nova conta bancária para o usuário com saldo inicial 0
        ContaBancaria conta = new ContaBancaria(nome, sobrenome, cpf);

        int opcao;
        // Loop do...while para exibir o menu repetidamente até o usuário escolher sair
        do {
            // Exibe o menu de opções e lê a escolha do usuário
            exibirMenu();
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    // Consulta e exibe o saldo atual da conta
                    double saldoAtual = conta.consultarSaldo();
                    System.out.println("Saldo atual: R$ " + saldoAtual);
                    break;
                case 2:
                    // Realiza um depósito na conta
                    System.out.print("Informe o valor para depósito: ");
                    double valorDeposito = scanner.nextDouble();
                    conta.depositar(valorDeposito);
                    break;
                case 3:
                    // Realiza um saque da conta
                    System.out.print("Informe o valor para saque: ");
                    double valorSaque = scanner.nextDouble();
                    conta.sacar(valorSaque);
                    break;
                case 4:
                    // Encerra a aplicação com mensagem de despedida
                    System.out.println("Encerrando aplicação... Obrigado por utilizar nosso banco, " + nome + "!");
                    break;
                default:
                    // Caso a opção digitada não corresponda a nenhuma válida
                    System.out.println("Opção inválida. Tente novamente.");
            }
            // O loop continua enquanto a opção escolhida for diferente de 4
        } while (opcao != 4);

        scanner.close();
    }
}
