package ex1;

import java.net.Socket;

public class SimpleFTPClient {

    public static void main(String[] args) throws Exception{
        try{
            SimpleFTPClient sfc = new SimpleFTPClient("127.0.0.1", 6789);
            sfc.start();
        } catch(Exception e){
            System.out.println(e);
        }
    }

    public SimpleFTPClient(String s, int port) throws Exception {
        clientSocket = new Socket(s, port);
    }

    public void start() throws Exception{
        ProtocolHandler ph = new SimpleFTPHandlerClient(clientSocket);
        ph.handle();
    }

    private Socket clientSocket;
}
