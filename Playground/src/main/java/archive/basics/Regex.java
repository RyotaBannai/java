import java.util.regex.*;

public class Regex {
  public static void main(String args[]) {
    String input = "people";
    String regex = "p.*"; // matches to 'people'
    pattern_match(input, regex);

    String regex2 = "\\Apeople"; // matches to 'people'
    pattern_match(input, regex2);

    String input2 = "How are you?";
    String regex3 = "\\byou"; // matches to 'you'
    pattern_match(input2, regex3);

    String regex4 = "\\Bou"; // matches to 'ou'. doesn't match to 'you'
    pattern_match(input2, regex4);
  }

  private static void pattern_match(String input, String regex) {
    Pattern p = Pattern.compile(regex);
    Matcher m = p.matcher(input);
    if (m.find()) {
      System.out.println(m.group());
    } else {
      System.out.println("doesn't match");
    }
  }
}