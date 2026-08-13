
package resortmanagemn;
import java.util.List;
import java.util.ArrayList;

public class CustomerManager{
    private List<Customer>customerList = new ArrayList<>();
    
    public Customer addCustomer(String name,String nationalId,String phone){
        Customer newCustomer=new Customer(name,nationalId,phone);
        customerList.add(newCustomer);
        return newCustomer;
  
    

}