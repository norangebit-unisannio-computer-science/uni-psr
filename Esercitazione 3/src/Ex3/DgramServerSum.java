package Ex3;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

class DgramServerSum {
    public static void main(String args[]) throws Exception {

        DatagramSocket serverSocket = new DatagramSocket(1200);

        byte[] receiveData = new byte[1024];
        byte[] sendData;//  = new byte[1024];
        int a, b;
        int somma = 0;

        while (true) {
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            ByteArrayInputStream buffromClient = new ByteArrayInputStream(receivePacket.getData());
            DataInputStream shortStreamfromClinet = new DataInputStream(buffromClient);
            a = shortStreamfromClinet.readInt();
            b = shortStreamfromClinet.readInt();
            somma = a + b;

            ByteArrayOutputStream bufToClient = new ByteArrayOutputStream();
            DataOutputStream shortStreamToClinet = new DataOutputStream(bufToClient);
            shortStreamToClinet.writeInt(somma);
            sendData = bufToClient.toByteArray();
            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();
			
			DatagramPacket sendPacket = new	DatagramPacket(sendData, sendData.length, IPAddress, port); 
			
			serverSocket.send(sendPacket);
        }
    }
}  
