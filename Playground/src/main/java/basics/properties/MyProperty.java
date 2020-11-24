package basics.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;


/**
 * システムプロパティ値を起動オプションを使って渡す -D<キー名>="値"
 * java -jar -Dxyz="foo" TestApp3.jar
 */

public class MyProperty {
    public static void main(String[] argas) {
        Properties properties = System.getProperties();
        System.out.println(properties.getProperty("user.home"));
        properties.list(System.out);

//        try {
//            read();
//        } catch (IOException error) {
//            error.printStackTrace();
//        }
    }

    /**
     * Properties は java.util.Hashtable から派生して、キー・バリューペアを実現
     * プロパティ値は String 型のみ
     * //
     * foo,bar,baz: メタ構文変数 (metasyntactic variable)  (RFC 3092)
     */

    private static void write() throws IOException {
        Properties settings = new Properties();
        settings.setProperty("country", "USA");
        settings.setProperty("language", "English");

        File outputDir = new File("src/main/java/basics/properties/");
        File outputFile = new File(outputDir, "myProperties");

        try (FileOutputStream outputStream = new FileOutputStream(outputFile)) {
            settings.store(outputStream, "Test Properties");
        }
    }

    private static void read() throws IOException {
        Properties settings = new Properties();

        File inputDir = new File("src/main/java/basics/properties/");
        File inputFile = new File(inputDir, "myProperties");

        try (FileInputStream inputStream = new FileInputStream(inputFile)) {
            settings.load(inputStream);
        }
        System.out.println(settings.getProperty("country"));
        System.out.println(settings.getProperty("language"));
    }
}
