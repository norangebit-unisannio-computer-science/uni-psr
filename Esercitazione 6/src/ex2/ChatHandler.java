package ex2;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ChatHandler implements ProtocolHandler {
	
	public ChatHandler(Socket soc) {
		socket = soc;
	}
	
	public void handle() {

		System.out.print("Inserisci il tuo nome: ");
		String nome = new Scanner(System.in).nextLine();

		System.out.print("In quale stanza vuoi entrare: ");
		try{
			String nomeRoom = new Scanner(System.in).nextLine();
			new PrintStream(socket.getOutputStream()).println(nomeRoom);
		}catch (IOException e){
			e.printStackTrace();
		}


		Reader reader = new Reader(socket);
		Writer writer = new Writer(socket, nome);

		reader.start();
		writer.start();
	}
	

	private Socket socket;
}
