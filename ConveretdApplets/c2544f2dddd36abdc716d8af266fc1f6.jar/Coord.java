// 
// Decompiled by Procyon v0.5.30
// 

public class Coord
{
    public double lon;
    public double lat;
    
    public Coord(final double lon, final double lat) {
        this.lon = lon;
        this.lat = lat;
    }
    
    @Override
    public String toString() {
        return "(lon: " + this.lon + ", lat: " + this.lat + ")";
    }
}
