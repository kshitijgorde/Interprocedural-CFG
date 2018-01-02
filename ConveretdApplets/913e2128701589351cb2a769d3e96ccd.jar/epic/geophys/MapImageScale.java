// 
// Decompiled by Procyon v0.5.30
// 

package epic.geophys;

import java.awt.Rectangle;
import java.awt.Point;

public class MapImageScale
{
    private Point topLeft;
    private Point bottomRight;
    private GeoPoint northWest;
    private GeoPoint southEast;
    
    public MapImageScale(final Point point, final Point point2, final GeoPoint geoPoint, final GeoPoint geoPoint2) {
        this.topLeft = new Point(point.x, point.y);
        this.bottomRight = new Point(point2.x, point2.y);
        this.northWest = new GeoPoint(geoPoint);
        this.southEast = new GeoPoint(geoPoint2);
    }
    
    public Rectangle getMapRectangle() {
        return new Rectangle(this.topLeft.x, this.topLeft.y, this.bottomRight.x - this.topLeft.x, this.bottomRight.y - this.topLeft.y);
    }
    
    public String printIt(final GeoPoint geoPoint) {
        this.southEast.Lon().LonValue();
        this.northWest.Lon().LonValue();
        final int n = (int)(this.southEast.Lat().LatValue() - this.northWest.Lat().LatValue());
        if (n == 0) {
            final int y = this.topLeft.y;
        }
        else {
            final int n2 = this.topLeft.y + (int)((geoPoint.Lat().LatValue() - this.northWest.Lat().LatValue()) * (this.bottomRight.y - this.topLeft.y) / n);
        }
        return new Integer((int)geoPoint.Lat().LatValue()).toString();
    }
    
    public GeoPoint toGeoPoint(final int n, final int n2) {
        return this.toGeoPoint(new Point(n, n2));
    }
    
    public GeoPoint toGeoPoint(final Point point) {
        double lonValue = this.southEast.Lon().LonValue();
        final double lonValue2 = this.northWest.Lon().LonValue();
        if (lonValue <= lonValue2) {
            lonValue += 360.0;
        }
        return new GeoPoint(new Longitude(this.northWest.Lon().LonValue() + (point.x - this.topLeft.x) * (lonValue - lonValue2) / (this.bottomRight.x - this.topLeft.x)), new Latitude(this.northWest.Lat().LatValue() + (point.y - this.topLeft.y) * (this.southEast.Lat().LatValue() - this.northWest.Lat().LatValue()) / (this.bottomRight.y - this.topLeft.y)));
    }
    
    public Point toPoint(final double n, final double n2) {
        return this.toPoint(new GeoPoint(n, n2));
    }
    
    public Point toPoint(final GeoPoint geoPoint) {
        double lonValue = this.southEast.Lon().LonValue();
        final double lonValue2 = this.northWest.Lon().LonValue();
        double lonValue3 = geoPoint.Lon().LonValue();
        if (lonValue <= lonValue2) {
            lonValue += 360.0;
            if (lonValue3 < 0.0) {
                lonValue3 += 360.0;
            }
        }
        final int n = (int)(lonValue - lonValue2);
        int x;
        if (n == 0) {
            x = this.topLeft.x;
        }
        else {
            x = this.topLeft.x + (int)((lonValue3 - lonValue2) * (this.bottomRight.x - this.topLeft.x) / n);
        }
        final int n2 = (int)(this.southEast.Lat().LatValue() - this.northWest.Lat().LatValue());
        int y;
        if (n2 == 0) {
            y = this.topLeft.y;
        }
        else {
            y = this.topLeft.y + (int)((geoPoint.Lat().LatValue() - this.northWest.Lat().LatValue()) * (this.bottomRight.y - this.topLeft.y) / n2);
        }
        return new Point(x, y);
    }
    
    public Point toPoint(final Longitude longitude, final Latitude latitude) {
        return this.toPoint(new GeoPoint(longitude, latitude));
    }
}
