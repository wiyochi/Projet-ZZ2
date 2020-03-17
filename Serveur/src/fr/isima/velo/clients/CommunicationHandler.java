package fr.isima.velo.clients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

public class CommunicationHandler {

	private BufferedReader reader;
	private PrintWriter writer;

	public CommunicationHandler(InputStream in, OutputStream out) {
		reader = new BufferedReader(new InputStreamReader(in));
		writer = new PrintWriter(out);
	}

	public String read() {
		try {
			return reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void write(String txt) {
		writer.println(txt);
	}
}
