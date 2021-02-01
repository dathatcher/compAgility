import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Vector;
public class ProcessDirCSV {    public static void main(String args[]) throws IOException {
      //Creating a File object for directory
      File directoryPath = new File("/workspace/java/fax");
      //List of all files and directories
      String contents[] = directoryPath.list();
      //System.out.println("List of files and directories in the specified directory:");

      Vector system = new Vector();
      Vector category = new Vector();
      Vector score = new Vector();
      int sysCntr = 0;
      int catCntr = 0;
      int scoreCntr = 0;
      
      for(int i=0; i<contents.length; i++) {
        // System.out.println(contents[i]);
         String filename = contents[i];
         if (filename.endsWith(".csv")) {
            //System.out.println("FILE = "+ filename);
            Scanner sc = new Scanner(new File("/workspace/java/fax/" + filename));    
            sc.useDelimiter(",");   //sets the delimiter pattern 
            int firstOccurence=0;
            String scat=filename;
            
            system.add(sysCntr, filename);
            sysCntr++;

            while (sc.hasNext())  //returns a boolean value  
                    {  
                      String s = sc.next();
                      
                      if (s.startsWith("People -") || s.startsWith("Process -" ) || s.startsWith("Tech -" ) ){
                          firstOccurence++;
                         if (firstOccurence <= 21) { 
                             scat = scat +"," + s+"("+firstOccurence+") ";
                             category.add(catCntr, s); 
                             catCntr++;                                                        
                            // System.out.println("FILENAME: " + s);  //find and returns the next complete token from this scanner  
                          }
                       }
                       if (firstOccurence >= 21 && firstOccurence < 45){
                           firstOccurence++;
                           if (firstOccurence > 23 && firstOccurence< 45){
                               scat = scat +"," + s+"("+firstOccurence+") ";
                               score.add(scoreCntr, s);
                               scoreCntr++;                               
                           }
                       }
                        }
                    System.out.println("system: " + system.size());
                    System.out.println("category: " + category.size());
                    System.out.println("score: " + score.size()); 
                    Iterator isystem = system.iterator();  
                    System.out.println("The iterator values are: "); 
                    while (isystem.hasNext()) { 
                        System.out.println(isystem.next()); 
                    } 
                    Iterator icategory = category.iterator();  
                    System.out.println("The iterator values are: "); 
                    while (icategory.hasNext()) { 
                        System.out.println(icategory.next()); 
                    } 
                    Iterator iscore = score.iterator();  
                    System.out.println("The iterator values are: "); 
                    while (iscore.hasNext()) { 
                        System.out.println(iscore.next()); 
                    } 
                    system.clear();
                    category.clear();
                    score.clear();
                    sysCntr = 0;
                    catCntr = 0;
                    scoreCntr = 0;
                    sc.close();  
         }
          
      }
   }
}