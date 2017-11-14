package webletApi;

/*
 *  Author: Raffaele Mignone
 *  Mat: 863/747
 *  Date: 07/11/17
 *
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;

public interface HttpRequest {

	// Restituisce lo stream di input associato al messaggio
	InputStream getInputStream() throws IOException;

	// Restituisce lo stream orientato ai caratteri associato al messaggio
	BufferedReader getReader() throws IOException;

	// Restituisce il metodo usato per la richiesta
	String getMethod();

	// Restituisce il file richiesto
	String getFileName();

	// Restituisce i formati MIME accettati
	String getAccept();

	// Restituisce il tipo e la versione dello User Agent usato dal lato client
	String getUserAgent();

	// Restituisce il valore del parametro specificato
	void setParameters(String param);

	String getParameter(String tag);

	boolean isExecutable();

	String getRoot();
}