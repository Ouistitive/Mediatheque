package appli;

import java.io.IOException;

import bserveur.Serveur;
import mediatheque.ListeAbonnes;
import mediatheque.ListeDocuments;
import mediatheque.service.ServiceEmprunt;
import mediatheque.service.ServiceReservation;
import mediatheque.service.ServiceRetour;

public class Appli {
	public static void main(String[] args) {	
		final int PORT_RESERVATION = 3000;
		final int PORT_EMPRUNT = 4000;
		final int PORT_RETOUR = 5000;
		
		ListeAbonnes.setLesAbonnes();
		ListeDocuments.setLesDocuments();
		
		try {
			new Thread(new Serveur(ServiceReservation.class, PORT_RESERVATION)).start();
			new Thread(new Serveur(ServiceEmprunt.class, PORT_EMPRUNT)).start();
			new Thread(new Serveur(ServiceRetour.class, PORT_RETOUR)).start();
		}
		catch(IOException e) {
			System.err.println("Problème erreur lors de la création du serveur : " + e);
		}
	}
}
