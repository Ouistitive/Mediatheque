package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import bttp2.Codage;
import mediatheque.ListeAbonnes;
import mediatheque.ListeDocuments;
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
			
			
			socketOut.println(Codage.coder(ListeDocuments.getString() + "\n" + "Num�ro d'abonn� : "));

			int numAbo = Integer.parseInt(Codage.decoder(new String(socketIn.readLine())));
			socketOut.println(Codage.coder("Num�ro de document : "));
			int numDoc = Integer.parseInt(Codage.decoder(new String(socketIn.readLine())));
			
			try {
				ListeDocuments.getDocument(numDoc).reservationPour(ListeAbonnes.getAbonne(numAbo));
				
				
			} catch (RestrictionException e) {
				socketOut.println(e.toString());
				
			}
			fermer();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
