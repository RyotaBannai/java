import java.util.ArrayList;

public class Array {
  public static void main(String[] args) {
    int result[] = { 1, 2, 3, 4, 5 };
    System.out.println(result.length);
    for (int item : result) {
      System.out.println(item);
    }

    ArrayList<Integer> addableList = new ArrayList<>();
    for (int i = 0; i < result.length; i++) {
      addableList.add(i);
    }
    System.out.println(addableList);
    System.out.println(addableList.size());
  }
}