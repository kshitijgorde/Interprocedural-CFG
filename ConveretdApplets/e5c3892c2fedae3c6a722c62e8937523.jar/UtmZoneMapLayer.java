import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.Color;
import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

public class UtmZoneMapLayer extends MapLayer
{
    private imgViewer applet;
    private LatLong ULcorner;
    private LatLong LRcorner;
    private Point[] gridOnScreen;
    
    public UtmZoneMapLayer(final imgViewer applet, final Color color, final int n) {
        super(applet.imgArea, "UTM Zone Centers", color, n, true);
        this.applet = applet;
        this.ULcorner = new LatLong(0.0, 0.0);
        this.LRcorner = new LatLong(0.0, 0.0);
    }
    
    @Override
    public int setDisplayAreaUsingLatLong(final LatLong latLong, final LatLong latLong2, final int n) {
        double latitude = (int)((int)(latLong.latitude + 10.0) / 5.0) * 5;
        if (latitude > 90.0) {
            latitude = 87.5;
        }
        double latitude2 = (int)((int)(latLong2.latitude - 10.0) / 5.0) * 5;
        if (latitude2 < -90.0) {
            latitude2 = -90.0;
        }
        final int n2 = ((int)(latLong.longitude - 12.0) + 180) / 6 * 6 - 180;
        final int n3 = ((int)(latLong2.longitude + 12.0) + 180) / 6 * 6 - 180;
        this.ULcorner.latitude = latitude;
        this.ULcorner.longitude = n2;
        this.LRcorner.latitude = latitude2;
        this.LRcorner.longitude = n3;
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
        final NavigationModel navModel = this.applet.sensorMenu.getCurrentSensor().navModel;
        final double n2 = 180.0 / (navModel.getMaximumRow() + 1);
        final double n3 = 360.0 / (navModel.getMaximumColumn() + 1);
        this.gridOnScreen = new Point[((int)((this.ULcorner.latitude - this.LRcorner.latitude) / n2) + 1) * ((int)((this.LRcorner.longitude - this.ULcorner.longitude) / n3) + 1)];
        int n4 = 0;
        final LatLong latLong = new LatLong(0.0, 0.0);
        for (double latitude = this.LRcorner.latitude + n2 / 2.0; latitude <= this.ULcorner.latitude; latitude += n2) {
            latLong.latitude = latitude;
            for (double longitude = this.ULcorner.longitude + n3 / 2.0; longitude <= this.LRcorner.longitude; longitude += n3) {
                latLong.longitude = longitude;
                final Point latLongToProj = projectionTransformation.latLongToProj(latLong);
                this.gridOnScreen[n4++] = new Point((latLongToProj.x - point.x) / n, (point.y - latLongToProj.y) / n);
            }
        }
    }
    
    @Override
    public void draw(final Graphics graphics) {
        if (this.gridOnScreen == null) {
            return;
        }
        for (int i = 0; i < this.gridOnScreen.length; ++i) {
            if (this.gridOnScreen[i] != null) {
                graphics.setColor(Color.BLACK);
                graphics.fillRect(this.gridOnScreen[i].x - 6, this.gridOnScreen[i].y - 6, 12, 12);
                graphics.setColor(this.color);
                graphics.fillRect(this.gridOnScreen[i].x - 5, this.gridOnScreen[i].y - 5, 10, 10);
            }
        }
    }
}
