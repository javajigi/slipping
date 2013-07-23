package net.slipp.service.user;

public class PasswordMismatchException extends Exception {
	private static final long serialVersionUID = -124944399687435182L;

	@Override
	public String getMessage() {
		return "아이디와 비밀번호가 틀립니다.";
	}
}
