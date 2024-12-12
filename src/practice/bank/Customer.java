package practice.bank;

public class Customer {
    private String id;//ID
    private String name;//이름
    private Account[] account; //계좌
    private int accountCount;
    private static final int MAX_ACCOUNT = 5;

    public Customer(String id, String name) {
        this.id = id;
        this.name = name;
        this.account = new Account[MAX_ACCOUNT];
        this.accountCount = 0;
    }

    public String getId() {
        return id;
    }

    public Account findAccountNumber(String accountNumber) throws AccountNotFoundException {
        for (int i = 0; i < accountCount; i++) {
            if (account[i].getAccountNumber().equals(accountNumber)) {
                return account[i];
            }
        }
        throw new AccountNotFoundException("계좌 번호가 존재하지 않습니다.");
    }

    public void addAccountNumber(String accountNumber) throws BankOperationException {
        if (accountCount >= MAX_ACCOUNT) {
            throw new BankOperationException("더 이상 계좌를 개설할 수 없습니다.");
        }

        try {
            findAccountNumber(accountNumber);
            throw new BankOperationException("이미 존재하는 계좌 번호입니다.");
        } catch (AccountNotFoundException e) {
            account[accountCount++] = new Account(accountNumber, this.id);
        }
    }
}
