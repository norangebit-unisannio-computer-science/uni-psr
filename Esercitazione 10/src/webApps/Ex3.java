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

public class Ex3 implements WebLet{

		@Override
		public void service(HttpRequest req, HttpResponse res) {
		try{
			FileOutputStream fos = new FileOutputStream("database", true);
			PrintStream ps = new PrintStream(fos);
			ps.println("nome: "+req.getParameter("firstname"));
			ps.println("cognome: "+req.getParameter("lastname"));
			ps.println("mail: "+req.getParameter("email"));
			ps.println("password: "+req.getParameter("password").hashCode());
			PrintWriter writer = res.getWriter();
			res.flushHeader();
			writer.write(req.getParameter("firstname")+", la tua registrazione è avvenuta con successo.");
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
