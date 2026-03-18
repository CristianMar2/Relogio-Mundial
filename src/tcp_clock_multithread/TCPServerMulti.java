package tcp_clock_multithread;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * Autor: Cristian Martins Fernandes
 * Data: 16/03/2026
 * Resumo: Servidor TCP concorrente usando múltiplas threads.
 */

public class TCPServerMulti {
    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(6789)) {

            System.out.println("Servidor TCP Multithread rodando...");

            while (true) {

                Socket client = serverSocket.accept();

                Thread t = new Thread(new ClientHandler(client));

                t.start();
            }

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
