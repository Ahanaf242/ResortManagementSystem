package resortmanagemn;

import java.util.List;
import java.util.ArrayList;

public class CustomerManager {

    private List<Customer> customerList = new ArrayList<>();

    public Customer addCustomer(String name, String nationalId, String phone) {
        Customer existing = findByNID(nationalId);
        if (existing != null) {
            return existing;
        }
        if (!isValidPhone(phone)) {
            System.out.println("Invalid phoone no.Customer not created.");
            return null;
        }
        Customer newCustomer = new Customer(name, nationalId, phone);
        customerList.add(newCustomer);
        return newCustomer;

    }

    public boolean isValidPhone(String phone) {
        if (phone.length() != 11) {
            return false;
        }
        for (int i = 0; i < phone.length(); i++) {
            if (!Character.isDigit(phone.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public Customer findByNID(String nid) {
        for (Customer c : customerList) {
            if (c.isSameCustomer(nid)) {
                return c;
            }
        }
        return null;
    }

    public Customer findByPhone(String phone) {
        for (Customer c : customerList) {
            if (c.getPhone().equals(phone)) {
                return c;
            }
        }
        return null;
    }

    public boolean deleteCustomer(String phone) {
        Customer c = findByPhone(phone);
        if (c == null) {
            return false;
        }
        customerList.remove(c);
        return true;
    }

    public void printAllCustomers() {
        if (customerList.isEmpty()) {
            System.out.println("No customers found");
            return;
        }
        for (Customer c : customerList) {
            System.out.println("c");
        }
    }
}
//Mitu's feature_251-15-091 ends