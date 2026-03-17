package udp_clock;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.util.Scanner;

/**
 * Autor: Cristian Martins Fernandes
 * Data: 16/03/2026
 * Resumo: Cliente UDP que solicita a hora de uma região.
 */

public class UDPClient {

    public static void main(String[] args) {

        DatagramSocket socket = null;

        try {

            socket = new DatagramSocket();
            socket.setSoTimeout(5000);

            Scanner sc = new Scanner(System.in);

            System.out.print("Digite a região (ex: America/Sao_Paulo): ");
            String region = sc.nextLine();

            byte[] message = region.getBytes();

            InetAddress serverHost = InetAddress.getByName("localhost");

            DatagramPacket request =
                    new DatagramPacket(message, message.length, serverHost, 6789);

            socket.send(request);

            byte[] buffer = new byte[1024];
            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);

            socket.receive(reply);

            System.out.println("Resposta: " +
                    new String(reply.getData(), 0, reply.getLength()));

        } catch (SocketTimeoutException e) {
            System.out.println("Servidor ocupado ou offline");

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            if (socket != null) socket.close();
        }
    }
}