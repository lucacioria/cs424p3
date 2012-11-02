package com.anotherbrick.inthewall.datasource;

public class DSCrash {

  // mandatory attributes
  public int _state;
  public int _case;
  public int _year;
  public int month;
  public int hour;
  public int minutes;
  public int day_of_week;
  public String weather;
  public String alcohol_involved;
  public int[] travel_speed;
  public int[] vehicle_configuration;
  public int[] age;
  public Float latitude;
  public Float longitude;

  // not mandatory attributes
  public String drug_involved;
  public String roadway_surface_condition;
  public int number_of_fatalities;

  public Float dimension;
  public boolean selected = false;

  public DSCrash() {
  }

  public DSCrash(String weather, String alcohol_involved, String drug_involved, int number_of_fatalities, Float latitude,
      Float longitude) {
    this.weather = weather;
    this.alcohol_involved = alcohol_involved;
    this.drug_involved = drug_involved;
    this.number_of_fatalities=number_of_fatalities;
    this.latitude = latitude;
    this.longitude = longitude;
  }

  @Override
  public String toString() {
    return _case + "";
  }

}
