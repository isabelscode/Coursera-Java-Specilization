import edu.duke.*;
import org.apache.commons.csv.*;
/**
 * Write a description of CountryExports here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CountryExports {
    
    public String countryInfo(CSVParser parser, String country){
        for (CSVRecord record :parser){
            String countryCSV= record.get("Country");
            if (countryCSV.equalsIgnoreCase(country)){
                String exports=record.get("Exports");
                String value=record.get("Value (dollars)");
                return country+":"+exports+":"+value;
            }
            
        }
        
        return "COUNTRY NOT FOUND"; 
    }
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
        for(CSVRecord record:parser){
            String exports=record.get("Exports");
            
            if(exports.contains(exportItem1) && exports.contains(exportItem2)){
                String country=record.get("Country");
                System.out.println(country);
            }
       
        
       }
       
    }
    public int numberofExporters(CSVParser parser, String exportItem){
        int counter=0;
        for(CSVRecord record : parser){
            String export=record.get("Exports");
            
            if(export.contains(exportItem)){
                counter+=1;
            }
        }
        return counter;
    }
    public void bigExporters(CSVParser parser, String amount){
        for(CSVRecord record :parser){
            String value= record.get("Value (dollars)");
            String country=record.get("Country"); 
            if(value.length() > amount.length()){
                System.out.println(country+" "+value);
            }
        }
        
    }
    
    
    public void tester(){
        FileResource fr =new FileResource();
        CSVParser parser= fr.getCSVParser();
        //Test the methods created here
        //System.out.println(countryInfo(parser,"Nauru"));
        listExportersTwoProducts(parser, "cotton", "flowers");
        //System.out.println(numberofExporters(parser, "cocoa"));
        
        //bigExporters(parser,"$999,999,999,999");
        
        
        //parser = fr.getCSVParser();//everytime you want to use the parser with another method you need to reset the parser by calling this
        
        
    }
    
}
