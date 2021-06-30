
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public String findSimpleGene(String dna, String startCodon, String stopCodon){
        //Finds the index position of the start codon “ATG”. If there is no “ATG”, return the empty string.
        int indexStart=dna.indexOf(startCodon);
        int indexStop=dna.indexOf(stopCodon);
        
  
        if(indexStart!=-1){
            System.out.println("Start Codex index= "+indexStart);
            if((indexStop !=-1) && (indexStop>indexStart)){
                System.out.println("Stop Codex index= "+indexStop);
                String substring=dna.substring(indexStart+3,indexStop);
                if((substring.length() %3)==0){
                    return substring; 
                }else{
                    return "";
                }
                
            }else{
                return "";
            }
        }else{
            return ""; 
        }
       
    }
    
    public void testSimpleGene(){
        String noATG="TAACGTAGC";
        String noTAA="ATGCGTAGC";
        String noATG_TAA="AAGCTAGG";
        String ATGandTAA_multi="ATGGGCTAA";
        String ATGandTAA_noMulti="ATGGCTAAC";
        
        String startCodon="ATG";
        String stopCodon="TAA";
        String result=findSimpleGene(ATGandTAA_noMulti,startCodon,stopCodon);
        System.out.println("Result= "+result);
        
        
        
    }


}
