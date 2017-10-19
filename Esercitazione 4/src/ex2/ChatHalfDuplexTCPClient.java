package ex2;

import ex1.ProtocolHandler;

import java.io.IOException;
import java.net.Socket;


public class ChatHalfDuplexTCPClient {

	public static void main(String[] args) {
	try {
		ChatHalfDuplexTCPClient chc = new ChatHalfDuplexTCPClient("127.0.0.1", 6789);
		chc.start();
	}catch(Exception e) {
		System.out.println(e);
	}

	}
public ChatHalfDuplexTCPClient(String s, int port) throws Exception{
	clientSocket = new Socket(s, port);
	
}
public void start() throws IOException{
	ProtocolHandler ph = new ChatHalfDuplexHandler(clientSocket, ChatHalfDuplexHandler.YOU);
	ph.handle();
}
private Socket clientSocket;
}
