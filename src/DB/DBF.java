
package DB;

import HotelClasses.Food;
import HotelClasses.HoteCustomer;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBF {
    static String foodPath = "food.txt";
    static Scanner x;
    public static void addFood(int id, String foodName){//this parameter is object id!
        try {
            FileWriter fw = new FileWriter(foodPath, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            x = new Scanner(new File(foodPath));
            x.useDelimiter("[,\n]");
            pw.println(id + "," + foodName);  
            pw.flush();
            pw.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    public static void fillFoodList(HoteCustomer obj){
        int myId;
        String myFoodType;
        
        try {
            x = new Scanner(new File(foodPath));
            x.useDelimiter("[,\n]");
            while (x.hasNext()) {                
                myId = x.nextInt();
                myFoodType = x.next();
                if (myId == obj.getId()) {
                    obj.setOrderedFood(myFoodType);
                    obj.setFoodPrice(Food.getFoodByName(myFoodType).getPrice());
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
    }
    public static void deleteFood(HoteCustomer obj){
        int myId;
        String foodName, temp = "temp.txt";
        try {
            File oldFile = new File(foodPath);
            File newFile = new File(temp);
            FileWriter fw = new FileWriter(temp,true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            x = new Scanner(new File(foodPath));
            x.useDelimiter("[,\n]");
            while (x.hasNext()) {
                myId = x.nextInt();
                foodName = x.next();
                if (myId != obj.getId()) {
                    pw.println(myId + "," + foodName);
                } 
            }
            pw.flush();
            pw.close();
            x.close();
            oldFile.delete();
            File dum = new File(foodPath);
            newFile.renameTo(dum);
        } catch (IOException ex) {
            Logger.getLogger(DBF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
