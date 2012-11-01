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
  private HashMap<String, ArrayList<String>> listAttributes = new HashMap<String, ArrayList<String>>();

  public DSFilter() {
    for (HashMap<String, String> h : DSFilter.getFilterNames()) {
      listAttributes.put(h.get("name"), new ArrayList<String>());
    }
  }

  public String getWhereClause() {
    ArrayList<String> a = new ArrayList<String>();
    if (latitudeMax != null) a.add("latitude < " + latitudeMax);
    if (latitudeMin != null) a.add("latitude > " + latitudeMin);
    if (longitudeMax != null) a.add("longitude < " + longitudeMax);
    if (longitudeMin != null) a.add("longitude > " + longitudeMin);
    Iterator<String> it = listAttributes.keySet().iterator();
    while (it.hasNext()) {
      String name = it.next();
      ArrayList<String> values = listAttributes.get(name);
      if (values.size() > 0) {
        String codes = getCodesByName(name, values);
        a.add(name + " IN (" + codes + ")");
      }
    }
    return join(a, " AND ");
  }

  private String getCodesByName(String name, ArrayList<String> values) {
    HashMap<String, ArrayList<Integer>> mappings = getMapping(name);
    ArrayList<String> out = new ArrayList<String>();
    for (String value : values) {
      ArrayList<Integer> codes = mappings.get(value);
      out.add(join(codes, ", "));
    }
    return join(out, ", ");
  }

  public void setAttributeWithList(String name, ArrayList<? extends Object> list) {
    ArrayList<String> attributeValues = listAttributes.get(name);
    attributeValues.clear();
    for (Object o : list) {
      attributeValues.add(o.toString());
    }
  }

  public ArrayList<String> getAttributeValues(String name) {
    return listAttributes.get(name);
  }

  private String join(ArrayList<? extends Object> a, String separator) {
    String out = "";
    if (a.size() == 0) {
      return "";
    }
    for (Object s : a) {
      out += s.toString() + separator;
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

  public static ArrayList<HashMap<String, String>> getFilterNames() {
    ArrayList<HashMap<String, String>> filterNames = new ArrayList<HashMap<String, String>>();
    try {
      JSONObject j = getJsonFile("_filters");
      JSONArray lines = j.getJSONArray("lines");
      for (int i = 0; i < lines.length(); i++) {
        JSONObject line = lines.getJSONObject(i);
        HashMap<String, String> val = new HashMap<String, String>();
        val.put("name", line.getString("name"));
        val.put("label", line.getString("label"));
        val.put("select", line.getString("select"));
        filterNames.add(val);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return filterNames;
  }

  public static String getLabelByName(String name) {
    for (HashMap<String, String> h : getFilterNames()) {
      if (h.get("name").equals(name)) return h.get("label");
    }
    return null;
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
