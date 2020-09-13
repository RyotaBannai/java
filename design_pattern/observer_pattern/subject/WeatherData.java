package subject;

import java.util.ArrayList;
import observer.Observer;

public class WeatherData implements Subject {
  private ArrayList observers;
  private float temperature;
  private float humidity;
  private float pressure;

  public WeatherData() {
    observers = new ArrayList();
  }

  public void registerObserver(Observer o) {
    observers.add(o);
  }

  public void removeObserver(Observer o) {
    int i = observers.indexOf(o);
    /**
     * The indexOf() method of ArrayList returns the index of the first occurrence
     * of the specified element in this list, or -1 if this list does not contain
     * the element
     */
    if (i >= 0) {
      observers.remove(i);
    }
  }

  public void notifyObservers() {
    for (int i = 0; i < observers.size(); i++) {
      Observer observer = (Observer) observers.get(i);
      observer.update(temperature, humidity, pressure);
    }
  }

  public void measurementsChanged() {
    notifyObservers();
  }

  public void setMeasurements(float temperature, float humidity, float pressure) {
    /**
     * 気象観測所から気象データを擬似的に取得
     */
    this.temperature = temperature;
    this.humidity = humidity;
    this.pressure = pressure;
    measurementsChanged();
  }
}