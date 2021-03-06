import java.io.IOException;
import java.util.*;

/**This class contains the method to filter out Donated aids*/
public class Matching extends MatchAlgorithm{

    /**Initializing scanner*/
    private static final Scanner sc = new Scanner(System.in);
    
    /**This method to filters out Donated aids and pass it all to match the requested aids*/
    public static void filter() throws IOException{
        ArrayList<String> donatedaids = Donor.donatedaids();     //this takes the previous aids currently in the DC
        ArrayList<String> requiredaids = Ngo.requestedaids();   //this takes the previous aids requested to the DC
    
        //these array lists are to store filtered donors aids information accordingly
        ArrayList<String> dName = new ArrayList<String>();
        ArrayList<String> dAids = new ArrayList<String>();
        ArrayList<Integer> dQty = new ArrayList<Integer>();

        //these array lists are to store left over donor aids information, not to be matched in this iteration
        ArrayList<String> fName = new ArrayList<String>();
        ArrayList<String> fAids = new ArrayList<String>();
        ArrayList<Integer> fQty = new ArrayList<Integer>();

        //these array lists are to store NGO requested aids information
        ArrayList<String> nName = new ArrayList<String>();
        ArrayList<String> nAids = new ArrayList<String>();
        ArrayList<Integer> nQty = new ArrayList<Integer>();
        ArrayList<String> nStatus = new ArrayList<String>();

        for (String requiredaid : requiredaids) {
            String[] temp = requiredaid.split("\\s");  //split by spaces

            String status = temp[3];       //stores the status of the aids in NGO
            if (status.contains("unsatisfied")) {   //if status is "unsatisfied"
                nName.add(temp[0]);        //stores the name of the NGO
                nAids.add(temp[1]);        //stores the aids name
                int Qty = Integer.parseInt(temp[2]);   //converts the quality of the aids to Integer
                nQty.add(Qty);             //stores the quality of the aids
                nStatus.add(temp[3]);      //stores the status of the aids
            }
        }
        System.out.println("\n|------------------------------------|");
        System.out.format("|%10s  |%11s |%10s|","Name ","Aid","Quantity"); //template for the table.
        System.out.println("\n|------------------------------------|");

        for(int i=0; i< nAids.size();i++){
            String[] temp = requiredaids.get(i).split("\\s+");  //split by spaces
            System.out.printf("| %10s |%11s |%10s|",temp[0],temp[1],temp[2]);   //print the data
            System.out.println("\n|------------------------------------|");
        }
        System.out.println("-------------------------------------------------------------------");
        System.out.println("Enter the name of the aids which you want to satisfy: ");   //asks which aids user wants to satisfy
        System.out.println("-------------------------------------------------------------------");

        String name = sc.nextLine();        //takes the name of the aids
        int flag=0;
        for (String donatedaid : donatedaids) {
            String[] temp = donatedaid.split("\\s+");   //split the array

            String aids = temp[1];
            int Qty = Integer.parseInt(temp[2]);                //converts the quantity to a number

            if (aids.equals(name)) {       //if aids matches the entered aid
                dName.add(temp[0]);         //stores the name of the NGO
                dAids.add(aids);            //stores the name of the aids
                dQty.add(Qty);              //store the quantity
                flag = 1;                     //to check if at least one aid is available in the aids list
            } else {                           //else if the aid requested doesn't match in the list
                fName.add(temp[0]);         //stores the name of the NGO
                fAids.add(aids);            //stores the name of the aids
                fQty.add(Qty);              //store the quantity of the aids
            }
        }
        if(flag==0){                        //if requested aids is not in the list
            System.out.println("------------------------------------------------------------------------------------------");
            System.out.println("The required aid by the Ngo's is currently unavailable in the distribution center");
            System.out.println("Please, Check back later");
            System.out.println("Sorry for inconvenience");
            System.out.println("------------------------------------------------------------------------------------------");
        }
        else{                               //if requested aids is in the list
            WriteBack.updateDonor(fName, fAids, fQty,0);        //send the Donor aids not required back in the DonatedItems.csv
            runAlgo(name, dName, dAids, dQty, nName, nAids, nQty, nStatus);      //calls the method to run the Algorithm
        }
    }  
    //-------------------------------------------------------------------------------------------//
}