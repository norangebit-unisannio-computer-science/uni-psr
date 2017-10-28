package ex2;

import java.io.IOException;
import java.net.Socket;


public class ChatClient {

	public static void main(String[] args) {
		try {
			ChatClient chc = new ChatClient("127.0.0.1", 6789);
			chc.start();
		}catch(Exception e) {
			System.out.println(e);
		}
	}

	public ChatClient(String s, int port) throws Exception{
		clientSocket = new Socket(s, port);

	}
	public void start() throws IOException{
		ProtocolHandler ph = new ChatHandler(clientSocket);
		ph.handle();
	}

	private Socket clientSocket;
}
