package xyz.firstlab.member;

public class DuplicateMemberException extends RuntimeException {
    public DuplicateMemberException(String message){
        super(message);
    }
}
