package basics.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 設定値を他のシステムから読み出す必要がある場合は XML 形式で保存しておくと、
 * XML のパーサーが利用できるので便利なことがあり
 */
public class MyPropertyXML {
    public static void main(String[] args) {
        try {
            readXML();
        } catch (IOException error) {
            error.printStackTrace();
        }
    }

    private static void writeXML() throws IOException {
        Properties settings = new Properties();
        settings.setProperty("country", "USA");
        settings.setProperty("language", "English");

        File outputDir = new File("src/main/java/basics/properties/");
        File outputFile = new File(outputDir, "myPropertiesXML");

        try (FileOutputStream outputStream = new FileOutputStream(outputFile)) {
            settings.storeToXML(outputStream, "Test Properties");
        }
    }


    private static void readXML() throws IOException {
        Properties settings = new Properties();

        File inputDir = new File("src/main/java/basics/properties/");
        File inputFile = new File(inputDir, "myPropertiesXML");

        try (FileInputStream inputStream = new FileInputStream(inputFile)) {
            settings.loadFromXML(inputStream);
        }
        System.out.println(settings.getProperty("country"));
        System.out.println(settings.getProperty("language"));
    }
}
