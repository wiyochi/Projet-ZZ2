package fr.isima.velo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class LocalCommand extends Thread {

	private boolean isRunning = true;
	private Serveur s;
	private Statement st;
	private ResultSet rs;

	public LocalCommand(Serveur s, Statement st) {
		this.s = s;
		this.st = st;
	}

	@Override
	public void run() {
		super.run();
		Scanner sc = new Scanner(System.in);
		while (isRunning) {
			switch (sc.nextLine()) {
			case "stop":
				s.kill();
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
			default:
				break;
			}

		}
		sc.close();
	}
}
