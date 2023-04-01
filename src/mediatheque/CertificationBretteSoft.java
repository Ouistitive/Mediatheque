package mediatheque;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import jdbc.ConnexionBD;

public class CertificationBretteSoft {
	private static final int DUREE_MAX_EMPRUNT = 14; // 14 jours
	
	public static boolean retourDansLesTemps(int numDoc) {
		Date dateRetour = ConnexionBD.recupererDateEmprunt(numDoc);
		GregorianCalendar dateMaximale = new GregorianCalendar();
		dateMaximale.setTime(dateRetour);
		dateMaximale.add(Calendar.DAY_OF_MONTH, 14);
		GregorianCalendar dateRetourGregorian = new GregorianCalendar();
		dateRetourGregorian.setTime(new Date());
		
		return dateRetourGregorian.before(dateMaximale);
	}
}
