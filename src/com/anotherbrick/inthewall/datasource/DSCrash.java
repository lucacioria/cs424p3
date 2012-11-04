package com.anotherbrick.inthewall.datasource;

import com.anotherbrick.inthewall.Cluster;

public class DSCrash {

  // mandatory attributes
  public int _state;
  public int _case;
  public int _year;
  public int month;
  public int hour;
  public int minutes;
  public String day_of_week;
  public String weather;
  public String alcohol_involved;
  public int[] travel_speed;
  public int[] vehicle_configuration;
  public int[] age;
  public Float latitude;
  public Float longitude;
  
  public boolean visibleOnMap=true;
  public Cluster cluster;

  // not mandatory attributes
  public String drug_involved;
  public String roadway_surface_condition;
  public int number_of_fatalities;

  public Float dimension=15f;
  public int clusterNumber;
  public boolean selected = false;

  public DSCrash() {
  }

  @Override
  public String toString() {
    return _case + "";
  }

  public int getClusterNumber() {
    return clusterNumber;
  }

  public void setClusterNumber(int clusterNumber) {
    this.clusterNumber = clusterNumber;
  }

  public Cluster getCluster() {
    return cluster;
  }

  public void setCluster(Cluster cluster) {
    this.cluster = cluster;
  }

  public boolean isVisibleOnMap() {
    return visibleOnMap;
  }

  public void setVisibleOnMap(boolean visibleOnMap) {
    this.visibleOnMap = visibleOnMap;
  }

}
