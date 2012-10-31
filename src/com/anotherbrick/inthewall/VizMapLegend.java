package com.anotherbrick.inthewall;

import com.anotherbrick.inthewall.Config.MyColorEnum;

public class VizMapLegend extends VizPanel implements TouchEnabled {
private String colorFilter="";
	public VizMapLegend(float x0, float y0, float width, float height,
			VizPanel parent) {
		super(x0, y0, width, height, parent);
	}
	
public void setup(){
		
	}

	public boolean draw(){
		pushStyle();
		fill(MyColorEnum.WHITE);
		rect(0,0,getWidth(),getHeight());
		textSize(10);
		fill(MyColorEnum.BLACK);
		text("Color coding by: "+colorFilter,5,15);
		popStyle();
		return false;
	}
	
	
	
	
	
	public String getColorFilter() {
		return colorFilter;
	}

	public void setColorFilter(String colorFilter) {
		this.colorFilter = colorFilter;
	}

	public boolean touch(float x, float y, boolean down, TouchTypeEnum touchType) {
		return false;
	}
}
