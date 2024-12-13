package practice.bank2;

public class Bank {
    private Customer[] customers;
    private int customerNumber; // 현재 등록된 유저 수
    private static final int MAX_CUSTOMERS = 100;

    public Bank() {
        this.customers = new Customer[MAX_CUSTOMERS];
        this.customerNumber = 0;
    }

    public Customer login(String id, String password) {
        Customer customer = findCustomer(id);
        if (customer != null && customer.getPassword().equals(password)) {
            return customer;
        }
        return null;
    }

    public void addCustomer(String id, String password, String name) throws BankOperationException {
        if (customerNumber >= MAX_CUSTOMERS) {
            throw new BankOperationException("더 이상 고객을 등록할 수 없습니다.");
        }
        if (findCustomer(id) != null) {
            throw new BankOperationException("이미 등록된 ID 입니다: " + id);
        }
        customers[customerNumber++] = new Customer(id, password, name);
    }

    public Customer findCustomer(String id) {
        for (int i = 0; i < customerNumber; i++) {
            if (customers[i].getId().equals(id)) {
                System.out.println("고객이 확인 되었습니다.");
                return customers[i];
            }
        }
        return null;
    }

}