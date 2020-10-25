package effective_java.chap1.test;

import java.util.Objects;

import effective_java.chap1.NutritionFacts;
import effective_java.chap1.pizza.NyPizza;
import effective_java.chap1.pizza.NyPizza.Size;
import effective_java.chap1.pizza.Pizza.Topping;

public class Main {
  public static void main(String[] args) {
    NutritionFacts.Builder builder = new NutritionFacts.Builder(240, 8); // set required props
    NutritionFacts nutritions = builder.calories(100).sodium(35).carbohydrate(27).build();
    // System.out.println(nutritions.toString());
    System.out.println(Objects.toString(nutritions));
    /**
     * Objects.toString: null の場合は null を返す
     */

    NyPizza.Builder pizzaBuilder = new NyPizza.Builder(Size.SMALL);
    NyPizza pizza = pizzaBuilder.addTopping(Topping.SAUSAGE).build();
    // System.out.println(pizza.toString());
    System.out.println(Objects.toString(pizza));
  }
}