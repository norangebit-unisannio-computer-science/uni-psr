package ex3;

/*
 *  Author: Raffaele Mignone
 *  Mat: 863/747
 *  Date: 07/11/17
 *
 */

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

public interface HttpResponse {

	// Restituisce lo stream associato al messaggio
	public OutputStream getOutputStream() throws IOException;

	// Restituisce il PrintWriter associato al messaggio
	public PrintWriter getWriter() throws IOException;

	// Specifica il tipo di contenuto del messaggio
	public void setContentType(String cType);

	// Specifica la lunghezza del body del messaggio
	public void setContentLength(int cLength);

	// Invia nello stream di output le intestazioni del messaggio
	public void flushHeader() throws IOException;

	public void setConnection(String connection);

	public void setStatusLine(String status);

	public void sendFile(File file) throws Exception;

}
