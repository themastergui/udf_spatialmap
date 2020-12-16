package io.lenses.sql.udf;

import io.lenses.sql.udf.datatype.DataType;
import io.lenses.sql.udf.value.DoubleValue;
import io.lenses.sql.udf.value.StringValue;
import io.lenses.sql.udf.value.Value;
import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SpatialMap implements UserDefinedFunction3
{
    public static double PI;
    public static double TWOPI;

    static {
        PI = 3.14159265;
        TWOPI = 2.0 * PI;
    }

    public SpatialMap(){
    }

    public static void main(final String[] args) {

        final ArrayList<Double> lat_array = new ArrayList<Double>();
        final ArrayList<Double> long_array = new ArrayList<Double>();

        JSONObject object = new JSONObject("{\"runway\":[[51.465164,-0.434132],[51.464743,-0.434099],[51.464993,-0.482352],[51.464569,-0.482352]]}");
        SpatialMap client = new SpatialMap();
        List<Point2D>  polygonsList = client.getPolygonsListFromJson(object);


        System.out.println("coordinates should be outside south runway: " + client.coordinateIsInsidePolygon(51.4649486409, -0.427777950819, polygonsList));
        System.out.println("coordinates should be inside south runway: " + client.coordinateIsInsidePolygon(51.4648884095, -0.453833976531, polygonsList));
    }

    private List<Point2D> getPolygonsListFromJson(JSONObject object)
    {
        Iterator<String> keys = object.keys();
        JSONArray jsonArray = new JSONArray();
        List<Point2D> polygonsList = new ArrayList<Point2D>();

        //For each polygon
        if(keys.hasNext()) {
            //get the name of the polygon
            String key = keys.next();
//            System.out.println("key is " + key);
            if (object.get(key) instanceof JSONArray) {
                //get the array of coordinates of the polygon
                jsonArray = object.getJSONArray(key);
                //iterate over each coordinate (point) for the polygon
                for (int i = 0; i < jsonArray.length(); i++) {
                    String s = jsonArray.getJSONArray(i).toString();
                    Point2D p = new Point2D.Double(new Double(jsonArray.getJSONArray(i).get(0).toString()), new Double(jsonArray.getJSONArray(i).get(1).toString()));
//                    System.out.println("coordinates of point are  " + p.toString());
                    polygonsList.add(p);
                }
            }
        }
        return polygonsList;
    }

    @Override
    public DataType typeMapping(DataType lat, DataType lon, DataType polygons) throws UdfException {
        if (!lat.isDouble())
            throw new UdfException("Expecting first argument to be double but found:" + lat.name);
        if (!lon.isDouble())
            throw new UdfException("Expecting second argument to be double but found:" + lon.name);


        return DataType.ltString();
    }

    @Override
    public Value evaluate(Value arg1, Value arg2, Value arg3) throws UdfException {
        double lat = getDoubleOrThrow(arg1);
        double lon = getDoubleOrThrow(arg2);
        JSONObject polygonJson = getJsonOrThrow(arg3);
        List<Point2D> polygonsList = getPolygonsListFromJson(polygonJson);

        Boolean b = coordinateIsInsidePolygon(lat, lon, polygonsList);
        return new StringValue(b.toString());
    }

    private double getDoubleOrThrow(Value value) throws UdfException {
        if (value instanceof DoubleValue) {
            return ((DoubleValue) value).get();
        }
        throw new UdfException("Invalid input received for aggregateValue. Expecting a Long but received:" + value.getDataType().name);
    }

    private JSONObject getJsonOrThrow(Value value) throws UdfException {
        try
        {
            JSONObject object = new JSONObject(value);
            return object;
        }
        catch(Exception e){
            throw new UdfException("Invalid input received for aggregateValue. Expecting a Long but received:" + value.getDataType().name);
        }

    }

    @Override
    public String name() {
        return "spatialmap";
    }

    @Override
    public String version() {
        return "1.0";
    }

    @Override
    public String owner() {
        return "LeMasterGui";
    }

    public static boolean coordinateIsInsidePolygon(final double latitude, final double longitude, final List<Point2D> polygonsList){
        double angle = 0.0;
        for (int n = polygonsList.size(), i = 0; i < n; ++i) {
            final double point1_lat = polygonsList.get(i).getX() - latitude;
            final double point1_long = polygonsList.get(i).getY() - longitude;
            final double point2_lat = polygonsList.get((i + 1) % n).getX() - latitude;
            final double point2_long = polygonsList.get((i + 1) % n).getY() - longitude;
            angle += Angle2D(point1_lat, point1_long, point2_lat, point2_long);
        }
        return Math.abs(angle) >= PI;
    }

    public static double Angle2D(final double y1, final double x1, final double y2, final double x2) {
        final double theta1 = Math.atan2(y1, x1);
        final double theta2 = Math.atan2(y2, x2);
        double dtheta;
        for (dtheta = theta2 - theta1; dtheta > PI; dtheta -= TWOPI) {}
        while (dtheta < -PI) {
            dtheta += TWOPI;
        }
        return dtheta;
    }

    public static boolean is_valid_gps_coordinate(final double latitude, final double longitude) {
        return latitude > -90.0 && latitude < 90.0 && longitude > -180.0 && longitude < 180.0;
    }

}

