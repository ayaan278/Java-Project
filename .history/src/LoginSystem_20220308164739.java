import java.util.*;
import java.io.*;
import java.nio.file.*;

public class LoginSystem {
    private static Scanner sc;
    
    private static ArrayList<String> readCredsFromFile(int flag) throws IOException {
        ArrayList<String> credentials = new ArrayList<>();
        if(flag==1){
            List<String> lines = Files.readAllLines(Paths.get("src/credentials.csv"));
            for (int i = 0; i< lines.size();i++) {
                String[] items = lines.get(i).split(",");
                String username = items[0];
                credentials.add(username+" "+items[1]);
            }
        }
        else if(flag==2){
            List<String> lines = Files.readAllLines(Paths.get("src/NgoCredentials.csv"));
            for (int i = 0; i< lines.size();i++) {
                String[] items = lines.get(i).split(",");
                String username = items[0];
                credentials.add(username+" "+items[1]);
            }
        } 
        return credentials;
    }   
    //This method saves input into credentials.csv and should be used for signing up method
    private static void saveCredsToFile(ArrayList<Credentials> credentials) throws IOException {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < credentials.size(); i++){
        sb.append(credentials.get(i).toCSVString()+"\n");
    }
    Files.write(Paths.get("src/credentials.csv"), sb.toString().getBytes());
    }

    public static void authenticateUser(int flag) throws IOException{
    sc = new Scanner(System.in);

    ArrayList<String> credentials = readCredsFromFile(flag);

    for (int i =0; i<credentials.size();i++){
        System.out.println(credentials.get(i));
        // if(credentials.get(i).equals("ahmad 1151")) {
        //     System.out.println("okkk lesgoo");
        // }
    }

    if(flag==1){

    }
    else if(flag==2){

    }
        
                
    }
            
    //This method creates a new username and sign them up in the system and will be saved to credentials .csv
    private static void createNewUsers() throws IOException {
    String  temppassword;
    //    ArrayList<String> credentials=readCredsFromFile();
    sc = new Scanner(System.in);
    System.out.println("Please Enter your new Username: ");
    //    String username = sc.nextLine();
    System.out.println("Please Enter your new password");
    String password = sc.nextLine();
    System.out.println("Please Enter your password again: ");
    temppassword=sc.nextLine();
    if (temppassword!=password){
    do {
        System.out.println("Passwords don't match. Please Enter your password again");
        temppassword=sc.nextLine();

    } while(temppassword!=password);
    }

    System.out.println("Entry successful!, Your info has been saved in our system");

    //    credentials.add(new Credentials(username,password));
    //    saveCredsToFile(credentials);
    }

}