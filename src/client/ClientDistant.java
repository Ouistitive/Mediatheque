package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import static bttp2.Codage.*;

public class ClientDistant {

	public static void main(String[] args) {
		final int PORT_RESERVATION = 3000;
		
		try {
			Socket socket = new Socket("localhost", PORT_RESERVATION);
			System.out.println("connecté");
			Scanner sc = new Scanner(System.in);
			
			PrintWriter socketOut = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			System.out.println(decoder(socketIn.readLine()));
			socketOut.println(coder(sc.next()));
			
			System.out.println(decoder(socketIn.readLine()));
			socketOut.println(coder(sc.next()));
			
			System.out.println(decoder(socketIn.readLine()));
			
			socket.close();
			sc.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
