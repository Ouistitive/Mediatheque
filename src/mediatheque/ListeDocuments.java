package mediatheque;

import java.util.Map;

public class ListeDocuments {
	private static Map<Integer, Document> documents;
	
	public static void setLesDocuments(IConnexionBD bd) {
		documents = bd.recupererDocuments();
	}
	
	public static Document getDocument(int i) {
		return documents.get(i);
	}
	
	public static String getString() {
		StringBuilder sb = new StringBuilder();
		
		for (Map.Entry<Integer, Document> mapentry : documents.entrySet()) {
			sb.append(mapentry.getValue() + "\n");
		}
		
		return sb.toString();
	}
}
