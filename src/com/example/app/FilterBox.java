package com.example.app;

import java.awt.font.TextMeasurer;
import java.util.ArrayList;
import java.util.HashMap;

import com.anotherbrick.inthewall.Config.MyColorEnum;
import com.anotherbrick.inthewall.TouchEnabled;
import com.anotherbrick.inthewall.VizPanel;
import com.anotherbrick.inthewall.datasource.DSFilter;

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
    filterList = new FilterList(200, 0, 200, getHeight(), this);
    filterList.setVisible(false);
    filterList.setup();
    addTouchSubscriber(filterList);
  }

  private void setupFilterRows() {
    filterRows = new ArrayList<FilterRow>();
    float rowHeight = 30;
    ArrayList<HashMap<String, String>> filterNames = DSFilter.getFilterNames();
    for (int i = 0; i < filterNames.size(); i++) {
      FilterRow row = new FilterRow(0, i * rowHeight, 200, rowHeight, this);
      row.label = filterNames.get(i).get("label");
      row.name = filterNames.get(i).get("name");
      row.setup();
      filterRows.add(row);
      addTouchSubscriber(row);
    }
  }

  @Override
  public boolean draw() {
    pushStyle();
    background(MyColorEnum.RED);
    for (FilterRow row : filterRows) {
      row.draw();
    }
    filterList.draw();
    popStyle();
    return false;
  }

  @Override
  public boolean touch(float x, float y, boolean down, TouchTypeEnum touchType) {
    return propagateTouch(x, y, down, touchType);
  }

}
