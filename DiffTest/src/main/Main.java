package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;


public class Main {
	
	public static void main(String[] args) {
		Configuration configuration = Configuration.getConfiguration();
		
		try {
			Set<String> previousColumnsSet = getPreviousColumnsSet(configuration.getPreviousFilePath());
			Set<String> nextColumnsSet = getPreviousColumnsSet(configuration.getNextFilePath());
			
			Set<String> removedFromPreviousSet = getDifferenceBetweenSets(previousColumnsSet, nextColumnsSet);
			writeToFile(removedFromPreviousSet, configuration.getremovedFromPreviousPath());
			
			Set<String> intersection = getIntersectionOfSets(previousColumnsSet, nextColumnsSet);
			writeToFile(intersection, configuration.getIntersection());
			
			Set<String> addedElementsInNextSet = getDifferenceBetweenSets(nextColumnsSet, previousColumnsSet);
			writeToFile(addedElementsInNextSet, configuration.getAddedInNextPath());
						
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static Set<String> getDifferenceBetweenSets(Set<String> set1, Set<String> set2) {
		Set<String> setCopy = new LinkedHashSet<>(set1);
		setCopy.removeAll(set2);
		return setCopy;
	}
	
	private static Set<String> getIntersectionOfSets(Set<String> set1, Set<String> set2) {
		Set<String> setCopy = new LinkedHashSet<>(set1);
		setCopy.retainAll(set2);
		return setCopy;
	}
	
	private static void writeToFile(Set<String> set, String outFolderPath) throws IOException {
		 PrintWriter writer = new PrintWriter(outFolderPath, "UTF-8");
		 Iterator<String> itr = set.iterator();
	     while(itr.hasNext()){
			writer.println(itr.next());
	     }
	     writer.close();
	}

	private static Set<String> getPreviousColumnsSet(String filePath) throws IOException{
	    Set<String> previousColumns = new LinkedHashSet<>();
	    BufferedReader br = new BufferedReader(new FileReader(filePath));
	    String sCurrentLine;
		while ((sCurrentLine = br.readLine()) != null) {
			if (!sCurrentLine.isEmpty() && !sCurrentLine.startsWith("#")) {
				previousColumns.add(sCurrentLine.trim());
			}
		}
		br.close();
		return previousColumns;
	}
}
