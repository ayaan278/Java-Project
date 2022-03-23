// import java.io.File;
import java.io.IOException;
// import java.nio.file.Files;
// import java.nio.file.Paths;
import java.util.*;
import java.io.*;
public class matching{  

    public static void main(String[]args) throws IOException{
        ArrayList<String> donatedaids = Donor.donatedaids();
        ArrayList<String> requiredaids = Ngo.requiredaids();
        
        Scanner sc = new Scanner(System.in);

        ArrayList<String> dName = new ArrayList<String>();
        ArrayList<String> dAids = new ArrayList<String>();
        ArrayList<Integer> dQty = new ArrayList<Integer>();

        ArrayList<String> nName = new ArrayList<String>();
        ArrayList<String> nAids = new ArrayList<String>();
        ArrayList<Integer> nQty = new ArrayList<Integer>();
        ArrayList<String> nStatus = new ArrayList<String>();

        for (int i =0; i<requiredaids.size();i++){
             String[] temp = requiredaids.get(i).split("\\s");

             String status = temp[3];
             if (status.contains("unsatisfied")){
                 nName.add(temp[0]);
                 nAids.add(temp[1]);
                 int Qty = Integer.parseInt(temp[2]);
                 nQty.add(Qty);
                 nStatus.add(temp[3]);
             }
        }
        for(int i=0; i< nAids.size();i++){
            System.out.println(nName.get(i) + " " + nAids.get(i) + " " + nQty.get(i) + " " + nStatus.get(i));
        }

        System.out.println("Enter the name of the Ngo which requests you want to satisfy: ");
        String name = sc.nextLine();

        int iter=0;

        for (int i =0; i<donatedaids.size();i++){
            String[] temp = donatedaids.get(i).split("\\s");

            String nameDonor = temp[0];
            String aids = temp[1];
            int Qty = Integer.parseInt(temp[2]);

            Sys
            if (aids.contains(name)){
                dName.add(nameDonor);
                dAids.add(aids);
                dQty.add(Qty);
            }
            else{
                System.out.println(aids);
                String path  = "src/Documentation/DonatedItems.csv";
                
                try( FileWriter fw = new FileWriter(path);){
                    if(iter==0){
                        fw.write(nameDonor+"," + aids + "," + Qty);
                        i++;
                    }
                    else{
                        fw.write("\n"+nameDonor+"," + aids + "," + Qty);
                    }
                    fw.close();
                }catch(Exception e){

                }
            }
        }
        matchAlgo.runAlgo(name, dName, dAids, dQty, nName, nAids, nQty, nStatus);
        sc.close();  
    }  

}