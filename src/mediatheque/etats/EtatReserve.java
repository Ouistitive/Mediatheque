package mediatheque.etats;

import mediatheque.Abonne;
import mediatheque.EtatDocument;

public class EtatReserve extends EtatDocument {
	private Abonne reserveur;
	
	public EtatReserve(Abonne reserveur) {
		this.reserveur = reserveur;
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
	public EtatDocument empruntPar(Abonne a) {
		assert(a == reserveur);
		return new EtatEmprunte(a);
	}

}
