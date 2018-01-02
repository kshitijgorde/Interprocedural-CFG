// 
// Decompiled by Procyon v0.5.30
// 

public class USLocatorMapConfig extends GeographicLocatorMapConfig
{
    private static final int IMAGE_WIDTH = 750;
    private static final int IMAGE_HEIGHT = 309;
    private static final double LEFT_LON = -128.04;
    private static final double RIGHT_LON = -60.74;
    private static final double TOP_LAT = 50.85;
    private static final double BOTTOM_LAT = 23.04;
    private static final String MAP_IMAGE = "graphics/USMap.jpg";
    private static final String BOUNDARY_IMAGE = "graphics/USBoundariesBlack.gif";
    private static final boolean ENFORCE_GEOGRAPHIC_BUMPER = true;
    private static final boolean CROSSES_DATELINE = false;
    
    public USLocatorMapConfig() {
        super(750, 309, -128.04, -60.74, 50.85, 23.04, "graphics/USMap.jpg", "graphics/USBoundariesBlack.gif", true, false);
    }
}
