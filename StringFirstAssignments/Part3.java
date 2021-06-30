
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public boolean twoOccurrences (String stringa, String stringb){
        //returns true if stringa appears at least twice in stringb, otherwise false
        int index=stringb.indexOf(stringa);
       
        if (index !=-1){
            index+= stringa.length();
            if(index<= stringb.length()-stringa.length()){
                index=stringb.indexOf(stringa,index);
                if (index !=-1){
                    return true;
                }else{
                    return false;
                }
            }else{
                return false;
            }

        }else{
            return false;
        }
        
        
    }
    
    public String lastPart(String stringa,String stringb){
        int index= stringb.indexOf(stringa);
        if(index !=-1){
            String lastPart=stringb.substring(index+stringa.length());
            return lastPart;
        }else{
            return stringb;
        }
        
    }

    public void twoOccurancesTest(){
        String a1="cat",b1="catalog catagories"; //should return true
        String a2="dog",b2="burnhouse"; //should return false
        String a3="bird",b3="birdhouse"; // should return false
        String a4= "an", b4="banana";//should return true
        
        boolean result= twoOccurrences(a4,b4);
        System.out.println("Results= "+result);
        
    
    
    }
    public void lastPartTest(){
        String a1="an", b1="banana";
        String a2="zoo", b2="forest";
        
        String result= lastPart(a2,b2);
        System.out.println("Results= "+result);
        
    }

}
