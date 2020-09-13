package test;

import observer.CurrentConditionsDisplay;
import subject.WeatherData;

public class WeatherStation {
  public static void main(String args[]) {
    WeatherData weatherData = new WeatherData();
    CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay(weatherData);
    // StatisticsDisplay statisticsDisplay = new StatisticsDisplay(weatherData);

    weatherData.setMeasurements(27f, 65f, 30.4f);
  }
}