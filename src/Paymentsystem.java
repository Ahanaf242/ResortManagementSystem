/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class Paymentsystem {/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */


public class Payment {
    private int amount;
    private String method;

    public Payment(int amount, String method) {
        this.amount = amount;
        this.method = method;
    }

    public int getAmount() {
        return amount;
    }

    public String getMethod() {
        return method;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public String toString() {
        return amount + " Paid by " + method;
    }
}
    


    
}
