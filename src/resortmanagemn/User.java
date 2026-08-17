package resortmanagemn;



public class User {

    protected String id;
    protected String password;


    public boolean login(String id, String password) {

        return this.id.equals(id) && this.password.equals(password);

    }

}