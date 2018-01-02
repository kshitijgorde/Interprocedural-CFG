import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Dimension;
import java.io.BufferedInputStream;
import java.net.URL;
import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class AddressSearchMapLayer extends MapLayer
{
    private imgViewer applet;
    private Image pushPinIcon;
    private LatLong latLong;
    private String addressName;
    public boolean enabled;
    private Point addressInScreenCoords;
    
    public AddressSearchMapLayer(final imgViewer applet, final int n) {
        super(applet.imgArea, "Address Search Result", null, n, true);
        this.applet = applet;
        this.enabled = false;
        try {
            new BufferedInputStream(new URL(applet.getCodeBase(), "searchenabled").openStream()).close();
        }
        catch (Exception ex) {
            this.hide();
        }
        this.pushPinIcon = applet.getImage(applet.getCodeBase(), "graphics/pushPinYellow.gif");
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
    public void clip(final Point point, final int n, final Dimension dimension, final ProjectionTransformation projectionTransformation) {
        this.addressInScreenCoords = this.getLatLongInScreenCoords(point, this.applet.imgArea.md.actualPixelSize, projectionTransformation);
    }
    
    private Point getLatLongInScreenCoords(final Point point, final double n, final ProjectionTransformation projectionTransformation) {
        if (point == null) {
            return null;
        }
        Point latLongToProj = projectionTransformation.latLongToProj(this.latLong);
        if (latLongToProj != null) {
            latLongToProj = new Point((int)Math.round((latLongToProj.x - point.x) / n), (int)Math.round((-latLongToProj.y + point.y) / n));
        }
        return latLongToProj;
    }
    
    @Override
    public MapLayerFeatureInfo findFeatureName(final int n, final int n2) {
        if (this.addressInScreenCoords == null) {
            return null;
        }
        final int x = this.addressInScreenCoords.x;
        final int y = this.addressInScreenCoords.y;
        final int height = this.pushPinIcon.getHeight(null);
        if (n >= x - this.pushPinIcon.getWidth(null) && n < x + 5 && n2 >= y - height && n2 < y) {
            final MapLayerFeatureInfo mapLayerFeatureInfo = new MapLayerFeatureInfo();
            mapLayerFeatureInfo.name = this.addressName;
            mapLayerFeatureInfo.area = 10.0;
            return mapLayerFeatureInfo;
        }
        return null;
    }
    
    @Override
    public void draw(final Graphics graphics) {
        final Point addressInScreenCoords = this.addressInScreenCoords;
        if (addressInScreenCoords != null) {
            graphics.drawImage(this.pushPinIcon, addressInScreenCoords.x - this.pushPinIcon.getWidth(null), addressInScreenCoords.y - this.pushPinIcon.getHeight(null), this.applet.imgArea);
        }
    }
    
    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
    
    public void setPoint(final LatLong latLong, final String addressName) {
        this.latLong = latLong;
        this.addressName = addressName;
        this.enabled = true;
        this.applet.mapLayerMenu.setLayerState(this.getName(), true, this.isEnabled());
        this.applet.md.gotoLatLong(this.latLong.latitude, this.latLong.longitude);
        this.applet.imgArea.repaint();
    }
    
    public void clearPoint() {
        this.latLong = null;
        this.addressInScreenCoords = null;
        this.enabled = false;
        this.applet.mapLayerMenu.setLayerState(this.getName(), false, this.isEnabled());
        this.applet.imgArea.repaint();
    }
}
