package ex4;

/*
 *  Author: Raffaele Mignone
 *  Mat: 863/747
 *  Date: 23/10/17
 *
 */

import ex2.ProtocolHandler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler implements ProtocolHandler {

    public ClientHandler(Socket socket){
        this.socket=socket;
    }
    @Override
    public void handle() {
        DataOutputStream outServer = null;
        Scanner inUser = null;
        PrintStream outUser = null;
        DataInputStream inServer = null;

        try{
            inUser = new Scanner(System.in);
            outUser = System.out;
            inServer = new DataInputStream(socket.getInputStream());
            outServer = new DataOutputStream(socket.getOutputStream());

            outUser.println("Inserisci il numero: ");
            int n = inUser.nextInt();

            outServer.writeInt(n);

            outUser.println(inServer.readInt());

        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(socket!=null)
                try{
                    socket.close();
                }catch (Exception e){}
            outUser.close();
            if(inServer!=null)
                try{
                    inServer.close();
                }catch (Exception e){}

        }

    }

    private Socket socket;
}
