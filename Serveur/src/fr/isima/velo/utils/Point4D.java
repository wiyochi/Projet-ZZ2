package fr.isima.velo.utils;

import java.sql.ResultSet;

public class Point4D extends Point3D {

	private final double t;

	public Point4D(ResultSet rs) {
		super(rs);
		t = getCoordinateFromResult(rs, "t");
	}
	
	@Override
	public String toString() {
		return super.toString() + "+" + t;
	}
}
