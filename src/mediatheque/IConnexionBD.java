package mediatheque;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

public interface IConnexionBD {

	Map<Integer, Abonne> recupererAbonnes();

	Map<Integer, Document> recupererDocuments(Mediatheque m);

	Date recupererDateEmprunt(int numDoc);

	void insererEmprunt(int numDoc, int numAbo);

	void insererRetour(int numDoc);

	void bannirAbonne(int numAbo, GregorianCalendar dateBan);

}
