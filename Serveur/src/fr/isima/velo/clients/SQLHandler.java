package fr.isima.velo.clients;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.isima.velo.utils.Point3D;
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

	public String history(int idUser, String type, int start, int end) {
		StringBuilder builder = new StringBuilder();

		ResultSet tps = execute(String.format(
				"" + "SELECT * " + "FROM (SELECT id_tp FROM Access Where id_user=%d) AS Acc " + "NATURAL JOIN Projects "
						+ "WHERE type_project='%s' " + "ORDER BY id_tp DESC " + "LIMIT %d OFFSET %d;",
				idUser, type, end - start, start));

		try {
			while (tps.next()) {
				StringBuilder tBuild = new StringBuilder();
				if (type.equals("Travel"))
					tBuild.append("Journey:");
				else
					tBuild.append("Projects:");
				int id_tp = tps.getInt("id_tp");
				tBuild.append(id_tp);
				tBuild.append(":");
				tBuild.append(tps.getString("tp_name"));
				tBuild.append(":");
				if (type.equals("Travel")) {
					tBuild.append(tps.getDate("date_project"));
					tBuild.append(":");
					ResultSet points = execute("SELECT * FROM Point4D WHERE id_tp=" + id_tp + ';');
					while (points.next()) {
						tBuild.append(new Point4D(points));
						tBuild.append(';');
					}
				} else 
				{
					ResultSet points = execute("SELECT * FROM Point3D WHERE id_tp=" + id_tp + ';');
					while (points.next()) {
						tBuild.append(new Point3D(points));
						tBuild.append(';');
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return builder.toString();
	}

	public void createProject(String name, String points, int id) {
		String[] pointsSplit = points.split(";");
		try {
			for (String string : pointsSplit)
				statement.executeUpdate(new Point3D(string).toSQLString(id));
			statement.executeUpdate(String.format("INSERT INTO Projects (tp_name, type_project) VALUES (%s, 'Project');", name));
			int id_tp = execute("SELECT MAX(id_tp) FROM Projects;").getInt(0);
			int id_tp_user = execute(String.format("SELECT MAX(id_tp_user) FROM Access WHERE id_user='%s';", id)).getInt(0);
			statement.executeUpdate(String.format("INSERT INTO Access VALUES (%d, %d, %d);", id_tp, id, id_tp_user + 1));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
