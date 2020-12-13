package effective_java.chap3;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * HashMap は個々のエントリに関連づけられているハッシュコードをキャッシュする最適化を行っており、
 * そのハッシュコードが一致しなければ、オブジェクトの等価性を検査しない
 */
public class hasCode {

    public static void main(String[] args) {

        Map<PhoneNumber, String> m = new HashMap<>();
        m.put(new PhoneNumber(707, 867, 5309), "Jenny");
        p(m.get(new PhoneNumber(707, 867, 5309))); // null... why not Jenny!

        // hashCode メソッドを追加したあとは、Jenny を返却するようになる

    }

    private static class PhoneNumber {
        private int hashCode; // 自動的に 0 に初期化される
        public final short areaCode, prefix, lineNum;

        public PhoneNumber(int areaCode, int prefix, int lineNum) {
            this.areaCode = rangeCheck(areaCode, 999, "areaCode");
            this.prefix = rangeCheck(prefix, 999, "prefix");
            this.lineNum = rangeCheck(lineNum, 9999, "lineNum");
        }

        private static short rangeCheck(int val, int max, String arg) {
            if (val < 0 || val > max) {
                throw new IllegalArgumentException(String.format("%s is invalid. %d", arg, val));
            }
            return (short) val;
        }

        // hashCode 追加
        // significant field のみ計算の対象にする
        // Type.hashCode の Type はその field のボクシングされた型
        @Override
        public int hashCode() {
            int result = hashCode; // 毎回計算されないように修正(遅延初期化（lazily initialize）されてキャッシュされた hashCode を持つ)
            if (result == 0) {
                result = Short.hashCode(areaCode);
                result = 31 * result + Short.hashCode(prefix);
                result = 31 * result + Short.hashCode(lineNum);
            }
            return result;
        }

        @Override
        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof PhoneNumber)) {
                return false;
            }
            PhoneNumber pn = (PhoneNumber) o;
            return pn.areaCode == areaCode
                    && pn.prefix == prefix
                    && pn.lineNum == lineNum;
        }
    }

    private static void p(Object... inputs) {
        Stream.of(inputs).forEach(System.out::println);
    }
}
