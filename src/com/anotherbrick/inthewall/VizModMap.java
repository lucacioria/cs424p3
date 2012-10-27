package com.anotherbrick.inthewall;

import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import processing.core.PVector;

import com.anotherbrick.inthewall.Config.MyColorEnum;
import com.anotherbrick.inthewall.TouchEnabled.TouchTypeEnum;
import com.anotherbrick.inthewall.datasource.DSAccident;
import com.modestmaps.InteractiveMap;
import com.modestmaps.core.Point2f;
import com.modestmaps.geo.Location;
import com.modestmaps.providers.Microsoft;

public class VizModMap extends VizPanel implements TouchEnabled {
	private int index = 0;
	private InteractiveMap map;
	private PVector mapOffset;
	private PVector mapSize;
	
	private ArrayList<DSAccident> accidents=new ArrayList<DSAccident>();
	//private ArrayList<>

	public VizModMap(float x0, float y0, float width, float height,
			VizPanel parent) {
		super(x0, y0, width, height, parent);
		this.parent = parent;
	}

	@Override
	public void setup() {

		mapOffset = new PVector(0, 0);
		mapSize = new PVector(getWidth(), getHeight());

		map = new InteractiveMap(m.p, new Microsoft.RoadProvider(), getX0(),
				getY0(), mapSize.x, mapSize.y);
		float[] Illinois=focusOnState(17);
		map.setCenterZoom(new Location(Illinois[0], Illinois[1]), (int)Illinois[2]);
		
		accidents.add(new DSAccident(41.878114f,-87.629798f));
		

	}

	@Override
	public boolean draw() {
		pushStyle();

		background(MyColorEnum.BLACK);

		map.draw();
		float[] array = focusOnState(index);
		//map.setCenterZoom(new Location(array[0], array[1]), (int) array[2]);
		noFill();
		stroke(MyColorEnum.RED);
		strokeWeight(2);
		rect(mapOffset.x, mapOffset.y, mapSize.x, mapSize.y);
		drawAccidents(accidents);
		popStyle();
		return false;
	}

	@Override
	public boolean touch(float x, float y, boolean down, TouchTypeEnum touchType) {
		index++;
		return true;

	}

	public void drawAccidents(ArrayList <DSAccident> accidents){
		for(DSAccident accident: accidents){
			Location location=new Location(accident.getLatitude(), accident.getLongitude());
			Point2f p=map.locationPoint(location);
			
			fill(MyColorEnum.BLACK);
			stroke(MyColorEnum.RED);
			ellipse(p.x-getX0(), p.y-getY0(), 15, 15);
			println(p.x+" "+p.y);
			
		}
	}
	
	public float[] focusOnState(int i) {

		if (i == 1) {
			return new float[] { 33.027088f, -86.616211f, 6 };
		} else if (i == 2) {
			return new float[] { 64.54844f,-139.945312f, 3 };
		} else if (i == 4) {
			return new float[] { 34.361576f, -111.665039f, 6 };
		} else if (i == 5) {
			return new float[] { 34.849875f, -92.197266f, 6 };
		} else if (i == 6) {
			return new float[] { 37.834557f, -120.014648f, 5 };
		} else if (i == 8) {
			return new float[] { 38.925229f, -105.724805f, 6 };
		} else if (i == 9) {
			return new float[] { 41.590797f,-72.597656f, 8 };
		} else if (i == 10) {
			return new float[] { 39.095963f, -75.454102f, 8 };
		} else if (i == 11) {
			return new float[] { 38.895112f, -77.036366f, 6 };
		} else if (i == 12) {
			return new float[] { 28.116767f, -83.518555f, 6 };
		} else if (i == 13) {
			return new float[] { 32.953368f, -83.320312f, 6 };
		} else if (i == 15) {
			return new float[] { 20.704739f, -157.137451f, 6 };
		} else if (i == 16) {
			return new float[] { 45.614037f, -115.290527f, 5 };
		} else if (i == 17) {
			return new float[] { 39.833125f, -89.398528f, 6 };
		} else if (i == 18) {
			return new float[] { 40.069665f, -86.033936f, 6 };
		} else if (i == 19) {
			return new float[] { 42.098222f, -93.405762f, 6 };
		} else if (i == 20) {
			return new float[] { 38.634036f, -98.239746f, 6};
		} else if (i == 21) {
			return new float[] { 37.544577f, -84.737549f, 6 };
		} else if (i == 22) {
			return new float[] { 31.344254f, -92.307129f, 6 };
		} else if (i == 23) {
			return new float[] { 45.483244f, -69.071045f, 6 };
		} else if (i == 24) {
			return new float[] { 38.967951f, -76.508789f, 6 };
		} else if (i == 25) {
			return new float[] { 42.366662f, -72.004395f, 7 };
		} else if (i == 26) {
			return new float[] { 43.786958f, -84.550781f, 6 };
		} else if (i == 27) {
			return new float[] { 46.407564f, -94.262695f, 6 };
		} else if (i == 28) {
			return new float[] { 32.657876f, -89.472656f, 6 };
		} else if (i == 29) {
			return new float[] { 38.565348f, -92.504883f, 6 };
		} else if (i == 30) {
			return new float[] { 47.010226f, -109.775391f, 5 };
		} else if (i == 31) {
			return new float[] { 41.492121f, -99.558105f, 6 };
		} else if (i == 32) {
			return new float[] { 38.80547f, -116.71875f, 5 };
		} else if (i == 33) {
			return new float[] { 43.866218f, -71.520996f, 7 };
		} else if (i == 34) {
			return new float[] { 40.33817f, -74.498291f, 7 };
		} else if (i == 35) {
			return new float[] { 34.51994f, -105.87009f, 6 };
		} else if (i == 36) {
			return new float[] { 42.90816f, -75.981445f, 6 };
		} else if (i == 37) {
			return new float[] { 35.496456f, -78.09082f, 6 };
		} else if (i == 38) {
			return new float[] { 47.551493f, -101.002012f, 6 };
		} else if (i == 39) {
			return new float[] { 40.417287f, -82.907123f, 6 };
		} else if (i == 40) {
			return new float[] { 35.407752f, -98.492877f, 6 };
		} else if (i == 41) {
			return new float[] { 43.804133f, -120.554201f, 6 };
		} else if (i == 42) {
			return new float[] { 41.203322f, -77.194525f, 6 };
		} else if (i == 44) {
			return new float[] { 41.580095f, -71.477429f, 8 };
		} else if (i == 45) {
			return new float[] { 33.836081f, -81.163724f, 6 };
		} else if (i == 46) {
			return new float[] { 44.569515f, -100.501813f, 6 };
		} else if (i == 47) {
			return new float[] { 35.517491f, -86.580447f, 6 };
		} else if (i == 48) {
			return new float[] { 31.968599f, -99.901813f, 5 };
		} else if (i == 49) {
			return new float[] { 39.32098f, -111.693731f, 6 };
		} else if (i == 50) {
			return new float[] { 43.958803f, -72.577841f, 7 };
		} else if (i == 51) {
			return new float[] { 37.431573f, -80.156894f, 6 };
		} else if (i == 53) {
			return new float[] { 47.751074f, -120.740139f, 6 };
		} else if (i == 54) {
			return new float[] { 39.697626f, -80.454903f, 6 };
		} else if (i == 55) {
			return new float[] { 44.78444f, -89.787868f, 6 };
		} else if (i == 56) {
			return new float[] { 43.075968f, -107.690284f, 6 };
		} else
			return new float[] { 0, 0, 0 };

	}

	public ArrayList<DSAccident> getAccidents() {
		return accidents;
	}

	public void setAccidents(ArrayList<DSAccident> accidents) {
		this.accidents = accidents;
	}

}
