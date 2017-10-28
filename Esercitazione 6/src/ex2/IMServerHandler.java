package ex2;

/*
 *  Author: Raffaele Mignone
 *  Mat: 863/747
 *  Date: 27/10/17
 *
 */

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class IMServerHandler extends Thread implements ProtocolHandler {

	public IMServerHandler(Socket soc, Room room) {
		socket = soc;
		this.room = room;
	}

	public void handle() {
		start();
	}

	@Override
	public void run() {
		try{
			Scanner inClient = new Scanner(socket.getInputStream());
			while (true){
				String str = inClient.nextLine();
				room.broadcast(str, socket.hashCode());
			}
		}catch (IOException e){
			e.printStackTrace();
		}

	}

	private Socket socket;
	private Room room;
}
