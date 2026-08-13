package resortmanagemn;



 
public class Booking {
    private Customer customer;
    private Room room;
    private String checkInDate;
    private String checkOutDate;
    private int days;
    
    private float totalCost;

public Booking(Customer customer, Room room, int days,String checkInDate,String checkOutDate)
{
        this.customer = customer;
        this.room = room;
        this.days = days;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        
        this.totalCost = room.getPrice() * days;
        room.bookRoom();
    }

    public Customer getCustomer() {
        return customer;
    }

    public Room getRoom() {
        return room;
    }

    public String getCheckInDate() {

        return checkInDate;

    }
 public String getCheckOutDate() {

        return checkOutDate;

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
                 "\nCheck-in Date : " + checkInDate +

        "\nCheck-out Date : " + checkOutDate +
               " | Total Cost: " + totalCost;
    }
}
    // Updated by Hira

