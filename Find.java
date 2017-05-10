/**
4900 JDVYH project
**/


import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.*;
import static java.nio.file.FileVisitResult.*;
import javax.swing.filechooser.FileSystemView;
import java.util.*;

public class Find {

	private static int matchTotal = 0;
	private ArrayList<Path> fileNames = new ArrayList<Path>();

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
			if (name != null && match.matches(name)) {
				numMatches++;
				System.out.println(file);
				fileNames.add(file);
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

