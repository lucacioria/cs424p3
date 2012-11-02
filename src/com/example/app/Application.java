package com.example.app;

import java.util.ArrayList;

import processing.core.PApplet;

import com.anotherbrick.inthewall.BarData;
import com.anotherbrick.inthewall.Config.MyColorEnum;
import com.anotherbrick.inthewall.EventSubscriber;
import com.anotherbrick.inthewall.TouchEnabled;
import com.anotherbrick.inthewall.VizBarChart;
import com.anotherbrick.inthewall.VizModMap;
import com.anotherbrick.inthewall.VizNotificationCenter.EventName;
import com.anotherbrick.inthewall.VizPanel;

public class Application extends VizPanel implements TouchEnabled, EventSubscriber {

  public Application(float x0, float y0, float width, float height) {
    super(x0, y0, width, height);
  }

  private VizModMap map;
  private FilterBox filterBox;
  private MapButtons mapButtons;
  private BarChart barChart;

  @Override
  public boolean touch(float x, float y, boolean down, TouchTypeEnum touchType) {
    return propagateTouch(x, y, down, touchType);
  }

  @Override
  public void setup() {
    setupMap();
    setupFilterBox();
    setupMapButtons();
    setupBarChart();
    m.notificationCenter.registerToEvent(EventName.CURRENT_FILTER_UPDATED, this);
  }

  private void setupBarChart() {
    barChart = new BarChart(400, 100, 400, 200, this);
    barChart.setup();
    addTouchSubscriber(barChart);
  }

  private void setupMapButtons() {
    mapButtons = new MapButtons(500, 200, 20, 40, this);
    mapButtons.setup();
    addTouchSubscriber(mapButtons);
  }

  private void setupFilterBox() {
    filterBox = new FilterBox(100, 50, 400, 300, this);
    filterBox.setup();
    addTouchSubscriber(filterBox);
  }

  private void setupMap() {
    map = new VizModMap(1360 / 6 * 4, 0, 1360 / 6 * 2, 384, this);
    map.setup();
    addTouchSubscriber(map);
  }

  @Override
  public boolean draw() {
    pushStyle();
    background(MyColorEnum.DARK_BLUE);
    map.draw();
    filterBox.draw();
    mapButtons.draw();
    barChart.draw();
    popStyle();
    return false;
  }

  @Override
  public void eventReceived(EventName eventName, Object data) {
    if (eventName == EventName.CURRENT_FILTER_UPDATED) {
      println("loading from database..");
      m.dataSourceSQL.getCrashes(m.currentFilter);
      println(m.crashes.size() + "");
      m.notificationCenter.notifyEvent(EventName.CRASHES_UPDATED);
      m.dataSourceSQL.getCrashesCountBy(m.currentFilter, "day_of_week");
      m.notificationCenter.notifyEvent(EventName.CRASHES_COUNT_BY_VALUE_UPDATED);
    }
  }
}
