import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class PointOfInterestMapLayer extends MapLayer
{
    private imgViewer applet;
    private Image pushPinIcon;
    private LatLong latLong;
    public boolean showMapLayer;
    private Point savedUpperLeft;
    private double savedPixelSize;
    private Dimension savedDispSize;
    private ProjectionTransformation savedProj;
    
    public PointOfInterestMapLayer(final imgViewer applet, final int n) {
        super(applet.imgArea, "Point Of Interest", null, n, true);
        this.applet = applet;
        this.showMapLayer = false;
        this.pushPinIcon = applet.getImage(applet.getCodeBase(), "graphics/pushPin.gif");
    }
    
    @Override
    public int setDisplayAreaUsingLatLong(final LatLong latLong, final LatLong latLong2, final int n) {
        return 0;
    }
    
    @Override
    public int setDisplayAreaUsingProjCoords(final Point point, final Point point2, final int n) {
        return 0;
    }
    
    @Override
    public void read(final CancelLoad cancelLoad, final Point point, final int n, final MapLayerLoadingCallback mapLayerLoadingCallback) {
    }
    
    @Override
    public void clip(final Point savedUpperLeft, final int n, final Dimension savedDispSize, final ProjectionTransformation savedProj) {
        this.savedUpperLeft = savedUpperLeft;
        this.savedPixelSize = this.applet.imgArea.md.actualPixelSize;
        this.savedDispSize = savedDispSize;
        this.savedProj = savedProj;
    }
    
    private Point getLatLongInScreenCoords() {
        if (this.savedUpperLeft == null) {
            return null;
        }
        Point latLongToProj = this.savedProj.latLongToProj(this.latLong);
        if (latLongToProj != null) {
            latLongToProj = new Point((int)Math.round((latLongToProj.x - this.savedUpperLeft.x) / this.savedPixelSize), (int)Math.round((-latLongToProj.y + this.savedUpperLeft.y) / this.savedPixelSize));
        }
        return latLongToProj;
    }
    
    public void clearPoint() {
        this.latLong = null;
        this.showMapLayer = false;
        this.applet.mapLayerMenu.setLayerState("Point Of Interest", false, this.isEnabled());
        this.applet.imgArea.repaint();
    }
    
    @Override
    public void draw(final Graphics graphics) {
        final Point latLongInScreenCoords = this.getLatLongInScreenCoords();
        if (latLongInScreenCoords != null) {
            graphics.drawImage(this.pushPinIcon, latLongInScreenCoords.x - this.pushPinIcon.getHeight(null), latLongInScreenCoords.y - this.pushPinIcon.getWidth(null), this.applet.imgArea);
        }
    }
    
    @Override
    public boolean isEnabled() {
        return this.showMapLayer;
    }
    
    public LatLong getPointOfInterestLatLong() {
        return this.latLong;
    }
    
    public void setPoint(final LatLong latLong) {
        this.latLong = latLong;
        this.showMapLayer = true;
        this.applet.pointOfInterestDialog.setLatLong(this.latLong);
        this.applet.mapLayerMenu.setLayerState("Point Of Interest", true, this.isEnabled());
        this.applet.imgArea.repaint();
    }
}
