package ex3;

/*
 *  Author: Raffaele Mignone
 *  Mat: 863/747
 *  Date: 12/11/17
 *
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class HttpRequestImp implements HttpRequest{

	public HttpRequestImp(String root, InputStream in) {
		this.root = root;
		this.in = in;
		Scanner sc = new Scanner(in);
		String requestLine = sc.nextLine();
		StringTokenizer st = new StringTokenizer(requestLine);
		method = st.nextToken();
		fileName = st.nextToken();
		sc.nextLine();
		String user = sc.nextLine();
		st = new StringTokenizer(user);
		st.nextToken(":");
		userAgent = st.nextToken("\n");
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
	public String getQueryString() {
		StringTokenizer st = new StringTokenizer(fileName);
		String queryString = "";
		String s = st.nextToken("=");
		while(!s.equals("")){
			queryString+=st.nextToken("&")+":";
			s = st.nextToken("=");
		}
		return queryString;
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
	public String getParameter(String param) {
		return null;
	}


	private String root;
	private InputStream in;
	private String method;
	private String userAgent;
	private String fileName;
	private String[] MIMEType = new String[]{"text/html", "image/jpeg", "image/gif", "application/octet-stream"};
}
