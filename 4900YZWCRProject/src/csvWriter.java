import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class csvWriter {


   // public String[][] files = new String[rows][columns];
    public ArrayList<String[]> files = new ArrayList<String[]>();
   

    //String[] items2 = {"Exchange", "https://fc6961f961d34f8792b1089.blob.core.windows.net/ingestiondata", PstFileName, "superuser@acit4900.onmicrosoft.com", "FALSE", "/"};
    public String getFileName(String filename) {
//        System.out.println(filename);
        return filename;
    }

    public void writeCsvFile() throws IOException {

        ArrayList<String[]> entries = new ArrayList<String[]>();
        String[] columnHead = {"Workload", "FilePath", "Name", "Mailbox", "IsArchive", "TargetRootFolder", "SPFileContainer", "SPManifestContainer", "SPSiteUrl"};

        entries.add(columnHead);
        
        for (String[] file : files) {
            entries.add(file);
        }
        
//        entries.add(items2);
        String fileName = "collection_of_pstfiles.csv";

        try (CSVWriter writer = new CSVWriter(new FileWriter(fileName))) {
            writer.writeAll(entries);
        }
    }
}
