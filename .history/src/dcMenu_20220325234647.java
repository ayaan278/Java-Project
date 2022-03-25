import java.util.*;
import java.io.IOException;

public class DcMenu {
    public static void showDcMenu() throws IOException {
        System.out.println
        ("\n|---------------------------------------------------------------|"
        +"\n|               Welcome to Distribution Center                  |"
        +"\n|---------------------------------------------------------------|"
        +"\n|     Enter 1: To view all the aids available in DC             |"
        +"\n|     Enter 2: To view all the NGO's demand                     |"
        +"\n|     Enter 3: To match the aids available to NGO               |"
        +"\n|     Enter 4: To view all the aids donated by specific Donor   |"
        +"\n|     Enter 5: To view all the aids recieve by specific NGO     |"
        +"\n|     Enter 6: To show the history of all the aids distributed  |"
        +"\n|---------------------------------------------------------------|");
        
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();      //takes the input from the user

        switch (choice) {
            case 1:     DonorMenu.showAvailableAids("null",1);
                        directback();
                        break;
            case 2:     NgoMenu.showStatus("null",1);
                        directback();
                        break;
            case 3:     Matching.filter();
                        directback();
                        break;
            case 4:     System.out.println("Please Enter the name of the Donor-");
            show(0);
                        directback();
                        
                        break;
            case 5: System.out.println("Please Enter the name of the NGO-");
            directback();
                    show(0);
                    break;
            case 6: show(1);    
            directback();
                    break;
        }
        sc.close();
    }
    //-------------------------------------------------------------------------------------------//

    //------------------This method is show the history according to the flag---------------------------
    public static void show(int flag){
        if(flag==0){
                Scanner sc = new Scanner(System.in);
                String str;
                str = sc.nextLine();            //takes the name of NGO/Donor
                ShowHistory.viewAidsHistory(str,0);     //calls show history for method 
                sc.close();
        }
        else if(flag==1){
                ShowHistory.viewAidsHistory("null",1);  //calls the show history method without any Donor/Ngo name
        } 
    }
    //-------------------------------------------------------------------------------------------//

    //------------------This method directs back the user----------------------------------------------
    public static void directback() throws IOException{
        Scanner sc = new Scanner(System.in);
        System.out.println(      "\n|---------------------------------------------------------------|"
                                +"\n| Please Press 1 to continue back to your menu                  |"
                                +"\n| Please Press 0 or any other number to exit the menu           |"
                                +"\n|---------------------------------------------------------------|");
        int choice = sc.nextInt(); 
        if(choice == 1){                //if choice is 1
                showDcMenu();
        }
        else{                           //if choice is 0 or any other number
                System.exit(0);
        }
        sc.close();          
    }
    //-------------------------------------------------------------------------------------------//
}