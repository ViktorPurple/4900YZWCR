import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.*;
import static java.nio.file.FileVisitResult.*;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Find {

	private static int matchTotal = 0;
	private ArrayList<Path> fileNames = new ArrayList<Path>();
        //private Path distFolder = FileSystems.getDefault().getPath("D:/pst_temp/");

	public class Finder
			extends SimpleFileVisitor<Path> {

		private final PathMatcher match;
		private int numMatches = 0;

		Finder(String pattern) {
			match = FileSystems.getDefault()
					.getPathMatcher("glob:" + pattern);
		}

		void find(Path file) {
			Path name = file.getFileName();    
                        new File("pst_temp/").mkdir();
                        
			if (name != null && match.matches(name)) {
				numMatches++;
				System.out.println(file);
				fileNames.add(file);
                                try {
                                String filenamedist = "pst_temp/"  + file.getFileName();
                                Path distFile = FileSystems.getDefault().getPath(filenamedist);
                                Files.copy(file, distFile, REPLACE_EXISTING );    
                                
                                }catch (IOException ex) {
                                Logger.getLogger(Find.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
		}

		void done() {
			System.out.println("Matched: "
					+ numMatches);
			matchTotal = matchTotal + numMatches;
		}

		@Override
		public FileVisitResult visitFile(Path file,
				BasicFileAttributes attrs) {
			find(file);
			return CONTINUE;
		}

		@Override
		public FileVisitResult preVisitDirectory(Path dir,
				BasicFileAttributes attrs) {
			find(dir);
			return CONTINUE;
		}

		@Override
		public FileVisitResult visitFileFailed(Path file,
				IOException exc) {
			return CONTINUE;
		}
	}



	public void Filesearching()
			throws IOException {

		File[] paths;

		paths = File.listRoots();

		for (File path : paths) {
			String str = path.toString();
			String slash = "\\";

			String s = new StringBuilder(str).append(slash).toString();

			Path startingDir = Paths.get(s);

			String pattern = "*.pst";
			

			Finder finder = new Finder(pattern);
			Files.walkFileTree(startingDir, finder);
			finder.done();

		}
		System.out.println("Total Matched Number of Files : " + matchTotal);
		}
			
	public ArrayList<Path> getNames(){
		return fileNames;
	}
}

