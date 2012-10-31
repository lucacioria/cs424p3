package com.anotherbrick.inthewall.datasource;

import java.util.ArrayList;

public class DSFilter {
  public Float latitudeMin;
  public Float latitudeMax;
  public Float longitudeMin;
  public Float longitudeMax;

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
}
