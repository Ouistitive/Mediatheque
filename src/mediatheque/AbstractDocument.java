package mediatheque;

public abstract class AbstractDocument implements Document {
	private int numero;
	private String titre;

	private Abonne abonneReserve;
	private Abonne abonneEmprunt;
	
	public AbstractDocument(int numero, String titre) {
		this.numero = numero;
		this.titre = titre;
		this.abonneReserve = null;
		this.abonneEmprunt = null;
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
		return this.abonneEmprunt;
	}

	@Override
	/**
	 * @brief Retourne l'abonne qui a reserve le document
	 * @return Abonne
	 */
	public Abonne reserveur() {
		return this.abonneReserve;
	}

	@Override
	/**
	 * @brief Reserve le document pour l'abonne
	 * @param ab : l'abonne qui reserve
	 * @pre le document doit etre libre
	 */
	public void reservationPour(Abonne ab) {
		assert(estLibre());
		abonneReserve = ab;
	}
	
	@Override
	/**
	 * @brief Emprunte le document pour un abonne
	 * @param ab : l'abonne qui emprunte
	 * @pre Le document doit etre libre ou l'abonne qui emprunte est le meme
	 * que celui qui a reserve
	 */
	public void empruntPar(Abonne ab) {
		assert(estLibre() || abonneReserve == ab);
		abonneEmprunt = ab;
		abonneReserve = null;
	}

	@Override
	/**
	 * @brief Retourne l'abonne qui a emprunte le document
	 */
	public void retour() {
		abonneEmprunt = null;
	}
	
	/**
	 * @brief Teste sur le document est libre (ni reserve, ni emprunte)
	 * @return boolean : true, si il est libre
	 */
	private boolean estLibre() {
		return abonneReserve == null && abonneEmprunt == null;
	}
	
	public String toString() {
		return titre + " (" + numero + ")";
	}
}
