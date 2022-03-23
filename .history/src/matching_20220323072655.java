import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.io.*;
public class matching{
    
    public static void main(String[]args) throws IOException{
        ArrayList<String> requiredaids = requiredaids();
        ArrayList<String> donatedaids = donatedaids();
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the aids name ngo wants:");
        String name = sc.nextLine();

        ArrayList<String> fName = new ArrayList<String>();
        ArrayList<String> fAids = new ArrayList<String>();
        ArrayList<Integer> fQty = new ArrayList<Integer>();

        for (int i =0; i<requiredaids.size();i++){
            System.out.println(requiredaids.get(i));
        }
        int j=0;
        for (int i =0; i<donatedaids.size();i++){
            System.out.println(donatedaids.get(i));
            String[] temp = donatedaids.get(i).split("\\s");

            String aids = temp[1];
            if (aids.contains(name)){
                fName.add(temp[0]);
                fAids.add(temp[1]);
                int Qty = Integer.parseInt(temp[2]);
                fQty.add(Qty);
            
            }
            
        }
        
        sc.close();
        
    }  
    

    private static ArrayList<String> requiredaids() throws IOException{
        ArrayList<String> required = new ArrayList<>();
        List<String>lines = Files.readAllLines(Paths.get("src/Documentation/ngoDemands.csv"));
        for(int i =0; i<lines.size();i++){
            String[] items = lines.get(i).split(",");
            String ngoName = items[0];
            String aidRequired = items[1];
            String quantityRequired = items[2];
            String status = items[3];
            required.add(ngoName+" "+aidRequired+" "+quantityRequired+ " "+ status);

        }
        return required;
    }
    private static ArrayList<String> donatedaids() throws IOException{
        ArrayList<String> required = new ArrayList<>();
        List<String>lines = Files.readAllLines(Paths.get("src/Documentation/DonatedItems.csv"));

        for(int i =0; i<lines.size();i++){
            String[] items = lines.get(i).split(",");
            String Name = items[0];
            String aids = items[1];
            String quantity = items[2];
            required.add(Name+" "+aids+" "+quantity);

        }
        return required;
    }
    }
