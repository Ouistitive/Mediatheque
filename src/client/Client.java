package client;

import static bttp2.Codage.coder;
import static bttp2.Codage.decoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) {
		final int PORT = Integer.parseInt(args[0]);
		
		try (
				Socket socket = new Socket("localhost", PORT);
				Scanner sc = new Scanner(System.in);
			){
			
			System.out.println("connecté");
			PrintWriter socketOut = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			String recu = socketIn.readLine();
			while(recu != null) {
				System.out.println(decoder(recu));
				socketOut.println(coder(sc.next()));
				recu = socketIn.readLine();
			}
			
		} catch (IOException e) {
			
		}
		System.out.println("Connexion terminée");
	}
}
