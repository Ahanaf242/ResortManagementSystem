package resortmanagemn;

import java.util.ArrayList;
import java.util.List;

public class ApplicationData {
    private static final BookingManager BOOKING_MANAGER = new BookingManager();
    private static final CustomerManager CUSTOMER_MANAGER = new CustomerManager();
    private static final List<Room> ROOM_LIST = new ArrayList<>();
    private static final List<String> PAYMENT_LIST = new ArrayList<>();
    private static final List<String> SERVICE_LIST = new ArrayList<>();

    private ApplicationData() {
    }

    public static BookingManager getBookingManager() {
        return BOOKING_MANAGER;
    }

    public static CustomerManager getCustomerManager() {
        return CUSTOMER_MANAGER;
    }

    public static List<Room> getRoomList() {
        return ROOM_LIST;
    }

    public static Room findRoom(int roomNumber) {
        for (Room room : ROOM_LIST) {
            if (room.getRoomNumber() == roomNumber) {
                return room;
            }
        }
        return null;
    }

    public static boolean addRoom(Room room) {
        if (room == null || findRoom(room.getRoomNumber()) != null) {
            return false;
        }
        ROOM_LIST.add(room);
        return true;
    }

    public static List<String> getPaymentList() {
        return PAYMENT_LIST;
    }

    public static List<String> getServiceList() {
        return SERVICE_LIST;
    }
}
