import java.util.*;
import java.io.*;

// import java.util.logging.Logger;
// import java.lang.reflect.Array;
// import javax.swing.JTable;
// import javax.swing.table.DefaultTableModel;
// import javax.swing.*;
// import java.awt.event.*;

public class donorMenu {
    //-------------------------------------Shows Donor Menu and re-routes to enter aids or view all aids donated.-----------------------------------------------------------------------//
    public static void showDonormenu(String name) throws IOException{
    Scanner sc = new Scanner(System.in);
    String menu=
    ("\n|------------------------------------------------------------|"
    +"\n                Welcome Donor "+name+"                        "
    +"\n|------------------------------------------------------------|"
    +"\n|      Enter 1: To Enter the aids to be donated              |"
    +"\n|      Enter 2: To view all the aids donated to DC           |"
    +"\n|      Enter 3: To view all the aids used by NGO             |"
    +"\n|------------------------------------------------------------|");
    
    System.out.println(menu);
    int choice= sc.nextInt();
    switch (choice){
        case(1):  enterAids(name); //re-routes to the enter aids method.
                    break;
        case(2):  showStatus(name);
                    break;    
        case(3):  viewAidsDonated(name); //re-routes to view all aids donated.
                    break;
    }
   sc.close();
   }
    //-------------------------------------------------------------------------------------------//
   
   //---------------------------------------------Allows User to enter aids to donate along with donor info-------------------------------------------------------------------------//
    public static void enterAids(String name) throws IOException{
        Scanner sc = new Scanner(System.in);
        System.out.println("Please Enter the item name: ");
        String itemName = sc.nextLine();
        System.out.println("Please Enter How many items you want to donate");
        String quantity = sc.nextLine();

        Donor set = new Donor(name,itemName,quantity);
        set.saveDonorInfo();
        sc.close();
        // showDonormenu(name);
    }
    //-------------------------------------------------------------------------------------------//
    
    //-------------------------------------------------------------------------------------------//
    public static void showStatus(String name){
        File file =  new File("src/Documentation/DonatedItems.csv");
        try{
            Scanner input = new Scanner(file); 
            do{
                String data = input.nextLine(); //reads data from csv file
                List<String> source = Arrays.asList(data.split(",")); //put data in a list and saves it
                String list = Arrays.toString(source.toArray()).replace("[", "  ").replace("]", "  ").replace(",", "    "); //return to string without brackets or commas
                
                if (list.contains(name)){
                    System.out.printf(list);
                }
                System.out.println();
                
            } while(input.hasNextLine());
           input.close();  
        } catch (Exception e){
            System.out.println("x");
        }
    }
    //-----------------------------------------------------------------------------------------------//

    //--------------------------------------------Allows the user to view all aids donated-------------------------------------------------------------------------------------------//
    private static void  viewAidsDonated(String name) throws IOException {
        File file =  new File("src/Documentation/donor&NgoMatched.csv");
        try{
            Scanner input = new Scanner(file);  
            System.out.format("%-5s%15s%6s%10s%5s","Name","PhoneNumber","Aid","Quantity","Ngo"+"\n"); //template for the table.
            do{
                String data = input.nextLine(); //reads data from csv file
                List<String> source = Arrays.asList(data.split(",")); //put data in a list and saves it
                String list = Arrays.toString(source.toArray()).replace("[", "  ").replace("]", "  ").replace(",", "    "); //return to string without brackets or commas
                
                if (list.contains(name) ){
                    System.out.printf(list);
                }
                System.out.println();
                
            } while(input.hasNextLine());
           input.close();  
        }
           catch (Exception e){
                System.out.println("x");
            }
            
    }               
}
//-------------------------------------------------------------------------------------------//
    
