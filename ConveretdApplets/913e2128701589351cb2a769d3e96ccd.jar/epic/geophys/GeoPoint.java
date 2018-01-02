// 
// Decompiled by Procyon v0.5.30
// 

package epic.geophys;

public class GeoPoint
{
    private Longitude lon;
    private Latitude lat;
    
    public GeoPoint(final double n, final double n2) {
        this.lon = new Longitude(n);
        this.lat = new Latitude(n2);
    }
    
    public GeoPoint(final GeoPoint geoPoint) {
        this.lon = new Longitude(geoPoint.Lon());
        this.lat = new Latitude(geoPoint.Lat());
    }
    
    public GeoPoint(final Longitude lon, final Latitude lat) {
        this.lon = lon;
        this.lat = lat;
    }
    
    public Latitude Lat() {
        return this.lat;
    }
    
    public Longitude Lon() {
        return this.lon;
    }
    
    public boolean equals(final Object o) {
        boolean b = false;
        if (o instanceof GeoPoint && this.lon.equals(((GeoPoint)o).Lon()) && this.lat.equals(((GeoPoint)o).Lat())) {
            b = true;
        }
        return b;
    }
    
    public String toString() {
        return "(" + this.lon.toString() + "," + this.lat.toString() + ")";
    }
    
    public String toString(final String s, final String s2) {
        return "( " + this.lon.toString(s) + ",  " + this.lat.toString(s2) + " )";
    }
}
