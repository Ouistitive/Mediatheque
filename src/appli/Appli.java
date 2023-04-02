package appli;

import java.io.IOException;

import bserveur.Serveur;
import jdbc.ConnexionBD;
import jdbc.Mail;
import mediatheque.ListeAbonnes;
import mediatheque.ListeAlertes;
import mediatheque.ListeDocuments;
import services.ServiceEmprunt;
import services.ServiceReservation;
import services.ServiceRetour;

public class Appli {
	public static void main(String[] args) {		
		final int PORT_RESERVATION = 3000;
		final int PORT_EMPRUNT = 4000;
		final int PORT_RETOUR = 5000;
		ConnexionBD bd = new ConnexionBD();
		
		ListeAbonnes.setLesAbonnes(bd);
		ListeDocuments.setLesDocuments(bd);
		
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
