package archive.basics;
/**
 * javadoc
 */

class Hello {
  public static void main(String[] args) {
    String hello = "hello";
    String message = hello;
    var this_is_string_type = "string";
    int data[] = { 85, 72, 89 };

    // for (int i = 0; i < data.length; i++){ System.out.println(data[i]); }
    // previous way
    for (int seiseki : data) {
      System.out.println(seiseki);
    }
    System.out.println(this_is_string_type);
  }
}

class Calc {
  final float PI = 3.1415F; // 定数として使いたい
  int result;
  float float_result;

  void plus(int val1, int val2) {
    result = val1 + val2;
  }

  void calcCircumstance(int val1) {
    float_result = val1 * PI;
  }
}