package com.mycompany.servidor;

import java.io.*;
import java.net.*;

public class ThreadSockets extends Thread {

    private final Socket socket;

    public ThreadSockets(Socket s) {
        this.socket = s;
    }

    @Override
    public void run() {
        try {
            //Streams de entrada do servidor (operacao, operando1, operando2)
            DataInputStream entradaOperacao = new DataInputStream(socket.getInputStream());
            DataInputStream entradaNumero1 = new DataInputStream(socket.getInputStream());
            DataInputStream entradaNumero2 = new DataInputStream(socket.getInputStream());
            String operacao = entradaOperacao.readUTF();
            double numero1 = entradaNumero1.readDouble();
            double numero2 = entradaNumero2.readDouble();

            //Stream de saída do servidor (resultado)
            DataOutputStream saida = new DataOutputStream(socket.getOutputStream());
            double resultado;
            String erro = "Erro: operacao Invalida";

            switch (operacao) {
                case "+":
                    resultado = numero1 + numero2;
                    saida.writeDouble(resultado); //Envia o resultado para o cliente
                    break;

                case "-":
                    resultado = numero1 - numero2;
                    saida.writeDouble(resultado); //Envia o resultado para o cliente
                    break;

                case "*":
                    resultado = numero1 * numero2;
                    saida.writeDouble(resultado); //Envia o resultado para o cliente
                    break;

                case "/":
                    if (numero2 != 0) {
                        resultado = numero1 / numero2;
                        saida.writeDouble(resultado); //Envia o resultado para o cliente
                    } else {
                        resultado = 0;
                        saida.writeDouble(resultado); //Envia o resultado para o cliente
                    }
                    break;

                case "SAIR":
                    System.out.println("Cliente desconectado: " + socket);
                    socket.close();
                    break;

                default:
                    saida.writeUTF(erro); //envia a mensagem de erro para o cliente
                    System.out.println("Erro: Operacao Invalida");

            }

        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
