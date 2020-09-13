package duck;

import quack_behavior.Quack;
import fly_behavior.FlyWithWings;

public class MallardDuck extends Duck {
  public MallardDuck() {
    quackBehavior = new Quack();
    flyBehavior = new FlyWithWings();
  }

  public void display() {
    System.out.println("本物のマガモです");
  }

}