package server;

/*
 *  Author: Raffaele Mignone
 *  Mat: 863/747
 *  Date: 07/11/17
 *
 */

import webletApi.HttpRequest;
import webletApi.HttpResponse;
import webletApi.ProtocolHandler;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;

// Gestore del protocollo HTTP. Versione in grado di elaborare solo richieste con metodo GET
class HttpHandler implements ProtocolHandler {

	// Costruttore
	// I parametri socket e root sono usati rispettivamente per recuperare gli stream associati al canale e per specificare la directory radice
	public HttpHandler(Socket socket, String root, WebLetEngine engine) {
		this.socket = socket;
		this.root = root;
		this.engine = engine;
	}

	// Interpreta il messaggio di richiesta HTTP per produrre il relativo messaggio di risposta HTTP
	public void handle() throws IOException {

		// Si recuperano i riferimenti agli stream di input e di output
		InputStream is = socket.getInputStream();
		OutputStream os = new DataOutputStream(socket.getOutputStream());

		HttpRequest request = new HttpRequestImp(root, is);
		HttpResponse response = new HttpResponseImp(os);

		response.setConnection("close");
		try{
			File resurce = new File(request.getFileName());

			if(resurce.exists()){
				response.setStatusLine("HTTP/1.1 200 OK");
				response.setContentType(contentType(request.getFileName()));

				response.flushHeader();
				response.sendFile(resurce);
			}else{
				if(request.isExecutable()){
					response.setStatusLine("HTTP/1.1 200 OK");
					response.setContentType("text/plain");

					engine.invokeService(resurce, request, response);
				}else{
					response.setStatusLine("HTTP/1.1 404 Not Found");
					response.setContentType("text/html");

					response.flushHeader();
					response.sendFile(resurce);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		socket.close();
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

	private Socket socket;
	private String root;
	private WebLetEngine engine;
}
