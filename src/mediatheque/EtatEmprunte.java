package mediatheque;

public class EtatEmprunte extends EtatDocument {
	private Abonne emprunteur;
	
	public EtatEmprunte(Abonne emprunteur) {
		super();
		this.emprunteur = emprunteur;
	}
	
	public EtatEmprunte(Document d, Abonne emprunteur) {
		super(d);
		this.emprunteur = emprunteur;
	}
	
	public EtatDocument reservationPour(Abonne a) throws RestrictionException {
		throw new IndisponibleException(super.getDoc().toString() +" est d�j� emprunt�");
	}
	
	public EtatDocument empruntPar(Abonne a) throws RestrictionException {
		throw new IndisponibleException(super.getDoc().toString() +" est d�j� emprunt�");
	}
	
	@Override
	public Abonne emprunteur() {
		// TODO Auto-generated method stub
		return emprunteur;
	}
	@Override
	public EtatDocument retour() {
		return new EtatLibre(super.getDoc());
	}

	public String toString() {
		return "emprunt�";
	}
}
