package ex2;

/*
 *  Author: Raffaele Mignone
 *  Mat: 863/747
 *  Date: 27/10/17
 *
 */

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.HashMap;

public class Room {

	public Room() {
		clients = new HashMap<Integer, PrintStream>();
	}

	public synchronized void addSocket(Socket s) throws IOException{
		clients.put(s.hashCode(), new PrintStream(s.getOutputStream()));
	}

	public synchronized void broadcast(String str, int hash) {
		for(int i: clients.keySet())
			if(i!=hash)
				clients.get(i).println(str);
	}

	private HashMap<Integer, PrintStream> clients;


}
