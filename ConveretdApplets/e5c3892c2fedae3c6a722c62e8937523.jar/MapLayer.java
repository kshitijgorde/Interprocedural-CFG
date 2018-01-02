import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Color;
import java.awt.Component;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class MapLayer
{
    private String name;
    private Component parent;
    private boolean layerOn;
    private Color originalColor;
    private int menuShortcut;
    private boolean lowResAvailable;
    private boolean isHidden;
    protected Color color;
    
    public MapLayer(final Component parent, final String name, final Color color, final int menuShortcut, final boolean lowResAvailable) {
        this.parent = parent;
        this.name = name;
        this.originalColor = color;
        this.color = color;
        this.menuShortcut = menuShortcut;
        this.lowResAvailable = lowResAvailable;
        this.layerOn = false;
        this.isHidden = false;
    }
    
    public String getName() {
        return this.name;
    }
    
    int getMenuShortcut() {
        return this.menuShortcut;
    }
    
    public Color getOriginalColor() {
        return this.originalColor;
    }
    
    public Color getColor() {
        return this.color;
    }
    
    public void setColor(final Color color) {
        this.color = color;
        if (this.layerOn) {
            this.parent.repaint();
        }
    }
    
    public void setLayerOn(final boolean layerOn) {
        this.layerOn = layerOn;
    }
    
    public boolean isLayerOn() {
        return this.layerOn;
    }
    
    public boolean isEnabled() {
        return true;
    }
    
    public boolean isLowResAvailable() {
        return this.lowResAvailable;
    }
    
    public void hide() {
        this.isHidden = true;
    }
    
    public boolean isHidden() {
        return this.isHidden;
    }
    
    public abstract int setDisplayAreaUsingLatLong(final LatLong p0, final LatLong p1, final int p2);
    
    public abstract int setDisplayAreaUsingProjCoords(final Point p0, final Point p1, final int p2);
    
    public abstract void read(final CancelLoad p0, final Point p1, final int p2, final MapLayerLoadingCallback p3);
    
    public abstract void clip(final Point p0, final int p1, final Dimension p2, final ProjectionTransformation p3);
    
    public MapLayerFeatureInfo findFeatureName(final int n, final int n2) {
        return null;
    }
    
    public void updateAttributeWindow() {
    }
    
    public abstract void draw(final Graphics p0);
}
