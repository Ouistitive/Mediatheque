package jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import documents.DVD;
import mediatheque.Abonne;
import mediatheque.Document;
import mediatheque.IConnexionBD;
import mediatheque.ListeAbonnes;
import mediatheque.etats.EtatEmprunte;
import mediatheque.etats.EtatLibre;

public class ConnexionBD implements IConnexionBD{
	private static String url = "jdbc:mysql://localhost:3306/Mediatheque";
	private static String utilisateur = "root";
	private static String motDePasse = "root";
	
	private static Statement connexion() {
        try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, utilisateur, motDePasse);
			return con.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
        return null;
	}
	
	@Override
	public Map<Integer, Abonne> recupererAbonnes() {
		Map<Integer, Abonne> map = new HashMap<>();
		Statement stmt = connexion();
		
		try {
			ResultSet rs = stmt.executeQuery("SELECT * FROM Abonne;");
			
			while(rs.next()) {
	            int idAbo = rs.getInt("id");
	            String nom = rs.getString("nom");
	            Date dateNaiss = rs.getDate("dateNaiss");
	            Date dateBan = rs.getDate("dateBanni");
	            
	            GregorianCalendar c = new GregorianCalendar();
	            c.setTime(dateNaiss);
	            
	            GregorianCalendar dateBanni = new GregorianCalendar();
	            dateBanni.setTime(dateBan);
	            
	            map.put(idAbo, new Abonne(idAbo, nom, c, dateBanni));
	         }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return map;
	}
	
	@Override
	public Map<Integer, Document> recupererDocuments() {
		Map<Integer, Document> map = new HashMap<>();
		Statement stmt = connexion();
		try {
			ResultSet rs = stmt.executeQuery("SELECT * FROM DVD;");
			
			while(rs.next()) {
	            int idDoc = rs.getInt("id");
	            String titre = rs.getString("titre");
	            boolean adulte = rs.getBoolean("adulte");
	            int dispo = rs.getInt("abonneId");
	            
	            map.put(idDoc, 
	            	new DVD(idDoc, titre, adulte, 
	            	(dispo == 0 ? 
	            		new EtatLibre() 
	            	: new EtatEmprunte(ListeAbonnes.getAbonne(dispo)))));
	            
	         }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return map;
	}
	
	public static void insererEmprunt(int idDoc, int idAbo) {
		Statement stmt = connexion();
		try {
			stmt.executeUpdate("UPDATE DVD SET AbonneId = " + idAbo + ", dernierEmprunt = sysdate() WHERE id = " + idDoc);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void insererRetour(int idDoc) {
		Statement stmt = connexion();
		try {
			stmt.executeUpdate("UPDATE DVD SET AbonneId = null WHERE id = " + idDoc);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void bannirAbonne(int numAbo, GregorianCalendar dateBanni) {
		Statement stmt = connexion();
		try {
			stmt.executeUpdate("UPDATE Abonne SET dateBanni = "
			+ "'" + dateBanni.get(Calendar.YEAR) + "-" + (dateBanni.get(Calendar.MONTH)+1) + "-" + dateBanni.get(Calendar.DAY_OF_MONTH) + ""
					+ "' WHERE id = " + numAbo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Date recupererDateEmprunt(int numDoc) {
		Statement stmt = connexion();
		try {
			ResultSet rs = stmt.executeQuery("SELECT dernierEmprunt FROM DVD WHERE id = " + numDoc);
			if(rs.next())
				return rs.getDate("dernierEmprunt");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
