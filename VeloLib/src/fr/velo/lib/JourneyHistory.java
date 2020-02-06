package fr.velo.lib;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class JourneyHistory implements Iterable<Journey>{

	private static JourneyHistory instance;
	private List<Journey> history;
	
	private JourneyHistory() {
		history = new LinkedList<Journey>();
	}
	
	public void insert(Journey j) {
		int index = Collections.binarySearch(history, j, Comparator.comparing(Journey::getDateTime));
	    if (index < 0) {
	        index = -index - 1;
	    }
		history.add(index, j);
	}
	
	public Journey get(int i) {
		return history.get(i);
	}
	
	public int size() {
		return history.size();
	}

	@Override
	public Iterator<Journey> iterator() {
		return history.iterator();
	}

	public static JourneyHistory getInstance() {
		if (instance == null) instance = new JourneyHistory();
		return instance;
	}
	
}
