
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    
    public String findSimpleGene(String dna){
        //Finds the index position of the start codon “ATG”. If there is no “ATG”, return the empty string.
        int indexATG=dna.indexOf("ATG");
        int indexTAA=dna.indexOf("TAA");
        
  
        if(indexATG!=-1){
            System.out.println("ATG index= "+indexATG);
            if((indexTAA !=-1) && (indexTAA>indexATG)){
                System.out.println("TAA index= "+indexTAA);
                String substring=dna.substring(indexATG+3,indexTAA);
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
        
        String result=findSimpleGene(ATGandTAA_noMulti);
        System.out.println("Result= "+result);
        
        
        
    }

}
