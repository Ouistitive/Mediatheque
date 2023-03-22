package mediatheque;

import java.util.Map;

import jdbc.ConnexionBD;

public class ListeDocuments {
	private static Map<Integer, Document> documents;
	
	public ListeDocuments() {
		documents = ConnexionBD.recupererDocuments();
		
		/*for (Map.Entry mapentry : documents.entrySet()) {
			 System.out.println("clé: "+mapentry.getKey() 
			 + " | valeur: " + mapentry.getValue());
		}*/
	}
	
	public static void setLesDocuments() {
		documents = ConnexionBD.recupererDocuments();
	}
	
	public static Document getDocument(int i) {
		return documents.get(i);
	}
	
	public static String getString() {
		StringBuilder sb = new StringBuilder();
		
		for (Map.Entry mapentry : documents.entrySet()) {
			sb.append(mapentry.getValue() + "\n");
		}
		
		return sb.toString();
	}
}
