package ex3;

/*
 *  Author: Raffaele Mignone
 *  Mat: 863/747
 *  Date: 12/11/17
 *
 */

import java.io.*;
import java.util.Date;

public class HttpResponseImp implements HttpResponse {

	public HttpResponseImp(OutputStream out) {
		this.out = out;
	}

	@Override
	public OutputStream getOutputStream() throws IOException {
		return null;
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		return null;
	}

	@Override
	public void setContentType(String cType) {
		contentTypeLine+=cType+CRLF;
	}

	@Override
	public void setContentLength(int cLength) {

	}

	@Override
	public void flushHeader() throws IOException {
		PrintStream ps = new PrintStream(out);
		ps.print(statusLine);
		ps.print(contentTypeLine);
		ps.print(CRLF);
	}

	@Override
	public void setConnection(String connection) {
		connectionLine+=connection+CRLF;
	}

	@Override
	public void setStatusLine(String status) {
		statusLine = status+CRLF;
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

	private OutputStream out;
	private String connectionLine = "Connection: ";
	private String statusLine;
	private String contentTypeLine = "Content-Type: " ;
	private String entityBody = "<HTML>" + "<HEAD> <TITLE>Not Found</TITLE> </HEAD>" + "<BODY>File Not Found </BODY> </HTML>";

	private static final String CRLF = "\r\n";
}
