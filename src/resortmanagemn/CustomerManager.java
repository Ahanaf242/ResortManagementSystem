
package resortmanagemn;
import java.util.List;
import java.util.ArrayList;

public class CustomerManager{
    private List<Customer>customerList = new ArrayList<>();
    
    public Customer addCustomer(String name,String nationalId,String phone){
        if(!isValidPhone(phone)){
            System.out.println("Invalid phoone no.Customer not created.");
            return null;
        }
        Customer newCustomer=new Customer(name,nationalId,phone);
        customerList.add(newCustomer);
        return newCustomer;
  
    
    }
    public boolean isValidPhone(String phone){
        if(phone.length()!= 11){
            return false;
        }
        for(int i=0;i<phone.length();i++){
            if(!Character.isDigit(phone.charAt(i))){
                return false;
            }
        }
        return true;
    }
}