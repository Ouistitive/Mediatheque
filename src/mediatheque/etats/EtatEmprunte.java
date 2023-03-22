package mediatheque.etats;

import mediatheque.Abonne;
import mediatheque.EtatDocument;
import mediatheque.RestrictionException;

public class EtatEmprunte extends EtatDocument {
	private Abonne emprunteur;
	
	public EtatEmprunte(Abonne emprunteur) {
		this.emprunteur = emprunteur;
	}
	
	public EtatDocument reservationPour(Abonne a) throws RestrictionException {
		throw new RestrictionException("ce DVD est déjà emprunté");
	}
	
	public EtatDocument empruntPar(Abonne a) throws RestrictionException {
		throw new RestrictionException("ce DVD est déjà emprunté");
	}
	
	@Override
	public Abonne emprunteur() {
		// TODO Auto-generated method stub
		return emprunteur;
	}
	@Override
	public EtatDocument retour() {
		return new EtatLibre();
	}

	public String toString() {
		return "emprunté";
	}
}
