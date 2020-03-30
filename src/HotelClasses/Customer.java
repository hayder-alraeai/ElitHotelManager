
package HotelClasses;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Customer {
    private int id;
    private String name;
    private long contact;
    private int days;
    private int roomNumber;
    static ObservableList<Customer> customerObs = FXCollections.observableArrayList();
    public Customer(String name, long contact, int days, int roomNumber) {
        this.name = name;
        this.contact = contact;
        this.days = days;
        this.roomNumber = roomNumber;
        if (HoteCustomer.hotelCustomerList.isEmpty()) {
            this.id = 1;
        }else{
            this.id = HoteCustomer.hotelCustomerList.get(HoteCustomer.hotelCustomerList.size()-1).getId() + 1;
        }
        
    }
    public Customer(int id, String name, int contact, int days, int roomNumber) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.days = days;
        this.roomNumber = roomNumber;
    }
    

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getContact() {
        return contact;
    }

    public void setContact(int contact) {
        this.contact = contact;
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
    public double totalPrice(){
        return days * Room.getRoomByRoomNr(roomNumber).getRoomPrice();
    }

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", name=" + name + ", contact=" + contact + ", days=" + days + ", roomNumber=" + roomNumber + '}';
    }
    
    
}
