package com.anotherbrick.inthewall;

import processing.core.PApplet;

import com.anotherbrick.inthewall.Config.MyColorEnum;

public class VizBar extends VizPanel implements TouchEnabled {

  public VizBar(float x0, float y0, float width, float height, VizPanel parent) {
    super(x0, y0, width, height, parent);
  }

  public BarData barData;
  public float maxValue;
  public MyColorEnum barColor = MyColorEnum.LIGHT_ORANGE;
  public MyColorEnum textColor = MyColorEnum.WHITE;

  private float paddingPercentage = 0.1f;
  private float xLeft;
  private float xRight;
  private float yBottom;
  private float yTop;

  @Override
  public boolean touch(float x, float y, boolean down, TouchTypeEnum touchType) {
    log("bar touched: " + barData.label);
    return true;
  }

  @Override
  public boolean draw() {
    pushStyle();
    // set styles
    background(MyColorEnum.LIGHT_BLUE);
    fill(barColor);
    noStroke();
    // rect
    rect(xLeft, yTop, xRight - xLeft, yBottom - yTop);
    // label
    fill(textColor);
    textSize(8);
    textAlign(PApplet.CENTER, PApplet.TOP);
    text(barData.label, xLeft + (xRight - xLeft) / 2, yBottom + 3);
    //
    popStyle();
    return false;
  }

  @Override
  public void setup() {
    xLeft = getWidth() * paddingPercentage;
    xRight = getWidth() - getWidth() * paddingPercentage;
    yBottom = getHeight();
    yTop = yBottom - (barData.value / maxValue) * getHeight();
  }

}
