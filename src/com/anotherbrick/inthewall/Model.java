package com.anotherbrick.inthewall;

import java.util.ArrayList;

public class Model {

  static Model instance;
  public Main p;
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

  public enum Datas {
    NUMBER_OF_MOVIES, AVERAGE_RATING, AVERAGE_BUDGET, AVERAGE_VOTES
  };

  public Datas currentDataDisplayed = Datas.NUMBER_OF_MOVIES;

  public static void setup(Main p) {
    Model.instance = new Model(p);
  }

  private Model(Main p) {
    this.p = p;
  }

  public static Model getInstance() {
    return Model.instance;
  }

  public void loadFiles() {
    // TODO Auto-generated method stub

  }

}
