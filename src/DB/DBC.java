
package DB;

import HotelClasses.HoteCustomer;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DBC {
    static String customerPath = "customers.txt";
    static Scanner x;
    
    public static void updateDBCustomers(){
        String temp = "temp.txt";
        File oldFile = new File(customerPath);
        File newFile = new File(temp);
        try {
            FileWriter fw = new FileWriter(temp,true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            HoteCustomer.hotelCustomerList.forEach((o) -> {
                pw.println(o.getId() + "," + o.getName() + "," + o.getContact() + "," + o.getDays() + "," + o.getRoomNumber() + "," + o.getRoomType() + "," + (int)o.getTotalPrice());
            });
            pw.flush();
            pw.close();
            oldFile.delete();
            File dum = new File(customerPath);
            newFile.renameTo(dum);
        } catch (IOException ex) {
            System.out.println(ex);
        }
        
    }
    public static List<HoteCustomer> fillArray(){
        List<HoteCustomer> myList = new ArrayList();
         int id;
         int roomNumber;
         int days; 
         String name;
         String roomType; 
         long contact; 
         double totalPrice;
        try {
            x = new Scanner(new File(customerPath));
            x.useDelimiter("[,\n]");
            while(x.hasNext()){
                id = x.nextInt(); 
                name = x.next();
                contact = x.nextLong();
                days = x.nextInt(); 
                roomNumber = x.nextInt();
                roomType = x.next(); 
                totalPrice = x.nextDouble();
                //int id, String name, long contact, int days, int roomNumber, String roomType, double totalPrice
                myList.add(new HoteCustomer(id, name, contact, days, roomNumber, roomType, totalPrice));
            }
            x.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
        return myList;
    }
}
