package basics.fp.exception;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.Stream;

public class ARM {
    static class FileWriterSample implements AutoCloseable { // static にしないと main で静的に呼び出せない...
        private FileWriter writer;

        public FileWriterSample(final String fileName) throws IOException {
            writer = new FileWriter(fileName);
        }

        public void writeStuff(final String message) throws IOException {
            writer.write(message);
        }

//        public void finalize() throws IOException { // GC が自動的に削除してくれるがメモリがいっぱいになるまで残される...
//            writer.close();
//        }

        public void close() throws IOException {
            System.out.println("close called automatically...");
            writer.close();
        }
    }

    public static void main(String[] args) throws IOException {
//        Stream.of("/", "temp")
//                .map(path -> new File(path).getCanonicalFile()) // unhundled exception
//                .forEach(System.out::println);

        // ARM の適用
        try (final FileWriterSample writeARM = new FileWriterSample("peekaboo.txt")) { // 自動的に close を呼ぶ
            writeARM.writeStuff("Automatic Resource Management");
            System.out.println("done with the resource...");
        }
    }
}
