import java.util.*;
import java.io.*;

/**This class contains the methods for the Donor menu*/
public class DonorMenu extends Donor{
    /**Initializing scanner*/
    private static final Scanner sc = new Scanner(System.in);
    /**Shows Donor Menu and re-routes to enter aids or view all aids donated*/
    public static void welcomeDonor(String name) throws Exception { //takes the name of the Donor
    
    String menu=
    ("\n|------------------------------------------------------------|"
    +"\n                Welcome Donor "+name+"                        "
    +"\n|------------------------------------------------------------|"
    +"\n|     Enter 1: To Enter the aids to be donated               |"
    +"\n|     Enter 2: To view all the aids donated to DC            |"
    +"\n|     Enter 3: To view all the aids donated delivered to NGO |"
    +"\n|------------------------------------------------------------|");
    
    System.out.println(menu);
    int choice= sc.nextInt();           //takes the Donor choice according to the menu
    switch (choice){
        case(1):    enterAids(sc, name);      //re-routes to enter aids method.
                    break;
        case(2):    showAvailableAids(name,0); //show the aids still in DC
                    break;    
        case(3):    ShowHistory.viewAidsHistory(name,0); //re-routes to view all aids donated.
                    break;
    }
    DirectBackMenu.directBack(name,1);   //take user to direct back menu
    sc.close();
    }
    //-------------------------------------------------------------------------------------------//
   
   /**This Method allows Users to enter aids to donate along with donor info*/
    public static void enterAids(Scanner sc, String name) throws IOException{  //takes the name of the Donor
        sc = new Scanner(System.in);
        System.out.println("-------------------------------------------------------------------");
        System.out.println("Please Enter the item name: ");
        String itemName = sc.nextLine();            // takes the aid to be donated
        System.out.println("Please Enter How many items you want to donate");
        int quantity = sc.nextInt();                // takes the quantity to be donated 
        System.out.println("-------------------------------------------------------------------");
        if(quantity>0){                             // the quantity cannot be 0 or negative
            Donor set = new Donor(name,itemName,quantity); // create a new object to store the Donor
            set.saveDonorAids();        // calls the method to save the Donor aids
            System.out.println("\nThank you for your Donation\nThe aids you donated will be used wisely by the DC");
            
        }
        else{                           // else ask the donor to try again
            System.out.println("Quantity cannot be 0 or less"+"\nPlease try again");
            enterAids(sc, name);            // re-routes the Donor to try entering again
        }
    }
    //-------------------------------------------------------------------------------------------//
    
    /**This method show all the Available aids in the System*/
    public static void showAvailableAids(String name,int flag){    // takes the name of the Donor and flag
        File file =  new File("src/Documentation/DonatedItems.csv");//stores the path of the file
        try{
            Scanner input = new Scanner(file); 
            System.out.println("\n|------------------------------------|");
            System.out.format("|%10s  |%11s |%10s|","Name ","Aid","Quantity"); //template for the table.
            System.out.println("\n|------------------------------------|");
            boolean found = false;      //to check if data to print is there in the files
            do{
                String data = input.nextLine();                       //reads data from csv file
                List<String> source = Arrays.asList(data.split(",")); //put data in a list and saves it
                String list = Arrays.toString(source.toArray()).replace("[", "  ").replace("]", "  ").replace(",", "    "); //return to string without brackets or commas
                
                if (list.contains(name) && flag==0){     // if the list contains the name of the Donor
                    String[] temp = list.split("\\s+");  // splits the spaces
                    System.out.printf("| %10s |%11s |%10s|",temp[1],temp[2],temp[3]);  //print the data
                    System.out.println("\n|------------------------------------|");
                    found = true;
                }
                else if(flag==1){                       // if DC wants to check all the available aids
                    String[] temp = list.split("\\s+"); // splits the comma
                    System.out.printf("| %10s |%11s |%10s|",temp[1],temp[2],temp[3]); //prints the data
                    System.out.println("\n|------------------------------------|");
                    found =true;
                }
            } while(input.hasNextLine());   //while the end of the file
            if(!found){
                System.out.printf("| %10s |%11s |%10s |%10s |%10s |%10s|","X","X","X","X","X","X");//prints the data
                System.out.println("\n|------------------------------------------------------------------------|");
                System.out.println("\n\nThere is nothing to show in the system");
            }
        } catch (Exception e){              //if unable to read form the file
            System.out.println("-------------------------------------------------------------------");
            System.out.println("Unable to print DonatedItems.csv");
            System.out.println("-------------------------------------------------------------------");
        }
    }
    //-----------------------------------------------------------------------------------------------//
}
