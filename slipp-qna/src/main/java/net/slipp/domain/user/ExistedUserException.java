package net.slipp.domain.user;

public class ExistedUserException extends Exception {
    private static final long serialVersionUID = -807405871630591194L;

    public ExistedUserException() {
		super();
	}

	public ExistedUserException(String arg0) {
		super(arg0);
	}
}
