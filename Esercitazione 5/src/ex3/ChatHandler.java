package ex3;

import ex2.ProtocolHandler;

import java.net.Socket;

public class ChatHandler implements ProtocolHandler {

	public static void main(String[] args) {
		

	}
	
	public ChatHandler(Socket soc) {
		socket = soc;
	}
	
	public void handle() {
		Reader reader = new Reader(socket);
		Writer writer = new Writer(socket);

		reader.start();
		writer.start();
	}	
	
	

	private Socket socket;
}
