package Ex4;

/*
 *  Author: Raffaele Mignone
 *  Mat: 863/747
 *  Date: 19/10/17
 *
 */

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ServerHandler implements ProtocolHandler {

    public  ServerHandler(Socket socket){
        this.socket = socket;
    }

    @Override
    public void handle() {

        DataOutputStream outUser = null;
        DataInputStream inServer = null;

        try{
            FileOutputStream outFile = new FileOutputStream("out.txt");
            inServer = new DataInputStream(socket.getInputStream());
            ObjectInputStream inObj = new ObjectInputStream(inServer);
            Persona p = (Persona)inObj.readObject();
            outUser = new DataOutputStream(outFile);
            outUser.writeUTF(p.toString());
            outFile.close();

        }catch (FileNotFoundException e){

        }catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                if(socket!=null)
                    socket.close();
            }catch (Exception e){}
            try {
                if(outUser!=null)
                    outUser.close();
                if(inServer!=null)
                    inServer.close();
            }catch (Exception e){}
        }


    }

    public Socket socket;
}
