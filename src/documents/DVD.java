package documents;

import mediatheque.Abonne;
import mediatheque.AbstractDocument;
import mediatheque.EtatDocument;
import mediatheque.RestrictionException;

public class DVD extends AbstractDocument {
	private boolean adulte; // true, si il est reserve au plus de 16 ans
	private final static int AGE_MINIMAL = 16;

	public DVD(int num, String titre, boolean adulte, EtatDocument etat) {
		super(num, titre, etat);
		this.adulte = adulte;
	}
	
	@Override
	/**
	 * @brief Reserve le document pour l'abonne
	 * @param ab : l'abonne qui reserve
	 */
	public void reservationPour(Abonne ab) throws RestrictionException {
		if(adulte) {
			if(ab.getAge() >= AGE_MINIMAL)
				super.reservationPour(ab);
			else
				throw new RestrictionException("vous n’avez pas l’âge pour emprunter ce DVD");
		}
		else
			super.reservationPour(ab);
	}

	@Override
	public void empruntPar(Abonne ab) throws RestrictionException {
		if(adulte) {
			if(ab.getAge() >= AGE_MINIMAL)
				super.empruntPar(ab);
			else
				throw new RestrictionException("vous n’avez pas l’âge pour emprunter ce DVD");
		}
		else
			super.empruntPar(ab);
	}
}
