package Ex5;

/*
 *  Author: Raffaele Mignone
 *  Mat: 863/747
 *  Date: 19/10/17
 *
 */

import Ex4.ProtocolHandler;

import java.io.*;
import java.net.Socket;

public class ServerHandler implements ProtocolHandler {

    public  ServerHandler(Socket socket){
        this.socket=socket;
    }
    @Override
    public void handle() {

        FileInputStream inFile = null;
        DataOutputStream outClient = null;

        try{
            outClient = new DataOutputStream(socket.getOutputStream());
            File f = new File("out.txt");
            inFile = new FileInputStream(f);
            long fileLen = f.length();

            outClient.writeLong(fileLen);

            byte buf[] = new byte[1024];
            int count = 0;

            while (count < fileLen){
                int n = inFile.read(buf);
                outClient.write(buf, 0, n);
                count += n;
            }

        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(socket!=null)
                try{
                    socket.close();
                }catch (Exception e){}

            try{
                if(inFile!=null)
                    inFile.close();
                if (outClient!= null)
                    outClient.close();
            }catch (Exception e){}
        }

    }

    private Socket socket;
}
