package com.anotherbrick.inthewall;

import java.util.ArrayList;

import com.anotherbrick.inthewall.datasource.DSCrash;
import com.anotherbrick.inthewall.datasource.DSFilter;
import com.anotherbrick.inthewall.datasource.DataSourceSQL;
import com.modestmaps.geo.Location;

public class Model {

  static Model instance;
  public Main p;
  public VizNotificationCenter notificationCenter;
  public float touchX;
  public float touchY;
  public float touchXZoom;
  public float touchYZoom;
  public TouchEnabled currentModalVizPanel;
  public DataSourceSQL dataSourceSQL;

  // global application variables
  public Location upperLeftLocation;
  public Location lowerRightLocation;
  public DSFilter currentFilter = new DSFilter();
  public ArrayList<DSCrash> crashes = null;
  public int currentStateCode;
  public String currentGroupField;
  public ArrayList<BarData> crashesCountForBarchart;

  public static void setup(Main p, DataSourceSQL dataSourceSQL,
      VizNotificationCenter notificationCenter) {
    Model.instance = new Model(p, dataSourceSQL, notificationCenter);
  }

  private Model(Main p, DataSourceSQL dataSourceSQL, VizNotificationCenter notificationCenter) {
    this.p = p;
    this.dataSourceSQL = dataSourceSQL;
    this.notificationCenter = notificationCenter;
  }

  public static Model getInstance() {
    return Model.instance;
  }

  public void loadFiles() {
    // TODO Auto-generated method stub

  }

}
