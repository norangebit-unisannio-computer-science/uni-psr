package ex1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {
    public static void main(String[] args) throws Exception{
        try{
            WebServer server = new WebServer();
            server.start();
        } catch(Exception e){
            System.out.println(e);
        }
    }

    public WebServer() throws Exception{
    }

    public void start() throws IOException {
        ServerSocket welcomeSocket = new ServerSocket(port);
        while(true){
            Socket connectSocket = welcomeSocket.accept();
            ProtocolHandler ph = new ServerHandler(connectSocket, root);
            try{
                ph.handle();
            }catch(Exception e){}

        }
    }

    private int port = 7890;
    private String root = "./res";
}
