package mediatheque;

public abstract class EtatDocument {
	public Abonne emprunteur() {return null;}
	public Abonne reserveur() {return null;}
	public boolean estLibre() {return false;}
	public EtatDocument reservationPour(Abonne a) {
		throw new RuntimeException();
	}
	public EtatDocument retour() {
		throw new RuntimeException();
	}
	public EtatDocument empruntPar(Abonne a) {
		throw new RuntimeException();
	}
}
