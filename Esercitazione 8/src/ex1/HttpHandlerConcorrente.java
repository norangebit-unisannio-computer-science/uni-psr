package ex1;

/*
 *  Author: Raffaele Mignone
 *  Mat: 863/747
 *  Date: 07/11/17
 *
 */

import java.io.IOException;
import java.net.Socket;

public class HttpHandlerConcorrente extends HttpHandler implements Runnable {

	public HttpHandlerConcorrente(Socket socket, String root) {
		super(socket, root);
	}

	@Override
	public void handle() throws IOException {
		new Thread(this).start();
	}

	@Override
	public void run() {
		try{
			super.handle();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
