package tcp_clock_multithread;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Autor: Cristian Martins Fernandes
 * Data: 16/03/2026
 * Resumo: Cliente TCP que solicita a hora de uma região.
 */

public class TCPClient {
    public static void main(String[] args) {

        try {

            Socket socket = new Socket("localhost", 6789);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            PrintWriter out = new PrintWriter(
                    socket.getOutputStream(), true);

            Scanner sc = new Scanner(System.in);

            System.out.print("Digite a região (ex: America/Sao_Paulo): ");
            String region = sc.nextLine();

            out.println(region);

            String response = in.readLine();

            System.out.println("Resposta: " + response);

            socket.close();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
