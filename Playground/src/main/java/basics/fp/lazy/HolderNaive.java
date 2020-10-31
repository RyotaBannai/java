package basics.fp.lazy;

import java.util.function.Supplier;

public class HolderNaive {
    public HolderNaive() {
        System.out.println("Holder created!");
    }

    //    private Heavy heavy;
//
//    public synchronized Heavy getHeavy() { // スレッドセーフ　smell pattern
//        if (heavy == null) {
//            heavy = new Heavy();
//        }
//        return heavy;
//    }

    public Heavy getHeavy() {
        return heavy.get();
    }

    Supplier<Heavy> heavy = () -> createAndCachedHeavy();

    private synchronized Heavy createAndCachedHeavy() {
        class HeavyFactory implements Supplier<Heavy> {
            private final Heavy heavyInstance = new Heavy();

            public Heavy get() {
                return heavyInstance;
            }
        }
        if (!HeavyFactory.class.isInstance(heavy)) {
            heavy = new HeavyFactory();
        }
        return heavy.get();
    }


    public static void main(String[] args) {
        final HolderNaive holder = new HolderNaive();
        System.out.println("deferring heavy creation....");
        System.out.println(holder.getHeavy());
        System.out.println(holder.getHeavy());
    }
}
