package ex2;

/*
 *  Author: Raffaele Mignone
 *  Mat: 863/747
 *  Date: 23/10/17
 *
 */

import java.net.Socket;

public class ConcurrentServerHandler extends ServerHandler implements Runnable{
    public ConcurrentServerHandler(Socket socket) {
        super(socket);
    }

    public void handle(){
        new Thread(this).start();
    }

    public void run(){
        super.handle();
    }
}
