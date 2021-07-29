import edu.duke.*;
import java.io.*;
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
    public int getRank(int year, String name, String gender){
        int rank=1;
        String yearS= Integer.toString(year);
        //Need to make sure the right file is used so we are going to determin the name here with the parameters given
        String dirName= "us_babynames_small\\testing\\"; 
        String fileName= "yob"+yearS+"short.csv";
        
        FileResource fr= new FileResource(dirName+fileName); 
        if(gender.equalsIgnoreCase("F") || gender.equalsIgnoreCase("M")){
            for(CSVRecord rec: fr.getCSVParser(false)){
                if(rec.get(1).equals(gender)){
                    if(rec.get(0).equals(name)){
                        return rank;
                    }
                    rank++;
                }
            }
            
        }else{
            System.out.println("Please enter the gender in the corrent format");
        }
        
        return -1;
        
    }
    public String getName(int year, int rank, String gender){
        String yearS= Integer.toString(year);
        int rankCompare=1;
        
        String dirName= "us_babynames_small\\testing\\"; 
        String fileName= "yob"+yearS+"short.csv";
        
        FileResource fr= new FileResource(dirName+fileName);
        for(CSVRecord rec : fr.getCSVParser(false)){
            if(rec.get(1).equals(gender)){
                if(rank == rankCompare){
                    return rec.get(0);
                }
                rankCompare+=1;
            }
        }
        return "NO NAME";
    }
    public void whatIsNameInYear(String name, int year, int newYear, String gender){
        int rankOri= getRank(year, name, gender);
        String newName= getName(newYear, rankOri, gender);
        
        System.out.println(name+" born in "+year+" would be "+newName+" if they were born in "+newYear);
        
        
    }
    
    public int yearOfHighestRank(String name, String gender){
        int highestRank=0;
        int year=0; 
        DirectoryResource dir= new DirectoryResource();
        for(File f: dir.selectedFiles()){
            int currYear= Integer.parseInt(f.getName().substring(3,7));
            int currRank= getRank(currYear, name, gender);
            if (currRank !=-1){
                if(highestRank==0 || currRank< highestRank){
                    highestRank=currRank;
                    year=currYear;
                    
                }
            }
        }
        if (year==0){
            return -1;
        }else{
            return year;
        }
        
    }
    
    public double getAverageRank(String name, String gender){
        double totalRank=0.0;
        double numberOfFiles=0.0;
        
        DirectoryResource dir= new DirectoryResource();
        for(File f: dir.selectedFiles()){
            numberOfFiles++;
            int currYear= Integer.parseInt(f.getName().substring(3,7));
            double currRank= getRank(currYear, name, gender);
            if(currRank !=-1){
                totalRank+=currRank;
            }
              
        }
        if(totalRank ==0){
            return -1.0;
        }else{
            return totalRank/numberOfFiles;
        }
        
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender){
        String yearS= Integer.toString(year);
        String dirName= "us_babynames_small\\testing\\"; 
        String fileName= "yob"+yearS+"short.csv";
        FileResource fr= new FileResource(dirName+fileName);
        int startRank= getRank(year,name, gender);
        int total=0;
        for(int i=0; i<startRank;i++){
            for (CSVRecord rec: fr.getCSVParser(false)){
                if(rec.get(1).equals(gender)){
                    total+=Integer.parseInt(rec.get(2));
                }
            }
        
        }        
        return 0;
    }
    //Testing Methods
    public void testTotalBirthsRankedHigher(){
        int total = getTotalBirthsRankedHigher(2012,"Ethen","M");
        System.out.println("Total Ranked Higher: "+total);
    }
    public void testGetAverageRank(){
        double averageRank= getAverageRank("Olivia","F");
        System.out.println("Average Rank: "+averageRank);
    }
    
    public void testYearOfHighestRank(){
        double highestRank = yearOfHighestRank("Olivia","F");
        System.out.println("Highest Rank: "+highestRank); 
        
    }
    public void testwhatNameInYear(){
        whatIsNameInYear("Olivia",2012,2013,"F");
    }
    public void testgetName(){
        String name=getName(2012,4,"F");
        System.out.println("Name: "+name);
    }
    public void testgetRank (){
        int rank =getRank(2012,"Olivia","F");
        if(rank == -1){
            System.out.println("The combination of name/gender/year is invalid. No data detected");
        }else{
            System.out.println("Rank: "+rank);
        }
         
    }
    
    public void testTotalBirths(){
        FileResource fr= new FileResource();
        totalBirths(fr);
    }
}
