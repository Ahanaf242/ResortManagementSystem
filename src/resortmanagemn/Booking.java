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
        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null.");
        }

        if (room == null) {
            throw new IllegalArgumentException("Room cannot be null.");
        }

        if (days <= 0) {
            throw new IllegalArgumentException("Days must be positive.");
        }

        if (!room.isAvailable()) {
            throw new IllegalStateException("Room is not available.");
        }
         this.customer = customer;
    this.room = room;
    this.days = days;
    this.checkInDate = checkInDate;
    this.checkOutDate = checkOutDate;


    this.totalCost = room.getPrice() * days;

    room.book();
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
        if (days <= 0) {
            throw new IllegalArgumentException("Days must be positive.");
        }

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

