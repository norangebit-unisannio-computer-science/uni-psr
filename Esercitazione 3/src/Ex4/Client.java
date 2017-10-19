package Ex4;

/*
 *  Author: Raffaele Mignone
 *  Mat: 863/747
 *  Date: 19/10/17
 *
 */

import java.net.Socket;

public class Client {
    public static void main(String[] args) throws Exception {
        try {
            Client sfc = new Client("127.0.0.1", 6789);
            sfc.start();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Client(String s, int port) throws Exception {
        clientSocket = new Socket(s, port);
    }

    public void start() throws Exception {
        ProtocolHandler ph = new ClientHandler(clientSocket);
        ph.handle();
    }

    private Socket clientSocket;
}