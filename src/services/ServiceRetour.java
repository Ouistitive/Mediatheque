package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import bttp2.Codage;
import jdbc.ConnexionBD;
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
			
			socketOut.println(Codage.coder("Numéro de document : "));
			int numDoc = Integer.parseInt(Codage.decoder(new String(socketIn.readLine())));
			
			try {
				ListeDocuments.getDocument(numDoc).retour();
				ConnexionBD.insererRetour(numDoc);
				
				
			} catch (RestrictionException e) {
				socketOut.println(e.toString());
				
			}
			fermer();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
