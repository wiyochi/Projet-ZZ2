package fr.velo.lib.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.velo.lib.Journey;
import fr.velo.lib.utils.Point4D;

public class JourneysTest {

	@Test
	public void init() {
		Journey j = new Journey();
		assertTrue(j.size() == 0);
	}
	
	@Test
	public void insert() {
		Journey j = new Journey();
		
		j.insert(new Point4D(1, 1, 1, 1));
		
		assertTrue(j.size() == 1);
	}
	
	@Test
	public void iterator() {
		Journey j = new Journey();
		int i = 0;
		for (Point4D point4d : j) ++i;
		assertTrue(i == 0);
		j.insert(new Point4D(1, 1, 1, 1));
		j.insert(new Point4D(1, 1, 1, 1));
		for (Point4D point4d : j) ++i;
		assertTrue(i == 2);
	}
	
	

}
