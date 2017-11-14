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

public class Ex1 implements WebLet {
	@Override
	public void service(HttpRequest req, HttpResponse res) {
		try{
			PrintWriter writer = res.getWriter();
			res.flushHeader();
			writer.write("Hello WebLet World");
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
