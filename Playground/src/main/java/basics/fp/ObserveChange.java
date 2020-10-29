package basics.fp;

import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.TimeUnit;

public class ObserveChange {
    public static void main(String[] args) {
        final Path path = Paths.get(".");
        try {
            final WatchService watchService =
                    path.getFileSystem().newWatchService();
            path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);
            System.out.println("Report any file changed within next 30 seconds...");

            final WatchKey watchKey = watchService.poll(30, TimeUnit.SECONDS);

            if (watchKey != null) {
                watchKey.pollEvents() // 複数のファイルが変更される可能性があり、コレクションが返される場合もある
                        .stream()
                        .forEach(event -> System.out.println(event.context()));
            }
        } catch (IOException | InterruptedException ignored) {
        }
    }
}
