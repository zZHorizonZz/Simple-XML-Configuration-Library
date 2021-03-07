package com.horizon;

import com.horizon.data.MemorySection;
import com.horizon.other.Configuration;

import java.io.File;
import java.nio.file.FileSystemNotFoundException;

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
    public void initialize() {
        File file = new File(this.pathName, this.fileName);
        if (!file.exists()) {
            create();
            load(true);
            memorySection.save(true);
        } else {
            create();
            load(false);
        }
    }

    @Override
    public void load(boolean newFile) {
        this.memorySection = new MemorySection();
        this.memorySection.initialize(this.configurationFile, newFile);
    }

    @Override
    public void create() {
        try {
            File xmlFile = new File(this.pathName, this.fileName);

            if (!xmlFile.exists()) {
                if (pathName != null && !pathName.equalsIgnoreCase(""))
                    xmlFile.getParentFile().mkdirs();

                if (xmlFile.createNewFile()) {
                    this.configurationFile = xmlFile;
                } else {
                    throw new FileSystemNotFoundException("Something went wrong with creation of xml file!");
                }
            } else {
                this.configurationFile = xmlFile;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void save(boolean reformat) {
        this.memorySection.save(reformat);
    }

    public String getPathName() {
        return pathName;
    }

    public String getFileName() {
        return fileName;
    }

    public File getConfigurationFile() {
        return configurationFile;
    }

    public MemorySection getConfig() {
        return memorySection;
    }
}
