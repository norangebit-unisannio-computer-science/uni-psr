package Ex5;

/*
 *  Author: Raffaele Mignone
 *  Mat: 863/747
 *  Date: 19/10/17
 *
 */

import Ex4.ProtocolHandler;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class ClientHandler implements ProtocolHandler {

    public ClientHandler(Socket socket){
        this.socket=socket;
    }
    @Override
    public void handle() {
        PrintStream outUser = null;
        DataInputStream inServer = null;

        try{
            outUser = System.out;
            inServer = new DataInputStream(socket.getInputStream());

            long fileLen = inServer.readLong();

            byte[] buf = new byte[1024];

            int count = 0;

            while(count < fileLen){
                int n = inServer.read(buf);
                outUser.write(buf, 0, n);
                count += n;
            }



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
