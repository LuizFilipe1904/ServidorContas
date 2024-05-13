package com.mycompany.servidor;

import java.io.*;
import java.net.*;

public class Servidor {

    public static void main(String[] args) throws IOException {
        ServerSocket servidor = new ServerSocket(55555); //Porta para conexão
        System.out.println("Servidor de contas!");
        
        //Mantém o servidor rodando
        while (true) {
            Socket socket = servidor.accept(); //Aguarda a conexao do cliente
            System.out.println("Cliente conectado: " + socket);
            
            //Thread para fazer os calculos, uma para cada cliente
            ThreadSockets thread = new ThreadSockets(socket);
            thread.start();
        }
    }
}
