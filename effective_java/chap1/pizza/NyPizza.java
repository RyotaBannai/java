package effective_java.chap1.pizza;

public class NyPizza extends Pizza {

  public enum Size {
    SMALL, MEDIUM, LARGE
  }

  private final Size size;

  public static class Builder extends Pizza.Builder<Builder> {
    private final Size size;

    public Builder(Size size) {
      this.size = size;
    }

    @Override
    public NyPizza build() {
      return new NyPizza(this);
    }

    @Override
    protected Builder self() {
      return this;
    }
  }

  private NyPizza(Builder builder) {
    super(builder);
    size = builder.size;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(super.toString());

    sb.append("size:\n");
    sb.append(size);
    sb.append("\n");

    return sb.toString();

  }
}