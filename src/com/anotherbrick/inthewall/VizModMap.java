package com.anotherbrick.inthewall;

import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import processing.core.PVector;

import com.anotherbrick.inthewall.Config.MyColorEnum;
import com.anotherbrick.inthewall.TouchEnabled.TouchTypeEnum;
import com.modestmaps.InteractiveMap;
import com.modestmaps.core.Point2f;
import com.modestmaps.geo.Location;
import com.modestmaps.providers.Microsoft;

public class VizModMap extends VizPanel implements TouchEnabled {

 
  private InteractiveMap map;
  private PVector mapOffset;
  private PVector mapSize;
  

  public VizModMap(float x0, float y0, float width, float height, VizPanel parent) {
    super(x0, y0, width, height, parent);
    this.parent = parent;
  }

  @Override
  public void setup() {

  

    mapOffset = new PVector(getX0(), getY0());
    mapSize = new PVector(getWidth(), getHeight());

    map = new InteractiveMap(m.p, new Microsoft.RoadProvider(), mapOffset.x, mapOffset.y,
        mapSize.x, mapSize.y);


  }

  @Override
  public boolean draw() {
    pushStyle();

    background(MyColorEnum.BLACK);
    
    map.draw();
 

    noFill();
    stroke(MyColorEnum.RED);
    strokeWeight(10);
    rect(mapOffset.x, mapOffset.y, mapSize.x, mapSize.y);

    popStyle();
    return false;
  }

 

 
  @Override
  public boolean touch(float x, float y, boolean down, TouchTypeEnum touchType) {
   return true;
      
  }




}

