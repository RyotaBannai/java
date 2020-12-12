package effective_java.chap3;

import java.util.Arrays;
import java.util.stream.Stream;

public class equalsContract {
    public static void main(String[] args) {
        Point point1 = new Point(1, 1);
        Point point2 = new Point(1, 2);
        Color color1 = new Color("White");
        Color color2 = new Color("white");
        ColorPoint colorPoint1 = new ColorPoint(point1, color1);
        ColorPoint colorPoint2 = new ColorPoint(point1, color2);
        ColorPoint colorPoint3 = new ColorPoint(point2, color2);

        p(colorPoint1.equals(colorPoint2)); // true
        p(colorPoint1.equals(colorPoint3)); // false
        p(colorPoint1.asPoint().equals(colorPoint2.asPoint())); // true
        p(colorPoint1.asPoint().equals(colorPoint3.asPoint())); // false
    }

    /**
     * composite pattern の merit をここでも利用できる
     */
    private static class ColorPoint {
        private final Point point;
        private final Color color;

        public ColorPoint(Point point, Color color) {
            this.point = point;
            this.color = color;
        }

        /**
         * ColorPoint の Point としての View を返す
         * → これにより、Point での比較も実現できる
         */
        public Point asPoint() {
            return point;
        }

        /**
         * 継承先で比較の対象を自由に追加することができる
         */
        @Override
        public boolean equals(Object o) {
            if (!(o instanceof ColorPoint)) {
                return false;
            }
            ColorPoint colorPoint = (ColorPoint) o;
            return colorPoint.asPoint().equals(point) && colorPoint.color.equals(color);
        }

    }

    private static class Point {
        private final int x;
        private final int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Point)) {
                return false;
            }
            Point p = (Point) o;
            return p.x == x && p.y == y;
        }
    }

    private static class Color {
        private final String c;

        public Color(String c) {
            this.c = c;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Color)) {
                return false;
            }
            Color color = (Color) o;
            return color.c.equalsIgnoreCase(c);
        }
    }

    private static void p(Object... inputs) {
        Stream.of(inputs).forEach(System.out::println);
    }
}
