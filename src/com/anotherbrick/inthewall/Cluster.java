package com.anotherbrick.inthewall;

import com.modestmaps.core.Point2f;

public class Cluster {
private int count;
private Point2f center;

public Cluster(Point2f center){
  this.setCenter(center);
  
}
public int getCount() {
  return count;
}

public void setCount(int count) {
  this.count = count;
}
public Point2f getCenter() {
  return center;
}
public void setCenter(Point2f center) {
  this.center = center;
}
}
