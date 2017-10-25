package ex2;

/*
 *  Author: Raffaele Mignone
 *  Mat: 863/747
 *  Date: 23/10/17
 *
 */

import java.io.*;
import java.net.Socket;

public class ServerHandler implements ProtocolHandler{

    public  ServerHandler(Socket socket){
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

           int fat=1;

           while(n!=1)
               fat*=n--;

           outClient.writeInt(fat);

        }catch (IOException e){
            e.printStackTrace();
        }finally {
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
