package employeeManagement.exception;

public class DataBaseException extends RuntimeException {
	
	public DataBaseException(String message, Throwable e) {
		super(message, e);
	}
	
	public DataBaseException(String message) {
		super(message);
	}
	
	public DataBaseException(Throwable e) {
		super(e);
	}
	
}