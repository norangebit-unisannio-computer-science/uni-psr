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

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

public class Ex4 implements WebLet {

	@Override
	public void service(HttpRequest req, HttpResponse res) {
		try{
			PrintWriter writer = res.getWriter();
			res.flushHeader();
			writer.write(req.getParameter("firstname")+" "+req.getParameter("lastname"));
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
