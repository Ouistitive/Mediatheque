package services;

import java.io.IOException;
import java.net.Socket;

import bserveur.Service;
import bttp2.Codage;
import mediatheque.Mediatheque;

public abstract class AbstractService implements Service {
	protected static Mediatheque mediatheque;
	private Socket client;
	
	public AbstractService(Socket s) {
		client = s;
	}
	
	protected String encoder(String msg) {
		return Codage.coder(msg);
	}
	protected String decoder(String msg) {
		return Codage.decoder(msg);
	}
	
	public static void setMediatheque(Mediatheque m) {
		mediatheque = m;
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
