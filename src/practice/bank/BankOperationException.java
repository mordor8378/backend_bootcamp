package practice.bank;

public class BankOperationException extends Exception {
    public BankOperationException() {}
    public BankOperationException(String message) {
        super(message);
    }
}
