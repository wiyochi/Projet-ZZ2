package fr.velo.lib;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import fr.velo.lib.utils.Point3D;

public final class Project implements Iterable<Point3D> {
	private final List<Point3D> points;
	
	public Project() {
		points = new ArrayList<Point3D>();
	}
	
	public void insert(Point3D point) {
		points.add(point);
	}
	

	@Override
	public Iterator<Point3D> iterator() {
		return points.iterator();
	}
	
}
