package mediatheque;

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
	
	public Socket getSocket() {
		return client;
	}
}
