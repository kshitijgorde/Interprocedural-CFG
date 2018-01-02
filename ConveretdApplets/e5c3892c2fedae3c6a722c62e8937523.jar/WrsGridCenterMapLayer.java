import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.Color;
import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

public class WrsGridCenterMapLayer extends MapLayer
{
    private imgViewer applet;
    private Point[] gridCenters;
    private Point[] gridOnScreen;
    
    public WrsGridCenterMapLayer(final imgViewer applet, final Color color, final int n) {
        super(applet.imgArea, "WRS Centers", color, n, true);
        this.applet = applet;
        this.gridCenters = null;
        this.gridOnScreen = null;
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
        final NavigationModel navModel = this.applet.sensorMenu.getCurrentSensor().navModel;
        final TOC[] mosaicCells = this.applet.imgArea.md.getMosaicCells();
        this.gridCenters = new Point[mosaicCells.length];
        this.gridOnScreen = new Point[mosaicCells.length];
        for (int i = 0; i < mosaicCells.length; ++i) {
            final int gridRow = mosaicCells[i].gridRow;
            if (navModel.checkRowBounds(gridRow) == gridRow) {
                this.gridCenters[i] = mosaicCells[i].getCenterProjCoords(projectionTransformation);
            }
            else {
                this.gridCenters[i] = null;
            }
        }
        for (int j = 0; j < this.gridCenters.length; ++j) {
            if (this.gridCenters[j] == null) {
                this.gridOnScreen[j] = null;
            }
            else {
                this.gridOnScreen[j] = new Point((this.gridCenters[j].x - point.x) / n, (point.y - this.gridCenters[j].y) / n);
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
