package ex1;

/*
 *  Author: Raffaele Mignone
 *  Mat: 863/747
 *  Date: 13/11/17
 *
 */

import java.io.*;

public class HelloWebLet implements WebLet{
	@Override
	public void service(HttpRequest req, HttpResponse res) {
		try{
			//PrintStream ps = new PrintStream(res.getOutputStream());
			//ps.print("Hello WebLet");
			PrintWriter writer = res.getWriter();
			res.flushHeader();
			writer.write("Hello WebLet World");
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
