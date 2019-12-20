package fr.isima.velo;

import java.io.IOException;
import java.net.ServerSocket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Serveur implements Runnable {

	private static final int NO_ERROR = 0;
	private static final int ERROR_SERVER = 2;
	private static final int ERROR_BDD = 3;
	private static final int ERROR_SQL = 4;
	//private ArrayList<Client> clients = new ArrayList<Client>();

	private final int PORT = 45703;
	private ServerSocket socket;
	private boolean isRunning = true;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BIKEDD", "root", "v3/0R1G0lo");
			Statement st = con.createStatement();
			ResultSet r = st.executeQuery("SELECT * FROM Users;");
			while (r.next())
				System.out.println(r.getInt(1) + " - " + r.getString(2));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(ERROR_BDD);
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(ERROR_SQL);
		}
		Serveur s = new Serveur();
		Thread t = new Thread(s);
		t.start();
		while (true) {
			System.out.println("Entrez commande : ");
			switch(sc.nextLine()) {
			case "exit":
				System.exit(NO_ERROR);
				break;
			case "t":
				break;
			}
		}
	}

	public Serveur() {
		try {
			socket = new ServerSocket(PORT);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(ERROR_SERVER);
		}
	}

	@Override
	public void run() {
		try {
			while (isRunning) {
				Client c = new Client(socket.accept());
				//clients.add(c);
				c.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
