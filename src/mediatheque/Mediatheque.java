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
	
	public void setLesAbonnes(IConnexionBD bd) {
		abonnes = bd.recupererAbonnes();
		
		/*for (Map.Entry mapentry : abonnes.entrySet()) {
		 System.out.println("clé: "+mapentry.getKey() 
		 + " | valeur: " + mapentry.getValue());
		}*/
	}
	
	public Abonne getAbonne(int i) {
		return abonnes.get(i);
	}
	
	//Documents
	
	private Map<Integer, Document> documents;
	
	public void setLesDocuments(IConnexionBD bd) {
		documents = bd.recupererDocuments(this);
	}
	
	public Document getDocument(int i) {
		return documents.get(i);
	}
	
	public String getString() {
		StringBuilder sb = new StringBuilder();
		
		for (Map.Entry<Integer, Document> mapentry : documents.entrySet()) {
			sb.append(mapentry.getValue() + "\n");
		}
		
		return sb.toString();
	}

	public void insererEmprunt(int numDoc, int numAbo) throws RestrictionException {
		getDocument(numDoc).empruntPar(getAbonne(numAbo));
		//Ne s'execute pas si empruntPar renvoie une Exception (donc pas de probleme de concurrence)
		bd.insererEmprunt(numDoc, numAbo);
		
	}

	public Date recupererDateEmprunt(int numDoc) {
		return bd.recupererDateEmprunt(numDoc);
	}

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

	public void reservationPour(int numDoc, int numAbo) throws RestrictionException {
		getDocument(numDoc).reservationPour(getAbonne(numAbo));
		
	}

	public void nouvelleAlerte(String adresse, int numDoc) {
		Mail m = new Mail(adresse, "Alerte document", getDocument(numDoc).toString() + " est de nouveau disponible ! Dépêchez-vous de le réserver !");
		ListeAlertes.ajouter(numDoc, m);
		
	}
	
	
	
	
}
