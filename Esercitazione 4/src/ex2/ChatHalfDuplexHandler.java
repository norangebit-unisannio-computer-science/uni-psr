package ex2;

import ex1.ProtocolHandler;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ChatHalfDuplexHandler implements ProtocolHandler {

	public static void main(String[] args) {
		

	}
	
	public  ChatHalfDuplexHandler(Socket soc, byte is) {
		socket = soc;
		status = is;
	}
	
	public void handle() {
		Scanner inFromUser = null, inFromPeer = null;
		PrintStream outToUser = null, outToPeer = null;
		try {
			inFromUser = new Scanner(System.in);
			outToUser = System.out;
			inFromPeer = new Scanner(socket.getInputStream());
			outToPeer = new PrintStream(socket.getOutputStream());
			String str = null;
			char lastChar = ' ';
			while(status!=EXIT) {
				switch(status) {
					case YOU:
						do {
							outToUser.println("You: ");
							str = inFromUser.nextLine();
							outToPeer.println(str);
							lastChar = str.charAt(str.length()-1);
							if(lastChar == PASS) status = PEER;
							else if(lastChar == END) status = EXIT;

						}while(status ==YOU);
						break;
					case PEER:
						do {
							outToUser.print("Peer: ");
							str = inFromPeer.nextLine();
							outToUser.println(str);
							lastChar = str.charAt(str.length()-1);
							if(lastChar == PASS) status = YOU;
							else if(lastChar == END) status = EXIT;

						}while(status ==PEER);
						break;

				}
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		finally {
			if(socket!=null)
				try {
					socket.close();
			} catch (IOException e) {
				e.printStackTrace();
				inFromUser.close(); outToUser.close();
				inFromPeer.close(); outToPeer.close();
			}
		}
	}	
	
	

private Socket socket;
private byte status;
public static final byte YOU = 0;
public static final byte PEER = 1;
public static final byte EXIT = 2;
public static final byte PASS = '-';
public static final byte END = '.';


}
