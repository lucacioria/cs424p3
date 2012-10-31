package com.anotherbrick.inthewall.datasource;

public class DSCrash {

  public int _year;
  public int _crash;
  public Float latitude;
  public Float longitude;

  public Float dimension;
  public boolean selected = false;

  public DSCrash() {
  }

  public DSCrash(Float latitude, Float longitude) {
    this.latitude = latitude;
    this.longitude = longitude;
  }

  @Override
  public String toString() {
    return _crash + "";
  }

}
