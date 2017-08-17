package main;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuration {
	
	private static Configuration instance;
	private String previousFilePath;
	private String nextFilePath;
	private String intersection;
	private String removedFromPreviousPath;
	private String addedInNextPath;
	
	private Configuration() {
		Properties config = new Properties();
        try {
        	config.load(new FileInputStream("config/Configuration.properties"));
        	setPreviousFilePath(config.getProperty("previous"));
        	setNextFilePath(config.getProperty("next"));
        	setIntersection(config.getProperty("intersection"));
        	setRemovedFromPreviousPath(config.getProperty("removedFromPrevious"));
        	setAddedInNextPath(config.getProperty("addedInNext"));
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

	public String getIntersection() {
		return intersection;
	}

	public void setIntersection(String intersection) {
		this.intersection = intersection;
	}

	public String getAddedInNextPath() {
		return addedInNextPath;
	}

	public void setAddedInNextPath(String addedInNextPath) {
		this.addedInNextPath = addedInNextPath;
	}

	public String getremovedFromPreviousPath() {
		return removedFromPreviousPath;
	}

	public void setRemovedFromPreviousPath(String removedFromPreviousPath) {
		this.removedFromPreviousPath = removedFromPreviousPath;
	}
}
