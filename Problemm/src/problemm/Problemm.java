/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problemm;

/* Applied StringTokenizer to Split the String but it restricted me to extract 
previous element, so reverted to String SPLIT.
   Used TreeSet because it's sorted but Object cannot be compared in a set, so 
moved to TreeMap to Key value pair
   Created Object Class to track count of each word and used ArrayList good 
enough to track sentence count.
   Summary: Splitted the String, passed each word to object class, added String 
and Object as Key Value pair in TreeMap.   
   Applied Conditions and String Manipulation logic for sentence break and 
managing delimiters. Please go through comments for code functionality.
Input and output is applied to make test cases
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
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
    
    public static void main(String[] args) throws IOException {
       
        //Please change the file name Accordingly from 1 to 5 Text files, to test Output
        File fl = new File("TestInput_4.txt");
        FileReader fr= new FileReader(fl);
        BufferedReader br = new BufferedReader(fr);
            try{
                String file = br.readLine();
                //Static Method
                concardance(file);   
            }
            catch(Exception e){
                System.out.println(e);
            }finally{
                br.close();
            }
        
        
        File fle = new File("TestOutput_4.txt");
        FileWriter fw= new FileWriter(fle);
        BufferedWriter bw = new BufferedWriter(fw); 
            try{
                ArrayList<String> list3 = print();
                    for(String st: list3){
                        bw.write(st);                        
                    }
            }
            catch(Exception e){
                System.out.println(e);
            }finally{
                bw.flush();
                bw.close();            
            }                        
    }

    
    public static void concardance(String file){
     
       // Splits String from WhiteSpace, to put in Array
        String[] tr = file.split(" ");     
        
        for(int i=0; i<tr.length;i++){
            
            String str = tr[i];
            
            //prevents NullPointerException
            String checker = "check";                  
            
            //prevents ArrayIndexException
            if(i>0){
            checker = tr[i-1];                 
            }
         
            int length = str.length();             
                             
            Object obj;
            
             //Not a Letter
            if(str.length()>0 && !Character.isLetter(str.charAt(length-1))){ 
                
            //Eliminates delimiter's
            obj = new Object(str.substring(0,length-1).toLowerCase());          
            }
            else{
            obj = new Object(str.toLowerCase());                                 
            }
         
            if(set.containsKey(str)){
                obj = set.get(str);
            }
            
            obj.setcount();
            obj.setsentencecount(sentencecount);
            //Adding to TreeMap
            set.put(str.toLowerCase(),obj);                            
         
            //Checks Sentence break            
            if(Character.isUpperCase(str.charAt(0)) && 
                    !Character.isLetter(checker.charAt((checker.length())-1))){     
                sentencecount++;                                                                          
            }                                                   
            
        }    
    
    }
    
    public static ArrayList<String> print(){
        
        //Will be passed in Ascii method 
        int num = 0;       
       
        Set<String> key = set.keySet();
        
        //ArrayList to save each String
        ArrayList<String> list2 = new ArrayList<>();
        
        //Key Iteration
        for(String value : key){                                  
            Object obj = set.get(value);
            
            //Sentence count of each object
            String sentence = obj.getsentencecount().toString();                             
            
            //Elmiminates Character
            sentence = sentence.substring(1, sentence.length()-1).replaceAll("\\s","");      
            Formatter formatter = new Formatter();                                           
                String st= formatter.format("%s %8s %12s", Ascii(num)+".", 
                        obj.getword(), "{" + obj.getcount() + ":" 
                                + sentence + "}\r\n").toString();
                
                //Add each String to ArrayList
                list2.add(st);
            num++;
            
        }
        
        //returns ArrayList
        return list2;
    }
    
    public static String Ascii(int num){
        
        //Joins the String and character
        StringBuilder sb = new StringBuilder();                       
           
            int remainder = num % 26;
            
            // Lowercase letter begins from 97 in ASCII format
            char letter = (char) (remainder + 97);                
            
            for(int i=num/26; i>=0; i--) {
                //Appends same letter
                    sb.append(letter);                           
            }
            return sb.toString(); 
        }
     
    }

    //Default Modifier
    class Object{                                  
    
    private String word;                                
    private int count;
    private ArrayList<Integer> list;                         
    
    // Default Constructor
    Object(){                               
    }
    
    //Constructor
    Object(String word){                      
    this.word = word;
    this.list = new ArrayList<>();
    }
    
    
    // Setter/Getter    
    
    
    //increment by 1
    void setcount(){         
     this.count++;   
    }
    
    int getcount(){
        return count;
    }
    
    //Sets sentence count
    void setsentencecount(int num){          
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
    

