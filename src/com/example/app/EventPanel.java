package com.example.app;

import com.anotherbrick.inthewall.Config.MyColorEnum;
import com.anotherbrick.inthewall.EventSubscriber;
import com.anotherbrick.inthewall.VizNotificationCenter.EventName;
import com.anotherbrick.inthewall.VizPanel;

public class EventPanel extends VizPanel implements EventSubscriber {

  public EventPanel(float x0, float y0, float width, float height, VizPanel parent) {
    super(x0, y0, width, height, parent);
  }

  private int currentEvent = 1;

  @Override
  public void setup() {
    m.notificationCenter.registerToEvent(EventName.EVENT_CHANGED, this);
  }

  @Override
  public boolean draw() {
    pushStyle();
    background(MyColorEnum.DARK_GRAY);
    switch (currentEvent) {
    case 1:
      break;
    case 2:
      break;
    case 3:
      break;
    case 4:
      break;
    case 5:
      break;
    }
    popStyle();
    return false;
  }

  @Override
  public void eventReceived(EventName eventName, Object data) {
    if (eventName == EventName.EVENT_CHANGED) {
      if (data.toString().equals("2001 - Illinois and alcohol")) {
        currentEvent = 1;
      } else if (data.toString().equals("2003 - Michigan changes speed")) {
        currentEvent = 2;
      } else if (data.toString().equals("2006 - Texas changes speed")) {
        currentEvent = 3;
      } else if (data.toString().equals("2009 - Illinois trucks speed")) {
        currentEvent = 4;
      } else if (data.toString().equals("2010 - Wisconsin and alcohol")) {
        currentEvent = 5;
      }
    }

  }
}
