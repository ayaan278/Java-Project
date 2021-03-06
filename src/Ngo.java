import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;

/**This class contains the constructors and methods to enter NGO requests in the DC*/
public class Ngo {
    /**Name of the NGO*/
    private String name;
    /**Name of the NGO's requests aids*/
    private String aids;
    /**Quantity of the NGO's request*/
    private int quantity;
    /**Status of the NGO requests*/
    private String status;

    /**Initializing name, aids , status to null and quantity t0 0 by default*/
    public Ngo() {};

    /**Initializing name, aids, quantity, status the values passed by the user while creating objects*/
    public Ngo(String name, String aids, int q, String status) {
        this.name = name;
        this.aids = aids;
        quantity = q;
        this.status = status;
    }
    
    /**This method will save all the request by the donor*/
    public void saveNgoDemands() throws IOException{
        ArrayList<String> data = requestedaids();        //this takes the previous aids requested currently in the DC

        ArrayList<String> nName = new ArrayList<String>();       //ArrayList stores all the name 
        ArrayList<String> nAids = new ArrayList<String>();       //ArrayList stores all the aids 
        ArrayList<Integer> nQty = new ArrayList<Integer>();      //ArrayList stores all the quantity 
        ArrayList<String> nStatus = new ArrayList<String>();     //ArrayList stores all the status
        int flag=0;
        for(int i = 0; i < data.size();i++){
            String[] temp = data.get(i).split("\\s");

            //initializing name, aids, quantity, status respectively
            String nameNgo = temp[0];
            String aidsNgo = temp[1];
            int qty = Integer.parseInt(temp[2]);    //convert to integer
            String statusNgo = temp[3];

            //if ngo request already exists
            if(nameNgo.equals(name) && aidsNgo.equals(aids)){
                qty += quantity;                //increase the number of the quantity requested
                flag=1;                         //to mark that ngo request already exists
            }

            nName.add(nameNgo);                 //append the name of the NGO
            nAids.add(aidsNgo);                 //append the name of the aids requested
            nQty.add(qty);                      //append the number of the quantity requested
            nStatus.add(statusNgo);             //append the status of the request
        }
        if(flag==0){                            //if new request is created
            nName.add(name);                    //append the name of the NGO
            nAids.add(aids);                    //append the name of the aids requested
            nQty.add(quantity);                 //append the number of the quantity requested
            nStatus.add(status);                //append the status of the request
        }
        WriteBack.updateNgo(nName, nAids, nQty, nStatus);       //write back the array again in ngoDemands.csv
    }  
    //-------------------------------------------------------------------------------------------//

    /**This method returns the requested aids by all the ngo*/
    public static ArrayList<String> requestedaids() throws IOException{
        ArrayList<String> required = new ArrayList<>();     //to store all the requested aids lists
        List<String>lines = Files.readAllLines(Paths.get("src/Documentation/NgoDemands.csv"));

        for(int i =0; i<lines.size();i++){
            String[] items = lines.get(i).split(",");       //split comma
            String ngoName = items[0];                      //store the names
            String aidRequired = items[1];                  //store the aids
            String quantityRequired = items[2];             //stores the quantity
            String status = items[3];                       //store the status
            required.add(ngoName+" "+aidRequired+" "+quantityRequired+ " "+ status);
        }
        return required;                                    //return the requested aids
    }
    //-------------------------------------------------------------------------------------------//
}
