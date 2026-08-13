package resortmanagemn;

enum RoomType {
    DELUXE,
    SUPER_DELUXE,
    KING_SIZE
}

enum RoomStatus {
    AVAILABLE,
    BOOKED,
    MAINTENANCE
}

public class Room {

    private int roomNumber;
    private RoomType type;
    private int price;
    private RoomStatus status;

    public Room(int roomNumber, RoomType type, int price) {

        if (roomNumber <= 0) {

            throw new IllegalArgumentException("Wrong Room Number Entry!!! Room Number Must Be Positive!!!");

        }
        if (price < 0) {

            throw new IllegalArgumentException("Wrong Price Entry!!! Price Can not be Negative!!!");

        }
        this.roomNumber = roomNumber;
        this.type = type;
        this.price = price;

        this.status = RoomStatus.AVAILABLE;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public RoomType getType() {
        return type;
    }

    public int getPrice() {
        return price;
    }

    public RoomStatus getRoomStatus() {
        return status;
    }

    public boolean isAvailable() {
        return status == RoomStatus.AVAILABLE;
    }

    public boolean book() {
        if (status == RoomStatus.AVAILABLE) {
            status = RoomStatus.BOOKED;
            return true;
        }
        return false;
    }

    public boolean free() {
        if (status == RoomStatus.BOOKED) {
            status = RoomStatus.AVAILABLE;
            return true;
        }
        return false;
    }

    public boolean setMaintenance() {
        if (status != RoomStatus.BOOKED) {
            status = RoomStatus.MAINTENANCE;
            return true;
        }
        return false;
    }

    public boolean endMaintenance() {
        if (status == RoomStatus.MAINTENANCE) {
            status = RoomStatus.AVAILABLE;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return roomNumber + " : " + type + " : " + price + " : " + status;
    }
}
