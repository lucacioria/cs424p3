package com.example.app;

import java.awt.font.TextMeasurer;
import java.util.ArrayList;

import processing.core.PApplet;

import com.anotherbrick.inthewall.TouchEnabled;
import com.anotherbrick.inthewall.VizPanel;
import com.anotherbrick.inthewall.datasource.DSFilter;

public class FilterBox extends VizPanel implements TouchEnabled {

  private ArrayList<FilterRow> filterRows;

  public FilterBox(float x0, float y0, float width, float height, VizPanel parent) {
    super(x0, y0, width, height, parent);
  }

  class FilterRow extends VizPanel implements TouchEnabled {

    public FilterRow(float x0, float y0, float width, float height, VizPanel parent) {
      super(x0, y0, width, height, parent);
    }

    public String label, name;
    private ArrayList<String> values = new ArrayList<String>();

    @Override
    public boolean touch(float x, float y, boolean down, TouchTypeEnum touchType) {
      // TODO Auto-generated method stub
      return false;
    }

    @Override
    public void setup() {
      values = DSFilter.getValues(name);
    }

    @Override
    public boolean draw() {
      textAlign(PApplet.LEFT, PApplet.CENTER);
      text(label, 0, getHeight() / 2);
      return false;
    }

  }

  @Override
  public void setup() {
    filterRows = new ArrayList<FilterRow>();
    FilterRow row = new FilterRow(0, 0, 200, 30, this);
  }

  @Override
  public boolean draw() {
    return false;
  }

  @Override
  public boolean touch(float x, float y, boolean down, TouchTypeEnum touchType) {
    // TODO Auto-generated method stub
    return false;
  }

}
