package fr.velo.lib;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import fr.velo.lib.utils.Point4D;

public final class Journey implements Iterable<Point4D>, Serializable {
	private static final long serialVersionUID = -6511317952698573048L;
	private List<Point4D> points;
	private String name;
	private long dateTime;

	public Journey() {
		points = new ArrayList<Point4D>();
		name = new String("");
		dateTime = System.currentTimeMillis();
	}

	public Journey(InputStream stream) {
		try {
			Journey j = (Journey) new ObjectInputStream(stream).readObject();
			points = j.points;
			name = j.name;
			dateTime = j.dateTime;
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
			System.err.println("Impossible de lire, on inialise un Journey vide.");
			points = new ArrayList<Point4D>();
			name = "";
			dateTime = 0;
		}
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

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public long getDateTime() {
		return dateTime;
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

	public void saveToStream(OutputStream stream) {
		try {
			new ObjectOutputStream(stream).writeObject(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Journey))
			return false;
		Journey j = (Journey) obj;
		if (j.name != name || j.dateTime != dateTime)
			return false;
		return points.equals(j.points);
	}

}
