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
import java.net.Socket;

public class ServerHandler implements ProtocolHandler{

    public ServerHandler(Socket socket){
        this.socket=socket;
    }

    @Override
    public void handle(){

        DataInputStream inClient = null;
        DataOutputStream outClient = null;


        try{
            inClient = new DataInputStream(socket.getInputStream());
            outClient = new DataOutputStream(socket.getOutputStream());

            int n = inClient.readInt();

            Fattoriale fat = new Fattoriale();

            int x=n/2;

            Thread t1 = new Thread(new Worker(fat, x, 0));
            Thread t2 = new Thread(new Worker(fat, n, x));


            t1.start();
            t2.start();
            t1.join();
            t2.join();

            outClient.writeInt(fat.getFattoriale());

        }catch (IOException e){
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if(socket!=null)
                try{
                socket.close();
                }catch (Exception e){}

            try{
            if (outClient!= null)
                outClient.close();
                }catch (Exception e){}
            }

    }

    private Socket socket;
}
