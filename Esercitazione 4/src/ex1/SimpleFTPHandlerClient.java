package ex1;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class SimpleFTPHandlerClient implements ProtocolHandler{

    public SimpleFTPHandlerClient(Socket socket){
        this.socket = socket;
    }

    @Override
    public void handle() {
        DataInputStream fromServer = null;
        DataOutputStream toServer = null;
        Scanner fromUser = null;
        PrintStream toUser = null;
        try{
            fromServer = new DataInputStream(socket.getInputStream());
            toServer = new DataOutputStream(socket.getOutputStream());
            fromUser = new Scanner(System.in);
            toUser = System.out;
            int command = LIST;
            do{
                toUser.print("Inserisci il comando: ");
                String commandLine = fromUser.nextLine();

                if(commandLine.equalsIgnoreCase("get"))
                    command = GET;
                else if(commandLine.equalsIgnoreCase("list"))
                    command = LIST;
                else if(commandLine.equalsIgnoreCase("close"))
                    command = CLOSE;

                toServer.write(command);
                switch (command){
                    case LIST:
                        int listLength = fromServer.readInt();
                        for(int i=0; i<listLength; i++)
                            toUser.println(fromServer.readUTF());
                        break;
                    case GET:
                        toUser.print("Inserisci il nome del file: ");
                        String fileName = fromUser.nextLine();
                        toServer.writeUTF(fileName);

                        long fileLength = fromServer.readLong();
                        if(fileLength == 0)
                            break;
                        int count = 0;
                        FileOutputStream fos = new FileOutputStream(fileName+".back");
                        byte[] buf = new byte[1024];

                        while(count < fileLength){
                            int n = fromServer.read(buf);
                            fos.write(buf, 0, n);
                            count += n;
                        }

                        toUser.println("File trasferito con successo");
                        break;
                }
            }while(command != CLOSE);
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            if(socket != null)
                try{
                    socket.close();
                }catch (IOException e){}

            fromUser.close();
            toUser.close();
            try{
                if(fromServer != null)
                    fromServer.close();
                if (toServer != null)
                    toServer.close();
            }catch (IOException e){}
        }
    }

    private Socket socket;

    private static final byte LIST = 0;
    private static final byte GET = 1;
    private static final byte CLOSE = 2;
}
