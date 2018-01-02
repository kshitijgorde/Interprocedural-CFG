// 
// Decompiled by Procyon v0.5.30
// 

package duc.cal.astro;

public class Location
{
    public String name;
    public double latitude;
    public double longitude;
    public double elevation;
    public double zone;
    public static final Location HANOI;
    public static final Location HUE;
    public static final Location TPHCM;
    public static final Location PEKING;
    public static final Location TOKYO;
    public static final Location PARIS;
    public static final Location SYDNEY;
    public static final Location LA;
    public static final Location HOUSTON;
    public static final Location CUSTOM;
    
    static {
        HANOI = new Location("Hanoi", 21.3, 105.85, 0.0, 7.0);
        HUE = new Location("Hue", 16.46, 107.58, 0.0, 7.0);
        TPHCM = new Location("Saigon", 10.75, 106.75, 0.0, 7.0);
        PEKING = new Location("Beijing", 39.55, 116.4, 0.0, 8.0);
        TOKYO = new Location("Tokyo", 35.7, 139.8, 0.0, 9.0);
        PARIS = new Location("Paris", 48.85, 2.35, 0.0, 1.0);
        SYDNEY = new Location("Sydney", -33.9, 151.25, 0.0, 10.0);
        LA = new Location("L.A.", 33.94, -118.4, 0.0, -8.0);
        HOUSTON = new Location("Houston", 29.65, -95.28, 0.0, -6.0);
        CUSTOM = new Location("(Custom)", 0.0, 0.0, 0.0, 0.0);
    }
    
    public Location(final String s, final double lat, final double lon, final double elev, final double tz) {
        this.name = s;
        this.latitude = lat;
        this.longitude = lon;
        this.elevation = elev;
        this.zone = tz;
    }
    
    public String toString() {
        return "" + this.name + "[latitude=" + this.latitude + ",longitude=" + this.longitude + ",elevation=" + this.elevation + ",zone=" + this.zone + "]";
    }
}
