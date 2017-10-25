package ex3;

/*
 *  Author: Raffaele Mignone
 *  Mat: 863/747
 *  Date: 23/10/17
 *
 */

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Reader extends Thread {

    public Reader(Socket socket){
        this.socket = socket;
    }

    public void run(){
        Scanner inPeer = null;
        PrintStream outUser = null;
        try{
            inPeer = new Scanner(socket.getInputStream());
            outUser = System.out;
            String str = null;
            do{
                str = inPeer.nextLine();
                outUser.println("peer: "+str);
            }while(!str.endsWith("."));
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            inPeer.close();
        }
    }

    private Socket socket;
}
