package udp_clock;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Autor: Cristian Martins Fernandes
 * Data: 16/03/2026
 * Resumo: Servidor UDP que retorna a hora da região solicitada.
 */

public class UDPServer {

    public static void main(String[] args) {

        DatagramSocket socket = null;

        try {
            socket = new DatagramSocket(6789);
            byte[] buffer = new byte[1024];

            System.out.println("Servidor UDP rodando...");

            while (true) {

                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                socket.receive(request);

                String region = new String(request.getData(), 0, request.getLength());

                String response;

                try {
                    ZonedDateTime time = ZonedDateTime.now(ZoneId.of(region));
                    response = time.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss z"));
                } catch (Exception e) {
                    response = "Região inválida";
                }

                byte[] replyBytes = response.getBytes();

                DatagramPacket reply = new DatagramPacket(
                        replyBytes,
                        replyBytes.length,
                        request.getAddress(),
                        request.getPort());

                socket.send(reply);
            }

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            if (socket != null) socket.close();
        }
    }
}