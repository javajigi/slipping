package net.slipp.service.user;

public class ExistedUserException extends Exception {
	private static final long serialVersionUID = -2161492654834321369L;
	
	private String userId;
	
	public ExistedUserException(String userId) {
		this.userId = userId;
	}
	
	public String getUserId() {
		return userId;
	}
	
	@Override
	public String getMessage() {
		return String.format("%s는 이미 존재하는 아이디입니다.", this.userId);
	}
}
