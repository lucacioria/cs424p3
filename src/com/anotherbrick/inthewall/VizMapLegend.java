package com.anotherbrick.inthewall;

import com.anotherbrick.inthewall.Config.MyColorEnum;
import com.anotherbrick.inthewall.TouchEnabled.TouchTypeEnum;

public class VizMapLegend extends VizPanel implements TouchEnabled {

	public VizMapLegend(float x0, float y0, float width, float height,
			VizPanel parent) {
		super(x0, y0, width, height, parent);
		this.parent = parent;
	}
	
public void setup(){
		
	}

	public boolean draw(){
		pushStyle();
		fill(MyColorEnum.WHITE);
		rect(getX0(),getY0(),getWidth(),getHeight());
		popStyle();
		return false;
	}
	
	
	
	
	
	public boolean touch(float x, float y, boolean down, TouchTypeEnum touchType) {
		return false;
	}
}
