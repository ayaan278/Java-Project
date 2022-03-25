import java.util.*;
import java.io.*;

public class showHistory {
    public static void viewAidsHistory(String name,int flag){
        File file =  new File("src/Documentation/donor&NgoMatched.csv");
        try{
            Scanner input = new Scanner(file);  
            System.out.format("%10s %15s %15s %10s %10s %10s","Donor","PhoneNumber","Aid","Quantity","Ngo","Manpower"+"\n"); //template for the table.
            do{
                String data = input.nextLine(); //reads data from csv file
                List<String> source = Arrays.asList(data.split(",")); //put data in a list and saves it
                String list = Arrays.toString(source.toArray()).replace("[", "  ").replace("]", "  ").replace(",", "    "); //return to string without brackets or commas
                
                if (list.contains(name) && flag==0){
                    for(int i = 0; i < list.size(); i++){
                        String[] temp = list.get(i).split("\\s");
                        System.out.println("%10s %15s %15s %10s %10s %10s",temp[0],temp[1],temp[2],temp[3],temp[4],temp[5]);
                    }
                    //System.out.printf(list); //to print just the data of named entity
                }
                else if(flag==1){
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