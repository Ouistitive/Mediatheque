package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import documents.DVD;
import mediatheque.Abonne;
import mediatheque.Document;

class DVDTest {

	@Test
	void test() {
		Abonne a1 = new Abonne(1, "Toto", "01/01/2006");
		Abonne a2 = new Abonne(2, "Tutu", "01/01/2008");
		
		Document dvd = new DVD(1, "Toto", true);
		
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
		dvd.reservationPour(a2);
		assertEquals(dvd.reserveur(), null);
	}

}
