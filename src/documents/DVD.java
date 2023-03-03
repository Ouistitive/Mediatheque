package documents;

import mediatheque.Abonne;
import mediatheque.AbstractDocument;

public class DVD extends AbstractDocument {
	private boolean adulte; // true, si il est reserve au plus de 16 ans
	private final static int AGE_MINIMAL = 16;

	public DVD(int num, String titre, boolean adulte) {
		super(num, titre);
		this.adulte = adulte;
	}
	
	@Override
	/**
	 * @brief Reserve le document pour l'abonne
	 * @param ab : l'abonne qui reserve
	 */
	public void reservationPour(Abonne ab) {
		if(adulte) {
			if(ab.getAge() >= AGE_MINIMAL)
				super.reservationPour(ab);
		}
		else
			super.reservationPour(ab);
	}

	@Override
	public void empruntPar(Abonne ab) {
		if(adulte) {
			if(ab.getAge() >= AGE_MINIMAL)
				super.empruntPar(ab);
		}
		else
			super.empruntPar(ab);
	}
}
