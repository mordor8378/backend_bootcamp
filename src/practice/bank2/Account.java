package practice.bank2;

public class Account {
    private String accountNumber;

    private String userID;

    private int money;

    public Account(String userID, String accountNumber) {
        this.accountNumber = accountNumber;
        this.userID = userID;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public int getMoney() {
        return money;
    }

    public Account(int money) {
        this.money = money;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void deposit(int money) throws InvalidTransactionException {
        if(money <= 0){
            throw new InvalidTransactionException("입금 내역이 0원 이하입니다. 다시 입력해주세요.");
        }//입금이 0 이하인 경우

        this.money += money;
        System.out.println(money + "원 입금되었습니다.");
        System.out.println("현재 잔고: " + this.money);
    }//입금


    public void withdraw(int money) throws InvalidTransactionException {
        if(money <= 0){
            throw new InvalidTransactionException("출금 내역이 0원 이하입니다. 다시 입력해주세요.");
        }//출금이 0 이하인 경우

        if(this.money - money < 0){
            throw new InvalidTransactionException("잔고가 부족합니다. 다시 출금해주세요.");
        }//잔고가 출금할 금액보다 적을 경우
        this.money -= money;
        System.out.println(money + "원 출금되었습니다.");
        System.out.println("현재 잔고: " + this.money);
    }//출금

}
