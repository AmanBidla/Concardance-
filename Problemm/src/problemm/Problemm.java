/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problemm;

/* Applied StringTokenizer to Split the String but it restricted me to extract previous element, so reverted to String SPLIT.
   Used TreeSet because it's sorted but Object cannot be compared in a set, so moved to TreeMap to Key value pair
   Created Object Class to track count of each word and used ArrayList good enough to track sentence count.
   Summary: Splitted the String, passed each word to object class, added String and Object as Key Value pair in TreeMap.   
   Applied Conditions and String Manipulation logic for sentence break and managing delimiters. Please go through comments for code functionality.    
*/

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;

/**
 *
 * @author birla
 */
public class Problemm {

    /**
     * @param args the command line arguments
     */
    
// Current Sentence number in file, initial will be 1
    static int sentencecount=1; 

// Automatically sorts the elements according to key    
    static TreeMap<String,Object> set = new TreeMap<>();
    
    public static void main(String[] args) {
        
        String file ="Given an arbitrary text document written in English, write a program that will generate a concordance, i.e. "
                + "an alphabetical list of all word occurrences, labeled with word frequencies. "
                + "Bonus: label each word with the sentence numbers in which each occurrence appeared.";
        
            concardance(file);   //Static Method
            print();        //prints result         
    }
    
    public static void concardance(String file){
        
       
        String[] tr = file.split(" ");     // Splits String from WhiteSpace, to put in Array
        
        for(int i=0; i<tr.length;i++){
            
            String str = tr[i];
            
            String checker = "check";         //prevents NullPointerException         
            
            if(i>0){
            checker = tr[i-1];                //prevents ArrayIndexException  
            }
         
            int length = str.length();             
                             
            Object obj;
            
            if(!Character.isLetter(str.charAt(length-1))){                   //Not a Letter
            obj = new Object(str.substring(0,length-1).toLowerCase());       //Eliminates delimiter's   
            }
            else{
            obj = new Object(str.toLowerCase());                                 
            }
         
            if(set.containsKey(str)){
                obj = set.get(str);
            }
            
            obj.setcount();
            obj.setsentencecount(sentencecount);
            set.put(str.toLowerCase(),obj);                           //Adding to TreeMap 
         
            //Checks Sentence break
            
            if(Character.isUpperCase(str.charAt(0)) && !Character.isLetter(checker.charAt((checker.length())-1))){     
                sentencecount++;                                                                          
            }                                                   
            
        }    
    
    }
    
    public static void print(){
    
        int num = 0;       //Will be passed in Ascii method
       
        Set<String> key = set.keySet();
        
        for(String value : key){                                  //Key Iteration
            Object obj = set.get(value);
            
            String sentence = obj.getsentencecount().toString();                             //Sentence count of each object
            
            sentence = sentence.substring(1, sentence.length()-1).replaceAll("\\s","");      //Elmiminates Character
            
            System.out.printf("%-5s %-20s %-20s", Ascii(num)+".", obj.getword(), "{" + obj.getcount() + ":" + sentence + "}");  //printing result
            System.out.println();     //line break
            
            num++;       
        }
    }
    
    public static String Ascii(int num){
              
        StringBuilder sb = new StringBuilder();                  //Joins the String and character     
           
            int remainder = num % 26;
            char letter = (char) (remainder + 97);               // Lowercase letter begins from 97 in ASCII format 
            
            for(int i=num/26; i>=0; i--) {
                    sb.append(letter);                           //Appends same letter
            }
            return sb.toString();
        }
     
    }


    class Object{                                  //Default Modifier
    
    private String word;                                
    private int count;
    private ArrayList<Integer> list;                         
    
    Object(){                               // Default Constructor
    }
    
    Object(String word){                    //Constructor  
    this.word = word;
    this.list = new ArrayList<>();
    }
    
    
    // Setter/Getter    
     
    void setcount(){         //increment by 1
     this.count++;   
    }
    
    int getcount(){
        return count;
    }
    
    void setsentencecount(int num){          //Sets sentence count
    this.list.add(num);
    }
    
    ArrayList<Integer> getsentencecount(){
    return list;
    }
    
    void setword(String word){
    this.word = word;
    }
    
    String getword(){
    return word;
    }
    
}
    

