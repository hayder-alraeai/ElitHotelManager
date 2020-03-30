
package HotelClasses;

import Interfaces.RoomInterface;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
public class Room implements RoomInterface{
    private int roomNumber;
    private String roomType;
    private double roomPrice;
    private String TV;
    private String available;
    public static ObservableList<Room> roomDetailsList = FXCollections.observableArrayList(new Room("Single Lux"),new Room("Single Delux"),new Room("Duble Lux"),new Room("Duble Delux"));
    public static ObservableList<String> roomTypeList = FXCollections.observableArrayList("Single Lux", "Single Delux" , "Duble Lux" , "Duble Delux");
    public static ObservableList<Room> roomList = fillRoomList();
    public static List<Room> availableRoomList;
    public Room(int roomNumber, String roomType) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        if (roomType.equals("Single Lux")) {
            this.roomPrice = 500;
        }else if (roomType.equals("Single Delux")) {
            this.roomPrice = 700;
        }else if (roomType.equals("Duble Lux")) {
            this.roomPrice = 1000;
        }if (roomType.equals("Duble Delux")) {
            this.roomPrice = 1300;
        }
    }
    //constaructor for room details

    public Room(String roomType) {
        this.roomType = roomType;
        if (roomType.equals("Single Lux")) {
            this.roomPrice = 500;
        }else if (roomType.equals("Single Delux")) {
            this.roomPrice = 700;
        }else if (roomType.equals("Duble Lux")) {
            this.roomPrice = 1000;
        }if (roomType.equals("Duble Delux")) {
            this.roomPrice = 1300;
        }
        this.TV = getTV();
        this.available = getAvailable();
    }
    
    public String getTV() {
        return hasTV();
    }

    public String getAvailable() {
        return isAvailable();
    }
    public double getRoomPrice() {
        return roomPrice;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }
    public static ObservableList<Room> fillRoomList(){
        ObservableList<Room> myList = FXCollections.observableArrayList();
        myList.addAll(new Room(1, "Single Lux"),new Room(2, "Single Lux"),new Room(3, "Single Lux"),new Room(4, "Single Lux")
                        ,new Room(5, "Single Delux"),new Room(6, "Single Delux"),new Room(7, "Single Delux"),new Room(8, "Single Delux")
                        ,new Room(9, "Duble Lux"),new Room(10, "Duble Lux"),new Room(11, "Duble Delux"),new Room(12, "Duble Delux"));
        return myList;
    }
    public static Room getRoomByRoomNr(int roomNr){
        return roomList.stream().filter(o -> o.getRoomNumber() == roomNr).findAny().get();
    }
    public static Room checkRoom(String roomType){
        return roomList.stream().filter(o -> !HoteCustomer.checkIfRoomIsBusy(o.roomNumber) && o.getRoomType().equals(roomType)).findAny().get();
        
    }
    public static Room getRoomByRoomType(String roomType){
        return roomList.stream().filter(o -> o.getRoomType().equals(roomType)).findAny().get();
    }

    @Override
    public String hasTV() {
        if (roomType.equals("Single Delux") || roomType.equals("Duble Delux")) {
            return "YES";
        }
        return "NO";
    }

    @Override
    public String isAvailable() {
        if ((roomType.equals("Single Lux") && HoteCustomer.getNumberOfRoomType(roomType) == 4) 
           || (roomType.equals("Single Delux") && HoteCustomer.getNumberOfRoomType(roomType) == 4)
           || (roomType.equals("Duble Lux") && HoteCustomer.getNumberOfRoomType(roomType) == 2)
           || (roomType.equals("Duble Delux") && HoteCustomer.getNumberOfRoomType(roomType) == 2)) {
            return "NO";
        }
        return "YES";
    }

}

