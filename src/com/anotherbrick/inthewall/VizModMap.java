package com.anotherbrick.inthewall;

import java.util.ArrayList;

import processing.core.PVector;

import com.anotherbrick.inthewall.Config.MyColorEnum;
import com.anotherbrick.inthewall.VizNotificationCenter.EventName;
import com.anotherbrick.inthewall.datasource.DSCrash;
import com.modestmaps.InteractiveMap;
import com.modestmaps.core.Point2f;
import com.modestmaps.geo.Location;
import com.modestmaps.providers.Microsoft;

public class VizModMap extends VizPanel implements TouchEnabled, EventSubscriber {
  private InteractiveMap map;
  private PVector mapOffset;
  private PVector mapSize;
  private PVector firstTouch = new PVector(0, 0);
  private ArrayList<DSCrash> accidents = new ArrayList<DSCrash>();
  private boolean mapTouched;
  long lastTouchTime;
  private VizMapLegend legend;
  private int numberOfClusters=2;
  private ArrayList<Cluster> clusters=new ArrayList<Cluster>();

  private String colorFilter = "alcohol_involved";

  private VizButton zoomInButton;
  private VizButton zoomOutButton;

  public VizModMap(float x0, float y0, float width, float height, VizPanel parent) {
    super(x0, y0, width, height, parent);
    this.parent = parent;
  }

  @Override
  public void setup() {

    m.notificationCenter.registerToEvent(EventName.CRASHES_UPDATED, this);
    m.notificationCenter.registerToEvent(EventName.BUTTON_TOUCHED, this);

    mapOffset = new PVector(0, 0);
    mapSize = new PVector(getWidthZoom(), getHeightZoom());

    map = new InteractiveMap(m.p, new Microsoft.RoadProvider(), getX0AbsoluteZoom(),
        getY0AbsoluteZoom(), mapSize.x, mapSize.y);
    centerAndZoomOnState(17);
    legend = new VizMapLegend(0, 0, getWidth(), getHeight() * 0.2f, this);
    legend.setColorFilter(colorFilter);
    legend.setup();

    updateCorners();
  }

  private void centerAndZoomOnState(int stateCode) {
    float[] array = focusOnState(stateCode);
    int zoom = (int) array[2];
    if (c.multiply == 2) {
      zoom += 1;
    } else if (c.multiply == 6) {
      zoom += 2;
    }
    map.setCenterZoom(new Location(array[0], array[1]), zoom);
  }

  @Override
  public boolean draw() {

    manageDrag();
    pushStyle();

    background(MyColorEnum.BLACK);

    map.draw();
    // map.setCenterZoom(new Location(array[0], array[1]), (int) array[2]);
    noFill();
    stroke(MyColorEnum.RED);
    strokeWeight(2);
    rect(mapOffset.x, mapOffset.y, mapSize.x, mapSize.y);
    drawAccidents(accidents);

    drawClusterGrid();
    legend.draw();
    updateClusters();
    drawClusters();
    popStyle();

    return false;
  }

  @Override
  public boolean touch(float x, float y, boolean down, TouchTypeEnum touchType) {
    if (down) {

      // manageAccidentPopup();

      firstTouch = new PVector(x, y);
      mapTouched = true;
      // if double touch, then zoom
      if ((System.currentTimeMillis() - lastTouchTime) < 1000) {
        map.setZoom(map.getZoom() + 1);
        Point2f center = new Point2f(x * c.multiply, y * c.multiply);
        map.setCenter(map.pointLocation(center));
        lastTouchTime = 0;
        updateCorners();
      }
      lastTouchTime = System.currentTimeMillis();
      updateCorners();

      return true;
    } else {
      mapTouched = false;
      return false;
    }
  }

  public void drawAccidents(ArrayList<DSCrash> accidents) {
    for (DSCrash accident : accidents) {
      Location location = new Location(accident.latitude, accident.longitude);
      Point2f p = map.locationPoint(location);
      p.x /= c.multiply;
      p.y /= c.multiply;
      fill(colorBy(colorFilter, accident));
      // fill(MyColorEnum.BLACK,100);
      stroke(MyColorEnum.BLACK);
      if (location.lon > m.upperLeftLocation.lon) {
        ellipse(p.x - getX0(), p.y - getY0(), 10, 10);
        if (accident.selected) {
          popUp(accident, p.x - getX0(), p.y - getY0());
        }
      }
    }
  }

  private void popUp(DSCrash accident, float ics, float ips) {
    fill(MyColorEnum.RED);
    rect(ics, ips, 100, 40);
    fill(255);
    textSize(6);
    // text("latitude: " + accident.latitude, ics + 5, ips + 7);
    // text("longitude: "+ accident.longitude, ics + 5, ips + 7+7);
    text("Weather: " + accident.weather, ics + 5, ips + 7);
    text("Alcohol involved: " + accident.alcohol_involved, ics + 5, ips + 7 + 7 * 1);

  }

  public void manageAccidentPopup() {
    for (DSCrash accident : accidents) {

      float ics = (map.locationPoint(new Location(accident.latitude, accident.longitude))).x;
      float ips = (map.locationPoint(new Location(accident.latitude, accident.longitude))).y;

      if (dist(m.touchX, m.touchY, ics, ips) < accident.dimension) {
        accident.selected = !accident.selected;
        break;
      }
    }
  }

  public void manageDrag() {
    if (mapTouched) {
      if (m.touchX != firstTouch.x && m.touchY != firstTouch.y && m.touchX != 0 && m.touchY != 0) {
        map.tx += (m.touchX - firstTouch.x) * c.multiply / map.sc;
        map.ty += (m.touchY - firstTouch.y) * c.multiply / map.sc;
        firstTouch = new PVector(m.touchX, m.touchY);
      }
      updateCorners();
    }
  }

  public void setProvider(int i) {
    if (i == 1) {
      map.setMapProvider(new Microsoft.RoadProvider());
    } else if (i == 2) {
      map.setMapProvider(new Microsoft.AerialProvider());
    } else if (i == 3) {
      map.setMapProvider(new Microsoft.HybridProvider());
    }
  }

  public float[] focusOnState(int i) {

    if (i == 1) {
      return new float[] { 33.027088f, -86.616211f, 6 };
    } else if (i == 2) {
      return new float[] { 64.54844f, -139.945312f, 3 };
    } else if (i == 4) {
      return new float[] { 34.361576f, -111.665039f, 6 };
    } else if (i == 5) {
      return new float[] { 34.849875f, -92.197266f, 6 };
    } else if (i == 6) {
      return new float[] { 37.834557f, -120.014648f, 5 };
    } else if (i == 8) {
      return new float[] { 38.925229f, -105.724805f, 6 };
    } else if (i == 9) {
      return new float[] { 41.590797f, -72.597656f, 8 };
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
      return new float[] { 38.634036f, -98.239746f, 6 };
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

  public ArrayList<DSCrash> getAccidents() {
    return accidents;
  }

  public void setAccidents(ArrayList<DSCrash> accidents) {
    this.accidents = accidents;
  }

  public void updateCorners() {
    m.upperLeftLocation = map.pointLocation(getX0AbsoluteZoom(), getY0AbsoluteZoom());
    m.lowerRightLocation = map.pointLocation(getX0AbsoluteZoom() + getWidthZoom(),
        getY0AbsoluteZoom() + getHeightZoom());
  }

  public void drawClusterGrid() {
   
    int clusterLevel = (int)getWidth()/numberOfClusters;
    for (int i = 0; i < getWidth(); i++) {
      if (i % clusterLevel == 0) {
        fill(MyColorEnum.RED);
        line(0, i, getWidth(), i);
      }
    }
    for (int j = 0; j < getWidth(); j++) {
      if (j % clusterLevel == 0) {
        fill(MyColorEnum.RED);
        line(+j, 0, j, getHeight());

      }
    }
  }
  
  public void updateClusters(){
    clusters.clear();
    int clusterLevel = (int)getWidth()/numberOfClusters;
    for (int i = 0; i < getWidth(); i++) {
      for (int j = 0; j < getWidth(); j++) {
      if (i % clusterLevel == 0 && j % clusterLevel == 0) {
        fill(MyColorEnum.RED);
       // ellipse(i+clusterLevel/2,j+clusterLevel/2,20,20);
        clusters.add(new Cluster(new Point2f(i+clusterLevel/2,j+clusterLevel/2)));
      }
    }}
   
      }
  
  public void drawClusters(){
    for(Cluster cluster: clusters){
      fill(MyColorEnum.RED);
      ellipse(cluster.getCenter().x, cluster.getCenter().y, 20, 20);
    }
  }
    

  public MyColorEnum colorBy(String filter, DSCrash crash) {
    if (filter.equals("alcohol_involved")) {
      if (crash.alcohol_involved.equals("no")) {
        return legend.getLegendColors().get(0);
      } else if (crash.alcohol_involved.equals("yes")) {
        return legend.getLegendColors().get(1);
      }
    } else if (filter.equals("drug_involved")) {
      if (crash.drug_involved.equals("no")) {
        return legend.getLegendColors().get(0);
      } else if (crash.drug_involved.equals("yes")) {
        return legend.getLegendColors().get(1);
      }
    } else if (filter.equals("weather")) {
      if (crash.weather.equals("sunny")) {
        return legend.getLegendColors().get(0);
      } else if (crash.weather.equals("cloudy")) {
        return legend.getLegendColors().get(1);
      } else if (crash.weather.equals("rainy") || crash.weather.equals("hail")) {
        return legend.getLegendColors().get(2);
      } else if (crash.weather.equals("snow")) {
        return legend.getLegendColors().get(3);
      } else if (crash.weather.equals("fog") || crash.weather.equals("windy")) {
        return legend.getLegendColors().get(4);
      }
    } else if (filter.equals("number_of_fatalities")) {

      if (crash.number_of_fatalities == 1) {
        return legend.getLegendColors().get(0);
      } else if (crash.number_of_fatalities == 2) {
        return legend.getLegendColors().get(1);
      } else if (crash.number_of_fatalities >= 3) {
        return legend.getLegendColors().get(2);
      }

    }
    return MyColorEnum.BLACK;
  }

  @Override
  public void eventReceived(EventName eventName, Object data) {
    if (eventName == EventName.CRASHES_UPDATED) {
      setAccidents(m.crashes);
      centerAndZoomOnState(m.currentStateCode);
    }
    if (eventName == EventName.BUTTON_TOUCHED) {
      if (data.toString().equals("zoomInButton")) {
        map.setZoom(map.getZoom() + 1);
      } else if (data.toString().equals("zoomOutButton")) {
        map.setZoom(map.getZoom() - 1);
      }
    }
  }
}
