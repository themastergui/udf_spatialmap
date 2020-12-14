package io.lenses.sql.udf;

//import java.util.Iterator;
//import java.util.ArrayList;
//import org.junit.Test;
//import org.junit.Assert;

//
// Decompiled by Procyon v0.5.36
//

public class SpacialMap //implements UserDefinedFunction1
{
/*    @Test
    public void test_lat_long_in_bounds() {
        final Runner r = new Runner();
        Assert.assertTrue(Runner.is_valid_gps_coordinate(25.0, -82.0));
        Assert.assertTrue(Runner.is_valid_gps_coordinate(-25.0, -82.0));
        Assert.assertTrue(Runner.is_valid_gps_coordinate(25.0, 82.0));
        Assert.assertTrue(Runner.is_valid_gps_coordinate(-25.0, 82.0));
        Assert.assertTrue(Runner.is_valid_gps_coordinate(0.0, 0.0));
        Assert.assertTrue(Runner.is_valid_gps_coordinate(89.0, 179.0));
        Assert.assertTrue(Runner.is_valid_gps_coordinate(-89.0, -179.0));
        Assert.assertTrue(Runner.is_valid_gps_coordinate(89.999, 179.0));
    }

    @Test
    public void realTest_for_points_inside() {
        final ArrayList<Double> lat_array = new ArrayList<Double>();
        final ArrayList<Double> long_array = new ArrayList<Double>();
        final ArrayList<String> polygon_lat_long_pairs = new ArrayList<String>();
        polygon_lat_long_pairs.add("51.477106,-0.433617");
        polygon_lat_long_pairs.add("51.477160, -0.490501");
        polygon_lat_long_pairs.add("51.477815,-0.490608");
        polygon_lat_long_pairs.add("51.478002,-0.433359");
        for (final String s : polygon_lat_long_pairs) {
            lat_array.add(Double.parseDouble(s.split(",")[0]));
            long_array.add(Double.parseDouble(s.split(",")[1]));
        }
        final Runner r = new Runner();
        final ArrayList<String> pointsInside = new ArrayList<String>();
        pointsInside.add("51.477601,-0.445526");
        pointsInside.add("51.47855,-0.442178");
        pointsInside.add("51.477548,-0.437007");
        pointsInside.add("51.477962,-0.440526");
        System.out.println("about to check...");
        for (final String s2 : pointsInside) {
            System.out.println("Is inside polygon? : " + Runner.coordinate_is_inside_polygon(Double.parseDouble(s2.split(",")[0]), Double.parseDouble(s2.split(",")[1]), (ArrayList)lat_array, (ArrayList)long_array));
        }
    }

    @Test
    public void realTest_for_points_outside() {
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
        final Runner r = new Runner();
        final ArrayList<String> pointsOutside = new ArrayList<String>();
        pointsOutside.add("31.451159,-87.958374");
        pointsOutside.add("31.319856,-84.607544");
        pointsOutside.add("30.868282,-84.717407");
        pointsOutside.add("31.338624,-81.685181");
        pointsOutside.add("29.452991,-80.498657");
        pointsOutside.add("26.935783,-79.487915");
        pointsOutside.add("25.159207,-79.916382");
        pointsOutside.add("24.311058,-81.17981");
        pointsOutside.add("25.149263,-81.838989");
        pointsOutside.add("27.726326,-83.695679");
        pointsOutside.add("29.787263,-87.024536");
        pointsOutside.add("29.205877,-62.102052");
        pointsOutside.add("14.025751,-80.690919");
        pointsOutside.add("29.029276,-90.805666");
        pointsOutside.add("-12.606032,-70.151369");
        pointsOutside.add("-56.520716,-172.822269");
        pointsOutside.add("-75.89666,9.082024");
        pointsOutside.add("-24.078567,142.675774");
        pointsOutside.add("84.940737,177.480462");
        pointsOutside.add("47.374545,9.082024");
        pointsOutside.add("25.831538,-1.069338");
        pointsOutside.add("0,0");
        for (final String s2 : pointsOutside) {
            Assert.assertFalse(Runner.coordinate_is_inside_polygon(Double.parseDouble(s2.split(",")[0]), Double.parseDouble(s2.split(",")[1]), (ArrayList)lat_array, (ArrayList)long_array));
        }
    }*/
}
