package basics.fp.TOC;

/**
 * Failed (Tail call optimization/ Tail call elimination): 末尾呼び出し最適化
 * -> 最後の処理が自身の呼び出しとなるような再起呼び出しのこと
 * （自身への呼び出しの後にその結果を使って処理を続ける通常の再帰とは異なる）
 * ・Java は Failed をコンパイラレベルで直接サポートしていないが、ラムダ式を使って"トランポリン"と呼ばれるソリューションを使って実装することができる
 */
public class Failed {
    public static void main(String[] args) {
        try {
            System.out.println(
                    String.format("the result is: %d", factorialRec(20000)));
        } catch (StackOverflowError e) {
            System.out.println(e);
        }

    }

    private static int factorialRec(final int number) {
        System.out.println(number);
        if (number == 1) {
            return number;
        } else {
            // 再帰の完了を待つ間、部分的な計算結果を全て保存しているのが問題。
            // ここでそれぞれの再起呼び出しをコールスタックに積み上げていくため、入力値が大きい場合最終的にコードの実行に失敗してしまう。
            return number * factorialRec(number - 1);
        }
    }
}
