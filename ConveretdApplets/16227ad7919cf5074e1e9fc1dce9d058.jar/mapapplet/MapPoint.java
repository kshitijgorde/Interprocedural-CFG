// 
// Decompiled by Procyon v0.5.30
// 

package mapapplet;

public class MapPoint
{
    private int x;
    private int y;
    private float lat;
    private float lon;
    
    public MapPoint(final float lat, final float lon) {
        this.x = Integer.MAX_VALUE;
        this.y = Integer.MAX_VALUE;
        this.lat = Float.NaN;
        this.lon = Float.NaN;
        this.lat = lat;
        this.lon = lon;
    }
    
    public MapPoint() {
        this.x = Integer.MAX_VALUE;
        this.y = Integer.MAX_VALUE;
        this.lat = Float.NaN;
        this.lon = Float.NaN;
        this.lat = 0.0f;
        this.lon = 0.0f;
    }
    
    public float getLat() {
        return this.lat;
    }
    
    public void setLat(final float lat) {
        this.lat = lat;
    }
    
    public float getLon() {
        return this.lon;
    }
    
    public float getNormalizedLon() {
        if (this.lon < -180.0f) {
            return this.lon + 360.0f;
        }
        if (this.lon > 180.0f) {
            return this.lon - 360.0f;
        }
        return this.lon;
    }
    
    public void setLon(final float lon) {
        this.lon = lon;
    }
    
    public int getX() {
        return this.x;
    }
    
    public void setX(final int x) {
        this.x = x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public void setY(final int y) {
        this.y = y;
    }
    
    public String toString() {
        return new String("lat=" + this.lat + "; lon=" + this.lon);
    }
    
    public void calculateXY(final MapProjection prj) {
        final float[] xy = prj.ll2xy(this.getLat(), this.getLon());
        this.setX((int)xy[0]);
        this.setY((int)xy[1]);
    }
    
    public void calculateLL(final MapProjection prj) {
        final float[] ll = prj.xy2ll(this.getX(), this.getY());
        this.setLat(ll[0]);
        this.setLon(ll[1]);
    }
    
    public String getPixelKey() {
        return new String(this.x + " " + this.y);
    }
    
    public void normalizeSNGeoCoords(final MapProjection prj) {
        if (this.getLat() > 90.0f) {
            this.setLat(90.0f);
        }
        if (this.getLat() < -90.0f) {
            this.setLat(-90.0f);
        }
        this.calculateXY(prj);
    }
}
