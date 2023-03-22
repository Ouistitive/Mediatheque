package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import static bttp2.Codage.*;

public class ClientBorne {
	private final static int PORT_EMPRUNT = 4000;
	private final static int PORT_RETOUR = 5000;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("Quel service voulez-vous accéder : \n1. Emprunter\n2. Retour");
			int choix = sc.nextInt();
			if(choix == 1)
				emprunt(sc);
			else if(choix == 2)
				retour(sc);
			else
				System.out.println("Ce service n'existe pas");
		}
	}
	
	public static void emprunt(Scanner sc) {
		try {
			Socket socket = new Socket("localhost", PORT_EMPRUNT);
			System.out.println("connecté");
			
			PrintWriter socketOut = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			System.out.println(decoder(socketIn.readLine()));
			socketOut.println(coder(sc.next()));
			
			System.out.println(decoder(socketIn.readLine()));
			socketOut.println(coder(sc.next()));
			
			System.out.println(decoder(socketIn.readLine()));
			
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void retour(Scanner sc) {
		try {
			Socket socket = new Socket("localhost", PORT_RETOUR);
			System.out.println("connecté");
			
			PrintWriter socketOut = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			System.out.println(socketIn.readLine());
			socketOut.println(sc.next());
			
			System.out.println(socketIn.readLine());
			
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
