package resortmanagemn;
public class Admin extends User {
    
    public Admin(String id, String password) {

        this.id = id;
        this.password = password;

    }
    
    public void changeCredentials(String newId, String newPass) {

        this.id = newId;
        this.password = newPass;

    }

}
