package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    //Declaring the properties object
    private static Properties properties;

    //Static block to load the properties file when the class is initialized
    static {
        try (InputStream inputStream = ConfigReader.class.getClassLoader()
                .getResourceAsStream("config/config.properties")) {
            properties = new Properties();

            //Check if the properties file was found
            if (inputStream == null) {
                throw new RuntimeException("config.properties file not found in classpath");
            }
            //Load the properties from the input stream
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not load config.properties file.");
        }
    }

    //Method to read a property by key
    public static String read(String key) {
        return properties.getProperty(key);
    }

    //Overloaded method to read a property from a specific path(if needed)
    public static String read(String key, String path) {
        Properties properties = new Properties();
        try (InputStream fis = ConfigReader.class.getClassLoader().getResourceAsStream(path)) {
            if (fis == null) {
                throw new RuntimeException(path + "file not found in classpath");
            }
            properties.load(fis);

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return properties.getProperty(key);
    }
}
