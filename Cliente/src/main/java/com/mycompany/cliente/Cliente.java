package com.mycompany.cliente;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        try {
            Socket socket = new Socket("127.0.0.1", 55555);
            System.out.println("Conectado ao servidor");

            //Saidas do cliente para o servidor (operacao, operando1, operando2) 
            DataOutputStream operacao = new DataOutputStream(socket.getOutputStream());
            DataOutputStream numero1 = new DataOutputStream(socket.getOutputStream());
            DataOutputStream numero2 = new DataOutputStream(socket.getOutputStream());

            //Entrada que o cliente recebe do servidor (resultado)
            DataInputStream entrada = new DataInputStream(socket.getInputStream());

            double resultado;

            System.out.println("""
                               Operacoes disponiveis:
                               +
                               -
                               *
                               /""");

            System.out.println("Escolha a operacao: ");
            operacao.writeUTF(teclado.nextLine()); //Envia a operação para o servidor

            System.out.println("Digite o primeiro numero: ");
            numero1.writeDouble(teclado.nextDouble()); //Envia o primeiro operando para o servidor

            System.out.println("Digite o segundo numero");
            numero2.writeDouble(teclado.nextDouble()); //Envia o segundo operando para o servidor

            resultado = entrada.readDouble(); //Recebe o resultado do servidor 

            System.out.println("Resultado: " + resultado);
            socket.close();

        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
