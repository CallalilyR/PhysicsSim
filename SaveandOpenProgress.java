import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SaveandOpenProgress{
	
	protected File recentEntry;
	
	public SaveandOpenProgress() {
		
		recentEntry = new File(".txt");
		
	}
	

//	public void handleLoadFile() {
//		
//		  try {
//	            FileReader fr = new FileReader(groundFile);
//	            BufferedReader br = new BufferedReader(fr);
//	            
//	            String ground;
//	            
//	            int i = 0;
//	        
//	            System.out.println("Consider your plateau loaded");
//	            while((ground = br.readLine())!=null) {
//	                plat[i] = Double.parseDouble(ground);
//	                System.out.println(plat[i]);
//	                i++;
//	            }
//	                
//	            
//	            br.close();
//	            
//	        }catch(IOException e) {
//	            e.printStackTrace();
//	        }
//	}
//	
//	public void handleSaveFile() {
//		
//		double intialVelo
//		
//		
//		 try {
//	            FileWriter fw = new FileWriter(recentEntry);
//	            BufferedWriter bw = new BufferedWriter(fw);
//	            
//	            double ground;
//	            
//	            System.out.println("Saved plateau");
//	            for (int i=0;i<20;i++) {
//	                ground = plat[i];
//	                bw.write(String.valueOf(ground));
//	                bw.newLine();
//	                System.out.println(ground);
//	            }
//	            
//	            bw.close();
//	            
//	        }catch(IOException e) {
//	            e.printStackTrace();
//	        }    
//	}
//	
//	
	
	
	
	
}