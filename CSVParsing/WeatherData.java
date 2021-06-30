import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
/**
 * Write a description of WeatherData here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WeatherData {
    
    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord coldest=null;
        for (CSVRecord record :parser){
            coldest=getColdestOfTwo(record, coldest);
        }
        
        return coldest;
    }
    public String fileWithColdestTemperature(){
        CSVRecord coldest =null;
        File filename=null;
        DirectoryResource dir=new DirectoryResource();
        
        for (File f : dir.selectedFiles()){
            FileResource fr= new FileResource(f);
            CSVParser parser=fr.getCSVParser();
            CSVRecord current= coldestHourInFile(parser);
            
            if(coldest==null){
                coldest=current;
            }else{
                double currentTemp=Double.parseDouble(current.get("TemperatureF"));
                double coldestTemp=Double.parseDouble(coldest.get("TemperatureF"));
                if((currentTemp <coldestTemp) && (currentTemp != -9999)){
                    coldest=current;
                    filename=f;
                }
                    
            }
        }
        return filename.getAbsolutePath();
        
    }
    public void testfileWithColdestTemperature(){
        String fileWithColdestTemp = fileWithColdestTemperature();
	File f = new File(fileWithColdestTemp);
	String fileName = f.getName();
	System.out.println("Coldest day was in file " + fileName);	
	FileResource fr = new FileResource(f);
	CSVParser parser = fr.getCSVParser();
	CSVRecord lowestTemp = coldestHourInFile(parser);

	System.out.println("Coldest Temperature is: " + lowestTemp.get("TemperatureF"));

	System.out.println("All the Temperatures on the coldest day were");
	CSVParser parser2 = fr.getCSVParser();
	for(CSVRecord record : parser2) {
		double temp = Double.parseDouble(record.get("TemperatureF"));
		System.out.println(record.get("DateUTC")+": "+temp);
	}
	
    }
    
    public CSVRecord getColdestOfTwo(CSVRecord currentRow, CSVRecord currentMax){
        if(currentMax==null){
                currentMax=currentRow;
            }else{
                double current=Double.parseDouble(currentRow.get("TemperatureF"));
                double coldestTemp=Double.parseDouble(currentMax.get("TemperatureF"));
                if((current <coldestTemp) && (current != -9999)){
                    currentMax=currentRow;
                }
                    
            }
            return currentMax;
    }
      public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord lowestSoFar = null;
        double lowest = 0;
        double current = 0;
        for (CSVRecord record: parser) {
            if (lowestSoFar == null) lowestSoFar = record;
            if (record.get("Humidity").equals("N/A")) {current = -999;}
            else {current = Double.parseDouble(record.get("Humidity"));}
            
            if (lowestSoFar.get("Humidity").equals("N/A")) {lowest = -999;}
            else {lowest = Double.parseDouble(lowestSoFar.get("Humidity"));}
            
            if (current < lowest && current != -999) lowestSoFar = record;
        }
        return lowestSoFar;
    }
    
    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVRecord result = lowestHumidityInFile(fr.getCSVParser());
        System.out.print("Lowest Humidity was ");
        System.out.print(result.get("Humidity"));
        System.out.print(" at ");
        System.out.println(result.get("DateUTC"));
    }
      
    public CSVRecord lowestHumidityInManyFiles() {
        CSVRecord lowestHumidity = null;
	DirectoryResource dr = new DirectoryResource();
	for(File file : dr.selectedFiles()){
	    FileResource fr = new FileResource(file);
	    CSVParser praser = fr.getCSVParser();
	    CSVRecord currLowHumidity = lowestHumidityInFile(praser);
	    if(lowestHumidity == null || Double.parseDouble(currLowHumidity.get("Humidity")) < Double.parseDouble( lowestHumidity.get("Humidity"))){
	       lowestHumidity = currLowHumidity;
	       }
				
			
	}//end for File loop;
		
		
	return lowestHumidity;
    }
    
    public void testLowestHumidityInManyFile() {
        CSVRecord csv = lowestHumidityInManyFiles();
	System.out.println("The lowest humidity in File is: " + csv.get("Humidity") +" at " + csv.get("DateUTC") );
    }
    
    public double TemperatureInFile(CSVParser parser) {
        double sum = 0;
        int number = 0;
        for (CSVRecord record:parser) {
            double current = Double.parseDouble(record.get("TemperatureF"));
            sum = sum + current;
            number = number + 1;
        }
        sum = sum / number;
        return sum;
    }
    
    public void testTemperatureInFile() {
        FileResource fr = new FileResource();
        double average = TemperatureInFile(fr.getCSVParser());
        System.out.print("Average temperature in file is ");
        System.out.println(average);
    }
    
    
    
    
    
    public void testColdestHour(){
        FileResource f =new FileResource();
        CSVParser parser=f.getCSVParser();
        CSVRecord coldest=coldestHourInFile(parser);
        System.out.println(coldest.get("TemperatureF")+" : "+coldest.get("DateUTC"));
    }
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        double sum = 0;
        double number = 0;
        double humidity = 0;
        for (CSVRecord record: parser) {
            if (record.get("Humidity").equals("N/A")) humidity = -999;
            else humidity = Double.parseDouble(record.get("Humidity"));
            if (humidity >= value) {
                number = number + 1;
                sum = sum + Double.parseDouble(record.get("TemperatureF"));
            }
        }
       
        return sum/number;
    }
    
    public void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource();
        double average = averageTemperatureWithHighHumidityInFile(fr.getCSVParser(), 80);
        if (average == 0) System.out.println("No temperatures with that humidity");
        else {
            System.out.print("Average temperature with high Humidity is ");
            System.out.println(average);
        }
    }
}
