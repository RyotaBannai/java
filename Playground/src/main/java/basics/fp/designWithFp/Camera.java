package basics.fp.designWithFp;

import java.awt.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * design goal: １つ以上のフィルタを追加して、色を変える
 */
public class Camera {
    private Function<Color, Color> filter;

    public Color capture(final Color inputColor) {
        if (filter == null) { // guard in case no filter applied...
            return inputColor;
        }
        final Color processedColor = filter.apply(inputColor);
        return processedColor;
    }

    public void setFilter(Function<Color, Color>... filters) {
        // intellij が賢いので、フィールドを宣言しておいて、"public set" と入力するとスラブを作成してくれる.
        filter = Stream.of(filters)
                .reduce(Function::compose) // (filter, next) -> filter.compose(next) // ラムダの代わりにメソッド参照を使う
                //.orElse(color -> color); // orElse をつけることで Optional 型を取り除くことができる!!
                .orElseGet(Function::identity); // identity() static メソッド: 与えられた引数をそのまま返す
    }

    public static void main(String[] args) {
        final Camera camera = new Camera();
        camera.setFilter(Color::brighter, Color::darker);
        final Consumer<String> printCaptured = filterInfo ->
                System.out.println(
                        String.format("with %s: %s", filterInfo, camera.capture(new Color(200, 100, 200))));
        printCaptured.accept("Brighter and darker");
    }
}
