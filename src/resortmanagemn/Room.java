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
        
    