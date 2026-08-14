
    package resortmanagemn;

import java.util.ArrayList;
import java.util.List;


public class BookingManager {

    private List<Booking> bookingList;


    public BookingManager() {

        bookingList = new ArrayList<>();

    }

    public boolean addBooking(Booking booking) {


        if (booking == null) {
            return false;
        }

        Booking existingBooking =
                findBookingByRoom(booking.getRoom().getRoomNumber());


        if (existingBooking != null) {

            return false;

        }


        bookingList.add(booking);

        return true;

    }

    public Booking findBookingByRoom(int roomNumber) {


        for (Booking booking : bookingList) {


            if (booking.getRoom().getRoomNumber() == roomNumber) {

                return booking;

            }

        }


        return null;

    }

    public Booking findBookingByCustomerPhone(String phone) {


        if (phone == null) {

            return null;

        }


        for (Booking booking : bookingList) {


            if (booking.getCustomer()
                    .getPhone()
                    .equals(phone)) {


                return booking;

            }

        }


        return null;

    }

    public boolean removeBooking(Booking booking) {


        if (booking == null) {

            return false;

        }

        if (bookingList.remove(booking)) {
            booking.getRoom().free();


            return true;

        }


        return false;

    }

    public boolean removeBookingByRoom(int roomNumber) {


        Booking booking = findBookingByRoom(roomNumber);


        if (booking != null) {

            return removeBooking(booking);

        }


        return false;

    }
    public void printAllBookings() {


        if (bookingList.isEmpty()) {


            System.out.println("No bookings found.");

            return;

        }



        for (Booking booking : bookingList) {


            System.out.println(booking);

            System.out.println("----------------------");

        }


    }

    public List<Booking> getBookingList() {


        return new ArrayList<>(bookingList);

    }
}  

