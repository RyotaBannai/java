import quack_behavior.QuackBehavior;
import fly_behavior.FlyBehavior;

public abstract class Duck {
  FlyBehavior flyBehavior;
  QuackBehavior quackBehavior;

  public abstract void display();

  public void performFly() {
    flyBehavior.fly();
  }

  public void performQuack() {
    quackBehavior.quack();
  }

  public void swim() {
    System.out.println("全ての鴨は浮かびます");
  }
}