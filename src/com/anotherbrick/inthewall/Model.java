package com.anotherbrick.inthewall;

import java.util.ArrayList;

import com.anotherbrick.inthewall.datasource.DataSourceSQL;
import com.modestmaps.geo.Location;

public class Model {

  static Model instance;
  public Main p;
  public VizNotificationCenter notificationCenter;
  public float touchX;
  public float touchY;
  public TouchEnabled currentModalVizPanel;
  public ArrayList<Object> selectedGenres = new ArrayList<Object>();
  public ArrayList<Object> selectedMonsters = new ArrayList<Object>();
  public ArrayList<Object> selectedCountries = new ArrayList<Object>();

  public VizList genrePanel;
  public VizList monsterPanel;
  public VizList countriesPanel;
  public VizList moviesList;

  public Location upperLeftLocation;
  public Location upperRightLocation;
  public Location lowerLeftLocation;
  public Location lowerRightLocation;
  
  public enum Datas {
    NUMBER_OF_MOVIES, AVERAGE_RATING, AVERAGE_BUDGET, AVERAGE_VOTES
  };

  public Datas currentDataDisplayed = Datas.NUMBER_OF_MOVIES;
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
