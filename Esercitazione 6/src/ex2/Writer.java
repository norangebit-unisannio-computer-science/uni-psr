package ex2;

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

public class Writer extends Thread {

    public Writer(Socket socket, String nome){
        this.socket = socket;
        this.nome = nome;
    }

    public void run(){
        Scanner inUser =  null;
        PrintStream outPeer = null;
        try{
            inUser = new Scanner(System.in);
            outPeer = new PrintStream(socket.getOutputStream());
            String str = null;
            do{
                str = inUser.nextLine();
                outPeer.println(nome+": "+str);
            } while(!str.endsWith("."));
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            try{
                socket.shutdownOutput();
            }catch (IOException e){}
            if(inUser!=null)
                inUser.close();
        }

    }

    private Socket socket;
    private String nome;
}
