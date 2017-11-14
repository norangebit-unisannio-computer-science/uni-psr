package server;

/*
 *  Author: Raffaele Mignone
 *  Mat: 863/747
 *  Date: 12/11/17
 *
 */

import webletApi.HttpRequest;

import java.io.*;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.StringTokenizer;

public class HttpRequestImp implements HttpRequest {

	public HttpRequestImp(String root, InputStream in) throws IOException{
		this.root = root;
		this.in = in;
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String requestLine = br.readLine();
		StringTokenizer st = new StringTokenizer(requestLine);
		method = st.nextToken();
		fileName = st.nextToken();
		st = new StringTokenizer(fileName);
		exe = st.nextToken("/").equals("webApps");
		br.readLine();
		String user = br.readLine();
		st = new StringTokenizer(user);
		st.nextToken(":");
		userAgent = st.nextToken("\n");

		String line = br.readLine();
		while (!line.equals("")){
			st = new StringTokenizer(line);
			String field = st.nextToken(": ");
			if (field.equalsIgnoreCase("Content-Type")) contentType = st.nextToken();
			if (field.equalsIgnoreCase("Content-Length")) contentLength = Integer.parseInt(st.nextToken().trim());
			line = br.readLine();
		}

		// A questo punto tutta l'intestazione del messaggio di richiesta è stata analizzata e rimossa dallo stream
		if (method.equalsIgnoreCase("GET")) {
			st = new StringTokenizer (fileName, "? ");
			fileName = st.nextToken();
			if (st.hasMoreElements())
				queryString = URLDecoder.decode(st.nextToken(), "Utf-8");
		/*/NOT WORK
		}if (method.equalsIgnoreCase("POST")) {
			if (contentType.equalsIgnoreCase("application/x-www-form-urlencoded"))
				queryString = URLDecoder.decode(readString(contentLength), "Utf-8");/*/
		}
		setParameters(queryString);

	}

	@Override
	public InputStream getInputStream() throws IOException {
		return in;
	}

	@Override
	public BufferedReader getReader() throws IOException {
		return new BufferedReader(new InputStreamReader(in));
	}

	@Override
	public String getMethod() {
		return method;
	}

	@Override
	public String getFileName() {
		return root+fileName;
	}

	@Override
	public String getAccept() {
		String MIMEAccept="";
		for (String s: MIMEType)
			MIMEAccept+=s+":";
		return MIMEAccept;
	}

	@Override
	public String getUserAgent() {
		return userAgent;
	}

	@Override
	public void setParameters(String query) {
		if (query == null) return;
		// In alternativa è possibile usare un doppio tokenizzatore: uno avente
		// '&' come delimitatore ed un altro innestato avente '=' come delimitatore
		// Ancora in alternativa è possibile usare substring di String
		StringTokenizer st = new StringTokenizer (query);
		while(st.hasMoreTokens()) {
			// Estrae dalla queryString le coppie parametro, valore
			parameters.put(st.nextToken("=").replace('&', ' ').trim(),
					st.nextToken("&").replace('=', ' ').trim());
		}
	}

	@Override
	public String getParameter(String tag) {
		return parameters.get(tag);
	}

	@Override
	public boolean isExecutable() {
		return exe;
	}

	@Override
	public String getRoot() {
		return root;
	}

	//NOT WORK
	public String readString (int len) throws IOException {
		int bytes = 0, pos = 0;
		char[] buf = new char[len];
		while (bytes < len )
			bytes += getReader().read(buf, pos+=bytes, len-bytes);
		return new String(buf);
	}



	private String root;
	private InputStream in;
	private String method;
	private String userAgent;
	private String contentType;
	private String queryString;
	private int contentLength;
	boolean exe;
	private String fileName;
	private HashMap<String, String> parameters = new HashMap<String, String>();
	private String[] MIMEType = new String[]{"text/html", "image/jpeg", "image/gif", "application/octet-stream"};
}
