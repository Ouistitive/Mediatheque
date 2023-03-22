package mediatheque;

public class RestrictionException extends Throwable {
	private String message;
	
	public RestrictionException(String msg) {
		message = msg;
	}
	
	public String toString() {
		return message;
	}
}
