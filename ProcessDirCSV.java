import java.io.File;
import java.io.IOException;
import java.util.Scanner;
public class ProcessDirCSV {    public static void main(String args[]) throws IOException {
      //Creating a File object for directory
      File directoryPath = new File("/workspace/java/fax");
      //List of all files and directories
      String contents[] = directoryPath.list();
      //System.out.println("List of files and directories in the specified directory:");
      for(int i=0; i<contents.length; i++) {
        // System.out.println(contents[i]);
         String filename = contents[i];
         if (filename.endsWith(".csv")) {
            //System.out.println("FILE = "+ filename);
            Scanner sc = new Scanner(new File("/workspace/java/fax/" + filename));    
            sc.useDelimiter(",");   //sets the delimiter pattern 
            int firstOccurence=0;
            String scat=filename;
            while (sc.hasNext())  //returns a boolean value  
                    {  
                      String s = sc.next();
                      
                      if (s.startsWith("People -") || s.startsWith("Process -" ) || s.startsWith("Tech -" ) ){
                          firstOccurence++;
                         if (firstOccurence <= 21) { 
                             scat = scat +"," + s;
                            // System.out.println("FILENAME: " + s);  //find and returns the next complete token from this scanner  
                          }
                       }
                       if (firstOccurence >= 21 && firstOccurence < 26){
                           firstOccurence++;
                           if (firstOccurence >= 22 && firstOccurence< 26){
                               scat = scat +"," + s;
                           }
                       }
                        }
                    System.out.println("FINAL: " + scat);    
                    sc.close();  
         }
         
      }
   }
}