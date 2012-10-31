package com.example.app;

import java.util.ArrayList;

import javax.jws.Oneway;

import processing.core.PApplet;

import com.anotherbrick.inthewall.EventSubscriber;
import com.anotherbrick.inthewall.TouchEnabled;
import com.anotherbrick.inthewall.VizButton;
import com.anotherbrick.inthewall.VizNotificationCenter.EventName;
import com.anotherbrick.inthewall.VizPanel;
import com.anotherbrick.inthewall.Config.MyColorEnum;
import com.anotherbrick.inthewall.TouchEnabled.TouchTypeEnum;
import com.anotherbrick.inthewall.datasource.DSFilter;

class FilterRow extends VizPanel implements TouchEnabled, EventSubscriber {

  public FilterRow(float x0, float y0, float width, float height, VizPanel parent) {
    super(x0, y0, width, height, parent);
  }

  public String label, name;
  private ArrayList<String> values = new ArrayList<String>();
  private VizButton openListButton;

  @Override
  public boolean touch(float x, float y, boolean down, TouchTypeEnum touchType) {
    return propagateTouch(x, y, down, touchType);
  }

  @Override
  public void setup() {
    values = DSFilter.getValues(name);
    setupOpenListButton();
    m.notificationCenter.registerToEvent(EventName.BUTTON_TOUCHED, this);
  }

  private void setupOpenListButton() {
    float buttonWidth = 50;
    openListButton = new VizButton(getWidth() - buttonWidth, 0, buttonWidth, 20, this);
    addTouchSubscriber(openListButton);
    openListButton.name = name + "FilterRowButton";
    openListButton.setText("SELECT");
    openListButton.setStyle(MyColorEnum.LIGHT_GRAY, MyColorEnum.WHITE, MyColorEnum.DARK_GRAY, 255f,
        255f, 10);
    openListButton.setStyleSelected(MyColorEnum.LIGHT_BLUE, MyColorEnum.WHITE,
        MyColorEnum.DARK_GRAY, 255f, 255f, 10);
  }

  @Override
  public boolean draw() {
    pushStyle();
    background(MyColorEnum.DARK_GRAY);
    // label
    textSize(10);
    textAlign(PApplet.LEFT, PApplet.CENTER);
    text(label, 10, 10);
    // open list button
    openListButton.draw();
    openListButton.drawTextCentered();
    popStyle();
    return false;
  }

  @Override
  public void eventReceived(EventName eventName, Object data) {
    if (eventName == EventName.BUTTON_TOUCHED && data.toString().equals(name + "FilterRowButton")) {
      if (openListButton.isSelected()) {
        openListButton.setSelected(false);
        m.notificationCenter.notifyEvent(EventName.FILTER_LIST_CLOSE);
        println("close list: " + name);
      } else {
        println("open list: " + name);
        openListButton.setSelected(true);
        m.notificationCenter.notifyEvent(EventName.FILTER_LIST_OPEN, values);
      }
    }

  }

}