package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import mediatheque.RestrictionException;

public class ServiceEmprunt extends AbstractService {
	public ServiceEmprunt(Socket s) {
		super(s);
	}

	@Override
	public void run() {
		try {
			BufferedReader socketIn = new BufferedReader(new InputStreamReader(getSocket().getInputStream()));
			PrintWriter socketOut = new PrintWriter(getSocket().getOutputStream(), true);
			
			socketOut.println(encoder("Numéro d'abonné : "));
			int numAbo = Integer.parseInt(decoder(new String(socketIn.readLine())));
			socketOut.println(encoder("Numéro de document : "));
			int numDoc = Integer.parseInt(decoder(new String(socketIn.readLine())));
			
			try {
				mediatheque.insererEmprunt(numDoc, numAbo);
				
			} catch (RestrictionException e) {
				socketOut.println(e.toString());
			}
			fermer();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
