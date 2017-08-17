package main;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuration {
	
	private static Configuration instance;
	private String previousFilePath;
	private String nextFilePath;
	private String outFolderPath;
	
	private Configuration() {
		Properties config = new Properties();
        try {
        	config.load(new FileInputStream("config/Configuration.properties"));
        	setPreviousFilePath(config.getProperty("previous"));
        	setNextFilePath(config.getProperty("next"));
        	setOutFolderPath(config.getProperty("out"));
        } catch (IOException ex) {
            System.out.println("Error"+ ex.getMessage());
        }
	}

	public static Configuration getConfiguration() {
        if (instance == null) {
        	instance = new Configuration();
        }
        return instance;
    }

	public String getPreviousFilePath() {
		return previousFilePath;
	}

	private void setPreviousFilePath(String previousFilePath) {
		this.previousFilePath = previousFilePath;
	}

	public String getNextFilePath() {
		return nextFilePath;
	}

	private void setNextFilePath(String nextFilePath) {
		this.nextFilePath = nextFilePath;
	}

	public String getOutFolderPath() {
		return outFolderPath;
	}

	private void setOutFolderPath(String outFolderPath) {
		this.outFolderPath = outFolderPath;
	}
}
