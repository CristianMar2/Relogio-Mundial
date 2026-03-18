package tcp_clock_multithread;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Autor: Cristian Martins Fernandes
 * Data: 16/03/2026
 * Resumo: Thread responsável por atender um cliente específico.
 */

public class ClientHandler implements Runnable {

    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {

        try {

            System.out.println(
                    "Thread " + Thread.currentThread().getName() +
                            " atendendo cliente " +
                            socket.getInetAddress() + ":" + socket.getPort());

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            PrintWriter out = new PrintWriter(
                    socket.getOutputStream(), true);

            String region = in.readLine();
            String response;

            try {
                ZonedDateTime time = ZonedDateTime.now(ZoneId.of(region));
                response = time.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss z"));
            } catch (Exception e) {
                response = "Região inválida";
            }

            out.println(response);

            socket.close();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
