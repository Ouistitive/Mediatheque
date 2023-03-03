package mediatheque;

public class Abonne {
	private int numero;
	private String nom;
	private String dateNaissance; // En format "jj/mm/aaaa"
	private final static int ANNEE_ACTUELLE = 2023;
	
	public Abonne(int numero, String nom, String dateNaissance) {
		this.numero = numero;
		this.nom = nom;
		this.dateNaissance = dateNaissance;
	}
	
	public int getAge() {
		return ANNEE_ACTUELLE - Integer.parseInt(dateNaissance.split("/")[2]);
	}
}
