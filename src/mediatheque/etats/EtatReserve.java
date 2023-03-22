package mediatheque.etats;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import mediatheque.Abonne;
import mediatheque.EtatDocument;
import mediatheque.RestrictionException;

public class EtatReserve extends EtatDocument {
	private final static int TEMPS_RESERVATION = 2;
	private Abonne reserveur;
	private GregorianCalendar heureReservation;
	
	public EtatReserve(Abonne reserveur) {
		this.reserveur = reserveur;
		this.heureReservation = new GregorianCalendar();
		this.heureReservation.setTime(new Date());
		this.heureReservation.add(Calendar.HOUR_OF_DAY, TEMPS_RESERVATION);
	}
	
	public EtatDocument reservationPour(Abonne a) throws RestrictionException {
		throw new RestrictionException("ce document est réservé jusqu’à " + heureReservation.get(Calendar.HOUR_OF_DAY) + "h" + heureReservation.get(Calendar.MINUTE));
	}

	@Override
	public Abonne reserveur() {
		// TODO Auto-generated method stub
		return reserveur;
	}
	
	@Override
	public EtatDocument retour() {
		return new EtatLibre();
	}
	
	@Override
	public EtatDocument empruntPar(Abonne a) throws RestrictionException {
		if(a == reserveur)
			return new EtatEmprunte(a);
		throw new RestrictionException("ce document est réservé jusqu’à " + heureReservation.get(Calendar.HOUR_OF_DAY) + "h" + heureReservation.get(Calendar.MINUTE));
	}

	public String toString() {
		return "reserve";
	}
}
