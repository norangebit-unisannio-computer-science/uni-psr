package ex2;


import ex1.ProtocolHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatHalfDuplexTCPServer  {

	public static void main(String[] args) {
		try {
			ChatHalfDuplexTCPServer chs = new ChatHalfDuplexTCPServer(6789);
			chs.start();
			
		}catch(Exception e) {
			System.out.println(e);
		}
		

	}
	
public ChatHalfDuplexTCPServer(int port) throws Exception{
	welcomeSocket = new ServerSocket(port);
	
}
public void start() throws IOException{
	Socket connectionSocket = welcomeSocket.accept();
	System.out.println("accettato");
	ProtocolHandler ph = new ChatHalfDuplexHandler(connectionSocket, ChatHalfDuplexHandler.PEER);
	ph.handle();
}
	
private ServerSocket welcomeSocket;

}
