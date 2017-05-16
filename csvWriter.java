package mail2cloud;

import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class csvWriter {

    int rows = 10;
    int columns = 10;

    public String[][] files = new String[rows][columns];

    //String[] items2 = {"Exchange", "https://fc6961f961d34f8792b1089.blob.core.windows.net/ingestiondata", PstFileName, "superuser@acit4900.onmicrosoft.com", "FALSE", "/"};
    public String getFileName(String filename) {
//        System.out.println(filename);
        return filename;
    }

    public void writeCsvFile() throws IOException {

        List<String[]> entries = new ArrayList<>();
        String[] columnHead = {"Workload", "FilePath", "Name", "Mailbox", "IsArchive", "TargetRootFolder", "SPFileContainer", "SPManifestContainer", "SPSiteUrl"};

        entries.add(columnHead);
        entries.add(files[0]);
        entries.add(files[1]);
        entries.add(files[2]);

//        entries.add(items2);
        String fileName = "collection_of_pstfiles.csv";

        try (CSVWriter writer = new CSVWriter(new FileWriter(fileName))) {
            writer.writeAll(entries);
        }
    }
}
