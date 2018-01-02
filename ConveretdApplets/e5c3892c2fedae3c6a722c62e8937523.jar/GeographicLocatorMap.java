import java.awt.Point;
import java.awt.Dimension;

// 
// Decompiled by Procyon v0.5.30
// 

public class GeographicLocatorMap extends LocatorMapImpl
{
    private imgViewer applet;
    private GeographicLocatorMapConfig config;
    
    GeographicLocatorMap(final imgViewer applet, final GeographicLocatorMapConfig config) {
        this.applet = applet;
        this.config = config;
        this.mapImage = applet.getImage(applet.getCodeBase(), config.mapImage);
        if (config.useBoundaryImage) {
            this.worldBoundaries = applet.getImage(applet.getCodeBase(), config.boundaryImage);
        }
        this.useBoundaryImage = config.useBoundaryImage;
        this.imageSize = new Dimension(config.imageWidth, config.imageHeight);
    }
    
    @Override
    public GeographicLocatorMapConfig getMapConfig() {
        return this.config;
    }
    
    @Override
    public Point latLongToPixel(final LatLong latLong) {
        if (this.config.crossesDateLine && latLong.longitude > 0.0) {
            latLong.longitude -= 360.0;
        }
        return new Point((int)((latLong.longitude - this.config.leftLon) / this.config.degreesPerPixelLon), (int)((this.config.topLat - latLong.latitude) / this.config.degreesPerPixelLat));
    }
    
    @Override
    public Point gridToPixel(final int n, final int n2) {
        return this.latLongToPixel(this.applet.sensorMenu.getCurrentSensor().navModel.gridToLatLong(n, n2));
    }
    
    @Override
    public LatLong pixelToLatLong(final int n, final int n2) {
        return new LatLong(this.config.topLat - n2 * this.config.degreesPerPixelLat, this.config.leftLon + n * this.config.degreesPerPixelLon);
    }
    
    @Override
    public Point pixelToGrid(final int n, final int n2) {
        final LatLong pixelToLatLong = this.pixelToLatLong(n, n2);
        return this.applet.sensorMenu.getCurrentSensor().navModel.latLongToGrid(pixelToLatLong.latitude, pixelToLatLong.longitude);
    }
    
    @Override
    public void moveTo(final int n, final int n2) {
        final LatLong pixelToLatLong = this.pixelToLatLong(n, n2);
        this.applet.imgArea.md.gotoLatLong(pixelToLatLong.latitude, pixelToLatLong.longitude);
    }
}
