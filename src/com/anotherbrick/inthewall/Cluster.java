package com.anotherbrick.inthewall;

import java.util.ArrayList;

import com.anotherbrick.inthewall.Config.MyColorEnum;
import com.anotherbrick.inthewall.datasource.DSCrash;
import com.modestmaps.core.Point2f;

public class Cluster {
private int count=0;
private Point2f center;
public ArrayList<DSCrash> accidents;
//pie chart stuff
private float[] percentages;
private ArrayList<Float> angles;
private MyColorEnum colors;
private float lastAngle=0;
private float diameter;
private float centerX;
  private float centerY;
  private boolean label=false;

public Cluster(Point2f center){
  this.setCenter(center);
  accidents=new ArrayList<DSCrash>();
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
