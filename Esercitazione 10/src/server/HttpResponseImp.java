package server;

/*
 *  Author: Raffaele Mignone
 *  Mat: 863/747
 *  Date: 12/11/17
 *
 */

import webletApi.HttpResponse;

import java.io.*;
import java.util.Date;

public class HttpResponseImp implements HttpResponse {

	public HttpResponseImp(OutputStream out) {
		this.out = out;
		this.pw = new PrintWriter(out);
	}

	@Override
	public OutputStream getOutputStream() throws IOException {
		return out;
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		return pw;
	}

	@Override
	public void setContentType(String cType) {
		contentTypeLine+=cType+CRLF;
		pw.write(contentTypeLine);
	}

	@Override
	public void setContentLength(int cLength) {
		contentLengthLine+=cLength;
		pw.write(contentLengthLine);
	}

	@Override
	public void flushHeader() throws IOException {
		pw.write(connectionLine);
		pw.print(CRLF);
		pw.flush();
	}

	@Override
	public void setConnection(String connection) {
		connectionLine+=connection+CRLF;
	}

	@Override
	public void setStatusLine(String status) {
		statusLine = status+CRLF;
		pw.write(statusLine);
	}

	@Override
	public void sendFile(File file) throws Exception{

		if(file.exists()){
			// Impiega un buffer di 1KB per recuperare blocchi di
			// byte dal file ed inviarli sullo stream di rete
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(file);
				byte[] buffer = new byte[1024];
				int bytes = 0;
				// Copia i blocchi dal file allo stream di output fino alla fine del file
				while((bytes = fis.read(buffer)) != -1 )
					out.write(buffer, 0, bytes);
			}
			finally {
				if (fis != null)
					fis.close();
			}
		}else{
			PrintStream ps = new PrintStream(out);
			ps.print(entityBody);
		}
		out.close();
	}

	@Override
	public void setlastModifiedLine(File file) {
		lastModifiedLine+= new Date(file.lastModified()) + CRLF;
		pw.write(lastModifiedLine);
	}

	private OutputStream out;
	private PrintWriter pw;
	private String lastModifiedLine = "Last-Modified: ";
	private String connectionLine = "Connection: ";
	private String statusLine;
	private String contentTypeLine = "Content-Type: " ;
	private String contentLengthLine = "Content-Length: ";
	private String entityBody = "<HTML>" + "<HEAD> <TITLE>Not Found</TITLE> </HEAD>" + "<BODY>File Not Found </BODY> </HTML>";

	private static final String CRLF = "\r\n";
}
