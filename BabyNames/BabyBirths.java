import edu.duke.*;
import org.apache.commons.csv.*;

/**
 * Write a description of BabyBirths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BabyBirths {
    
    public void printNames(){
        FileResource fr= new FileResource(); 
        for (CSVRecord rec: fr.getCSVParser(false)){//when setting to false the first row wont have a header
            System.out.println("Name: "+rec.get(0)+"Gender: "+rec.get(1)+"Number born: "+rec.get(2));
            
        }
    }   
    public void totalBirths(FileResource fr){
        int totalBirths=0;
        int totalBoys=0;
        int totalGirls=0;
        for(CSVRecord rec: fr.getCSVParser(false)){
            int numBorn= Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if(rec.get(1).equals("M")){
                totalBoys+=numBorn;
    
            }else{
                totalGirls+=numBorn;
            }
       }
        System.out.println("Total Births: "+totalBirths);
        System.out.println("Total Boys: "  +totalBoys);
        System.out.println("Total Girls: " +totalGirls);
    }
    public void getRank(){
        
    }
    
    public void testTotalBirths(){
        FileResource fr= new FileResource();
        totalBirths(fr);
    }
}
