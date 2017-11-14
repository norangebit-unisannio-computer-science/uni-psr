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

public class Ex5s1 implements WebLet {
	@Override
	public void service(HttpRequest req, HttpResponse res) {
		try{
			PrintWriter writer = res.getWriter();
			res.flushHeader();
			String revers = new StringBuffer(req.getParameter("string")).reverse().toString();
			writer.write(revers);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
