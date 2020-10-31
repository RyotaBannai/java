import static junit.framework.TestCase.fail;

public class TestHelper {
    public static <X extends Throwable> Throwable assertThrows(
            final Class<X> exceptionClass, // 例外クラス
            final UseInstance<Throwable> block) { // 実行させるコードブロック
        try {
            block.run();
        } catch (Throwable ex) {
            if (exceptionClass.isInstance(ex))
                return ex;
        }
        fail("Failed to throw expected exception ");
        // 例外が投げられない場合や、一つ目の引数で指定された型以外の例外が投げられた場合 test fail
        return null;
    }

    @FunctionalInterface // 関数型インターフェース：ラムダ式やメソッド参照から Java コンパイラが自動的に合成するための候補。
    public interface UseInstance<X extends Throwable> {
        void run() throws X;
    }
}
