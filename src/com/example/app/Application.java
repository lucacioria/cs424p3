package com.example.app;

import java.util.ArrayList;

import com.anotherbrick.inthewall.Config.MyColorEnum;
import com.anotherbrick.inthewall.datasource.DSCrash;
import com.anotherbrick.inthewall.datasource.DSFilter;
import com.anotherbrick.inthewall.TouchEnabled;
import com.anotherbrick.inthewall.VizModMap;
import com.anotherbrick.inthewall.VizPanel;

public class Application extends VizPanel implements TouchEnabled {

  public Application(float x0, float y0, float width, float height) {
    super(x0, y0, width, height);
  }

  private VizModMap map;

  @Override
  public boolean touch(float x, float y, boolean down, TouchTypeEnum touchType) {
    return propagateTouch(x, y, down, touchType);
  }

  @Override
  public void setup() {
    map = new VizModMap(1360 / 6 * 4, 0, 1360 / 6 * 2, 384, this);
    map.setup();
    addTouchSubscriber(map);
    DSFilter filter = new DSFilter();
    filter.latitudeMax = 34f;
    filter.latitudeMin = 32f;
    filter.longitudeMax = -84f;
    filter.longitudeMin = -86f;
    ArrayList<DSCrash> data = m.dataSourceSQL.getCrashes(filter);
    println(data.size() + "");
  }

  @Override
  public boolean draw() {
    pushStyle();
    background(MyColorEnum.DARK_BLUE);
    map.draw();
    popStyle();
    return false;
  }

}
