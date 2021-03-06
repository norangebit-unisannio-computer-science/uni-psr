package ex4;

/*
 *  Author: Raffaele Mignone
    Mat: 863/747
 *  Date: 23/10/17
 *
 */


import ex2.ProtocolHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args){
        try{
            Server server = new Server(6789);
            server.start();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public Server(int port) throws Exception{
        welcomesocket = new ServerSocket(port);
    }

    public void start() throws IOException{
        while (true){
            Socket socket = welcomesocket.accept();
            ProtocolHandler server = new ServerHandler(socket);
            server.handle();
        }
    }


    private ServerSocket welcomesocket;
}

