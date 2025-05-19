package exceptions;

public class UnsuitablePasswordException extends RuntimeException {
    public UnsuitablePasswordException() {
        super("Пароль не подходит под требования");
    }
}
