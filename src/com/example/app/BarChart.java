package com.example.app;

import com.anotherbrick.inthewall.BarChart.VizBarChart;
import com.anotherbrick.inthewall.Config.MyColorEnum;
import com.anotherbrick.inthewall.EventSubscriber;
import com.anotherbrick.inthewall.TouchEnabled;
import com.anotherbrick.inthewall.VizNotificationCenter.EventName;
import com.anotherbrick.inthewall.datasource.DSFilter;
import com.anotherbrick.inthewall.VizPanel;

public class BarChart extends VizPanel implements TouchEnabled, EventSubscriber {

  private VizBarChart barChart;
  public int barChartNumber;

  public BarChart(float x0, float y0, float width, float height, VizPanel parent) {
    super(x0, y0, width, height, parent);
  }

  @Override
  public void setup() {
    setupBarChart();
    m.notificationCenter.registerToEvent(EventName.CRASHES_COUNT_BY_VALUE_UPDATED, this);
  }

  private void setupBarChart() {
    barChart = new VizBarChart(0, 0, getWidth(), getHeight(), this);
    if (barChartNumber == 1) {
      barChart.data = m.crashesCountForBarchart1;
      barChart.title = DSFilter.getLabelByName(m.currentGroupField1);
    } else if (barChartNumber == 2) {
      barChart.data = m.crashesCountForBarchart2;
      barChart.title = DSFilter.getLabelByName(m.currentGroupField2);
    }
  }

  @Override
  public boolean draw() {
    pushStyle();
    background(MyColorEnum.LIGHT_ORANGE);
    if (barChart.title == null) {
      pushStyle();
      background(MyColorEnum.LIGHT_GREEN);
      textSize(30);
      fill(MyColorEnum.WHITE);
      text("NO DATA", 100, 100);
      popStyle();
    } else {
      barChart.draw();
    }
    popStyle();
    return false;
  }

  @Override
  public void eventReceived(EventName eventName, Object data) {
    if (eventName == EventName.CRASHES_COUNT_BY_VALUE_UPDATED) {
      if (barChartNumber == 1 && data.toString().equals("barChart1")) {
        barChart.data = m.crashesCountForBarchart1;
        barChart.title = m.currentGroupField1;
        barChart.setup();
      } else if (barChartNumber == 2 && data.toString().equals("barChart2")) {
        barChart.data = m.crashesCountForBarchart2;
        barChart.title = m.currentGroupField2;
        barChart.setup();
      }
    }
  }

  @Override
  public boolean touch(float x, float y, boolean down, TouchTypeEnum touchType) {
    return propagateTouch(x, y, down, touchType);
  }

}
