import java.util.ArrayList;

public class writeBack {
    public static void store(){

    }
    public static void updateDonor(ArrayList<String> dName,ArrayList<String> dAids,
                               ArrayList<Integer> dQty, ArrayList<String> nName,
                               ArrayList<String> nAids,ArrayList<Integer> nQty,
                               ArrayList<String> nStatus){
        try{
            String path  = "src/Documentation/DonatedItems.csv";
            FileWriter fw = new FileWriter(path,true);
        }                        
    }
}