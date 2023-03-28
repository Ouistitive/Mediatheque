package services;

import java.io.IOException;
import java.net.Socket;

import bserveur.Service;

public abstract class AbstractService implements Service {
	private Socket client;
	
	public AbstractService(Socket s) {
		client = s;
	}
	
	public void lancer() {
		new Thread(this).start();
	}
	
	public void fermer() throws IOException {
		client.close();
	}
	
	public Socket getSocket() {
		return client;
	}
}
