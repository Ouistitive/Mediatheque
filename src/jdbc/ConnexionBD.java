package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

import mediatheque.Document;

public class ConnexionBD {
	private static String url = "jdbc:mysql://localhost:3306/Mediatheque";
	private static String utilisateur = "root";
	private static String motDePasse = "root";
	
	private static void connexion() {
        try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, utilisateur, motDePasse);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static HashMap<Integer, Document> recupererDocuments() {
		connexion();
		
		return null;
	}
}
