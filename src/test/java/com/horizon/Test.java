package com.horizon;

public class Test {

    private static String filePath = null; //#INSERT FILE PATH#"
    private static String fileName = "data.xml";

    public static void main(String[] args) {
        XMLConfiguration configuration = new XMLConfiguration(filePath, fileName); //Firstly we need to create XMLConfiguration
        configuration.initialize(); // If xml configuration is prepared we can initialize it.

        configuration.getConfig().setValue("config.data", "test"); //This is used for setting the value.
        configuration.getConfig().setString("config.version", "1.0.1");
        configuration.getConfig().setInteger("config.identifier", 150546);
        configuration.getConfig().setDouble("config.double", 2.0);
        configuration.getConfig().setFloat("config.float", 2.0f);
        configuration.getConfig().setLong("config.float", 2000L);

        System.out.println("Data -> " + configuration.getConfig().getString("config.data")); //With this we can print value at path is data on that path exists.
        System.out.println("Version -> " + configuration.getConfig().getString("config.version"));
        System.out.println("Identifier -> " + configuration.getConfig().getInteger("config.identifier"));

        configuration.save(true); //This is used for saving the configuration. If we want to reformat saved xml file we need to save argument to true.
        //But there is bug that will add blank lines to the data that are reformatted.
    }
}
