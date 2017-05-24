import com.microsoft.azure.storage.*;
import com.microsoft.azure.storage.blob.*;
import java.io.*;
import java.util.*;
import java.nio.file.*;
import java.nio.file.attribute.*;
import com.opencsv.*;
import com.opencsv.bean.*;


public class Mail2Ccloud {

    /**
     * @param args the command line arguments
     */
    //azure Connection String 
    public static final String storageConnectionString
            = "DefaultEndpointsProtocol=https;"
            + "AccountName=**********"
            + "AccountKey=***************";

    public static void main(String[] args) {
        try {
			TransferTo365 transfer = new TransferTo365();
			transfer.delJournal();
			transfer.transferItScript();
			
            csvWriter cw = new csvWriter();
            int arrayIndex = 0;

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

            for (Path object : fileNames) {

                final String Exchange = "Exchange";
                final String FilePath = "https://fc6961f961d34f8792b1089.blob.core.windows.net/ingestiondata";
                final String Mailbox = "superuser@acit4900.onmicrosoft.com";
                final String IsArchive = "FALSE";
                final String TargetRootFolder = "/";
                
                cw.files[arrayIndex][0] = Exchange;
                cw.files[arrayIndex][1] = FilePath;
                cw.files[arrayIndex][2] = cw.getFileName(object.getFileName().toString());
                cw.files[arrayIndex][3] = Mailbox;
                cw.files[arrayIndex][4] = IsArchive;
                cw.files[arrayIndex][5] = TargetRootFolder;
                
                
                
                System.out.println("#" + arrayIndex + " file: " + object.toString());
                CloudBlockBlob blob = container.getBlockBlobReference(object.getFileName().toString());
                
                File sourceFile = new File(object.toString());
                
                blob.upload(new FileInputStream(sourceFile), sourceFile.length());
                arrayIndex++;
            }

            cw.writeCsvFile();

        } catch (FileNotFoundException fileNotFoundException) {
            System.out.print("FileNotFoundException encountered: ");
            System.out.println(fileNotFoundException.getMessage());
            System.exit(-1);
        } catch (StorageException storageException) {
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
