import java.util.*;
import java.io.*;

public class ngoMenu {
    public static void welcomeNgo(String name){

        Scanner sc = new Scanner(System.in);

        System.out.println
        ("\n|------------------------------------------------------------|"
        +"\n           Welcome NGO "+name+ "                             "
        +"\n|------------------------------------------------------------|"
        +"\n|      Enter 1: To Enter the aids required                   |"
        +"\n|      Enter 2: To view if requested aids are satisfied      |"
        +"\n|      Enter 3: To view history of aids received             |"
        +"\n|------------------------------------------------------------|");

        System.out.println("Enter name of the aid");
                        String amount=sc.nextLine();
                        System.out.println("Enter the number of quantity");
                        int qty=sc.nextInt();
    
                        ngo set = new ngo(amount, qty);
    
                        set.saveNgoDemands();
        int choice = sc.nextInt();

        switch (choice) {
            case (1):   
                        
                        break;
            case (2):   break;
            case (3):   break;
        }
        
        sc.close();

    }

}