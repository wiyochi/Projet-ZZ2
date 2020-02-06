package fr.velo.lib.tests;

import static org.junit.Assert.assertTrue;

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
	
	@SuppressWarnings("unused")
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
	
	@Test
	public void string() {
		Journey j = new Journey();
		assertTrue(j.toString().equals(":"));
		j.setName("nom");
		assertTrue(j.toString().equals("nom:"));
		

		Journey j3 = new Journey();
		j3.setName("nom");
		j3.insert(new Point4D(1, 1, 1, 1));
		assertTrue(j3.toString().equals("nom:1,000000+1,000000+1,000000+1,000000;"));
		

		Journey j4 = new Journey();
		j4.setName("nom");
		j4.insert(new Point4D(1, 1, 1, 1));
		j4.insert(new Point4D(2, 2, 2, 2));
		
		assertTrue(j4.toString().equals("nom:1,000000+1,000000+1,000000+1,000000;2,000000+2,000000+2,000000+2,000000;"));
	}
	
	@Test
	public void date() {
		Journey j = new Journey();
		assertTrue(System.currentTimeMillis() - j.getDateTime() <= 60000); // On regarde si le temps actuel est équivalent à la date de création de l'objet juste avant à 1 min près.
		
		//assertTrue(j.getDate().truncatedTo(ChronoUnit.MINUTES).equals(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES)));
	}

}
