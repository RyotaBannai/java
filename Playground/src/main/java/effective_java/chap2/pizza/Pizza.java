package effective_java.chap2.pizza;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

public abstract class Pizza {
  public enum Topping {
    HAM, MUSHROOM, ONION, PEPPER, SAUSAGE
  }

  final Set<Topping> toppings;

  abstract static class Builder<T extends Builder<T>> {
    EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);

    public T addTopping(Topping topping) {
      toppings.add(Objects.requireNonNull(topping));
      return self();
    }

    abstract Pizza build();

    protected abstract T self();
  }

  Pizza(Builder<?> builder) {
    toppings = builder.toppings.clone();
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Toppings:\n");
    toppings.forEach((value) -> {
      sb.append(value);
      sb.append("\n");
    });
    return sb.toString();
  }
}