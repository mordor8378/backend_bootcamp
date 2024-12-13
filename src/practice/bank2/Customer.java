package practice.bank2;

import java.util.Scanner;

public class Customer {
    private String id;//ID
    private String password;
    private String name;//이름
    private Account[] accounts; //계좌

    private static final int MAX_ACCOUNT = 5;

    public Customer(String id, String name, Account[] accounts) {
        this.id = id;
        this.name = name;
        this.accounts = accounts;
    }

    public Customer(String id, String password, String name) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.accounts = new Account[0];
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public Account[] getAccounts() {
        return accounts;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAccounts(Account[] accounts) {
        this.accounts = accounts;
    }


    public Account findAccountNumber(String accountNumber) throws AccountNotFoundException {
        for (Account account : this.accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        throw new AccountNotFoundException("존재하는 계좌 번호가 없습니다. 다른 번호로 조회 부탁드립니다.");
    }

    public void showAccountNumbers() throws AccountNotFoundException {
        if(this.accounts.length == 0){
            throw new AccountNotFoundException("현재 고객님이 가지고 계시는 계좌번호가 없습니다.");
        }
        System.out.println("< 고객 계좌번호 조회 결과 >");
        int index = 1;
        for (Account account : this.accounts) {
            System.out.print(index + ": ");
            System.out.println(account.getAccountNumber());
            index++;
        }
    }

    public void addAccountNumber() throws BankOperationException {
        if (this.accounts.length >= MAX_ACCOUNT) {
            throw new BankOperationException("개설 가능한 계좌를 초과하였습니다. 다음에 다시 이용해주세요.");
        }

        Scanner scanner = new Scanner(System.in);

        System.out.print("고객님이 개설하실 통장 계좌번호: ");
        String accountNumber = scanner.next();

        // 새 계좌 배열 생성 및 추가
        Account newAccount = new Account(this.id, accountNumber);
        Account[] updatedAccounts = new Account[this.accounts.length + 1];
        System.arraycopy(this.accounts, 0, updatedAccounts, 0, this.accounts.length);
        updatedAccounts[this.accounts.length] = newAccount;
        this.accounts = updatedAccounts;

        System.out.println("계좌가 성공적으로 생성되었습니다. " + this.id + "님의 계좌번호: (" + accountNumber + ")");
    }



}