
package MaitainClasses;

import HotelClasses.HoteCustomer;
import static javafx.scene.input.KeyCode.T;

public class Search {
    public static HoteCustomer search(String val, String searchedValue){
        HoteCustomer obj = null;
        if (val.equals("Id")) {
            obj = HoteCustomer.hotelCustomerList.stream().filter(o -> o.getId() == Integer.parseInt(searchedValue)).findAny().get();
        }else if (val.equals("Name" )) {
            obj = HoteCustomer.hotelCustomerList.stream().filter(o -> o.getName().equalsIgnoreCase(searchedValue)).findAny().get();
        }else if (val.equalsIgnoreCase("Room number")) {
            obj = HoteCustomer.hotelCustomerList.stream().filter(o -> o.getRoomNumber()== Integer.parseInt(searchedValue)).findAny().get();
        }
        return obj;
    }
}
