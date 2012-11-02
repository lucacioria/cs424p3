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
      query = "SELECT _year,_state,_case,number_of_vehicles,number_of_persons,weather,city,county,day,hour,month,crash_related_factor_1,crash_related_factor_2,crash_related_factor_3,time,day_of_week,first_harmful_event,holiday_related,latitude,longitude,light_condition,number_of_fatalities,number_of_travel_lanes,roadway_function_class,roadway_surface_condition,roadway_surface_type,route_signing,speed_limit,trafficway_flow,age,air_bag_availability,alcohol_test_result,injury_severity,person_related_factors_1,person_related_factors_2,person_related_factors_3,person_type,alcohol_involved,drug_involved,race,seating_position,sex,body_type,most_harmful_event,number_of_occupants,travel_speed,vehicle_configuration,vehicle_related_factors_1,vehicle_related_factors_2,driver_related_factors_1,driver_related_factors_2,driver_related_factors_3,driver_related_factors_4 "
          + " FROM _crashes WHERE " + f.getWhereClause();
      sql.query(query);
      createArrayFromQuery(crashes);
      Model.getInstance().crashes = crashes;
      Model.getInstance().currentStateCode = crashes.get(0)._state;
    }
  }

  private void createArrayFromQuery(ArrayList<DSCrash> array) {
    while (sql.next()) {
      DSCrash event = new DSCrash();
      event._year = sql.getInt("_year");
      event._state = sql.getInt("_state");
      event._case = sql.getInt("_case");
      event.latitude = sql.getFloat("latitude");
      event.longitude = sql.getFloat("longitude");
      event.weather = DSFilter.getValueByCode("weather", sql.getInt("weather"));
      event.number_of_fatalities = DSFilter.getIntValue("number_of_fatalities",
          sql.getInt("number_of_fatalities"));
      array.add(event);
    }
  }
}
