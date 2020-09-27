import java.util.ArrayList;
import static java.lang.System.out;

public class Array {
  public static void main(String[] args) {
    int result[] = { 1, 2, 3, 4, 5 };
    out.println(result.length);
    for (int item : result) {
      out.println(item);
    }
    ArrayList<Integer> addableList = new ArrayList<>();
    for (int i = 0; i < result.length; i++) {
      addableList.add(i);
    }
    out.println(addableList);
    out.println(addableList.size());
  }
}