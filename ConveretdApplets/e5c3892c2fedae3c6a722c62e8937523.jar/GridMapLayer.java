import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Component;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class GridMapLayer extends MapLayer
{
    private imgViewer applet;
    private MapLayer current;
    private ModisTileMapLayer modisLayer;
    private UtmZoneMapLayer utmLayer;
    private WrsGridCenterMapLayer wrsLayer;
    
    public GridMapLayer(final imgViewer applet, final Color color, final int n) {
        super(applet.imgArea, "Collection Grid", color, n, true);
        this.applet = applet;
        this.wrsLayer = new WrsGridCenterMapLayer(applet, color, n);
        this.modisLayer = new ModisTileMapLayer(applet, color, n);
        this.utmLayer = new UtmZoneMapLayer(applet, color, n);
        this.current = this.wrsLayer;
    }
    
    @Override
    public void setColor(final Color color) {
        this.wrsLayer.setColor(color);
        this.modisLayer.setColor(color);
        this.utmLayer.setColor(color);
        super.setColor(color);
    }
    
    @Override
    public int setDisplayAreaUsingLatLong(final LatLong latLong, final LatLong latLong2, final int n) {
        if (this.applet.sensorMenu.getCurrentSensor() instanceof TriDecEtmMosaicDataset || this.applet.sensorMenu.getCurrentSensor() instanceof TriDecTmMosaicDataset) {
            this.current = this.utmLayer;
        }
        else {
            this.current = this.wrsLayer;
        }
        return this.current.setDisplayAreaUsingLatLong(latLong, latLong2, n);
    }
    
    @Override
    public int setDisplayAreaUsingProjCoords(final Point point, final Point point2, final int n) {
        this.current = this.modisLayer;
        return this.current.setDisplayAreaUsingProjCoords(point, point2, n);
    }
    
    @Override
    public void read(final CancelLoad cancelLoad, final Point point, final int n, final MapLayerLoadingCallback mapLayerLoadingCallback) {
        this.current.read(cancelLoad, point, n, mapLayerLoadingCallback);
    }
    
    @Override
    public void clip(final Point point, final int n, final Dimension dimension, final ProjectionTransformation projectionTransformation) {
        this.current.clip(point, n, dimension, projectionTransformation);
    }
    
    @Override
    public void draw(final Graphics graphics) {
        this.current.draw(graphics);
    }
}
