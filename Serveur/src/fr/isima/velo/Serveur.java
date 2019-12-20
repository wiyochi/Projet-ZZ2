package fr.isima.velo;

import java.io.IOException;
import java.net.ServerSocket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Serveur implements Runnable {

	private static final int NO_ERROR = 0;
	private static final int ERROR_SERVER = 2;
	private static final int ERROR_BDD = 3;
	private static final int ERROR_SQL = 4;
	// private ArrayList<Client> clients = new ArrayList<Client>();

	private final int PORT = 45703;
	private ServerSocket socket;
	private boolean isRunning = true;
	private Connection con;

	public static void main(String[] args) {
		Serveur s = new Serveur();
		Thread t = new Thread(s);
		t.start();
	}

	public Serveur() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BIKEDD", "root", "v3/0R1G0lo");
			socket = new ServerSocket(PORT);
			//while (r.next())
			//	System.out.println(r.getInt(1) + " - " + r.getString(2));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(ERROR_BDD);
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(ERROR_SQL);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(ERROR_SERVER);
		}
	}

	@Override
	public void run() {
		try {
			while (isRunning) {
				Client c = new Client(socket.accept(), con.createStatement());
				// clients.add(c);
				c.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void kill() {

	}
}
