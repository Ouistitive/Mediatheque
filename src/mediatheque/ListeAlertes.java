package mediatheque;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jdbc.Mail;

public class ListeAlertes {
	private static Map<Integer, List<Mail>> mails; // Couple (mail, numero doc)
	
	static {
		mails = new HashMap<>();
	}
	
	public static void ajouter(int numDoc, Mail mail) {
		if(mails.get(numDoc) == null)
			mails.put(numDoc, new ArrayList<>());
		
		mails.get(numDoc).add(mail);
	}
	
	public static void envoyerMail(int numDoc) {
		for(Mail m : mails.get(numDoc)) {
			m.envoyer();
		}
	}
	
	public static void supprimer(int numDoc) {
		mails.remove(numDoc);
	}
}
