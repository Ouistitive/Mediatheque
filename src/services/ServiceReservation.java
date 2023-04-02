package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import mediatheque.IndisponibleException;
import mediatheque.RestrictionException;

public class ServiceReservation extends AbstractService {
	public ServiceReservation(Socket s) {
		super(s);
	}

	@Override
	public void run() {
		try {
			BufferedReader socketIn = new BufferedReader(new InputStreamReader(getSocket().getInputStream()));
			PrintWriter socketOut = new PrintWriter(getSocket().getOutputStream(), true);
			
			
			socketOut.println(Codage.coder(mediatheque.getString() + "\n" + "Numéro d'abonné : "));

			int numAbo = Integer.parseInt(Codage.decoder(new String(socketIn.readLine())));
			socketOut.println(Codage.coder("Numéro de document : "));
			int numDoc = Integer.parseInt(Codage.decoder(new String(socketIn.readLine())));
			
			try {
				mediatheque.reservationPour(numDoc, numAbo);
				
				
				
			} catch (IndisponibleException e) {
				socketOut.println(Codage.coder(e.toString() + "##Voulez-vous recevoir un mail lorsque le"
						+ " document sera retourné ?##1. Oui##2. Non##"));
				
				boolean doitEnvoyerMail = Integer.parseInt(Codage.decoder(new String(socketIn.readLine()))) == 1;
				if(doitEnvoyerMail) {
					socketOut.println(Codage.coder("Votre adresse email : "));
					String adresse = Codage.decoder(new String(socketIn.readLine()));
					
					mediatheque.nouvelleAlerte(adresse, numDoc);
					
					
				}
			} catch (RestrictionException e) {
				socketOut.println(Codage.coder(e.toString()));
			}
			fermer();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
