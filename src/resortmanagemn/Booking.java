package resortmanagemn;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class Booking {
    private Customer customer;
    private Room room;
    private String checkInDate;
    private String checkOutDate;
    private int days;
    private float totalCost;

    public Booking(Customer customer, Room room, int days,
            String checkInDate, String checkOutDate) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null.");
        }
        if (room == null) {
            throw new IllegalArgumentException("Room cannot be null.");
        }

        validateDates(days, checkInDate, checkOutDate);

        if (!room.isAvailable()) {
            throw new IllegalStateException("Room is not available.");
        }

        this.customer = customer;
        this.room = room;
        this.days = days;
        this.checkInDate = checkInDate.trim();
        this.checkOutDate = checkOutDate.trim();
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

    public void updateStay(int days, String checkInDate, String checkOutDate) {
        validateDates(days, checkInDate, checkOutDate);
        this.days = days;
        this.checkInDate = checkInDate.trim();
        this.checkOutDate = checkOutDate.trim();
        this.totalCost = room.getPrice() * days;
    }

    private void validateDates(int days, String checkInDate, String checkOutDate) {
        if (days <= 0) {
            throw new IllegalArgumentException("Days must be positive.");
        }
        if (checkInDate == null || checkInDate.trim().isEmpty()
                || checkOutDate == null || checkOutDate.trim().isEmpty()) {
            throw new IllegalArgumentException("Check-in and check-out dates are required.");
        }

        try {
            LocalDate checkIn = LocalDate.parse(checkInDate.trim());
            LocalDate checkOut = LocalDate.parse(checkOutDate.trim());
            long dateDifference = ChronoUnit.DAYS.between(checkIn, checkOut);

            if (dateDifference <= 0) {
                throw new IllegalArgumentException("Check-out date must be after check-in date.");
            }
            if (dateDifference != days) {
                throw new IllegalArgumentException(
                        "Days must match the difference between check-in and check-out dates."
                );
            }
        } catch (DateTimeParseException ex) {
            throw new IllegalArgumentException("Date format must be yyyy-MM-dd.");
        }
    }

    @Override
    public String toString() {
        return "Customer: " + customer
                + "\nRoom No: " + room.getRoomNumber()
                + " | Type: " + room.getType()
                + " | Price/Day: " + room.getPrice()
                + "\nCheck-in: " + checkInDate
                + " | Check-out: " + checkOutDate
                + " | Days: " + days
                + "\nTotal Cost: " + totalCost;
    }
}
