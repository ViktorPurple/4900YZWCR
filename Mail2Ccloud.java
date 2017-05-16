import java.io.*;
import java.util.*;
import java.nio.file.*;
import java.nio.file.attribute.*;

import com.microsoft.azure.storage.*;
import com.microsoft.azure.storage.blob.*;
//test comment for github
public class Mail2Ccloud {
	//azure Connection String 
	public static final String storageConnectionString =
	"DefaultEndpointsProtocol=https;"
	+ "AccountName=4900acit;"
	+ "AccountKey=OOUURGmvIirdpYkTA3P3miBph/PawGnAvIPb65C/pgBEBqSpYtHJpQAh1UEzL2v5mmpw/NKCtEz8W1+JNE/u/w==";

	public static void main(String[] args) {
		try {
			CloudStorageAccount account = CloudStorageAccount.parse(storageConnectionString);
			CloudBlobClient serviceClient = account.createCloudBlobClient();

            // Container name must be lower case.
			CloudBlobContainer container = serviceClient.getContainerReference("danisblop");
			container.createIfNotExists();

			ArrayList<Path> fileNames = new ArrayList<Path>();
			
			Find fs = new Find();
			fs.Filesearching();
			
            // Upload an image file.
			//make loop here 
			
			fileNames = fs.getNames();
			
			for (Path object: fileNames) {
				System.out.println("file: "  + object.toString());
				CloudBlockBlob blob = container.getBlockBlobReference(object.getFileName().toString());
				File sourceFile = new File(object.toString());
				blob.upload(new FileInputStream(sourceFile), sourceFile.length());				
			}
			
		}
		catch (FileNotFoundException fileNotFoundException) {
			System.out.print("FileNotFoundException encountered: ");
			System.out.println(fileNotFoundException.getMessage());
			System.exit(-1);
		}
		catch (StorageException storageException) {
			System.out.print("StorageException encountered: ");
			System.out.println(storageException.getMessage());
			System.exit(-1);
		}
		catch (Exception e) {
			System.out.print("Exception encountered: ");
			System.out.println(e.getMessage());
			System.exit(-1);
		}
	}
}
