package fr.velo.lib;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import fr.velo.lib.utils.Point4D;

public final class Journey implements Iterable<Point4D>, Serializable {
	private static final long serialVersionUID = -6511317952698573048L;
	private List<Point4D> points;
	private String name;
	private Date date;

	public Journey() {
		points = new ArrayList<Point4D>();
		name="";
		date = Calendar.getInstance().getTime();
	}
	
	public Journey(InputStream stream) {
		try {
			Journey j = (Journey) new ObjectInputStream(stream).readObject();
			points = j.points;
			name = j.name;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
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
	
	public Date getDate() {
		return date;
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
	
	public void saveToStream(OutputStream stream)
	{
		try {
			new ObjectOutputStream(stream).writeObject(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
