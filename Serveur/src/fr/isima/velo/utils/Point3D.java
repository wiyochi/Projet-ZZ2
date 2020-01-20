package fr.isima.velo.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Point3D {

	private final double x, y, z;
	
	public Point3D(ResultSet rs) {
		x = getCoordinateFromResult(rs, "x");
		y = getCoordinateFromResult(rs, "y");
		z = getCoordinateFromResult(rs, "z");
	}
	
	public Point3D(String s) {
		String[] args = s.split("+");
		x = Double.parseDouble(args[0]);
		y = Double.parseDouble(args[1]);
		z = Double.parseDouble(args[2]);
	}

	@Override
	public String toString() {
		StringBuilder bd = new StringBuilder();
		bd.append(x);
		bd.append('+');
		bd.append(y);
		bd.append('+');
		bd.append(z);
		
		return bd.toString();
	}
	
	protected double getCoordinateFromResult(ResultSet rs, String coord) {
		try {
			return rs.getDouble(coord);
		} catch (SQLException e) {
			e.printStackTrace();
			return Double.MIN_VALUE;
		}
	}
	
	public String toSQLString(int clientId) {
		return String.format("INSERT INTO Point3D VALUES (%d, %d, %d, %d);", x, y, z);
	}
}
