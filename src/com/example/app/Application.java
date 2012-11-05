package com.example.app;

import java.util.ArrayList;

import processing.core.PVector;

import com.anotherbrick.inthewall.Config.MyColorEnum;
import com.anotherbrick.inthewall.EventSubscriber;
import com.anotherbrick.inthewall.TouchEnabled;
import com.anotherbrick.inthewall.VizModMap;
import com.anotherbrick.inthewall.VizNotificationCenter.EventName;
import com.anotherbrick.inthewall.VizPanel;
import com.anotherbrick.inthewall.ScatterPlot.ScatterPlotData;
import com.anotherbrick.inthewall.ScatterPlot.VizScatterPlot;
import com.anotherbrick.inthewall.datasource.DSFilter;

public class Application extends VizPanel implements TouchEnabled, EventSubscriber {

  public Application(float x0, float y0, float width, float height) {
    super(x0, y0, width, height);
  }

  // filters
  private FilterBox filterBox;
  // map
  private VizModMap map;
  private MapButtons mapButtons;
  private SelectorPanel mapSelector;
  // barchart 1
  private SelectorPanel barChart1Selector;
  private BarChartButtons barChart1Buttons;
  private BarChart barChart1;
  // barchart 2
  private SelectorPanel barChart2Selector;
  private BarChartButtons barChart2Buttons;
  private BarChart barChart2;
  // scatter plot
  private SelectorPanel scatterPlotSelector;
  private VizScatterPlot scatterPlot;
  //
  private BlackBox blackBox;
  private float stdPadding = 10;
  private MapButtons2 mapButtons2;
  private SelectorPanel scatterSelector;

  @Override
  public boolean touch(float x, float y, boolean down, TouchTypeEnum touchType) {
    return propagateTouch(x, y, down, touchType);
  }

  @Override
  public void setup() {
    // SETUP ORDER MATTERS
    // FIRST ONE, HIGHEST CLICK PRIORITY
    setupMap();
    //
    setupBlackBox();
    //
    setupScatterSelector();
    setupMapSelector();
    setupFilterBox();
    setupBarChart1Selector();
    setupBarChart2Selector();
    //
    setupBarChart1();
    setupBarChart1Buttons();
    //
    setupBarChart2();
    setupBarChart2Buttons();
    //
    setupScatterPlot();
    setupMapButtons();
    setupMapButtons2();
    //
    m.notificationCenter.registerToEvent(EventName.CURRENT_FILTER_UPDATED, this);
    m.notificationCenter.registerToEvent(EventName.BUTTON_TOUCHED, this);
    if (c.initializeVisualization) initializeVisualization();
  }

  private void initializeVisualization() {
    DSFilter filter = new DSFilter();
    ArrayList<String> state = new ArrayList<String>();
    state.add("Alaska");
    filter.setAttributeWithList("_state", state);
    ArrayList<String> day_of_week = new ArrayList<String>();
    day_of_week.add("1_Sunday");
    filter.setAttributeWithList("day_of_week", day_of_week);
    m.currentFilter = filter;
    m.notificationCenter.notifyEvent(EventName.BUTTON_TOUCHED, "submitFilterBox");
    touchBarChartsToUpdateThem();
  }

  private void touchBarChartsToUpdateThem() {
    m.notificationCenter.notifyEvent(EventName.BUTTON_TOUCHED, "barchartSelectXButton1");
    m.notificationCenter.notifyEvent(EventName.BUTTON_TOUCHED, "barchartSelectXButton1");
    m.notificationCenter.notifyEvent(EventName.BUTTON_TOUCHED, "barchartSelectXButton2");
    m.notificationCenter.notifyEvent(EventName.BUTTON_TOUCHED, "barchartSelectXButton2");
  }

  private void setupBlackBox() {
    blackBox = new BlackBox(0, 0, 906, getHeight(), this);
    blackBox.addTouchSubscriber(this);
  }

  private void setupScatterPlot() {
    scatterPlot = new VizScatterPlot(barChart1.getX1() + stdPadding, stdPadding,
        (getHeight() - 3 * stdPadding) / 2, (getHeight() - 3 * stdPadding) / 2, this);
    ScatterPlotData data = new ScatterPlotData();
    ArrayList<PVector> points = new ArrayList<PVector>();
    for (int i = 0; i < 100; i++) {
      points.add(new PVector(m.p.random(30, 120), m.p.random(50, 600)));
    }
    data.setPoints(points);
    data.title = "Thou shan't scatter!";
    scatterPlot.data = data;
    scatterPlot.pointRadius = 5;
    scatterPlot.setup();
    addTouchSubscriber(scatterPlot);
  }

  private void setupBarChart1() {
    barChart1 = new BarChart(220, 0 + stdPadding, 400, (getHeight() - 4 * stdPadding) / 2, this);
    barChart1.barChartNumber = 1;
    barChart1.setup();
    addTouchSubscriber(barChart1);

  }

  private void setupScatterSelector() {
    scatterSelector = new SelectorPanel(910, 200, getHeight() - 170, 150, this);
    scatterSelector.panelName = "scatterSelector";
    scatterSelector.setVisible(false);
    scatterSelector.setup();
    addTouchSubscriber(scatterSelector);
  }

  private void setupMapSelector() {
    mapSelector = new SelectorPanel(910, 200, getHeight() - 170, 150, this);
    mapSelector.panelName = "mapSelector";
    mapSelector.setVisible(false);
    mapSelector.setup();
    addTouchSubscriber(mapSelector);
  }

  private void setupBarChart1Selector() {
    barChart1Selector = new SelectorPanel(400, 200, 200, 150, this);
    barChart1Selector.panelName = "selectorBarChart1";
    barChart1Selector.setVisible(false);
    barChart1Selector.setup();
    addTouchSubscriber(barChart1Selector);
  }

  private void setupBarChart2Selector() {
    barChart2Selector = new SelectorPanel(400, 200, 200, 150, this);
    barChart2Selector.panelName = "selectorBarChart2";
    barChart2Selector.setVisible(false);
    barChart2Selector.setup();
    addTouchSubscriber(barChart2Selector);
  }

  private void setupBarChart2() {
    barChart2 = new BarChart(220, (getHeight() + 2 * stdPadding) / 2, 400,
        (getHeight() - 4 * stdPadding) / 2, this);
    barChart2.barChartNumber = 2;
    barChart2.setup();
    addTouchSubscriber(barChart2);
  }

  private void setupMapButtons() {
    mapButtons = new MapButtons(1360 / 6 * 4, (getHeight() / 2), 20, 40, this);
    mapButtons.setup();
    addTouchSubscriber(mapButtons);
  }

  private void setupMapButtons2() {
    mapButtons2 = new MapButtons2(910, getHeight() - 20, this);
    mapButtons2.setup();
    addTouchSubscriber(mapButtons2);
  }

  private void setupBarChart1Buttons() {
    barChart1Buttons = new BarChartButtons(barChart1.getX1() - BarChartButtons.width,
        barChart1.getY1() - stdPadding, this);
    barChart1Buttons.barChartNumber = 1;
    barChart1Buttons.setup();
    addTouchSubscriber(barChart1Buttons);
  }

  private void setupBarChart2Buttons() {
    barChart2Buttons = new BarChartButtons(barChart2.getX1() - BarChartButtons.width,
        barChart2.getY1() - stdPadding, this);
    barChart2Buttons.barChartNumber = 2;
    barChart2Buttons.setup();
    addTouchSubscriber(barChart2Buttons);
  }

  private void setupFilterBox() {
    filterBox = new FilterBox(stdPadding, getHeight() * .25f, 400, getHeight() * .75f - stdPadding,
        this);
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
    //
    blackBox.draw();
    //
    mapButtons.draw();
    mapButtons2.draw();

    barChart1.draw();
    barChart1Buttons.draw();

    barChart2.draw();
    barChart2Buttons.draw();

    scatterPlot.draw();
    //
    filterBox.draw();
    //
    barChart1Selector.draw();
    barChart2Selector.draw();
    mapSelector.draw();
    scatterSelector.draw();
    popStyle();
    return false;
  }

  @Override
  public void eventReceived(EventName eventName, Object data) {
    if (eventName == EventName.CURRENT_FILTER_UPDATED) {
      println("loading from database..");
      m.dataSourceSQL.getCrashes(m.currentFilter);
      println(m.crashes.size() + "");
      //
      m.notificationCenter.notifyEvent(EventName.CRASHES_UPDATED);
      touchBarChartsToUpdateThem();
    } else if (eventName == EventName.BUTTON_TOUCHED) {
      manageButtons(data.toString());
    }
  }

  private void manageButtons(String name) {
    if (name.equals("barchartSelectXButton1")) {
      SelectorPanelData sdata = new SelectorPanelData();
      sdata.name = "selectorBarChart1XAxis";
      sdata.panelName = "selectorBarChart1";
      if (barChart1Selector.isVisible()) {
        m.notificationCenter.notifyEvent(EventName.SELECTOR_PANEL_CLOSE, sdata);
        m.dataSourceSQL.getCrashesCountBy(m.currentFilter, m.selectorPanelsState.get(sdata.name)
            .get(0).toString(), m.selectorPanelsState.get("selectorBarChart1State").get(0)
            .toString(), 1);
        m.notificationCenter.notifyEvent(EventName.CRASHES_COUNT_BY_VALUE_UPDATED, "barChart1");
      } else {
        ArrayList<String> values = new ArrayList<String>();
        values.add("_year");
        values.add("month");
        values.add("hour_range");
        values.add("weather");
        values.add("roadway_surface_condition");
        values.add("alcohol_involved");
        values.add("drug_involved");
        values.add("age_range");
        values.add("day_of_week");
        values.add("travel_speed_range");
        sdata.values = values;
        m.notificationCenter.notifyEvent(EventName.SELECTOR_PANEL_OPEN, sdata);
      }
    } else if (name.equals("barchartSelectStateButton1")) {
      // SelectorPanelData sdata = new SelectorPanelData();
      // sdata.name = "selectorBarChart1State";
      // sdata.panelName = "selectorBarChart1";
      // if (barChart1Selector.isVisible()) {
      // m.notificationCenter.notifyEvent(EventName.SELECTOR_PANEL_CLOSE,
      // sdata);
      // m.dataSourceSQL.getCrashesCountBy(m.currentFilter,
      // m.selectorPanelsState.get("selectorBarChart1XAxis").get(0).toString(),
      // m.selectorPanelsState.get(sdata.name).get(0).toString(), 1);
      // m.notificationCenter.notifyEvent(EventName.CRASHES_COUNT_BY_VALUE_UPDATED,
      // "barChart1");
      // } else {
      // ArrayList<String> values = DSFilter.getValues("_state");
      // sdata.values = values;
      // m.notificationCenter.notifyEvent(EventName.SELECTOR_PANEL_OPEN, sdata);
      // }
    } else if (name.equals("barchartSelectXButton2")) {
      SelectorPanelData sdata = new SelectorPanelData();
      sdata.name = "selectorBarChart2XAxis";
      sdata.panelName = "selectorBarChart2";
      if (barChart2Selector.isVisible()) {
        m.notificationCenter.notifyEvent(EventName.SELECTOR_PANEL_CLOSE, sdata);
        m.dataSourceSQL.getCrashesCountBy(m.currentFilter, m.selectorPanelsState.get(sdata.name)
            .get(0).toString(), m.selectorPanelsState.get("selectorBarChart2State").get(0)
            .toString(), 2);
        m.notificationCenter.notifyEvent(EventName.CRASHES_COUNT_BY_VALUE_UPDATED, "barChart2");
      } else {
        ArrayList<String> values = new ArrayList<String>();
        values.add("_year");
        values.add("month");
        values.add("hour_range");
        values.add("weather");
        values.add("roadway_surface_condition");
        values.add("alcohol_involved");
        values.add("drug_involved");
        values.add("age_range");
        values.add("day_of_week");
        values.add("travel_speed_range");
        sdata.values = values;
        m.notificationCenter.notifyEvent(EventName.SELECTOR_PANEL_OPEN, sdata);
      }
    } else if (name.equals("barchartSelectStateButton2")) {
      SelectorPanelData sdata = new SelectorPanelData();
      sdata.name = "selectorBarChart2State";
      sdata.panelName = "selectorBarChart2";
      if (barChart2Selector.isVisible()) {
        m.notificationCenter.notifyEvent(EventName.SELECTOR_PANEL_CLOSE, sdata);
        m.dataSourceSQL.getCrashesCountBy(m.currentFilter,
            m.selectorPanelsState.get("selectorBarChart2XAxis").get(0).toString(),
            m.selectorPanelsState.get(sdata.name).get(0).toString(), 2);
        m.notificationCenter.notifyEvent(EventName.CRASHES_COUNT_BY_VALUE_UPDATED, "barChart2");
      } else {
        ArrayList<String> values = DSFilter.getValues("_state");
        sdata.values = values;
        m.notificationCenter.notifyEvent(EventName.SELECTOR_PANEL_OPEN, sdata);
      }
    } else if (name.equals("selectColorByButton")) {
      SelectorPanelData sdata = new SelectorPanelData();
      sdata.name = "mapSelector";
      sdata.panelName = "mapSelector";
      if (mapSelector.isVisible()) {
        m.notificationCenter.notifyEvent(EventName.SELECTOR_PANEL_CLOSE, sdata);
        m.notificationCenter.notifyEvent(EventName.CHANGED_MAP_COLOR_ATTRIBUTE,
            m.selectorPanelsState.get("mapSelector").get(0).toString());
      } else {
        ArrayList<String> values = new ArrayList<String>();
        values.add("weather");
        values.add("alcohol_involved");
        values.add("drug_involved");
        values.add("number_of_fatalities");
        sdata.values = values;
        m.notificationCenter.notifyEvent(EventName.SELECTOR_PANEL_OPEN, sdata);
      }
    }
  }
}
