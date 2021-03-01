package com.horizon;

import com.horizon.data.MemorySection;
import com.horizon.other.Configuration;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class XMLConfiguration implements Configuration {

    private String pathName;
    private String fileName;

    private File configurationFile;
    private MemorySection memorySection;

    public XMLConfiguration(String pathName, String fileName) {
        this.pathName = pathName;
        this.fileName = fileName;
    }

    public XMLConfiguration(File file) {
        this.configurationFile = file;
    }

    @Override
    public void loadConfiguration() {
        this.memorySection = new MemorySection();
        this.memorySection.initialize(this.configurationFile);
    }

    @Override
    public void createConfiguration() {
        try {
            File xmlFile = new File(this.pathName, this.fileName);

            if (!xmlFile.exists()) {
                if(pathName != null && !pathName.equalsIgnoreCase(""))
                    xmlFile.getParentFile().mkdirs();

                if (xmlFile.createNewFile()) {
                    copyDefault(xmlFile);

                    System.out.println("XML File successfully created! Parameters: Path -> "
                            + xmlFile.getAbsolutePath() + " Can Read -> "
                            + xmlFile.canRead() + " Can Write -> "
                            + xmlFile.canWrite());
                } else {
                    throw new Exception("Something went wrong with creation of xml file!");
                }
            } else {
                this.configurationFile = xmlFile;
                System.out.println("This file already exist... " + xmlFile.getAbsolutePath());
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void parseXML() {

    }

    public boolean copyDefault(File file) {
        FileWriter writer = null;

        try {
            writer = new FileWriter(file);
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
