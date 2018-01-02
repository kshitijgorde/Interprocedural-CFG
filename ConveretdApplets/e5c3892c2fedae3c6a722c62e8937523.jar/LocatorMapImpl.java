import java.awt.Point;
import java.awt.Dimension;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class LocatorMapImpl
{
    public Image mapImage;
    public Image worldBoundaries;
    public boolean useBoundaryImage;
    public Dimension imageSize;
    
    public abstract Point latLongToPixel(final LatLong p0);
    
    public abstract Point gridToPixel(final int p0, final int p1);
    
    public abstract LatLong pixelToLatLong(final int p0, final int p1);
    
    public abstract Point pixelToGrid(final int p0, final int p1);
    
    public abstract void moveTo(final int p0, final int p1);
    
    public GeographicLocatorMapConfig getMapConfig() {
        return null;
    }
    
    public void cleanup() {
        if (this.mapImage != null) {
            this.mapImage.flush();
        }
        this.mapImage = null;
        if (this.worldBoundaries != null) {
            this.worldBoundaries.flush();
        }
        this.worldBoundaries = null;
    }
}
