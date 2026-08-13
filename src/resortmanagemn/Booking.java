package resortmanagemn;



 
public class Booking {
    private Customer customer;
    private Room room;
    private int days;
    
    private float totalCost;

public Booking(Customer customer, Room room, int days) {
        this.customer = customer;
        this.room = room;
        this.days = days;
  
        this.totalCost = room.getPrice() * days;
        room.bookRoom();
    }

    public Customer getCustomer() {
        return customer;
    }

    public Room getRoom() {
        return room;
    }

   
    public int getDays() {
        return days;
    }

    public float getTotalCost() {
        return totalCost;
    }

    public void setDays(int days) {
        this.days = days;
        this.totalCost = room.getPrice() * days;
    }

    @Override
    public String toString() {
        return "Booking: " + customer +
               " | Room No: " + room.getRoomNumber() +
               " | Days: " + days +
               " | Total Cost: " + totalCost;
    }
}
    // Updated by Hira

