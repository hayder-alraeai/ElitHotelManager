
package HotelClasses;

import DB.DBF;
import java.util.Arrays;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Food {
    private String name;
    private double price;
    public static ObservableList foodMenu = FXCollections.observableArrayList("Pasta","Sandwich","Hamburgar","Drinks");
    static List<Food> foodList =  Arrays.asList(
            new Food("Pasta", 80),
            new Food("Sandwich", 50),
            new Food("Hamburgar", 70),
            new Food("Drinks", 30)
    );
    public static ObservableList<Food> tblmenuList = FXCollections.observableArrayList(foodList);
    public Food(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public static Food getFoodByName(String name){
        return foodList.stream().filter(o -> o.getName().equalsIgnoreCase(name)).findAny().get();
    }
    public static void orderFood(String foodName, int id){
        Food food = getFoodByName(foodName);
        DBF.addFood(id, foodName);
        HoteCustomer.getObjById(id).clearFood();
        DBF.fillFoodList(HoteCustomer.getObjById(id));
    }
}
