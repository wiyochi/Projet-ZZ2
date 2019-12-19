package fr.isima.velo;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.stream.Collectors;

public class Client extends Thread {
	private static int ids = 0;
	private final Socket socket;
	private final int id;
	private OutputStreamWriter out;
	private BufferedInputStream in;

	private boolean isRunning = true;

	public Client(Socket socket) {
		this.socket = socket;
		this.id = ++ids;
		try {
			out = new OutputStreamWriter(socket.getOutputStream());
			in = new BufferedInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void preStop() {
		try {
			socket.close();
			isRunning = false;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String read(InputStream input) throws IOException {
        try (BufferedReader buffer = new BufferedReader(new InputStreamReader(input))) {
            return buffer.lines().collect(Collectors.joining("\n"));
        }
    }
	
	public String read() throws IOException {
		String str = read(socket.getInputStream());
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
				preStop();
			}
		}
	}
}
