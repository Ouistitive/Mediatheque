package mediatheque.etats;

import mediatheque.Abonne;
import mediatheque.EtatDocument;

public class EtatEmprunte extends EtatDocument {
	private Abonne emprunteur;
	
	public EtatEmprunte(Abonne emprunteur) {
		this.emprunteur = emprunteur;
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

}
