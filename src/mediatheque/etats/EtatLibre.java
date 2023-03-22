package mediatheque.etats;

import mediatheque.Abonne;
import mediatheque.EtatDocument;
import mediatheque.RestrictionException;

public class EtatLibre extends EtatDocument {

	@Override
	public boolean estLibre() {
		return true;
	}
	
	@Override
	public EtatDocument reservationPour(Abonne a) {
		return new EtatReserve(a);
	}
	
	@Override
	public EtatDocument empruntPar(Abonne a) {
		return new EtatEmprunte(a);
	}
	
	@Override
	public EtatDocument retour() throws RestrictionException {
		throw new RestrictionException("ce DVD n'a pas été emprunté, ni réservé");
	}

	public String toString() {
		return "libre";
	}
}
