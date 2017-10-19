package ex1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleFTPServer {
    public static void main(String[] args) throws Exception{
        try{
            SimpleFTPServer sfs = new SimpleFTPServer(6789);
            sfs.start();
        } catch(Exception e){
            System.out.println(e);
        }
    }

    public SimpleFTPServer(int port) throws Exception{
        welcomeSocket=new ServerSocket(port);
    }

    public void start() throws IOException {
        while(true){
            Socket connectSocket = welcomeSocket.accept();
            ProtocolHandler ph = new SimpleFTPHandlerServer(connectSocket);
            ph.handle();
        }
    }
    private ServerSocket welcomeSocket;
}
