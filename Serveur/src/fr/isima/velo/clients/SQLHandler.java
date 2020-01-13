package fr.isima.velo.clients;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLHandler {

	private Statement statement;

	public SQLHandler(Statement statement) {
		this.statement = statement;
	}

	public ResultSet execute(String query) {
		try {
			return statement.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public String getPassword(String id) {
		try {
			return execute("SELECT password FROM Users WHERE name = " + id + ';').getString(0);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public int getClientId(String name) {
		try {
			return execute("SELECT id_user FROM Users WHERE username = " + name + ';').getInt(0);
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public void subscribe(String name, String password) {
		try {
			statement.executeUpdate("INSERT INTO Users (username, password) VALUES ('" + name + "','" + password + "');");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
