import java.io.*;
import java.util.*;
import java.io.File;
import java.io.IOException;

import com.profesorfalken.jpowershell.*;


//test comment for github
public class TransferTo365 {
	
	void TransferTo365(){}
	

	
  public void transferItScript(){
	  
	  
			PowerShellResponse response = null;
			PowerShell powerShell = null;
			try {
				   //Creates PowerShell session
				   powerShell = PowerShell.openSession();
				   //Increase timeout to give enough time to the script to finish
				   Map<String, String> config = new HashMap<String, String>();
				   config.put("maxWait", "80000");
				   
				   //Execute script
				   response = powerShell.configuration(config).executeScript("./1.ps1");
				   
				   //Print results if the script
				   System.out.println("Script output:" + response.getCommandOutput());
			} catch(PowerShellNotAvailableException ex) {
				   //Handle error when PowerShell is not available in the system
				   //Maybe try in another way?
			} finally {
				   //Always close PowerShell session to free resources.
				   if (powerShell != null)
					powerShell.close();
   }

  }
   
   public void delJournal () { 
		File directory = new File("AzCopy.jln");
		//File directory = new File(SRC_FOLDER);

    	//make sure directory exists
    	if(!directory.exists()){

           System.out.println("Directory does not exist.");


        }else{

           try{

               delete(directory);

           }catch(IOException e){
               e.printStackTrace();

           }
        }

    	//System.out.println("Done");
    }

    public static void delete(File file)throws IOException{

   
			try {
				if(file.isDirectory()){

					//directory is empty, then delete it
					if(file.list().length==0){

					   file.delete();
					   System.out.println("Directory is deleted : "
														 + file.getAbsolutePath());

					}else{

					   //list all the directory contents
					   String files[] = file.list();

					   for (String temp : files) {
						  //construct the file structure
						  File fileDelete = new File(file, temp);

						  //recursive delete
						 delete(fileDelete);
					   }

					   //check the directory again, if empty then delete it
					   if(file.list().length==0){
						 file.delete();
						 System.out.println("Directory is deleted : "
														  + file.getAbsolutePath());
					   }
					}
				}else{
					//if file, then delete it
					file.delete();
					System.out.println("File is deleted : " + file.getAbsolutePath());
					}
			} catch (FileNotFoundException fileNotFoundException) {
				System.exit(-1);
			}

			catch (Exception e) {System.exit(-1);}
			} 
    }

		   
 