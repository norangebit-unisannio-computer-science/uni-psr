package Ex3;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

class DgramClientSum {
    public static void main(String args[]) throws Exception {
        int a, b, sum;

        Scanner inFromUser = new Scanner(System.in);

        DatagramSocket clientSocket = new DatagramSocket();

        InetAddress IPAddress = InetAddress.getLocalHost();

        byte[] sendData; // = new byte[1024];
        byte[] receiveData = new byte[1024];
        ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
        DataOutputStream shortsStreamToClient = new DataOutputStream(bufOut);

        System.out.println("Inserisci il primo numero: ");
        a = inFromUser.nextInt();
        System.out.println("Inserisci il secondo numero: ");
        b = inFromUser.nextInt();

        shortsStreamToClient.writeInt(a);
        shortsStreamToClient.writeInt(b);
        sendData = bufOut.toByteArray();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 1200);
        clientSocket.send(sendPacket);

        DatagramPacket recivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(recivePacket);

        ByteArrayInputStream bufIn = new ByteArrayInputStream(receiveData);
        DataInputStream in = new DataInputStream(bufIn);
        sum = in.readInt();

        System.out.println("La somma è: " + sum);
    }

} 
