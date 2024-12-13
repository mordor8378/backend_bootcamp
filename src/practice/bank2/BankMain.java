package practice.bank2;

import java.util.Scanner;

public class BankMain {
    public static void main(String[] args) {
        Bank bank = new Bank();
        Scanner scanner = new Scanner(System.in);

        while(true) {
            showMain();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice) {
                case 1: // 로그인
                    login(scanner, bank);
                    break;
                case 2: // 고객 등록
                    register(scanner, bank);
                    break;
                case 3: // 종료
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("잘못된 선택입니다.");
            }
        }
    }

    private static void showMain() {
        System.out.println("\n=== 라이온 은행 시스템 ===");
        System.out.println("1. 로그인");
        System.out.println("2. 고객 등록");
        System.out.println("3. 종료");
        System.out.print("선택: ");
    }

    private static void login(Scanner scanner, Bank bank) {
        System.out.print("아이디를 입력해주세요: ");
        String id = scanner.nextLine();
        System.out.print("비밀번호를 입력해주세요: ");
        String password = scanner.nextLine();

        Customer customer = bank.login(id, password);
        if (customer != null) {
            System.out.println("로그인 성공!");
            showLoggedInMenu(scanner, bank, customer);
        } else {
            System.out.println("로그인 실패. 아이디 또는 비밀번호를 확인해주세요.");
        }
    }

    private static void showLoggedInMenu(Scanner scanner, Bank bank, Customer customer) {
        while(true) {
            System.out.println("\n=== 고객 관리 메뉴 ===");
            System.out.println("1. 계좌 생성");
            System.out.println("2. 입금");
            System.out.println("3. 출금");
            System.out.println("4. 잔액 조회");
            System.out.println("5. 계좌 조회");
            System.out.println("6. 로그아웃");
            System.out.print("선택: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 6) {
                System.out.println("로그아웃 되었습니다.");
                break;
            }

            switch(choice) {
                case 1: // 계좌 생성
                    try {
                        customer.addAccountNumber();
                        System.out.println("===================================");
                    } catch (BankOperationException e) {
                        System.out.println(e.getMessage());
                        System.out.println("===================================");
                    }
                    break;

                case 2: // 입금
                    System.out.println("입금을 진행하기 앞서 계좌 정보를 입력해주세요");
                    System.out.print("계좌 번호: ");
                    String depositAccountNumber = scanner.next();
                    try {
                        Account account = customer.findAccountNumber(depositAccountNumber);
                        System.out.print("입금할 금액을 입력해주세요: ");
                        int money = scanner.nextInt();
                        account.deposit(money);
                        System.out.println("===================================");
                    } catch (AccountNotFoundException e) {
                        System.out.println(e.getMessage());
                        System.out.println("===================================");
                    } catch (InvalidTransactionException e) {
                        System.out.println(e.getMessage());
                        System.out.println("===================================");
                    }
                    break;

                case 3: // 출금
                    System.out.println("출금을 진행하기 앞서 계좌 정보를 입력해주세요");
                    System.out.print("계좌 번호: ");
                    String withdrawAccountNumber = scanner.next();
                    try {
                        Account account = customer.findAccountNumber(withdrawAccountNumber);
                        System.out.print("출금할 금액을 입력해주세요: ");
                        int money = scanner.nextInt();
                        account.withdraw(money);
                        System.out.println("===================================");
                    } catch (AccountNotFoundException e) {
                        System.out.println(e.getMessage());
                        System.out.println("===================================");
                    } catch (InvalidTransactionException e) {
                        System.out.println(e.getMessage());
                        System.out.println("===================================");
                    }
                    break;

                case 4: // 잔액 조회
                    System.out.println("잔액 조회를 진행하기 앞서 계좌 정보를 입력해주세요");
                    System.out.print("계좌 번호: ");
                    String balanceAccountNumber = scanner.next();
                    try {
                        Account account = customer.findAccountNumber(balanceAccountNumber);
                        System.out.println("현재 잔고: " + account.getMoney());
                        System.out.println("===================================");
                    } catch (AccountNotFoundException e) {
                        System.out.println(e.getMessage());
                        System.out.println("===================================");
                    }
                    break;

                case 5: // 계좌 조회
                    try {
                        customer.showAccountNumbers();
                        System.out.println("===================================");
                    } catch (AccountNotFoundException e) {
                        System.out.println(e.getMessage());
                        System.out.println("===================================");
                    }
                    break;

                default:
                    System.out.println("잘못된 선택입니다.");
                    System.out.println("===================================");
            }
        }
    }

    private static void register(Scanner scanner, Bank bank) {
        System.out.print("고객 아이디: ");
        String id = scanner.nextLine();
        System.out.print("비밀번호: ");
        String password = scanner.nextLine();
        System.out.print("고객 이름: ");
        String name = scanner.nextLine();

        try {
            bank.addCustomer(id, password, name);
            System.out.println("회원 가입이 완료되었습니다.");
        } catch (BankOperationException e) {
            System.out.println("등록 실패: " + e.getMessage());
        }
    }
}