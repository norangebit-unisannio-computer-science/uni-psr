package Ex2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

class StreamClientSum {

    public static void main(String argv[]) throws Exception {
        int a, b, sum;
        Scanner inFromUser = new Scanner(System.in);

        Socket clientSocket = new Socket("127.0.0.1", 6789);

        DataOutputStream outServer = new DataOutputStream(clientSocket.getOutputStream());
        DataInputStream inServer = new DataInputStream(clientSocket.getInputStream());

        System.out.println("Inserisci il primo numero: ");
        a = inFromUser.nextInt();
        outServer.writeInt(a);
        System.out.println("Inserisci il secondo numero: ");
        b = inFromUser.nextInt();
        outServer.writeInt(b);


        sum = inServer.readInt();

        System.out.println("La somma è: " + sum);

        inFromUser.close();
        inServer.close();
        outServer.close();
        clientSocket.close();
    }
} 

