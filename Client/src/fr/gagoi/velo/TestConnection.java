package fr.gagoi.velo;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;

public class TestConnection {

	public static void main(String[] args) {
		try {
			Socket sc = new Socket("172.16.40.88", 45703);
			OutputStreamWriter ous = new OutputStreamWriter(sc.getOutputStream());
			ous.write("Test");
			ous.flush();
			//Thread.sleep(1000);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
