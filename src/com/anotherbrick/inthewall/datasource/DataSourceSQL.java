package com.anotherbrick.inthewall.datasource;

import java.util.ArrayList;

import com.anotherbrick.inthewall.Model;

import processing.core.PApplet;
import de.bezier.data.sql.MySQL;

public class DataSourceSQL {

  private MySQL sql;

  public DataSourceSQL(PApplet context) {
    String user = "organic";
    String pass = "sharpcheddar";
    String database = "crash";
    String host = "inacarcrash.cnrtm99w3c2x.us-east-1.rds.amazonaws.com";
    sql = new MySQL(context, host, database, user, pass);
    try {
      Class.forName("com.mysql.jdbc.Driver").newInstance();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void getCrashes(DSFilter f) {
    ArrayList<DSCrash> crashes = new ArrayList<DSCrash>();
    String query;
    if (sql.connect()) {
      // public int month;
      // public int hour;
      // public int minutes;
      // public int day_of_week;
      // public String weather;
      // public String alcohol_involved;
      // public int[] travel_speed;
      // public int[] vehicle_configuration;
      // public int[] age;
      query = "SELECT _year, _state, _case, latitude, longitude FROM crashes WHERE "
          + f.getWhereClause();
      sql.query(query);
      createArrayFromQuery(crashes);
      Model.getInstance().crashes = crashes;
      Model.getInstance().currentStateCode = crashes.get(0)._state;
    }
  }

  private void createArrayFromQuery(ArrayList<DSCrash> array) {
    while (sql.next()) {
      DSCrash event = new DSCrash();
      event._year = sql.getInt(1);
      event._state = sql.getInt(2);
      event._case = sql.getInt(3);
      event.latitude = sql.getFloat(4);
      event.longitude = sql.getFloat(5);
      array.add(event);
    }
  }

}
