
package resortmanagemn;

public class Customer {
    private String name;
    private String nationalId;
    private String phone;

    public Customer(String name, String nationalId, String phone) {
        this.name = name;
        this.nationalId = nationalId;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationalId() {
        return nationalId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return name + " | NID: " + nationalId + " | Phone: " + phone;
    }
}