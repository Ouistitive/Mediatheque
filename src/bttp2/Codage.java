package bttp2;

public class Codage {
	public static String coder(String txt) {
		return txt.replaceAll("\n", "##");
	}
	
	public static String decoder(String txt) {
		return txt.replaceAll("##", "\n");
	}
}
