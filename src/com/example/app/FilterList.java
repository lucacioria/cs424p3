package com.example.app;

import java.util.ArrayList;

import processing.core.PApplet;

import com.anotherbrick.inthewall.EventSubscriber;
import com.anotherbrick.inthewall.TouchEnabled;
import com.anotherbrick.inthewall.VizButton;
import com.anotherbrick.inthewall.VizList;
import com.anotherbrick.inthewall.VizList.SelectionMode;
import com.anotherbrick.inthewall.VizNotificationCenter.EventName;
import com.anotherbrick.inthewall.VizPanel;
import com.anotherbrick.inthewall.Config.MyColorEnum;
import com.anotherbrick.inthewall.TouchEnabled.TouchTypeEnum;
import com.anotherbrick.inthewall.datasource.DSFilter;

class FilterList extends VizPanel implements TouchEnabled, EventSubscriber {

  public FilterList(float x0, float y0, float width, float height, VizPanel parent) {
    super(x0, y0, width, height, parent);
  }

  private ArrayList<String> values = new ArrayList<String>();
  private VizList list;

  @Override
  public boolean touch(float x, float y, boolean down, TouchTypeEnum touchType) {
    return propagateTouch(x, y, down, touchType);
  }

  @Override
  public void setup() {
    list = new VizList(0, 0, getWidth(), getHeight(), this);
    list.setup(MyColorEnum.LIGHT_BLUE, MyColorEnum.LIGHT_ORANGE, 10, values, false,
        SelectionMode.MULTIPLE);
  }

  @Override
  public boolean draw() {
    pushStyle();
    background(MyColorEnum.DARK_GRAY);
    list.draw();
    popStyle();
    return false;
  }

  @Override
  public void eventReceived(EventName eventName, Object data) {
  }

}