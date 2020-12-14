package io.lenses.sql.udf;

import java.util.Iterator;
import java.util.ArrayList;

//
// Decompiled by Procyon v0.5.36
//

public class Runner
{
    public static double PI;
    public static double TWOPI;

    static {
        Runner.PI = 3.14159265;
        Runner.TWOPI = 2.0 * Runner.PI;
    }

    public static void main(final String[] args) {
        final ArrayList<Double> lat_array = new ArrayList<Double>();
        final ArrayList<Double> long_array = new ArrayList<Double>();
        final ArrayList<String> polygon_lat_long_pairs = new ArrayList<String>();
        polygon_lat_long_pairs.add("31.000213,-87.584839");
        polygon_lat_long_pairs.add("31.009629,-85.003052");
        polygon_lat_long_pairs.add("30.726726,-84.838257");
        polygon_lat_long_pairs.add("30.584962,-82.168579");
        polygon_lat_long_pairs.add("30.73617,-81.476441");
        polygon_lat_long_pairs.add("29.002375,-80.795288");
        polygon_lat_long_pairs.add("26.896598,-79.938355");
        polygon_lat_long_pairs.add("25.813738,-80.059204");
        polygon_lat_long_pairs.add("24.93028,-80.454712");
        polygon_lat_long_pairs.add("24.401135,-81.817017");
        polygon_lat_long_pairs.add("24.700927,-81.959839");
        polygon_lat_long_pairs.add("24.950203,-81.124878");
        polygon_lat_long_pairs.add("26.0015,-82.014771");
        polygon_lat_long_pairs.add("27.833247,-83.014527");
        polygon_lat_long_pairs.add("28.8389,-82.871704");
        polygon_lat_long_pairs.add("29.987293,-84.091187");
        polygon_lat_long_pairs.add("29.539053,-85.134888");
        polygon_lat_long_pairs.add("30.272352,-86.47522");
        polygon_lat_long_pairs.add("30.281839,-87.628784");
        for (final String s : polygon_lat_long_pairs) {
            lat_array.add(Double.parseDouble(s.split(",")[0]));
            long_array.add(Double.parseDouble(s.split(",")[1]));
        }
        System.out.println(coordinate_is_inside_polygon(31.000213, -87.584839, lat_array, long_array));
        System.out.println(coordinate_is_inside_polygon(25.831538, -1.069338, lat_array, long_array));
    }

    public static boolean coordinate_is_inside_polygon(final double latitude, final double longitude, final ArrayList<Double> lat_array, final ArrayList<Double> long_array) {
        double angle = 0.0;
        for (int n = lat_array.size(), i = 0; i < n; ++i) {
            final double point1_lat = lat_array.get(i) - latitude;
            final double point1_long = long_array.get(i) - longitude;
            final double point2_lat = lat_array.get((i + 1) % n) - latitude;
            final double point2_long = long_array.get((i + 1) % n) - longitude;
            angle += Angle2D(point1_lat, point1_long, point2_lat, point2_long);
        }
        return Math.abs(angle) >= Runner.PI;
    }

    public static boolean coordinate_is_inside_polygon(final double latitude, final double longitude, final ArrayList<String> latLongArray) {
        final ArrayList<Double> lat_array = new ArrayList<Double>();
        final ArrayList<Double> long_array = new ArrayList<Double>();
        for (final String s : latLongArray) {
            lat_array.add(Double.parseDouble(s.split(",")[0]));
            long_array.add(Double.parseDouble(s.split(",")[1]));
        }
        double angle = 0.0;
        for (int n = lat_array.size(), i = 0; i < n; ++i) {
            final double point1_lat = lat_array.get(i) - latitude;
            final double point1_long = long_array.get(i) - longitude;
            final double point2_lat = lat_array.get((i + 1) % n) - latitude;
            final double point2_long = long_array.get((i + 1) % n) - longitude;
            angle += Angle2D(point1_lat, point1_long, point2_lat, point2_long);
        }
        return Math.abs(angle) >= Runner.PI;
    }

    public static double Angle2D(final double y1, final double x1, final double y2, final double x2) {
        final double theta1 = Math.atan2(y1, x1);
        final double theta2 = Math.atan2(y2, x2);
        double dtheta;
        for (dtheta = theta2 - theta1; dtheta > Runner.PI; dtheta -= Runner.TWOPI) {}
        while (dtheta < -Runner.PI) {
            dtheta += Runner.TWOPI;
        }
        return dtheta;
    }

    public static boolean is_valid_gps_coordinate(final double latitude, final double longitude) {
        return latitude > -90.0 && latitude < 90.0 && longitude > -180.0 && longitude < 180.0;
    }
}
