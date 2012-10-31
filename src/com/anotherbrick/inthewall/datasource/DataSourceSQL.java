package com.anotherbrick.inthewall.datasource;

import java.util.ArrayList;

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

  public ArrayList<DSCrash> getCrashes(DSFilter f) {
    ArrayList<DSCrash> crashes = new ArrayList<DSCrash>();
    String query;
    if (sql.connect()) {
      query = "SELECT _year, _case, latitude, longitude FROM crashes WHERE " + f.getWhereClause();
      sql.query(query);
      createArrayFromQuery(crashes);
      return crashes;
    }
    return null;
  }

  private void createArrayFromQuery(ArrayList<DSCrash> array) {
    while (sql.next()) {
      DSCrash event = new DSCrash();
      event._year = sql.getInt(1);
      event._crash = sql.getInt(2);
      event.latitude = sql.getFloat(3);
      event.longitude = sql.getFloat(4);
      array.add(event);
    }
  }

}
