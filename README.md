# Simple XML Configuration
 Basic xml config management library. It can simply save value with a path in the format 'data.info'.

## Download

## Examples

Creation of the XMLConfiguration
```java
    XMLConfiguration configuration = new XMLConfiguration(filePath, fileName); //Firstly we need to create XMLConfiguration
    configuration.initialize(); // If xml configuration is prepared we can initialize it.
```

Saving of the data to the configuration.
```java
    configuration.getConfig().setValue("config.data", "test"); //This is used for setting the value.
    configuration.getConfig().setString("config.version", "1.0.1"); //First parameter is the is the path to the value
    configuration.getConfig().setInteger("config.identifier", 150546); //And the second parameter is the value itself.
```

Getting data from the configuration.
```java
    //With this we can print value at path is data on that path exists.
    System.out.println("Data -> " + configuration.getConfig().getString("config.data"));
    System.out.println("Version -> " + configuration.getConfig().getString("config.version"));
    System.out.println("Identifier -> " + configuration.getConfig().getInteger("config.identifier"));
```

Full example class can be found in [Test.java](https://github.com/zMamutCZz/Simple-XML-Library/blob/main/src/test/java/com/horizon/Test.java)
## Contribution

A project by [Daniel (Horizon)](https://github.com/zMamutCZz) with help of [StackOverflow](https://stackoverflow.com/)
If you'd like to contribute, feel free to fork and make changes, then open a pull request to master branch.

### License
Project is under [Apache License 2.0](https://github.com/zMamutCZz/Simple-XML-Library/blob/main/LICENSE)