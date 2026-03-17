package tcp_clock_simple;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Autor: Cristian Martins Fernandes
 * Data: 16/03/2026
 * Resumo: Servidor TCP simples que retorna a hora da região solicitada.
 */

public class TCPServer {
    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(6789)) {

            System.out.println("Servidor TCP simples rodando...");

            while (true) {

                Socket clientSocket = serverSocket.accept();

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));

                PrintWriter out = new PrintWriter(
                        clientSocket.getOutputStream(), true);

                String region = in.readLine();
                String response;

                try {
                    ZonedDateTime time = ZonedDateTime.now(ZoneId.of(region));
                    response = time.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss z"));
                } catch (Exception e) {
                    response = "Região inválida";
                }

                out.println(response);

                clientSocket.close();
            }

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}