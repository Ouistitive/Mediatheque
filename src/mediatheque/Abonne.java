package mediatheque;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class Abonne {
	private int numero;
	private String nom;
	private GregorianCalendar dateNaissance;
	private GregorianCalendar dateBannissement;
	private static Calendar cal;
	
	static {
		cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
		cal.setTime(new Date());
	}
	
	public Abonne(int numero, String nom, GregorianCalendar dateNaissance, GregorianCalendar dateBannissement) {
		this.numero = numero;
		this.nom = nom;
		this.dateNaissance = dateNaissance;
		this.dateBannissement = dateBannissement;
	}
	
	public int getAge() {
		return cal.get(Calendar.YEAR) - dateNaissance.get(Calendar.YEAR);
	}
	
	public int getNumero() {
		return numero;
	}
	
	@Override
	public String toString() {
		return numero + " " + nom + " " + dateNaissance.toString();
	}

	public boolean estBanni() {
		GregorianCalendar aujourdhui = new GregorianCalendar();
		aujourdhui.setTime(new Date());
		
		return dateBannissement.after(aujourdhui);
	}

	public void bannir(GregorianCalendar dateBan) {
		dateBannissement = dateBan;
	}
}
