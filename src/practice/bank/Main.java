package practice.bank;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== 라이온 은행 시스템 ===");
        System.out.println("1. 고객 등록");
        System.out.println("2. 계좌 생성");
        System.out.println("3. 입금");
        System.out.println("4. 출금");
        System.out.println("5. 잔액 조회");
        System.out.println("6. 종료");

        Bank bank = new Bank();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("선택: ");
            int n = scanner.nextInt();
            scanner.nextLine();
            switch (n) {
                case 1: {
                    System.out.println("고객 ID 입력: ");
                    String id = scanner.nextLine();
                    System.out.println("고객 이름 입력: ");
                    String name = scanner.nextLine();

                    try {
                        bank.addCustomer(id, name);
                        System.out.println("고객 등록이 완료되었습니다.");
                    } catch (BankOperationException e) {
                        System.out.println("등록 실패: " + e.getMessage());
                    }
                    break;
                }
                case 2: {
                    System.out.println("고객 ID 입력: ");
                    String id = scanner.nextLine();

                    try {
                        Customer user = bank.findCustomer(id);
                        if (user == null) {
                            System.out.println("존재하지 않는 ID 입니다.");
                            break;
                        }
                        System.out.println("새 계좌 번호 입력: ");
                        String newAccountNumber = scanner.nextLine();
                        user.addAccountNumber(newAccountNumber);
                    } catch (BankOperationException e) {
                        System.out.println("계좌 생성 실패: " + e.getMessage());
                    }
                    break;
                }
                case 3: {
                    System.out.println("고객 ID 입력: ");
                    String id = scanner.nextLine();

                    Customer user = bank.findCustomer(id);
                    if (user == null) {
                        System.out.println("존재하지 않는 ID 입니다.");
                        break;
                    }
                    System.out.println("계좌 번호 입력: ");
                    String accoundNumber = scanner.nextLine();
                    try {
                        Account acc = user.findAccountNumber(accoundNumber);
                        System.out.println("입금 금액 입력: ");
                        int amount = scanner.nextInt();
                        scanner.nextLine();

                        acc.deposit(amount);
                    } catch (AccountNotFoundException e) {
                        System.out.println("계좌 조회 실패: " + e.getMessage());
                    } catch (InvalidTransactionException e) {
                        System.out.println("입금 실패: " + e.getMessage());
                    }
                    break;
                }
                case 4: {
                    System.out.println("고객 ID 입력: ");
                    String id = scanner.nextLine();

                    Customer user = bank.findCustomer(id);
                    if (user == null) {
                        System.out.println("존재하지 않는 ID 입니다.");
                        break;
                    }
                    System.out.println("계좌 번호 입력: ");
                    String accoundNumber = scanner.nextLine();
                    try {
                        Account acc = user.findAccountNumber(accoundNumber);
                        System.out.println("출금 금액 입력: ");
                        int amount = scanner.nextInt();
                        scanner.nextLine();

                        acc.withdraw(amount);
                    } catch (AccountNotFoundException e) {
                        System.out.println("계좌 조회 실패: " + e.getMessage());
                    } catch (InvalidTransactionException e) {
                        System.out.println("출금 실패: " + e.getMessage());
                    }
                    break;
                }
                case 5: {
                    System.out.println("고객 ID 입력: ");
                    String id = scanner.nextLine();

                    Customer user = bank.findCustomer(id);
                    if (user == null) {
                        System.out.println("존재하지 않는 ID 입니다.");
                        break;
                    }

                    System.out.println("계좌 번호 입력: ");
                    String accoundNumber = scanner.nextLine();
                    try {
                        Account acc = user.findAccountNumber(accoundNumber);
                        System.out.println("잔액: " + acc.getBalance());
                    } catch (AccountNotFoundException e) {
                        System.out.println("계좌 조회 실패: " + e.getMessage());
                    }
                    break;
                }
                case 6:
                    System.out.println("프로그램을 종료합니다.");
                    scanner.close();
                    return;
                default:
                    System.out.println("유효하지 않은 입력입니다.");
            }
        }

    }
}
