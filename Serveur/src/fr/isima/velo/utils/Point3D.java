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
}
