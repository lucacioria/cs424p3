package com.example.app;

import com.anotherbrick.inthewall.TouchEnabled;
import com.anotherbrick.inthewall.VizPanel;
import com.anotherbrick.inthewall.Config.MyColorEnum;

public class BlackBox extends VizPanel implements TouchEnabled {

  public BlackBox(float x0, float y0, float width, float height, VizPanel parent) {
    super(x0, y0, width, height, parent);
  }

  @Override
  public boolean touch(float x, float y, boolean down, TouchTypeEnum touchType) {
    return true;
  }

  @Override
  public void setup() {
  }

  @Override
  public boolean draw() {
    background(MyColorEnum.BLACK);
    return false;
  }

}
