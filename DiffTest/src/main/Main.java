package main;

import java.io.BufferedReader;
import java.io.File;
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
			
			previousColumnsSet.retainAll(nextColumnsSet);
			
			writeToFile(previousColumnsSet, configuration.getOutFolderPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
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
			System.out.println(sCurrentLine);
			if (!sCurrentLine.isEmpty() && !sCurrentLine.startsWith("#")) {
				previousColumns.add(sCurrentLine.trim());
			}
		}
		br.close();
		return previousColumns;
	}
}
