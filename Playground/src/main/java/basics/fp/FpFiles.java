package basics.fp;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class FpFiles {
    public static void main(String[] args) {
        try {
            Files.list(Paths.get("."))
                    .filter(Files::isDirectory)
                    .forEach(System.out::println);

            // find only java file
            Files.newDirectoryStream(Paths.get("."), path -> path.toString().endsWith(".java"))
                    .forEach(System.out::println);

            // 従来の file instance の FilenameFilter の代わりに lambda を渡す
            final File[] files = new File(".").listFiles(file -> file.isHidden());
            // final File[] files = new File(".").listFiles(File:isHidden); // method reference

            // monadic composition
            List<File> monadicfiles =
                    Stream.of(new File(".").listFiles())
                            .flatMap(file -> file.listFiles() == null ? Stream.of(file) : Stream.of(file.listFiles()))
                            .collect(toList());
            System.out.println(monadicfiles);

        } catch (IOException ignored) {
        }
    }
}
