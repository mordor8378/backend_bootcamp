package practice.bank;

public class Account {
    private String accountNumber;
    private String userId;
    private int balance;

    public Account(String accountNumber, String userId) {
        this.accountNumber = accountNumber;
        this.userId = userId;
        balance = 0;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public int getBalance() {
        return balance;
    }

    public void deposit(int amount) throws InvalidTransactionException {
        if (amount <= 0) {
            throw new InvalidTransactionException("0원 이하의 금액은 입금할 수 없습니다.");
        }
        balance += amount;
        System.out.println(amount + "원이 입금되었습니다.");
        System.out.println("현재 잔고: " + balance + "원");
    }

    public void withdraw(int amount) throws InvalidTransactionException {
        if (amount <= 0) {
            throw new InvalidTransactionException("0원 이하의 금액은 출금할 수 없습니다.");
        }
        if (amount > balance) {
            throw new InvalidTransactionException("잔고가 부족합니다.");
        }
        balance -= amount;
        System.out.println(amount + "원이 출금되었습니다.");
        System.out.println("현재 잔고: " + balance + "원");
    }
}
