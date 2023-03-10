package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import documents.DVD;
import mediatheque.Abonne;
import mediatheque.Document;
import mediatheque.RestrictionException;
import mediatheque.etats.EtatLibre;

class DVDTest {

	@Test
	void test() {
		Abonne a1 = new Abonne(1, "Toto", "01/01/2006");
		Abonne a2 = new Abonne(2, "Tutu", "01/01/2008");
		
		Document dvd = new DVD(1, "Toto", true, new EtatLibre());
		
		try {
			// On teste la reservation
			dvd.reservationPour(a1);
			assertEquals(dvd.reserveur(), a1);
			
			// On teste l'emprunt
			dvd.empruntPar(a1);
			assertEquals(dvd.emprunteur(), a1);
			
			// On teste le retour
			dvd.retour();
			assertEquals(dvd.reserveur(), null);
			assertEquals(dvd.emprunteur(), null);
			
			// On teste l'emprunt pour un mineur pour un dvd pour adulte
			assertThrows(RestrictionException.class, () -> { dvd.reservationPour(a2); });
			assertEquals(dvd.reserveur(), null);
		} catch (RestrictionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
