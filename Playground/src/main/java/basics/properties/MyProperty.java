package basics.properties;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class MyProperty {
    public static void main(String[] args) {
        try {
            test();
        } catch (IOException error) {
            error.printStackTrace();
        }
    }

    private static void test() throws IOException {
        Properties settings = new Properties();
        settings.setProperty("country", "USA");
        settings.setProperty("language", "English");

        try (FileOutputStream outputStream = new FileOutputStream("basics.properties.myProperties")) {
            settings.store(outputStream, "Test Properties");
        }
    }
}
