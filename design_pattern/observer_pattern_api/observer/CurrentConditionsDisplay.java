package observer;

import java.util.Observable;
import java.util.Observer;
import observable.WeatherData;

public class CurrentConditionsDisplay implements Observer, DisplayElement {
  Observable observable;
  private float temperature;
  private float humidity;

  public CurrentConditionsDisplay(Observable observable) {
    this.observable = observable;
    observable.addObserver(this);
  }

  public void update(Observable obs, Object arg) {
    if (obs instanceof WeatherData) {
      WeatherData weatherData = (WeatherData) obs;
      this.temperature = weatherData.getTemperature();
      this.humidity = weatherData.getHumidity();
      display();
    }
  }

  public void display() {
    System.out.println("現在の気象状況: 温度" + temperature + "度　湿度" + humidity + "%");
  }
}