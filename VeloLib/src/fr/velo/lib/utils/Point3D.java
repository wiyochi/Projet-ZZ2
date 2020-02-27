package fr.velo.lib.utils;

import java.io.Serializable;

public class Point3D implements Serializable {
	private static final long serialVersionUID = 8333475562210985029L;
	private double x, y, z;

	public Point3D(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setZ(double z) {
		this.z = z;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getZ() {
		return z;
	}

	/**
	 * ATTENTION : ARRONDIE A 10⁻⁶
	 */
	@Override
	public String toString() {
		return String.format("%f+%f+%f", getX(), getY(), getZ());
	}
}
