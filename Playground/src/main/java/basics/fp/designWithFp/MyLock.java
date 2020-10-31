package basics.fp.designWithFp;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyLock {
    static class Locking {
        Lock lock = new ReentrantLock(); // またはモックアップ

        protected void setLock(final Lock mock) {
            lock = mock;
        }

        private static void runLocked(Lock lock, Runnable block) {
            lock.lock();
            // execute around method
            try {
                block.run();
            } finally {
                lock.unlock();
            }
        }

        public void doOp1() {
            runLocked(lock, () -> {
            });
        }
    }


    public static void main(String[] args) {

    }
}
