package com.horizon;

public class Test {

    public static void main(String[] args) {
        XMLConfiguration configuration = new XMLConfiguration("C:\\Users\\danfi\\OneDrive\\Documents\\PersonalProjects\\Builds", "jonkers.xml");
        configuration.createConfiguration();
        configuration.loadConfiguration();
    }
}
