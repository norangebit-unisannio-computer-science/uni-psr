package ex1;

import java.io.*;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ServerHandler implements ProtocolHandler{

    public ServerHandler(Socket socket, String root){
        this.socket = socket;
        this.root = root;
    }


    @Override
    public void handle() {
        Scanner inClient = null;

        try{
            inClient = new Scanner(socket.getInputStream());
            String requestLine = inClient.nextLine();
            System.out.println("\n"+requestLine);
            String line;
            do{
                line = inClient.nextLine();
                System.out.println(line);
            }while(!line.equals(""));

            StringTokenizer token = new StringTokenizer(requestLine);
            String method = token.nextToken();
            String relativePath = token.nextToken();

            String absolutePath = root + relativePath;

            String statusLine = "";
            String dateLine = "";
            String connectionLine = "Connection: close"+CRLF;
            String contentTypeLine = "";
            String cacheControl = "";
            String lastModified = "";
            String entryBody = "";

            File file = new File(absolutePath);
            if(file.exists()){
                statusLine = "HTTP/1.1 200 OK"+CRLF;
                contentTypeLine = "Content-Type: "+contentType(relativePath)+CRLF;
                cacheControl = "Cache-Control: private, max-age=60"+CRLF;
                lastModified = "Last-Modified "+new Date(file.lastModified())+CRLF;
            }else{
                statusLine = "HTTP/1.1 404 Not Found"+CRLF;
                contentTypeLine = "Content-Type: text/html"+CRLF;
                entryBody = "<HTML>" + "<HEAD> <TITLE>Not Found</TITLE> </HEAD>" +"<BODY>404 pagina non trovata</BODY>" + "</HTML>"+CRLF;
            }

            DataOutputStream outClient = new DataOutputStream(socket.getOutputStream());

            outClient.writeBytes(statusLine);
            outClient.writeBytes(connectionLine);

            outClient.writeBytes(contentTypeLine);


            if(file.exists()){
                outClient.writeBytes(cacheControl);
                outClient.writeBytes(lastModified);
                outClient.writeBytes(CRLF);
                sendFile(file, outClient);
            }else{
                outClient.writeBytes(CRLF);
                outClient.writeBytes(entryBody);
            }

            socket.close();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void sendFile(File file, OutputStream out){
        FileInputStream inFile = null;
        try{
            inFile = new FileInputStream(file);
            byte[] buf = new byte[1024];
            int bytes = 0;
            while (bytes<file.length()){
                int n =inFile.read(buf);
                out.write(buf, 0, n);
                bytes +=n;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String contentType(String path){
        if(path.endsWith(".html"))
            return "text/html";
        if(path.endsWith(".jpeg"))
            return "image/jpeg";

        return null;
    }

    private Socket socket;
    private String root;
    private final static String CRLF = "\r\n";
}
