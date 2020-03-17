package fr.velo.lib.tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fr.velo.lib.Journey;
import fr.velo.lib.JourneyHistory;

public class JourneyHistoryTest {

	@Test
	public void insert() {
		assertTrue(JourneyHistory.getInstance().size() == 0);
		JourneyHistory.getInstance().insert(new Journey());
		assertTrue(JourneyHistory.getInstance().size() == 1);
		
		// TODO: Vérifier l'ordre d'insertion lorsque l'on aura l'accès à un historique stocké sur fichier (BDD/Local).
	}
}
