package mediatheque;

import java.util.Map;

public interface IConnexionBD {

	Map<Integer, Abonne> recupererAbonnes();

	Map<Integer, Document> recupererDocuments();

}
