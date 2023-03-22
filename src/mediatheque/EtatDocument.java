package mediatheque;

public abstract class EtatDocument {
	
	private Document doc;
	
	public EtatDocument() {
		;
	}
	
	public EtatDocument(Document d) {
		this.doc = d;
	}
	
	public void setDocument(Document d) {
		if(this.doc == null)
			this.doc = d;
		else
			throw new IllegalStateException("Le document ne peut pas être modifié");
	}
	
	protected Document getDoc() {return this.doc;}
	
	
	public Abonne emprunteur() {return null;}
	public Abonne reserveur() {return null;}
	public boolean estLibre() {return false;}
	
	
	
	public EtatDocument reservationPour(Abonne a) throws RestrictionException {
		throw new RuntimeException();
	}
	public EtatDocument retour() throws RestrictionException {
		throw new RuntimeException();
	}
	public EtatDocument empruntPar(Abonne a) throws RestrictionException {
		throw new RuntimeException();
	}
	public String toString() {
		return null;
	}
}
