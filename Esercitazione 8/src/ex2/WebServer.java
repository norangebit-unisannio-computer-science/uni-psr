package ex2; /**
*
* @author Eugenio Zimeo (2015 - rev. 17)
*/

import ex1.HttpHandlerConcorrente;
import ex1.ProtocolHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

// Implementazione sequenzale di un semplice Server Web
public class WebServer {

	public static void main(String argv[]) {
		try {
			WebServer server = new WebServer();
			server.configure();
			server.start();
		} catch (IOException e) {
			System.err.println("Il server non puo' essere lanciato" + e);
		}
	}

	public void start() throws IOException {
		// Si crea una socket di tipo server
		ServerSocket welcomeSocket = new ServerSocket(port);
		// Elabora le richieste http in un loop infinito
		while (true) {
			// Accetta richieste di connessione
			Socket connectionSocket = welcomeSocket.accept();
			// Costruisce un oggetto per elaborare un messaggio di richiesta HTTP
			ProtocolHandler request = new HttpHandlerConcorrente(connectionSocket, root);
			try {
				request.handle();
			} catch (IOException e) {
				System.err.println("La richiesta non puo' essere elaborata");
			}
		}
	}

	public void configure() {
		// Eventuale recupero del numero di porta e del percorso radice dal file di configurazione
		File conf = new File("webserver.conf");
		if (conf.exists()) {
			FileInputStream fis = null;
			try {
				fis	= new FileInputStream(conf);
				Scanner scanfis = new Scanner(fis);
				while (scanfis.hasNextLine()) {
					String confLine = scanfis.nextLine();
					if (confLine.startsWith("port"))
						port = Integer.parseInt(confLine.substring(4).trim());
					if (confLine.startsWith("root"))
						root = confLine.substring(4).trim();
				}
			} catch (FileNotFoundException e) { // Never reached
			} finally {
				try {
					if (fis != null) fis.close();
				} catch (IOException e) {
					System.err.println("Errore durante la chiusura del file di configurazione");
				}
			}
		}
		else
			System.out.println("Configuration file not found");

	}

	// Valori di default per il numero di porta e per il percorso radice
	private int port = 80;
	private String root = ".";
}



