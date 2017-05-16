import java.io.*;




import com.profesorfalken.jpowershell.*;

//test comment for github
public class testf {

	public static void main(String[] args) {

	//Execute a command in PowerShell session
   PowerShellResponse response = PowerShell.executeSingleCommand("Get-Process");
   
   //Print results
   System.out.println("List Processes:" + response.getCommandOutput());
   }
 }