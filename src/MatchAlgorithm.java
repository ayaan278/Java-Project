import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**This class contains the method to match the request*/
public class MatchAlgorithm {

    /**This method handles the algorithm to match the request*/
    public static void runAlgo(String name, ArrayList<String> dName,                //takes parameter of aids names, Donor's &
                                ArrayList<String> dAids ,ArrayList<Integer> dQty,   // and NGO's data in different ArrayLists
                                ArrayList<String> nName, ArrayList<String> nAids, 
                                ArrayList<Integer> nQty, ArrayList<String> nStatus) throws IOException{
                                
        for(int i =0; i< nName.size();i++){
            //checks if name of the aids to satisfy is equals to aids in the ngo demands
            if(name.equals(nAids.get(i))){

                //sorting function in descending order based on Quantity According to Donor's list
                sortArray(dName, dAids, dQty);

                if(dQty.get(0) == 0){
                    break; //that means all the elements inside dqty are 0. 
                }
                //for 1-1 matching: since dQty(0) is the largest after sorting,
                //comparing if dQty(0) is enough
                if(nQty.get(i)<=dQty.get(0)){
                    //write back the transactions in the file 
                    WriteBack.store(dName.get(i), dAids.get(i), nQty.get(i), nName.get(i), true);
                    dQty.set(0,dQty.get(0)-nQty.get(i));    //subtracts the difference from donors aids
                    nQty.set(i,0);                          //make requested aids 0 after satisfaction
                    nStatus.set(i,"satisfied");             //set status as "satisfied"
                    break;
                }   
                else{
                    for(int j=0; j< dQty.size();j++){
                        //checks if every next element is enough to satisfy the requests completely
                        if(nQty.get(i)<=dQty.get(j)){
                            dQty.set(j,dQty.get(j)-nQty.get(i)); //subtracts the difference from the donors aids
                            if(dQty.get(j)!=0){
                                //write back the transactions in the file 
                                WriteBack.store(dName.get(j), dAids.get(j), nQty.get(i), nName.get(i), true);
                            }
                            nQty.set(i,0);      //Ngo request satisfied
                            nStatus.set(i,"satisfied");     //set status as "satisfied"
                            break;
                        }
                        else{
                            //write back the transactions in the file 
                            WriteBack.store(dName.get(j), dAids.get(j), dQty.get(j), nName.get(i), true);
                            nQty.set(i,nQty.get(i)-dQty.get(j));    //subtracts the difference from the requested aids
                            dQty.set(j,0);      //set the donor aids zero
                        }
                    }
                }
            }
        }
        //write back the updated Donors aids in the file 
        WriteBack.updateDonor(dName, dAids, dQty,1);
        //write back the updates NGO request in the file
        WriteBack.updateNgo(nName, nAids, nQty, nStatus);
    }
    //-------------------------------------------------------------------------------------------//

    /**This method to sorts out Donated aids in Descending order*/
    public static void sortArray(ArrayList<String> dName,ArrayList<String> dAids,
                                ArrayList<Integer> dQty){   //takes Donors aids in different Arraylists 
        for(int i=0;i<dQty.size()-1;i++){
            for(int j=i+1;j<dQty.size();j++){
                if(dQty.get(i)<dQty.get(j)){        //if next element is greater than the previous one
                Collections.swap(dQty,i,j);         //swap the quantity
                Collections.swap(dName,i,j);        //swap the Donor name
                Collections.swap(dAids,i,j);        //swap the aids name
                }       
            }
        }
    }
    //-------------------------------------------------------------------------------------------//
}