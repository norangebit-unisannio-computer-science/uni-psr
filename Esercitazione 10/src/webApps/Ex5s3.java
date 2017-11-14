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

public class Ex5s3 implements WebLet {
	@Override
	public void service(HttpRequest req, HttpResponse res) {
		try{
			PrintWriter writer = res.getWriter();
			res.flushHeader();
			Float resut=null;
			float float1= Float.parseFloat(req.getParameter("float1"));
			float float2= Float.parseFloat(req.getParameter("float2"));
			String op = req.getParameter("op");
			switch (op){
				case "+":
					resut = new Float(float1+float2);
					break;
				case "-":
					resut = new Float(float1-float2);
					break;
				case "*":
					resut = new Float(float1*float2);
					break;
				case "/":
					resut = new Float(float1/float2);
					break;
			}
			writer.write(resut.toString());
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
