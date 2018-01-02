// 
// Decompiled by Procyon v0.5.30
// 

public class GeographicLocatorMapConfig
{
    public int imageWidth;
    public int imageHeight;
    public double leftLon;
    public double rightLon;
    public double topLat;
    public double bottomLat;
    public double degreesPerPixelLon;
    public double degreesPerPixelLat;
    public String mapImage;
    public String boundaryImage;
    public boolean enforceGeographicBumper;
    public boolean crossesDateLine;
    public boolean useBoundaryImage;
    
    public GeographicLocatorMapConfig(final int imageWidth, final int imageHeight, final double leftLon, final double rightLon, final double topLat, final double bottomLat, final String mapImage, final String boundaryImage, final boolean enforceGeographicBumper, final boolean crossesDateLine) {
        this.imageWidth = 1007;
        this.imageHeight = 503;
        this.leftLon = -180.0;
        this.rightLon = 180.0;
        this.topLat = 90.0;
        this.bottomLat = -90.0;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        this.leftLon = leftLon;
        this.rightLon = rightLon;
        this.topLat = topLat;
        this.bottomLat = bottomLat;
        this.mapImage = mapImage;
        this.boundaryImage = boundaryImage;
        this.enforceGeographicBumper = enforceGeographicBumper;
        this.crossesDateLine = crossesDateLine;
        this.useBoundaryImage = (boundaryImage != null);
        this.degreesPerPixelLon = (rightLon - leftLon) / imageWidth;
        this.degreesPerPixelLat = (topLat - bottomLat) / imageHeight;
    }
}
