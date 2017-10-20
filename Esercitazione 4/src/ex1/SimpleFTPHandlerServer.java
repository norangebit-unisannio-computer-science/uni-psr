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
            String dir = "";
            int command = 0;
            do{
                command = fromClient.read();
                switch (command){
                    case LIST:
                        if(dir.equals(""))
                            f = new File(".");
                        else
                            f = new File(dir);
                        String[] list = f.list();
                        toClient.writeInt(list.length);
                        for(int i=0; i<list.length; i++)
                            toClient.writeUTF(list[i]);
                        break;
                    case GET:
                        String fileName = fromClient.readUTF();
                        f = new File(dir+fileName);
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
                    case CD:
                        String newDir = fromClient.readUTF();
                        if(!newDir.equals(".."))
                            dir = dir.concat(newDir+"/");
                        else{
                            int i=dir.length()-2;
                            for(; i>0 && dir.charAt(i)!='/'; i--){}
                            dir = dir.substring(0, i);
                            dir = dir.concat("/");
                        }
                        break;
                    case PWD:
                        toClient.writeUTF(dir);
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
    private static final byte CD = 3;
    private static final byte PWD = 4;
}
