import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;

/**This class contains the constructors and methods to enter Donor aids in the DC*/
public class Donor{
    /**Name of the Donor*/
    private String name;
    /**Name of the aids*/
    private String itemName;
    /**Number of items*/
    private int quantity;

    /**Initializing name, itemName, quantity to null and 0 respectively by default*/
    public Donor(){}

    /**initializing name, itemName, quantity to what user passed while creating the Object*/
    public Donor(String name,String itemName, int qty){
        this.name = name;
        this.itemName = itemName;
        quantity = qty;
    }

    /**This method will save all the aids donated by the donor*/
    public void saveDonorAids() throws IOException{
        ArrayList<String> data = donatedaids();     //this takes the previous aids currently in the DC

        ArrayList<String> dName = new ArrayList<String>();      //ArrayList stores all the name
        ArrayList<String> dAids = new ArrayList<String>();      //ArrayList stores all the aids names
        ArrayList<Integer> dQty = new ArrayList<Integer>();     //Arraylist stores all the quantity
        int flag=0;
        WriteBack.store(name, itemName, quantity, "0", false);
        for (String datum : data) {
            String[] temp = datum.split("\\s");           //split the comma

            String nameDonor = temp[0];                         //stores the name of the donor
            String aids = temp[1];                              //stores the aids donated by the donor
            int qty = Integer.parseInt(temp[2]);                //stores the quantity of aids donated convert it to integer
            //if donor name and aids already exists in the file
            if (nameDonor.equals(name) && aids.equals(itemName)) {
                qty += quantity;            //increase the number of quantity
                flag = 1;                     //to mark that donor aids already exists
            }

            dName.add(nameDonor);           //appends the name of the donor
            dAids.add(aids);                //appends the aids
            dQty.add(qty);                  //appends the quantity
        }
        if(flag==0){                        //if new aid by the donor
            dName.add(name);                //appends the name
            dAids.add(itemName);            //appends the new aid
            dQty.add(quantity);             //appends the quantity
        }
        WriteBack.updateDonor(dName, dAids, dQty,0);   //write back the array again in the DonatedItems.csv
    }
    //-------------------------------------------------------------------------------------------//

    /**This method returns the donated aids by all the donors*/
    public static ArrayList<String> donatedaids() throws IOException{
        ArrayList<String> previousAids= new ArrayList<>();     //to store all the donated aids lists
        List<String>lines = Files.readAllLines(Paths.get("src/Documentation/DonatedItems.csv"));

        for(int i =0; i<lines.size();i++){
            String[] items = lines.get(i).split(",");  //splits the comma
            String name = items[0];                    //stores the name
            String aids = items[1];                    //stores the aids name
            String quantity = items[2];                //stores the quantity
            previousAids.add(name+" "+aids+" "+quantity);  //stores every thing in the array
        }
        return previousAids;                                //return the array
    }
    //-------------------------------------------------------------------------------------------//
}