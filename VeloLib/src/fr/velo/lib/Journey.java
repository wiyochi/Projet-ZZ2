package fr.velo.lib;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import fr.velo.lib.utils.Point4D;

public final class Journey implements Iterable<Point4D> {
	private final List<Point4D> points;
	private String name;

	public Journey(String name) {
		points = new ArrayList<Point4D>();
		this.name = name;
	}

	@Override
	public Iterator<Point4D> iterator() {
		return points.iterator();
	}

	public void insert(Point4D point) {
		points.add(point);
	}

	public int size() {
		return points.size();
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(name);
		builder.append(':');
		for (Point4D point4d : points) {
			builder.append(point4d);
			builder.append(';');
		}
		
		return builder.toString();
	}
}
