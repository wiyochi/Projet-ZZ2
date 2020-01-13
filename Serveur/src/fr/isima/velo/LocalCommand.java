package fr.isima.velo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import fr.isima.velo.clients.Client;
import fr.isima.velo.clients.SQLHandler;

public class LocalCommand extends Thread {

	private boolean isRunning = true;
	private Statement st;
	private ResultSet rs;

	private SQLHandler sqlHandler;

	public LocalCommand(Statement st) {
		this.st = st;
		this.sqlHandler = new SQLHandler(st);
	}

	@Override
	public void run() {
		super.run();
		Scanner sc = new Scanner(System.in);
		while (isRunning) {
			switch (sc.nextLine()) {
			case "stop":
				System.exit(0);
				break;
			case "query":
				try {
					rs = st.executeQuery(sc.nextLine());
					for (int i = 0; i < rs.getRow(); ++i) {
						rs.next();
						StringBuilder builder = new StringBuilder();
						for (int j = 1; j < rs.getMetaData().getColumnCount(); ++j)
							builder.append(rs.getString(j)).append(" ");
						System.out.println(builder.toString());
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			case "subscribe":
				String username = sc.nextLine().replaceAll("\n", "");
				String password = sc.nextLine().replaceAll("\n", "");
				
				sqlHandler.subscribe(username, password);
				break;
			default:
				break;
			}

		}
		sc.close();
	}
}
