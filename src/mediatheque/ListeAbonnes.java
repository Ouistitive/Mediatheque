package mediatheque;

import java.util.Map;

import jdbc.ConnexionBD;

public class ListeAbonnes {
	private static Map<Integer, Abonne> abonnes;
	
	public static void setLesAbonnes(IConnexionBD bd) {
		abonnes = bd.recupererAbonnes();
		
		/*for (Map.Entry mapentry : abonnes.entrySet()) {
		 System.out.println("clé: "+mapentry.getKey() 
		 + " | valeur: " + mapentry.getValue());
		}*/
	}
	
	public static Abonne getAbonne(int i) {
		return abonnes.get(i);
	}
}
