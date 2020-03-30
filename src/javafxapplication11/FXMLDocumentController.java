package javafxapplication11;
import DB.DBC;
import DB.DBF;
import HotelClasses.Customer;
import HotelClasses.Food;
import HotelClasses.HoteCustomer;
import HotelClasses.Room;
import MaitainClasses.Search;
import java.net.URL;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
public class FXMLDocumentController implements Initializable {
    @FXML
    Pane paneReciption, paneCustomer,paneCheckOut,paneFoodMenu,paneRoomDetails,paneMain;
    @FXML
    TextArea txtShowBill,txtShowBillC;
    @FXML
    Label lblDone,lblDoneC, lblFood;
    @FXML
    Button btnBook,btnBookC,btnClear,btnClearC,btnDelete,btnUpdate,btnReciption,btnCustomer, btnCheckout;
    @FXML
    ComboBox ComRoomType, ComSearch,ComMeal,ComRoomTypeC,ComMealC;
    @FXML
    TextField txtName,txtNameC, txtContact,txtContactC, txtDays,txtDaysC,txtSearch,txtFood, txtRoomNr,txtRoomNrFood;
    @FXML
    TableView<HoteCustomer> tblView;
    @FXML
    TableColumn<HoteCustomer, Integer> tblId,tblRoomNumber,tblDays;
    @FXML
    TableColumn<HoteCustomer, Long> tblContact;
    @FXML
    TableColumn<HoteCustomer, String> tblName,tblRoomType; 
    @FXML
    TableColumn<HoteCustomer, Double>tblTotalPrice;
    @FXML
    TableView<Food>tblFoodMenu,tblFoodMenuC;
    @FXML
    TableColumn<Food,String>tblFoodName,tblFoodNameC;
    @FXML
    TableColumn<Food,Double>tblFoodPriceMenu,tblFoodPriceMenuC;
    @FXML
    TableView<Room>tblRoomDetails;
    @FXML
    TableColumn<Room, String>tblRoomName,tblTV;
    @FXML
    TableColumn<Room,Integer>tblRoomPrice;
    @FXML
    TableColumn<Room,String>tblRoomAvailable;
    @FXML
    private void showBill(HoteCustomer obj){
        
        txtShowBill.setText(
        "Name: " + obj.getName() + "\nContact number: " + obj.getContact() + "\nNumber of booked days: " + obj.getDays() +
                "\nRoom number: " + obj.getRoomNumber() + "\nRoomType: " + obj.getRoomType()
                + "\nRoom price a day: " + Room.getRoomByRoomNr(obj.getRoomNumber()).getRoomPrice() + 
                "\nOrdered Food: " + obj.getOrderedFood() + "\nFood price: " + obj.getFoodPrice()  +
                " SEK\nTotalPrice for " + obj.getDays() + " days and ordered food: " + obj.getTotalPrice() + " SEK"       
        );
    }
    @FXML
    private void showBillC(HoteCustomer obj){
        
        txtShowBillC.setText(
        "Name: " + obj.getName() + "\nContact number: " + obj.getContact() + "\nNumber of booked days: " + obj.getDays() +
                "\nRoom number: " + obj.getRoomNumber() + "\nRoomType: " + obj.getRoomType()
                + "\nRoom price a day: " + Room.getRoomByRoomNr(obj.getRoomNumber()).getRoomPrice() + 
                "\nOrdered Food: " + obj.getOrderedFood() + "\nFood price: " + obj.getFoodPrice()  +
                " SEK\nTotalPrice for " + obj.getDays() + " days and ordered food: " + obj.getTotalPrice() + " SEK"       
        );
    }
    @FXML
    private void addCustomer(){
        if (txtName.getText().isEmpty() || txtContact.getText().isEmpty() || txtDays.getText().isEmpty() || ComRoomType.getSelectionModel().isEmpty()) {
            lblDone.setText("Please fill all fields...");
        }else{
            try {
            long cont = Long.parseLong(txtContact.getText());
            int roomN = Integer.parseInt(txtDays.getText());
            Room room = Room.checkRoom(ComRoomType.getSelectionModel().getSelectedItem().toString());//getting room number by type
            Customer c = new Customer(txtName.getText(),
                    cont, 
                    roomN, 
                    room.getRoomNumber());
            HoteCustomer.hotelCustomerList.add(new HoteCustomer(c.getId(), c.getName(), c.getContact(), c.getDays(), c.getRoomNumber(), room.getRoomType(), c.totalPrice()));
            DBC.updateDBCustomers();
            tblView.refresh();
            tblRoomDetails.refresh();
            lblDone.setText("Booking is succesful...");
            clear();
            } catch (NumberFormatException e) {
                lblDone.setText("Please type proper values...");
            }catch(NoSuchElementException e){
                lblDone.setText("This room type is not available....");
            }
        }
    }
    @FXML
    private void addCustomerC(){
        if (txtNameC.getText().isEmpty() || txtContactC.getText().isEmpty() || txtDaysC.getText().isEmpty() || ComRoomTypeC.getSelectionModel().isEmpty()) {
            lblDoneC.setText("Please fill all fields...");
        }else{
            try {
            long cont = Long.parseLong(txtContactC.getText());
            int roomN = Integer.parseInt(txtDaysC.getText());
            Room room = Room.checkRoom(ComRoomTypeC.getSelectionModel().getSelectedItem().toString());//getting room number by type
            Customer c = new Customer(txtNameC.getText(),
                    cont, 
                    roomN, 
                    room.getRoomNumber());
            HoteCustomer.hotelCustomerList.add(new HoteCustomer(c.getId(), c.getName(), c.getContact(), c.getDays(), c.getRoomNumber(), room.getRoomType(), c.totalPrice()));
            DBC.updateDBCustomers();
            tblView.refresh();
            tblRoomDetails.refresh();
            clearC();
            lblDoneC.setText("Booking is succesful... Your room number is " + c.getRoomNumber());
            } catch (NumberFormatException e) {
                lblDoneC.setText("Please type proper values...");
            }catch(NoSuchElementException e){
                lblDoneC.setText("This room type is not available....");
            }
        }
    }
    @FXML
    private void selectedTable(){
        try {
        //getting object
        HoteCustomer obj = tblView.getSelectionModel().getSelectedItem();
        //refill the food details
        obj.clearFood();
        DBF.fillFoodList(obj);

        System.out.println(obj.getFoodPrice() + obj.getTotalPrice());
        txtName.setText(tblView.getSelectionModel().getSelectedItem().getName());
        txtContact.setText(String.valueOf(tblView.getSelectionModel().getSelectedItem().getContact()));
        txtDays.setText(String.valueOf(tblView.getSelectionModel().getSelectedItem().getDays()));
        ComRoomType.setValue(tblView.getSelectionModel().getSelectedItem().getRoomType());
        showBill(tblView.getSelectionModel().getSelectedItem());
        btnBook.setVisible(false);
        btnClear.setVisible(true);
        btnDelete.setVisible(true);
        btnUpdate.setVisible(true);
        } catch (NullPointerException e) {
            lblDone.setText("No Customer is selected...");
        }
    }
    @FXML
    private void clear(){
        try{
            txtName.setText("");txtContact.setText("");txtDays.setText("");ComRoomType.setPromptText(ComRoomType.getPromptText());
            txtShowBill.setText("");
            lblDone.setText("");
            btnBook.setVisible(true);
        }catch(NullPointerException e){
            lblDone.setText("Please select a customer first..");
        }catch(ArrayIndexOutOfBoundsException e){
            lblDone.setText("Add som Customer first...");
        }
    }
    @FXML
    private void clearC(){
        try{
            txtNameC.setText("");txtContactC.setText("");txtDaysC.setText("");ComRoomTypeC.setPromptText(ComRoomTypeC.getPromptText());
            txtShowBillC.setText("");
            lblDoneC.setText("");
        }catch(NullPointerException e){
            lblDoneC.setText("Please select a customer first..");
        }catch(ArrayIndexOutOfBoundsException e){
            lblDoneC.setText("Add som Customer first...");
        }
    }
    @FXML
    private void deleteSelected(){
        try{
            HoteCustomer obj = tblView.getSelectionModel().getSelectedItem();
            DBF.deleteFood(obj);
            HoteCustomer.hotelCustomerList.remove(obj);
            DBC.updateDBCustomers();
            tblView.refresh();
            clear();
            btnBook.setVisible(true);
        }catch(NullPointerException e){
            lblDone.setText("Please select a customer first..");
        }catch(ArrayIndexOutOfBoundsException e){
            lblDone.setText("Add som Customer first...");
        }
    }
    @FXML
    private void updateSelected(){
        try {
        HoteCustomer objInTbl = tblView.getSelectionModel().getSelectedItem();
        HoteCustomer obj = HoteCustomer.hotelCustomerList.get(HoteCustomer.hotelCustomerList.indexOf(objInTbl));
        obj.setName(txtName.getText());
        obj.setContact(Long.parseLong(txtContact.getText()));
        obj.setDays(Integer.parseInt(txtDays.getText()));
        obj.setRoomType(ComRoomType.getSelectionModel().getSelectedItem().toString());
        obj.setRoomNumber(Room.getRoomByRoomType(obj.getRoomType()).getRoomNumber());
        obj.setTotalPrice(obj.getTotalPrice());
        DBC.updateDBCustomers();
        tblView.refresh();
        showBill(tblView.getSelectionModel().getSelectedItem());
        clear();
        btnBook.setVisible(true);
        lblDone.setText("Update is succesful...");
        } catch (NumberFormatException  e) {
            lblDone.setText("Please type proper values...");
        }catch(NullPointerException e){
            lblDone.setText("Please select a customer first..");
        }catch(ArrayIndexOutOfBoundsException e){
            lblDone.setText("Add som Customer first...");
        }
    }
    @FXML
    private void searchItem(){
        if (txtSearch.getText().isEmpty() || ComSearch.getSelectionModel().isEmpty()) {
            lblDone.setText("Please fill the fillds...");
        }else{
            try {
                HoteCustomer obj = Search.search(ComSearch.getSelectionModel().getSelectedItem().toString(), txtSearch.getText());
                System.out.println(tblView.getItems().indexOf(obj));
                ObservableList<HoteCustomer> list = FXCollections.observableArrayList(obj); //copying the orginal list to get the searched item
                tblView.setItems(list);
                lblDone.setText("");
            } catch (NoSuchElementException e) {
                lblDone.setText("Customer cannot be found...");
            }   
        }
    }
    @FXML
    private void showItemsInTblEfterSearching(){
        txtSearch.textProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                if (txtSearch.textProperty().get().isEmpty()) {
                    tblView.setItems(HoteCustomer.hotelCustomerList); 
                    lblDone.setText("");
                }
            }
        });
    }
    @FXML
    private void orderFood(){
        try {
        HoteCustomer obj = tblView.getSelectionModel().getSelectedItem();
        obj.clearFood();
        Food.orderFood(ComMeal.getSelectionModel().getSelectedItem().toString(), obj.getId());
        tblView.refresh();
        lblFood.setText("Your order is placed...");
        showBill(obj);
        } catch (NullPointerException e) {
            lblFood.setText("Choose a room first");
        }
    }
    @FXML
    private void showReciption(){
        paneCustomer.setVisible(false);
        paneMain.setVisible(false);
        paneReciption.setVisible(true);
    }
    @FXML
    private void showCustomer(){
        paneReciption.setVisible(false);
        paneMain.setVisible(false);
        paneCustomer.setVisible(true); 
    }
    @FXML
    private void showCheckOut(){//customers window
        paneFoodMenu.setVisible(false);
        paneRoomDetails.setVisible(false);
        paneCheckOut.setVisible(true); 
    }
    @FXML
    private void showFoodMenu(){//customers window
        paneCheckOut.setVisible(false);
        paneRoomDetails.setVisible(false);
        paneFoodMenu.setVisible(true);
    }
    @FXML
    private void showRoomDetails(){//customer window
         paneCheckOut.setVisible(false);
         paneFoodMenu.setVisible(false);
         paneRoomDetails.setVisible(true);
    }
    @FXML
    private void showAvailableRooms(){//customr window
         paneCheckOut.setVisible(false);
         paneFoodMenu.setVisible(false);
    }
    //metoder for customer window
    @FXML
    private void checkout(){
        try{
            HoteCustomer obj = HoteCustomer.getObjByRoomNr(Integer.parseInt(txtRoomNr.getText()));
            showBillC(obj);
            DBF.deleteFood(obj);
            HoteCustomer.hotelCustomerList.remove(obj);
            DBC.updateDBCustomers();
            tblView.refresh();
            tblRoomDetails.refresh();
            txtRoomNr.setText("");
        }catch(NullPointerException e){
            lblDoneC.setText("Please Enter Room number..");
        }catch(ArrayIndexOutOfBoundsException e){
            lblDoneC.setText("Room Not found...");
        }catch(NumberFormatException e){
            lblDoneC.setText("Enter Room number...");
        }catch(NoSuchElementException e){
            lblDoneC.setText("Room Not found...");
        }
    }
    @FXML
    private void orderFoodC(){
        try {
        HoteCustomer obj = HoteCustomer.getObjByRoomNr(Integer.parseInt(txtRoomNrFood.getText()));
        obj.clearFood();
        Food.orderFood(ComMealC.getSelectionModel().getSelectedItem().toString(), obj.getId());
        tblView.refresh();
        txtRoomNrFood.setText("");
        lblDoneC.setText("Your order is placed...");
        showBill(obj);
        } catch(NullPointerException e){
            lblDoneC.setText("Please Enter Room number..");
        }catch(ArrayIndexOutOfBoundsException e){
            lblDoneC.setText("Room Not found...");
        }catch(NumberFormatException e){
            lblDoneC.setText("Enter Room number...");
        }catch(NoSuchElementException e){
            lblDoneC.setText("Room Not found...");
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tblId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tblContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        tblDays.setCellValueFactory(new PropertyValueFactory<>("days"));
        tblRoomNumber.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        tblRoomType.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        tblTotalPrice.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        tblView.setItems(HoteCustomer.hotelCustomerList);
        //fill the room type combobox
        ComRoomType.setItems(Room.roomTypeList);
        //fill the room type customers
        ComRoomTypeC.setItems(Room.roomTypeList);
        //fill the search combobox
        ObservableList searchList = FXCollections.observableArrayList("Id", "Name" , "Room number" );
        ComSearch.setItems(searchList);
        //fill food combobox
        ComMeal.setItems(Food.foodMenu);
        ComMealC.setItems(Food.foodMenu);
        //fill the menu table
        tblFoodName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tblFoodPriceMenu.setCellValueFactory(new PropertyValueFactory<>("price"));
        tblFoodMenu.setItems(Food.tblmenuList);
        tblFoodNameC.setCellValueFactory(new PropertyValueFactory<>("name"));
        tblFoodPriceMenuC.setCellValueFactory(new PropertyValueFactory<>("price"));
        tblFoodMenuC.setItems(Food.tblmenuList);
        //fill the table room details
        tblRoomName.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        tblRoomPrice.setCellValueFactory(new PropertyValueFactory<>("roomPrice"));
        tblTV.setCellValueFactory(new PropertyValueFactory<>("TV"));
        tblRoomAvailable.setCellValueFactory(new PropertyValueFactory<>("available"));
        tblRoomDetails.setItems(Room.roomDetailsList);
    }    
    
}
