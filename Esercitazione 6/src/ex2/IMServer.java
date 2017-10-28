package ex2;

/*
 *  Author: Raffaele Mignone
 *  Mat: 863/747
 *  Date: 27/10/17
 *
 */

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;

public class IMServer {

    public static void main(String[] args) {
        try {
            IMServer server = new IMServer(6789);
            server.start();
        }catch(Exception e) {
            System.out.println(e);
        }
    }

    public IMServer(int port) throws Exception{
        welcomeSocket = new ServerSocket(port);
        rooms = new HashMap<String, Room>();

    }

    public void start() throws IOException{
        while(true){
            Socket connectionSocket = welcomeSocket.accept();
            Room room = selectionRoom(connectionSocket);
            room.addSocket(connectionSocket);
            ProtocolHandler ph = new IMServerHandler(connectionSocket, room);
            ph.handle();
        }

    }

    private Room selectionRoom(Socket socket) throws IOException{
        String nomeRoom = new Scanner(socket.getInputStream()).nextLine();
        if(!rooms.containsKey(nomeRoom))
            rooms.put(nomeRoom, new Room(nomeRoom));

        return rooms.get(nomeRoom);
    }

    private ServerSocket welcomeSocket;
    private HashMap<String, Room> rooms;

}
