package practice.bank;

public class Bank {
    private Customer[] customers;
    private int customerNumber; // 현재 등록된 유저 수
    private static final int MAX_CUSTOMERS = 100;

    public Bank() {
        this.customers = new Customer[MAX_CUSTOMERS];
        this.customerNumber = 0;
    }

    public void addCustomer(String id, String name) throws BankOperationException {
        if (customerNumber >= MAX_CUSTOMERS) {
            throw new BankOperationException("더 이상 고객을 등록할 수 없습니다.");
        }
        if (findCustomer(id) != null) {
            throw new BankOperationException("이미 등록된 ID 입니다: " + id);
        }
        customers[customerNumber++] = new Customer(id, name);
    }

    public Customer findCustomer(String id) {
        for (int i = 0; i < customerNumber; i++) {
            if (customers[i].getId().equals(id)) {
                return customers[i];
            }
        }
        return null;
    }

}
