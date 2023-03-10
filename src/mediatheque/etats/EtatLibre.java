package mediatheque.etats;

import mediatheque.Abonne;
import mediatheque.EtatDocument;

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

}
