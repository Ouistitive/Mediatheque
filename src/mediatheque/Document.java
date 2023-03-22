package mediatheque;

public interface Document {
	int numero();
	
	// @return null si pas emprunte ou pas reserve
	Abonne emprunteur(); // Abonne qui a emprunte ce document
	Abonne reserveur(); // Abonne qui a reserve ce document
	
	// @pre ni reserve ni emprunte
	void reservationPour(Abonne ab) throws RestrictionException;
	
	//@pre libre ou r�serv� par l�abonn� qui vient emprunter
	void empruntPar(Abonne ab) throws RestrictionException;	
	
	// @brief retour d'un document ou annulation d'une reservation
	void retour() throws RestrictionException;
}
