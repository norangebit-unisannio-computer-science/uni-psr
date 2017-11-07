package ex1;

/*
 *  Author: Raffaele Mignone
 *  Mat: 863/747
 *  Date: 07/11/17
 *
 */

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.util.Date;
import java.util.StringTokenizer;

// Gestore del protocollo HTTP. Versione in grado di elaborare solo richieste con metodo GET
class HttpHandler implements ProtocolHandler {

	// Costruttore
	// I parametri socket e root sono usati rispettivamente per recuperare gli stream associati al canale e per specificare la directory radice
	public HttpHandler(Socket socket, String root) {
		this.socket = socket;
		this.root = root;
	}

	// Interpreta il messaggio di richiesta HTTP per produrre il relativo messaggio di risposta HTTP
	public void handle() throws IOException {
		System.out.println("Richiesta ricevuta dal Browser");  // per debug

		// Si recuperano i riferimenti agli stream di input e di output
		InputStream is = socket.getInputStream();
		// E' possibile impiegare in alternativa PrintStream
		DataOutputStream os = new DataOutputStream(socket.getOutputStream());
		// Si costruisce uno stream filtro per leggere a linee
		// E' possibile impiegare in alternativa uno Scanner
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		// Si recupera dallo stream la prima linea (la request line del messaggio di richiesta)
		System.out.println(new Date()); // Per capire se la richiesta è nuova

		String requestLine = br.readLine();
		System.out.println("\n"+requestLine);

		// Si estrae il nome del file dalla linea di richiesta
		// Se si usa uno Scanner e' possibile estrarre direttamente i token con il metodo next()
		StringTokenizer tokens = new StringTokenizer(requestLine);
		// Si recupera il metodo dal primo token della request line
		String method = tokens.nextToken();
		// Si recupera il nome della risorsa richiesta dal secondo token della request line
		String fileName = tokens.nextToken();
		// Visualizza sullo standard output il resto del contenuto del messaggio di richiesta
		String line = "";


		// Questo codice salta tutte le linee di intestazione visualizzandole sullo stdout
		do {
			line = br.readLine();
			System.out.println(line);
		} while (!line.equals(""));

		// L'eventuale body non viene estratto in questa implementazione. Si assume che le richieste siano di tipo GET.
		// -------------------------------- First part -------------------------------------



		// DA QUI INIZIA LA PREPARAZIONE DEL MESSAGGIO DI RISPOSTA

		// Si mappa la radice su index.html. Non richiesto dalla traccia
		if (fileName.equals(File.separator))
			fileName = File.separator +"index.html";
		// Si antepone root in modo che il file sia recuperato da una posizione valida del file system
		// root rappresenta la radice per il file system gestito dal Web server
		fileName = root + fileName;
		//System.out.println(fileName);

		// Prepara il messaggio di risposta

		String statusLine = "";
		String dateLine = "";
		// Vogliamo forzare l'uso delle connessioni non permanenti in HTTP/1.1
		String connectionLine = "Connection: close" + CRLF;
		String contentTypeLine = "";
		//String contentLengthLine = "";
		String cacheControlLine = "";
		String lastModifiedLine = "";
		//String eTagLine = "";
		//String expiresLine = "";

		String entityBody = "";
		File file = new File(fileName);
		// Diversa costruzione del messaggio di risposta in dipendenza della presenza o meno del file richiesto
		if (file.exists()) {
			statusLine = "HTTP/1.1 200 OK" + CRLF;
			contentTypeLine = "Content-Type: " + contentType(fileName) + CRLF;
			//contentLengthLine = "Content-Length: " + file.length() + CRLF;
			//cacheControlLine = "Cache-Control: private, max-age=60" + CRLF;
			//cacheControlLine = "Cache-Control: no-store" + CRLF;
			//cacheControlLine = "Cache-Control: no-cache" + CRLF;
			lastModifiedLine = "Last-Modified: " + new Date(file.lastModified()) + CRLF;
			//eTagLine = "ETag: " + genETag() + CRLF;  // added
			//expiresLine = "Expires: " + "Tue Nov 08 22:02:35 CET 2016" + CRLF;
		} else {
			statusLine = "HTTP/1.1 404 Not Found" + CRLF;
			contentTypeLine = "Content-Type: text/html" + CRLF;
			entityBody = "<HTML>" +
			"<HEAD> <TITLE>Not Found</TITLE> </HEAD>" +
			"<BODY>File Not Found </BODY> </HTML>";
			//contentLengthLine = "Content-Length: " + entityBody.length() + CRLF;
		}
		dateLine = "Date: "+ new Date() +CRLF;
		// INVIO del messaggio di risposta con le informazioni precalcolate

		// STATUS LINE
		os.writeBytes(statusLine);


		// HEADER LINES
		os.writeBytes(connectionLine);
		os.writeBytes(dateLine);

		if (file.exists()) {
			//os.writeBytes(cacheControlLine);
			os.writeBytes(lastModifiedLine);
			//os.writeBytes(eTagLine);
			//os.writeBytes(expiresLine);
		}
		os.writeBytes(contentTypeLine);
		// os.writeBytes(contentLenghtLine);
		// Invia una linea vuota per indicare la fine delle linee di intestazione
		// FINE HEADER LINES
		os.writeBytes(CRLF);

		// BODY
		if (file.exists()) {
			try {
				sendFile(file, os);
			}  catch (IOException e) {
				System.err.println("Errore durante l'accesso al file " + file.getName());
			}
		} else {
			os.writeBytes(entityBody);
		}
		// Eventuale chiusura della connessione nel caso di connessioni non permanenti
		if (connectionLine.equals("Connection: close" + CRLF)) {
			os.close(); socket.close();
			System.out.println("Connessione chiusa\n");
		}
	}

	// Restituisce il formato MIME associato ad un file attraverso l'analisi dell'estensione
	private String contentType(String fileName) {

		// Il corpo di questo metodo puo' essere esteso per gestire altri formati MIME
		if(fileName.endsWith(".htm") || fileName.endsWith(".html"))
			return "text/html";
		if(fileName.endsWith(".gif"))
			return "image/gif";
		if(fileName.endsWith(".jpg") || (fileName.endsWith(".jpeg")))
			return "image/jpeg";

		try {
			return Files.probeContentType(new File(fileName).toPath());
		} catch (IOException e) {
		} finally {
			return "application/octet-stream";
		}
	}

	// Copia il file sullo stream di output os
	private void sendFile(File file, OutputStream os) throws IOException {
		// Impiega un buffer di 1KB per recuperare blocchi di
		// byte dal file ed inviarli sullo stream di rete
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			byte[] buffer = new byte[1024];
			int bytes = 0;
			// Copia i blocchi dal file allo stream di output fino alla fine del file
			while((bytes = fis.read(buffer)) != -1 )
				os.write(buffer, 0, bytes);
		}
		finally {
			if (fis != null)
				fis.close();
		}
	}

	private final static String CRLF = "\r\n";
	private Socket socket;
	private String root;
}
