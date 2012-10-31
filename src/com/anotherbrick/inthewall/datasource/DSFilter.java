package com.anotherbrick.inthewall.datasource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import us.monoid.json.JSONArray;
import us.monoid.json.JSONException;
import us.monoid.json.JSONObject;

import com.anotherbrick.inthewall.Config;
import com.anotherbrick.inthewall.Helper;

public class DSFilter {
  public Float latitudeMin;
  public Float latitudeMax;
  public Float longitudeMin;
  public Float longitudeMax;
  public String weather;

  public ArrayList<String> weatherVals = new ArrayList<String>();

  public String getWhereClause() {
    ArrayList<String> a = new ArrayList<String>();
    if (latitudeMax != null) a.add("latitude < " + latitudeMax);
    if (latitudeMin != null) a.add("latitude > " + latitudeMin);
    if (longitudeMax != null) a.add("longitude < " + longitudeMax);
    if (longitudeMin != null) a.add("longitude > " + longitudeMin);
    return join(a, " AND ");
  }

  private String join(ArrayList<String> a, String separator) {
    String out = "";
    for (String s : a) {
      out += s + separator;
    }
    return out.substring(0, out.length() - separator.length());
  }

  public static ArrayList<String> getValues(String name) {
    ArrayList<String> o = new ArrayList<String>();
    try {
      JSONObject j = getJsonFile(name);
      Iterator<String> i = j.keys();
      while (i.hasNext()) {
        String key = i.next();
        o.add(key);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return o;
  }

  private static HashMap<String, ArrayList<Integer>> getMapping(String name) {
    HashMap<String, ArrayList<Integer>> o = new HashMap<String, ArrayList<Integer>>();
    try {
      JSONObject j = getJsonFile(name);
      Iterator<String> i = j.keys();
      while (i.hasNext()) {
        String key = i.next();
        ArrayList<Integer> list = new ArrayList<Integer>();
        JSONArray ja = j.getJSONArray(key);
        for (int k = 0; k < ja.length(); k++) {
          list.add(ja.getInt(k));
        }
        o.put(key, list);
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return o;
  }

  private static JSONObject getJsonFile(String name) {
    JSONObject obj = null;
    try {
      String configString = getFileContents("mappings/" + name + ".json");
      obj = new JSONObject(configString);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return obj;
  }

  private static String getFileContents(String filename) {
    try {
      return Helper.readFile(Config.getInstance().path + filename);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}
