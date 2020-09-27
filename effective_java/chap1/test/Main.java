package effective_java.chap1.test;

import effective_java.chap1.NutritionFacts;
import effective_java.chap1.NutritionFacts.Builder;

public class Main {
  public static void main(String[] args) {
    Builder builder = new NutritionFacts.Builder(240, 8); // set required props
    NutritionFacts nutritions = builder.calories(100).sodium(35).carbohydrate(27).build();
    System.out.println(nutritions.toString());
  }
}