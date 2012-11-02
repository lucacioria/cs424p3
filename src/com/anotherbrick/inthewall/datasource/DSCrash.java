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
  public int alcohol_involved; // 1,0,-1
  public int[] travel_speed;
  public int[] vehicle_configuration;
  public int[] age;
  public int number_of_fatalities;
  public Float latitude;
  public Float longitude;

  // not mandatory attributes
  public String drug_involved;
  public String roadway_surface_condition;

  public Float dimension;
  public boolean selected = false;

  public DSCrash() {
  }

  @Override
  public String toString() {
    return _case + "";
  }

}
