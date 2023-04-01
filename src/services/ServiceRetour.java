package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import bttp2.Codage;
import jdbc.ConnexionBD;
import mediatheque.CertificationBretteSoft;
import mediatheque.ListeAbonnes;
import mediatheque.ListeDocuments;
import mediatheque.RestrictionException;

public class ServiceRetour extends AbstractService {
	
	public ServiceRetour(Socket s) {
		super(s);
	}

	@Override
	public void run() {
		try {
			BufferedReader socketIn = new BufferedReader(new InputStreamReader(getSocket().getInputStream()));
			PrintWriter socketOut = new PrintWriter(getSocket().getOutputStream(), true);
			
			socketOut.println(Codage.coder("Numéro de l'abonné : "));
			int numAbo = Integer.parseInt(Codage.decoder(new String(socketIn.readLine())));
			
			socketOut.println(Codage.coder("Numéro de document : "));
			int numDoc = Integer.parseInt(Codage.decoder(new String(socketIn.readLine())));
			
			socketOut.println(Codage.coder("Le document a t'il été abimé ?##1. Oui##2. Non##"));
			boolean abime = Integer.parseInt(Codage.decoder(new String(socketIn.readLine()))) == 1;
			
			try {
				
				Date dateRetour = ConnexionBD.recupererDateEmprunt(numDoc);
				GregorianCalendar dateMaximale = new GregorianCalendar();
				dateMaximale.setTime(dateRetour);
				dateMaximale.add(Calendar.DAY_OF_MONTH, 14);
				GregorianCalendar dateRetourGregorian = new GregorianCalendar();
				dateRetourGregorian.setTime(new Date());
				
				System.out.print(dateRetourGregorian.get(Calendar.DAY_OF_MONTH) + "/" + dateRetourGregorian.get(Calendar.MONTH) + "/" + dateRetourGregorian.get(Calendar.YEAR));

				System.out.println(" <= " + dateMaximale.get(Calendar.DAY_OF_MONTH) + "/" + dateMaximale.get(Calendar.MONTH) + "/" + dateMaximale.get(Calendar.YEAR));
				
				ListeDocuments.getDocument(numDoc).retour(ListeAbonnes.getAbonne(numAbo));
				ConnexionBD.insererRetour(numDoc);
				
				if(abime || !CertificationBretteSoft.retourDansLesTemps(numDoc)) {
					bannir(numAbo);
				}
				
				socketOut.println("Retour réussi");
			} catch (RestrictionException e) {
				socketOut.println(e.toString());
				
			}
			fermer();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @brief Ban un abonne pendant 1 mois
	 * @param numAbo : Le numero de l'abonne banni
	 */
	private void bannir(int numAbo) {
		GregorianCalendar dateBan = new GregorianCalendar();
		dateBan.setTime(new Date());
		dateBan.add(Calendar.MONTH, 1);
		
		ListeAbonnes.getAbonne(numAbo).bannir(dateBan);
		ConnexionBD.bannirAbonne(numAbo, dateBan);
	}
}
