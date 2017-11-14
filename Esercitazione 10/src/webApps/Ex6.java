package webApps;

/*
 *  Author: Raffaele Mignone
 *  Mat: 863/747
 *  Date: 13/11/17
 *
 */

import webletApi.HttpRequest;
import webletApi.HttpResponse;
import webletApi.WebLet;

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Ex6 implements WebLet{

		@Override
		public void service(HttpRequest req, HttpResponse res) {
		try{
			boolean flag = false;
			StringTokenizer st;
			Scanner sc = new Scanner(new File("database"));
			while (sc.hasNextLine() && !flag){
				String line = sc.nextLine();
				st = new StringTokenizer(line);
				if(st.nextToken().equals("mail:") && st.nextToken().equals(req.getParameter("email"))){
					line = sc.nextLine();
					st = new StringTokenizer(line);
					st.nextToken();
					flag = Integer.parseInt(st.nextToken())==req.getParameter("password").hashCode();
				}
			}

			PrintWriter writer = res.getWriter();
			res.flushHeader();
			if(flag)
				writer.write("Login effettuato");
			else
				writer.write("Login non effettuato");
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
