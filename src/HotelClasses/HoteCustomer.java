
package HotelClasses;

import DB.DBC;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class HoteCustomer {
    private int id;
    private String name;
    private long contact;
    private int days;
    private int roomNumber;
    private String roomType;
    private double totalPrice;
    private double foodPrice;
    private List<String> orderedFood = new ArrayList<>();
    public static ObservableList<HoteCustomer> hotelCustomerList = FXCollections.observableArrayList(DBC.fillArray());

    public HoteCustomer(int id, String name, long contact, int days, int roomNumber, String roomType, double totalPrice) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.days = days;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.totalPrice = totalPrice;
    }

    public double getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(double foodPrice) {
        this.foodPrice += foodPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getContact() {
        return contact;
    }

    public void setContact(long contact) {
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
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

    public double getTotalPrice() {
        return getFoodPrice() + (Room.getRoomByRoomNr(roomNumber).getRoomPrice() * getDays());
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }


    public String getOrderedFood() {
        String txt = "";
        StringBuilder builder = new StringBuilder();
        for (String string : orderedFood) {
            builder.append(string).append(", ");
        }
        txt = builder.toString();
        System.out.println(txt);
        return txt;
    }
    public void clearFood(){
        orderedFood.clear();
        foodPrice = 0;
    }
    
    public void setOrderedFood(String orderedFood) {
        this.orderedFood.add(orderedFood);
    }
    
    public static boolean checkIfRoomIsBusy(int roomNr){
        return hotelCustomerList.stream().anyMatch(o -> o.getRoomNumber() == roomNr);
    }
    public static HoteCustomer getObjById(int id){
        return hotelCustomerList.stream().filter(o -> o.getId() == id).findAny().get();
    }
    public static HoteCustomer getObjByRoomNr(int roomNr){
        return hotelCustomerList.stream().filter(o -> o.getRoomNumber() == roomNr).findAny().get();
    }
    public static long getNumberOfRoomType(String roomType){
        return hotelCustomerList.stream().filter(o -> o.getRoomType().equals(roomType)).count();
    }
    
    
}
