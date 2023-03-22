package mediatheque;

import java.util.Timer;
import java.util.TimerTask;

public abstract class AbstractDocument implements Document {
	private int numero;
	private String titre;

	private EtatDocument etat;
	
	public AbstractDocument(int numero, String titre, EtatDocument etat) {
		this.numero = numero;
		this.titre = titre;
		this.etat = etat;
		this.etat.setDocument(this);
	}
	
	@Override
	public int numero() {
		return this.numero;
	}

	@Override
	/**
	 * @brief Retourne l'abonne qui a emprunte le document
	 * @return Abonne
	 */
	public Abonne emprunteur() {
		return this.etat.emprunteur();
	}

	@Override
	/**
	 * @brief Retourne l'abonne qui a reserve le document
	 * @return Abonne
	 */
	public Abonne reserveur() {
		return this.etat.reserveur();
	}

	@Override
	/**
	 * @brief Reserve le document pour l'abonne
	 * @param ab : l'abonne qui reserve
	 * @pre le document doit etre libre
	 */
	public void reservationPour(Abonne ab) throws RestrictionException {
		etat = etat.reservationPour(ab);
		
	}
	
	@Override
	/**
	 * @brief Emprunte le document pour un abonne
	 * @param ab : l'abonne qui emprunte
	 * @pre Le document doit etre libre ou l'abonne qui emprunte est le meme
	 * que celui qui a reserve
	 */
	public void empruntPar(Abonne ab) throws RestrictionException {
		etat = etat.empruntPar(ab);
	}

	@Override
	/**
	 * @brief Retourne l'abonne qui a emprunte le document
	 */
	public void retour() throws RestrictionException {
		etat = etat.retour();
	}
	
	/**
	 * @brief Teste sur le document est libre (ni reserve, ni emprunte)
	 * @return boolean : true, si il est libre
	 */
	private boolean estLibre() {
		return etat.estLibre();
	}
	
	public String toString() {
		return "Numero : " + numero + ", " + titre + " (" + etat + ")";
	}
	
	
	
	
	
}
