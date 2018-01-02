// 
// Decompiled by Procyon v0.5.30
// 

public class LocatorMapConfig extends GeographicLocatorMapConfig
{
    private static final int IMAGE_WIDTH = 1007;
    private static final int IMAGE_HEIGHT = 503;
    private static final double LEFT_LON = -180.0;
    private static final double RIGHT_LON = 180.0;
    private static final double TOP_LAT = 90.0;
    private static final double BOTTOM_LAT = -90.0;
    private static final String MAP_IMAGE = "graphics/World5Minute.jpg";
    private static final String BOUNDARY_IMAGE = "graphics/WorldBoundariesBlack.gif";
    private static final boolean ENFORCE_GEOGRAPHIC_BUMPER = false;
    private static final boolean CROSSES_DATELINE = false;
    
    public LocatorMapConfig() {
        super(1007, 503, -180.0, 180.0, 90.0, -90.0, "graphics/World5Minute.jpg", "graphics/WorldBoundariesBlack.gif", false, false);
    }
}
