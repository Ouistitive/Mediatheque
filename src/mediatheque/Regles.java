package mediatheque;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Regles {
	private static final int DUREE_MAX_EMPRUNT = 14; // 14 jours
	
	public boolean retourDansLesTemps(int numDoc, IConnexionBD bd) {
		Date dateRetour = bd.recupererDateEmprunt(numDoc);
		GregorianCalendar dateMaximale = new GregorianCalendar();
		dateMaximale.setTime(dateRetour);
		dateMaximale.add(Calendar.DAY_OF_MONTH, 14);
		GregorianCalendar dateRetourGregorian = new GregorianCalendar();
		dateRetourGregorian.setTime(new Date());
		
		return dateRetourGregorian.before(dateMaximale);
	}
}
