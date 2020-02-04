package fr.velo.lib.utils;

public final class Point4D extends Point3D {

	private double t;

	public Point4D(double x, double y, double z, double t) {
		super(x, y, z);
		this.t = t;
	}
	
	public void setT(double t) {
		this.t = t;
	}
	
	public double getT() {
		return t;
	}
	

	/**
	 * ATTENTION : ARRONDIE A 10⁻⁶
	 */
	@Override
	public String toString() {
		return String.format("%s+%f", super.toString(), getT());
	}
}