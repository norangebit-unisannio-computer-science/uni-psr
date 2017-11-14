package webApps;

/*
 *  Author: Raffaele Mignone
 *  Mat: 863/747
 *  Date: 14/11/17
 *
 */

import webletApi.HttpRequest;
import webletApi.HttpResponse;
import webletApi.WebLet;

import java.io.IOException;
import java.io.PrintWriter;

public class Ex5s2 implements WebLet {
	@Override
	public void service(HttpRequest req, HttpResponse res) {
		try{
			PrintWriter writer = res.getWriter();
			res.flushHeader();
			String stringa = req.getParameter("string");
			int intero = Integer.parseInt(req.getParameter("int"));
			writer.write(Integer.toString(intero+stringa.length()));
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
