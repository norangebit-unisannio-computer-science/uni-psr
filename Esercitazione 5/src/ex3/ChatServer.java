package ex3;


import ex2.ProtocolHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {

	public static void main(String[] args) {
		try {
			ChatServer chs = new ChatServer(6789);
			chs.start();
			
		}catch(Exception e) {
			System.out.println(e);
		}
		

	}
	
	public ChatServer(int port) throws Exception{
		welcomeSocket = new ServerSocket(port);

	}
	public void start() throws IOException{
		Socket connectionSocket = welcomeSocket.accept();
		ProtocolHandler ph = new ChatHandler(connectionSocket);
		ph.handle();
	}

	private ServerSocket welcomeSocket;

}
