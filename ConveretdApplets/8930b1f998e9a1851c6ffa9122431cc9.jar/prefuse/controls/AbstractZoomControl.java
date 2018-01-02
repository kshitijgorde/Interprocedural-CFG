// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.controls;

import java.awt.geom.Point2D;
import prefuse.Display;

public class AbstractZoomControl extends ControlAdapter
{
    public static final double DEFAULT_MIN_SCALE = 0.001;
    public static final double DEFAULT_MAX_SCALE = 75.0;
    protected static final int ZOOM = 0;
    protected static final int MIN_ZOOM = 1;
    protected static final int MAX_ZOOM = 2;
    protected static final int NO_ZOOM = 3;
    protected double m_minScale;
    protected double m_maxScale;
    protected boolean m_zoomOverItem;
    
    public AbstractZoomControl() {
        this.m_minScale = 0.001;
        this.m_maxScale = 75.0;
        this.m_zoomOverItem = true;
    }
    
    protected int zoom(final Display display, final Point2D point2D, double n, final boolean b) {
        if (display.isTranformInProgress()) {
            return 3;
        }
        final double scale = display.getScale();
        final double n2 = scale * n;
        int n3 = 0;
        if (n2 < this.m_minScale) {
            n = this.m_minScale / scale;
            n3 = 1;
        }
        else if (n2 > this.m_maxScale) {
            n = this.m_maxScale / scale;
            n3 = 2;
        }
        if (b) {
            display.zoomAbs(point2D, n);
        }
        else {
            display.zoom(point2D, n);
        }
        display.repaint();
        return n3;
    }
    
    public double getMaxScale() {
        return this.m_maxScale;
    }
    
    public void setMaxScale(final double maxScale) {
        this.m_maxScale = maxScale;
    }
    
    public double getMinScale() {
        return this.m_minScale;
    }
    
    public void setMinScale(final double minScale) {
        this.m_minScale = minScale;
    }
    
    public boolean isZoomOverItem() {
        return this.m_zoomOverItem;
    }
    
    public void setZoomOverItem(final boolean zoomOverItem) {
        this.m_zoomOverItem = zoomOverItem;
    }
}
