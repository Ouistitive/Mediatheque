package mediatheque;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;


public class Mediatheque {
	
	private Regles regles;
	private IConnexionBD bd;

	public Mediatheque(IConnexionBD bd) {
		this.bd = bd;
		this.regles = new Regles();
	}
	
	//abonnes
	private Map<Integer, Abonne> abonnes;
	/**
	 * @brief Récupère les instances d'abonnes de la BD
	 * @param Implémentaton de IConnexionBD 
	 */
	public void setLesAbonnes(IConnexionBD bd) {
		abonnes = bd.recupererAbonnes();
		
		/*for (Map.Entry mapentry : abonnes.entrySet()) {
		 System.out.println("clé: "+mapentry.getKey() 
		 + " | valeur: " + mapentry.getValue());
		}*/
	}
	
	/**
	 * @brief Renvoie l'instance d'abonne correspondant au numéro donné
	 * @param int i, numéro de l'abonne
	 * @return Abonne correspondant, null si aucun
	 */
	public Abonne getAbonne(int i) {
		return abonnes.get(i);
	}
	
	//Documents
	
	private Map<Integer, Document> documents;
	
	/**
	 * @brief Récupère les instances de documents de la BD
	 * @param Implémentaton de IConnexionBD 
	 */
	public void setLesDocuments(IConnexionBD bd) {
		documents = bd.recupererDocuments(this);
	}
	
	/**
	 * @brief Renvoie l'instance de Document correspondant au numéro donné
	 * @param int i, numéro du document
	 * @return Document correspondant, null si aucun
	 */
	public Document getDocument(int i) {
		return documents.get(i);
	}
	
	/**
	 * @brief Renvoie le catalogue de documents
	 * @return String du catalogue
	 */
	public String getCatalogue() {
		StringBuilder sb = new StringBuilder();
		
		for (Map.Entry<Integer, Document> mapentry : documents.entrySet()) {
			sb.append(mapentry.getValue() + "\n");
		}
		
		return sb.toString();
	}

	/**
	 * @brief Enregistre un emprunt, dynamiquement et dans la BD
	 * @param numDoc
	 * @param numAbo
	 * @throws RestrictionException si c'est impossible
	 */
	public void insererEmprunt(int numDoc, int numAbo) throws RestrictionException {
		getDocument(numDoc).empruntPar(getAbonne(numAbo));
		//Ne s'execute pas si empruntPar renvoie une Exception (donc pas de probleme de concurrence)
		bd.insererEmprunt(numDoc, numAbo);
		
	}

	/**
	 * @brief Recupère la date d'emprunt d'un document
	 * @param numDoc
	 * @return Date, 1 jan 1970 si aucun emprunt, null si le docuement est inexistant
	 */
	public Date recupererDateEmprunt(int numDoc) {
		return bd.recupererDateEmprunt(numDoc);
	}

	/**
	 * @brief Retourne un document, alerte en mail si besoin, applique les regles.
	 * @param numDoc
	 * @param abime
	 * @throws RestrictionException
	 */
	public void insererRetour(int numDoc, boolean abime) throws RestrictionException {
		Abonne emprunteur = getDocument(numDoc).emprunteur();
		int numAbo = emprunteur==null ? 0 : emprunteur.getNumero();
		
		
		getDocument(numDoc).retour();
		bd.insererRetour(numDoc);
		
		if(abime || !regles.retourDansLesTemps(numDoc, bd)) {
			bannir(numAbo);
		}
		ListeAlertes.envoyerMail(numDoc);
		ListeAlertes.supprimer(numDoc);
		
	}

	/**
	 * @brief Ban un abonne pendant 1 mois
	 * @param numAbo : Le numero de l'abonne banni
	 */
	private void bannir(int numAbo) {
		GregorianCalendar dateBan = new GregorianCalendar();
		dateBan.setTime(new Date());
		dateBan.add(Calendar.MONTH, 1);
		
		getAbonne(numAbo).bannir(dateBan);
		bd.bannirAbonne(numAbo, dateBan);
	}

	/**
	 * @brief Enregistre une reservation
	 * @param numDoc
	 * @param numAbo
	 * @throws RestrictionException
	 */
	public void reservationPour(int numDoc, int numAbo) throws RestrictionException {
		getDocument(numDoc).reservationPour(getAbonne(numAbo));
		
	}

	/**
	 * @brief Enregistre une adresse email à laquelle sera envoyé une alerte de retour du document.
	 * @param adresse, adresse email valide
	 * @param numDoc
	 */
	public void nouvelleAlerte(String adresse, int numDoc) {
		if(getDocument(numDoc) == null) throw new IllegalArgumentException("Document inexistant");
		
		Mail m = new Mail(adresse, "Alerte document", getDocument(numDoc).toString() + " est de nouveau disponible ! Dépêchez-vous de le réserver !");
		ListeAlertes.ajouter(numDoc, m);
		
	}
	
	
	
	
}
