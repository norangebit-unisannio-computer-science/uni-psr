package Ex4;

/*
 *  Author: Raffaele Mignone
 *  Mat: 863/747
 *  Date: 19/10/17
 *
 */

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler implements ProtocolHandler {

    public ClientHandler(Socket socket){
        this.socket=socket;
    }

    @Override
    public void handle() {

        Scanner inUser = null;
        PrintStream outUser = null;
        DataInputStream inServer = null;
        DataOutputStream outServer = null;


        try{
            inUser = new Scanner(System.in);
            outUser = System.out;
            outServer = new DataOutputStream(socket.getOutputStream());

            outUser.println("Inserisci il nome: ");
            String nome = inUser.nextLine();
            outUser.println("Inserisci il cognome: ");
            String cognome = inUser.nextLine();
            outUser.println("Inserisci l'età: ");
            int età = inUser.nextInt();

            Persona p = new Persona(nome, cognome, età);

            ObjectOutputStream outObj = new ObjectOutputStream(outServer);
            outObj.writeObject(p);
            outObj.flush();

        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(socket != null)
                try{
                    socket.close();
                }catch (IOException e){}

            inUser.close();
            outUser.close();

            try{
                if(inServer != null)
                    inServer.close();
                if(outServer != null)
                    outServer.close();
            }catch (IOException e){}
        }

    }

    private Socket socket;
}
