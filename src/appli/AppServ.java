package appli;

import java.io.IOException;

import bserveur.Serveur;
import mediatheque.Mediatheque;
import services.ServiceEmprunt;
import services.ServiceReservation;
import services.ServiceRetour;

public class AppServ {
	public static void main(String[] args) {		
		final int PORT_RESERVATION = 3000;
		final int PORT_EMPRUNT = 4000;
		final int PORT_RETOUR = 5000;
		ConnexionBD bd = new ConnexionBD();
		Mediatheque m = new Mediatheque(bd);
		ServiceReservation.setMediatheque(m);
		ServiceEmprunt.setMediatheque(m);
		ServiceRetour.setMediatheque(m);
		
		m.setLesAbonnes(bd);
		m.setLesDocuments(bd);
		
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
