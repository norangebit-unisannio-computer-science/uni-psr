package server;

import webletApi.HttpRequest;
import webletApi.HttpResponse;
import webletApi.WebLet;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

class WebLetEngine {

	public WebLetEngine(String classPath) {
		this.classPath = classPath;
	}

	public void invokeService(File file, HttpRequest req, HttpResponse res)
	throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		WebLet wl = null;
		// Si verifica se la WebLet è stata già caricata in precedenza
		wl = webLets.get(file.getName()); 
		if (wl == null) {
			String className = file.getPath().replace(req.getRoot(), classPath);
			className = className.replaceAll("\\"+File.separator, ".").replaceAll(".class", "");

			// Si carica la classe usando il nome della risorsa passata nell'URI (l'estensione non va usata)
			Class<?> externalClass = Class.forName(className);
			// Si istanzia un oggetto usando il costruttore senza argomenti
			wl = (WebLet)externalClass.newInstance();
			// Si inserisce la nuova WebLet nella HashMap per successivi usi
			webLets.put(file.getName(), wl);
			// Se la WebLet avesse un meotodo di inizializzazione dovrebbe essere chiamato in questo punto
		}
		wl.service(req, res);
	}
	private String classPath;
	private HashMap<String, WebLet> webLets = new HashMap<String, WebLet>();

}
