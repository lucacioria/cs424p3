package com.example.app;

import java.util.ArrayList;

import com.anotherbrick.inthewall.Config.MyColorEnum;
import com.anotherbrick.inthewall.EventSubscriber;
import com.anotherbrick.inthewall.TouchEnabled;
import com.anotherbrick.inthewall.VizList;
import com.anotherbrick.inthewall.VizList.SelectionMode;
import com.anotherbrick.inthewall.VizNotificationCenter.EventName;
import com.anotherbrick.inthewall.VizPanel;

class FilterList extends VizPanel implements TouchEnabled, EventSubscriber {

  public FilterList(float x0, float y0, float width, float height, VizPanel parent) {
    super(x0, y0, width, height, parent);
  }

  private VizList list;

  @Override
  public boolean touch(float x, float y, boolean down, TouchTypeEnum touchType) {
    return propagateTouch(x, y, down, touchType);
  }

  @Override
  public void setup() {
    m.notificationCenter.registerToEvent(EventName.FILTER_LIST_CLOSE, this);
    m.notificationCenter.registerToEvent(EventName.FILTER_LIST_OPEN, this);
  }

  private void setupVizList(ArrayList<String> values) {
    list = new VizList(0, 0, getWidth(), getHeight(), this);
    list.setup(MyColorEnum.LIGHT_GRAY, MyColorEnum.MEDIUM_GRAY, 10, values, false,
        SelectionMode.MULTIPLE);
    addTouchSubscriber(list);
  }

  @Override
  public boolean draw() {
    if (!isVisible()) return false;
    pushStyle();
    background(MyColorEnum.DARK_GRAY);
    list.draw();
    popStyle();
    return false;
  }

  @SuppressWarnings("unchecked")
  @Override
  public void eventReceived(EventName eventName, Object data) {
    switch (eventName) {
    case FILTER_LIST_OPEN:
      setupVizList((ArrayList<String>) data);
      setVisible(true);
      break;
    case FILTER_LIST_CLOSE:
      setVisible(false);
      break;
    default:
      break;
    }
  }

}