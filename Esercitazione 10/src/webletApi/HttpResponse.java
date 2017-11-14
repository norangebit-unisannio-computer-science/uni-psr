package webletApi;

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
	OutputStream getOutputStream() throws IOException;

	// Restituisce il PrintWriter associato al messaggio
	PrintWriter getWriter() throws IOException;

	// Specifica il tipo di contenuto del messaggio
	void setContentType(String cType);

	// Specifica la lunghezza del body del messaggio
	void setContentLength(int cLength);

	// Invia nello stream di output le intestazioni del messaggio
	void flushHeader() throws IOException;

	void setConnection(String connection);

	void setStatusLine(String status);

	void sendFile(File file) throws Exception;

	void setlastModifiedLine(File file);

}
