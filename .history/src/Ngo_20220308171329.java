import java.io.IOException;
import java.util.*;

public class Ngo {
    private String NGOname;
    private String NGOpassword;

    public Ngo() {};
    public Ngo(String NGOname, String NGOpassword) {
        this.NGOname = NGOname;
        this.NGOpassword = NGOpassword;
    }

    // public String getNgoName(){
    //     return NGOname;
    // }
    // public String getNgoPassword(){
    //     return NGOpassword;
    // }

    public static void welcomeNgo(String name, String password){

        Scanner sr = new Scanner();

        Ngo n= new Ngo(name,password);
        System.out.println
        ("\n|------------------------------------------------------------|"
        +"\n|           Welcome NGO "+name+ "                            |"
        +"\n|           Please Enter the aids required                   |"
        +"\n|------------------------------------------------------------|"
        
        +"\n|------------------------------------------------------------|");

        String aids=sc.nextLine();
    }

}