package com.anotherbrick.inthewall;

import com.anotherbrick.inthewall.datasource.DSFilter;
import com.anotherbrick.inthewall.datasource.DataSourceSQL;

public class Model {

  static Model instance;
  public Main p;
  public VizNotificationCenter notificationCenter;
  public float touchX;
  public float touchY;
  public TouchEnabled currentModalVizPanel;
  // Application Public Variables
  public DSFilter currentFilter = new DSFilter();

  //

  public DataSourceSQL dataSourceSQL;

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
