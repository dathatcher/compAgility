import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.io.IOException;  
import java.io.*;  
import java.util.Scanner;  

public class MonitorDirectory {

	public static void main(String[] args) throws IOException,
			InterruptedException {

		Path csvFolder = Paths.get("/workspace/java/csv");
		WatchService watchService = FileSystems.getDefault().newWatchService();
		csvFolder.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
        try {
            File myObj = new File("/workspace/java/csv/filename.txt");
            if (myObj.createNewFile()) {
                 System.out.println("File created: " + myObj.getName());
            } else {
                 System.out.println("File already exists.");
            }
            
         } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
         }

        boolean valid = true;
        FileWriter myWriter = new FileWriter("/workspace/java/csv/filename.txt");
        
		do {
			WatchKey watchKey = watchService.take();

			for (WatchEvent event : watchKey.pollEvents()) {
				WatchEvent.Kind kind = event.kind();
				if (StandardWatchEventKinds.ENTRY_CREATE.equals(event.kind())) {
                    
                    String fileName = event.context().toString();
                    System.out.println("File Created:" + fileName);
                    Scanner sc = new Scanner(new File("/workspace/java/csv/" + fileName));    
                    sc.useDelimiter(",");   //sets the delimiter pattern 
                    
                    while (sc.hasNext())  //returns a boolean value  
                    {  
                      System.out.print(sc.next());  //find and returns the next complete token from this scanner  
                      myWriter.write(sc.toString());
                    }  

                myWriter.close();     
                sc.close();
                    
				}
			}
			valid = watchKey.reset();

		} while (valid);

	}
}