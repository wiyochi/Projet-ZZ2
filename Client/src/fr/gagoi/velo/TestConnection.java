package fr.gagoi.velo;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Random;

public class TestConnection {

	public static void main(String[] args) {
		try {
			Socket sc = new Socket("172.16.40.88", 45703);
			OutputStreamWriter ous = new OutputStreamWriter(sc.getOutputStream());
			double r = Math.random();
			System.out.println(r);
			ous.write("Test " + r);
			ous.flush();
			//Thread.sleep(1000);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
