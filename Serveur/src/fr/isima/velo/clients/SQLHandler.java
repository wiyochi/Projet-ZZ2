package fr.isima.velo.clients;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.isima.velo.utils.Point4D;

public class SQLHandler {

	private Statement statement;

	public SQLHandler(Statement statement) {
		this.statement = statement;
	}

	private ResultSet execute(String query) {
		try {
			return statement.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public int subscribe(String name, String password) {
		int id = 0;
		try {
			statement.executeUpdate(
					"INSERT INTO Users (username, password) VALUES ('" + name + "','" + password + "');");
			return execute("SELECT MAX(id_user) FROM Users WHERE username = '" + name + "';").getInt(0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	public String connect(int id, String pass) {
		try {
			if (pass.equals(execute("SELECT password FROM Users WHERE username = '" + id + "';").getString(0)))
				return execute("SELECT username FROM Users WHERE id_user = " + id + ';').getString(0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String history(int idUser, int start, int end) {
		StringBuilder builder = new StringBuilder();

		ResultSet id_tps = execute("SELECT * FROM Access WHERE id_user=" + idUser + " AND id_tp_user BETWEEN " + start
				+ " AND " + end + ';');

		try {
			while (id_tps.next()) {
				StringBuilder tBuild = new StringBuilder("Journey:");
				int id_tp = id_tps.getInt("id_tp_user");
				tBuild.append(id_tp);
				tBuild.append(":");
				ResultSet tp = execute("SELECT * FROM Projects WHERE id_tp=" + id_tp + ';');
				tBuild.append(tp.getString("tp_name"));
				tBuild.append(":");
				tBuild.append(tp.getDate("date_project"));
				tBuild.append(":");
				ResultSet points = execute("SELECT * FROM Point4D WHERE id_tp=" + id_tp + ';');
				while (points.next()) {
					tBuild.append(new Point4D(points));
					tBuild.append(';');
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return builder.toString();
	}
}
