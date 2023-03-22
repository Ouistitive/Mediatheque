package mediatheque.etats;

import mediatheque.Abonne;
import mediatheque.Document;
import mediatheque.EtatDocument;
import mediatheque.RestrictionException;

public class EtatLibre extends EtatDocument {

	public EtatLibre() {
		super();
	}
	
	public EtatLibre(Document d) {
		super(d);
	}
	
	@Override
	public boolean estLibre() {
		return true;
	}
	
	@Override
	public EtatDocument reservationPour(Abonne a) {
		return new EtatReserve(super.getDoc(), a);
	}
	
	@Override
	public EtatDocument empruntPar(Abonne a) {
		return new EtatEmprunte(super.getDoc(), a);
	}
	
	@Override
	public EtatDocument retour() throws RestrictionException {
		throw new RestrictionException("ce DVD n'a pas été emprunté, ni réservé");
	}

	public String toString() {
		return "libre";
	}
}
