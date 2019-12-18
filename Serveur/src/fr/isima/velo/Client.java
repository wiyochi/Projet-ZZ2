package fr.isima.velo;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client extends Thread {
	private static int ids = 0;
	private final Socket socket;
	private final int id;
	private OutputStreamWriter out;
	private Scanner in;

	private boolean isRunning = true;

	public Client(Socket socket) {
		this.socket = socket;
		this.id = ++ids;
		try {
			out = new OutputStreamWriter(socket.getOutputStream());
			in = new Scanner(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void preStop() {
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String read() throws IOException {
		String str = in.nextLine();
		System.out.println(id + " : " + str);
		return str;
	}

	public void send(String str) throws IOException {
		out.write(str);
		out.flush();
	}

	@Override
	public void run() {
		while (isRunning) {
			try {
				read();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
