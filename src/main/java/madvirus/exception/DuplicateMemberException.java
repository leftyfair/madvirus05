package madvirus.exception;

public class DuplicateMemberException extends RuntimeException {
	
	// 이메일 중복시 예외발생
	public DuplicateMemberException(String message) {
		super(message);
	}

}
