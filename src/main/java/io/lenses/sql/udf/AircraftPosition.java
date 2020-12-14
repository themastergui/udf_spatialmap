package io.lenses.sql.udf;

import io.lenses.sql.udf.datatype.DataType;
import io.lenses.sql.udf.value.DoubleValue;
import io.lenses.sql.udf.value.StringValue;
import io.lenses.sql.udf.value.Value;

import java.util.ArrayList;

public class AircraftPosition implements UserDefinedFunction2
{
    public static double PI;
    public static double TWOPI;

    static {
        Runner.PI = 3.14159265;
        Runner.TWOPI = 2.0 * Runner.PI;
    }

    static private ArrayList<String> heathrowSouthRunway;

    public AircraftPosition(){
        heathrowSouthRunway = new ArrayList<String>();
        heathrowSouthRunway.add("51.465164,-0.434132");
        heathrowSouthRunway.add("51.464743,-0.434099");
        heathrowSouthRunway.add("51.464993,-0.482352");
        heathrowSouthRunway.add("51.464569,-0.482352");
    }

    public static void main(final String[] args) {

        final ArrayList<Double> lat_array = new ArrayList<Double>();
        final ArrayList<Double> long_array = new ArrayList<Double>();


        final ArrayList<String> heathrowSouthRunway = new ArrayList<String>();
        heathrowSouthRunway.add("51.465164,-0.434132");
        heathrowSouthRunway.add("51.464743,-0.434099");
        heathrowSouthRunway.add("51.464993,-0.482352");
        heathrowSouthRunway.add("51.464569,-0.482352");



        for (final String s : heathrowSouthRunway) {
            lat_array.add(Double.parseDouble(s.split(",")[0]));
            long_array.add(Double.parseDouble(s.split(",")[1]));
        }
        AircraftPosition runner = new AircraftPosition();

 //       51.4648884095,-0.453833976531

//        51.4649486409,-0.427777950819
        System.out.println("coordinates should be outside south runway: " + runner.coordinate_is_inside_polygon(51.4649486409, -0.427777950819, lat_array, long_array));
        System.out.println("coordinates should be inside south runway: " + runner.coordinate_is_inside_polygon(51.4648884095, -0.453833976531, lat_array, long_array));
//        System.out.println(runner.coordinate_is_inside_polygon(31.000213, -87.584839, lat_array, long_array));
//        System.out.println(runner.coordinate_is_inside_polygon(25.831538, -1.069338, lat_array, long_array));
    }

/*    public static String realTest_for_points_inside(final Double lat, final Double lng) {
        final ArrayList<Double> lat_array = new ArrayList<Double>();
        final ArrayList<Double> long_array = new ArrayList<Double>();
        final ArrayList<String> heathrowNorthRunway = new ArrayList<String>();
        final ArrayList<String> heathrowSouthRunway = new ArrayList<String>();
        final ArrayList<String> heathrowSouthTaxi = new ArrayList<String>();
        final ArrayList<String> heathrowNorthTaxi = new ArrayList<String>();
        heathrowNorthRunway.add("51.477106,-0.433617");
        heathrowNorthRunway.add("51.477160, -0.490501");
        heathrowNorthRunway.add("51.477815,-0.490608");
        heathrowNorthRunway.add("51.478002,-0.433359");
        heathrowSouthRunway.add("51.465164,-0.434132");
        heathrowSouthRunway.add("51.464743,-0.434099");
        heathrowSouthRunway.add("51.464993,-0.482352");
        heathrowSouthRunway.add("51.464569,-0.482352");
        heathrowSouthTaxi.add("51.465184,-0.434175");
        heathrowSouthTaxi.add("51.46775,-0.434217");
        heathrowSouthTaxi.add("51.46759,-0.487518");
        heathrowSouthTaxi.add("51.464997,-0.487261");
        heathrowNorthTaxi.add("51.477133,-0.49063");
        heathrowNorthTaxi.add("51.474741,-0.489686");
        heathrowNorthTaxi.add("51.474527,-0.433509");
        heathrowNorthTaxi.add("51.477374,-0.433295");

        final ArrayList<String> pointsInside = new ArrayList<String>();
        pointsInside.add("51.477601,-0.445526");
        pointsInside.add("51.47855,-0.442178");
        pointsInside.add("51.477548,-0.437007");
        pointsInside.add("51.477962,-0.440526");
        String positions = "";
        positions = (coordinate_is_inside_polygon((double)lat, (double)lng, (ArrayList)heathrowNorthRunway) ? (String.valueOf(positions) + ",heathrowNorthRunway") : positions);
        positions = (coordinate_is_inside_polygon((double)lat, (double)lng, (ArrayList)heathrowSouthRunway) ? (String.valueOf(positions) + ",heathrowSouthRunway") : positions);
        positions = (coordinate_is_inside_polygon((double)lat, (double)lng, (ArrayList)heathrowSouthTaxi) ? (String.valueOf(positions) + ",heathrowSouthTaxi") : positions);
        positions = (coordinate_is_inside_polygon((double)lat, (double)lng, (ArrayList)heathrowNorthTaxi) ? (String.valueOf(positions) + ",heathrowNorthTaxi") : positions);
        return positions;
    }*/

    @Override
    public DataType typeMapping(DataType lat, DataType lon) throws UdfException {
        if (!lat.isDouble())
            throw new UdfException("Expecting first argument to be double but found:" + lat.name);
        if (!lon.isDouble())
            throw new UdfException("Expecting second argument to be double but found:" + lon.name);

//        System.out.println(realTest_for_points_inside(new Double(lat.toString()), new Double(lon.toString())));

        return DataType.ltString();
    }

    @Override
    public Value evaluate(Value arg1, Value arg2) throws UdfException {
        double lat = getDoubleOrThrow(arg1);
        double lon = getDoubleOrThrow(arg2);
        final ArrayList<Double> lat_array = new ArrayList<Double>();
        final ArrayList<Double> long_array = new ArrayList<Double>();

        for (final String s : heathrowSouthRunway) {
            lat_array.add(Double.parseDouble(s.split(",")[0]));
            long_array.add(Double.parseDouble(s.split(",")[1]));
        }

        Boolean b = coordinate_is_inside_polygon(lat, lon, lat_array, long_array);
        //false
//        Boolean b = coordinate_is_inside_polygon(51.4649486409, -0.427777950819, lat_array, long_array);
        //true
        //coordinate_is_inside_polygon(51.4648884095, -0.453833976531, lat_array, long_array));


        //return new StringValue(ch.hsr.geohash.GeoHash.geoHashStringWithCharacterPrecision(lat, lon, 8));
        return new StringValue(b.toString());
    }

    private double getDoubleOrThrow(Value value) throws UdfException {
        if (value instanceof DoubleValue) {
            return ((DoubleValue) value).get();
        }
        throw new UdfException("Invalid input received for aggregateValue. Expecting a Long but received:" + value.getDataType().name);
    }

    @Override
    public String name() {
        return "spacialmap";
    }

    @Override
    public String version() {
        return "1.0";
    }

    @Override
    public String owner() {
        return "LeMasterGui";
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

