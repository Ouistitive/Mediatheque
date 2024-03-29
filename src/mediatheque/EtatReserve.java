package mediatheque;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

public class EtatReserve extends EtatDocument {
	private final static int TEMPS_RESERVATION = 2;
	private Abonne reserveur;
	private GregorianCalendar heureReservation;
	private Timer timer;
	
	
	
	public EtatReserve(Document d, Abonne reserveur) {
		super(d);
		this.reserveur = reserveur;
		this.heureReservation = new GregorianCalendar();
		this.heureReservation.setTime(new Date());
		this.heureReservation.add(Calendar.MINUTE, TEMPS_RESERVATION);
		
		this.timer = new Timer();
		this.timer.schedule(new FinReservation(super.getDoc()), heureReservation.getTime());
	}
	
	public EtatDocument reservationPour(Abonne a) throws RestrictionException {
		throw new IndisponibleException(super.getDoc().toString()+" est r�serv� jusqu�� " + heureReservation.get(Calendar.HOUR_OF_DAY) + "h" + heureReservation.get(Calendar.MINUTE));
	}

	@Override
	public Abonne reserveur() {
		return reserveur;
	}
	
	@Override
	public EtatDocument retour() {
		this.timer.cancel();
		System.out.println("Retour de "+super.getDoc());
		return new EtatLibre(super.getDoc());
	}
	
	@Override
	public EtatDocument empruntPar(Abonne a) throws RestrictionException {
		synchronized(this) {
			if(a == reserveur) {
				this.timer.cancel();
				return new EtatEmprunte(super.getDoc(), a);
			}
			throw new IndisponibleException(super.getDoc().toString()+" est r�serv� jusqu�� " + heureReservation.get(Calendar.HOUR_OF_DAY) + "h" + heureReservation.get(Calendar.MINUTE));
	
		}
	}
	public String toString() {
		return "reserve";
	}
	
	
	private class FinReservation extends TimerTask {

		private Document doc;

		FinReservation(Document d){
			this.doc = d;
		}
		
		@Override
		public void run() {
			try {
				doc.retour();
				ListeAlertes.envoyerMail(doc.numero());
				ListeAlertes.supprimer(doc.numero());
			} catch (RestrictionException e) {
				e.printStackTrace();
			}
			
		}
		
		
	}
	
	
}
