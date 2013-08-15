package net.slipp.domain.user;

public class PasswordMismatchException extends Exception {
    private static final long serialVersionUID = 5500971489909200610L;

    public PasswordMismatchException() {
		super();
	}

	public PasswordMismatchException(String arg0) {
		super(arg0);
	}
}
