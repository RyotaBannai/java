package effective_java.chap2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Builder pattern. not same as design pattern's builder pattern Builder pattern
 * is kinda of Singleton pattern in the design pattern
 */

public class NutritionFacts {
  private final int servingSize;
  private final int servings;
  private final int calories;
  private final int fat;
  private final int sodium;
  private final int carbohydrate;

  // Member inner class
  public static class Builder {
    // required params
    private final int servingSize;
    private final int servings;
    // optional params
    private int calories = 0;
    private int fat = 0;
    private int sodium = 0;
    private int carbohydrate = 0;

    // Builder for required params
    public Builder(int servingSize, int servings) {
      this.servingSize = servingSize;
      this.servings = servings;
    }

    // Builder for optional params
    public Builder calories(int val) {
      calories = val;
      return this;
    }

    public Builder fat(int val) {
      fat = val;
      return this;
    }

    public Builder sodium(int val) {
      sodium = val;
      return this;
    }

    public Builder carbohydrate(int val) {
      carbohydrate = val;
      return this;
    }

    public NutritionFacts build() {
      return new NutritionFacts(this);
    }
  }

  private NutritionFacts(Builder builder) {
    servingSize = builder.servingSize;
    servings = builder.servings;
    calories = builder.calories;
    fat = builder.fat;
    sodium = builder.sodium;
    carbohydrate = builder.carbohydrate;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    Map<String, Integer> nutritionMap = this.map();
    nutritionMap.forEach((name, value) -> {
      sb.append(name);
      sb.append(":");
      sb.append(value);
      sb.append("\n");
    });
    return sb.toString();
  }

  public Map<String, Integer> map() {
    Map<String, Integer> nutritionMap = new HashMap<>();
    nutritionMap.put("servingSize", servingSize);
    nutritionMap.put("servings", servings);
    nutritionMap.put("calories", calories);
    nutritionMap.put("fat", fat);
    nutritionMap.put("sodium", sodium);
    nutritionMap.put("carbohydrate", carbohydrate);
    return nutritionMap;
  }

  public void showFields() {
    Arrays.asList(getClass().getDeclaredFields()).forEach(System.out::println);
  }
}