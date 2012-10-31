package com.example.app;

import java.awt.font.TextMeasurer;
import java.util.ArrayList;

import com.anotherbrick.inthewall.TouchEnabled;
import com.anotherbrick.inthewall.VizPanel;

public class FilterBox extends VizPanel implements TouchEnabled {

  private ArrayList<FilterRow> filterRows;
  private FilterList filterList;

  public FilterBox(float x0, float y0, float width, float height, VizPanel parent) {
    super(x0, y0, width, height, parent);
  }

  @Override
  public void setup() {
    setupFilterRows();
    setupFilterList();

  }

  private void setupFilterList() {
    filterList = new FilterList(getWidth(), 0, 200, getHeight(), this);
  }

  private void setupFilterRows() {
    filterRows = new ArrayList<FilterRow>();
    FilterRow row = new FilterRow(0, 0, 200, 30, this);
    row.label = "weather";
    row.name = "weather";
    row.setup();
    filterRows.add(row);
    addTouchSubscriber(row);
  }

  @Override
  public boolean draw() {
    for (FilterRow row : filterRows) {
      row.draw();
    }
    filterList.draw();
    return false;
  }

  @Override
  public boolean touch(float x, float y, boolean down, TouchTypeEnum touchType) {
    return propagateTouch(x, y, down, touchType);
  }

}
