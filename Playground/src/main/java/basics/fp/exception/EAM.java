package basics.fp.exception;

import java.io.FileWriter;
import java.io.IOException;

public class EAM {
    static class FileWriterSample {
        private FileWriter writer;

        private FileWriterSample(final String fileName) throws IOException {
            writer = new FileWriter(fileName);
        }

        private void close() throws IOException {
            System.out.println("close called automatically...");
            writer.close();
        }

        public void writeStuff(final String message) throws IOException {
//            throw new IOException(); // test purpose.
            writer.write(message);
        }

        // execute around method
        public static void use(
                final String fileName,
                final UseInstance<FileWriterSample, IOException> block
        ) throws IOException {

            final FileWriterSample writer = new FileWriterSample(fileName);
            try {
                block.accept(writer);
            } finally {
                writer.close();
            }
        }

        @FunctionalInterface // 関数型インターフェース：ラムダ式やメソッド参照から Java コンパイラが自動的に合成するための候補。
        public interface UseInstance<T, X extends Throwable> {
            void accept(T instance) throws X;
        }
    }

    /**
     * インスタンスや close をカプセル化したので、不用意に close を遅延できないようにしている
     * リソース管理を開発者に負わせるのではなく、コードの挙動をより一貫性のある物にして開発者の負担を減らすことができる
     */
    public static void main(String[] args) throws IOException {
        FileWriterSample.use("eat.txt", writerEAM -> writerEAM.writeStuff("sweet!"));
    }
}