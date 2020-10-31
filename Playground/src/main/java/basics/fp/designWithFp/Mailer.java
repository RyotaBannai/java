package basics.fp.designWithFp;

import java.util.function.Consumer;

public class Mailer {
    private Mailer() {
    }

    private Mailer from(final String address) {
        System.out.println("set up from address");
        return this;
    }

    private Mailer to(final String address) {
        System.out.println("set up to address");
        return this;
    }

    private Mailer subject(final String address) {
        System.out.println("set subject");
        return this;
    }

    private Mailer body(final String address) {
        System.out.println("set body");
        return this;
    }

    public static void send(final Consumer<Mailer> block) {
        /**
         * Mailer のインスタンスはこのメソッド内になるため、send メソッドが終了したら、参照は消える.
         * スコープを取得してスコープないで作業して、返す、パターンを"ローンパターン"という.
         * */
        final Mailer mailer = new Mailer();
        block.accept(mailer);
        System.out.println("sending...");
    }

    /**
     * コンストラクタを private にして直接オブジェクトを生成するのを禁止（new は流暢さや可読性が低くなるため）
     * オブジェクトの参照がブロック内になり、send メソッドの実行が終了すると参照が消える
     */
    public static void main(String[] args) {
        Mailer.send(mailer -> mailer
                .from("test_from@gmail.com")
                .to("test_to@gmail.com")
                .subject("subject")
                .body("body")
        );

    }
}