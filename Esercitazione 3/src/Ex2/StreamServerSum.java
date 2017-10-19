package Ex2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


class StreamServerSum {

    public static void main(String argv[]) throws Exception {
        int sum;

        ServerSocket welcomeSocket = new ServerSocket(6789);

        while (true) {

            Socket connectionSocket = welcomeSocket.accept();

            DataInputStream inFromClient = new DataInputStream(connectionSocket.getInputStream());
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

            sum = inFromClient.readInt();
            sum += inFromClient.readInt();


            outToClient.writeInt(sum);

            inFromClient.close();
            outToClient.close();
            connectionSocket.close();
        }
    }
} 
