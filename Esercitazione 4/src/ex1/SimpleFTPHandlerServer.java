package ex1;

import java.io.*;
import java.net.Socket;

public class SimpleFTPHandlerServer implements ProtocolHandler{

    public SimpleFTPHandlerServer(Socket socket){
        this.socket = socket;
    }


    @Override
    public void handle() {
        DataInputStream fromClient = null;
        DataOutputStream toClient = null;
        try{
            fromClient = new DataInputStream(socket.getInputStream());
            toClient = new DataOutputStream(socket.getOutputStream());
            File f = null;
            int command = 0;
            do{
                command = fromClient.read();
                switch (command){
                    case LIST:
                        f = new File(".");
                        String[] list = f.list();
                        toClient.writeInt(list.length);
                        for(int i=0; i<list.length; i++)
                            toClient.writeUTF(list[i]);
                        break;
                    case GET:
                        String fileName = fromClient.readUTF();
                        f = new File(fileName);
                        if(!f.exists())
                            toClient.writeLong(0);
                        else{
                            long fileLength = f.length();
                            toClient.writeLong(fileLength);

                            FileInputStream fis = new FileInputStream(f);
                            byte[] buf = new byte[1024];
                            int count = 0;

                            while (count < fileLength){
                                int n = fis.read(buf);
                                toClient.write(buf, 0, n);
                                count += n;
                            }

                            fis.close();
                        }
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
            try{
                if(fromClient != null)
                    fromClient.close();
                if(toClient != null)
                    toClient.close();
            }catch (IOException e){}
        }
    }

    private Socket socket;

    private static final byte LIST = 0;
    private static final byte GET = 1;
    private static final byte CLOSE = 2;
}
