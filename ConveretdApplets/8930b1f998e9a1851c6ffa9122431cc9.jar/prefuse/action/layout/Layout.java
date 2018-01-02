// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.action.layout;

import prefuse.util.PrefuseLib;
import prefuse.visual.VisualItem;
import prefuse.Display;
import java.awt.Insets;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import prefuse.action.GroupAction;

public abstract class Layout extends GroupAction
{
    protected Rectangle2D m_bounds;
    protected Point2D m_anchor;
    protected boolean m_margin;
    protected Insets m_insets;
    protected double[] m_bpts;
    protected Rectangle2D m_tmpb;
    protected Point2D m_tmpa;
    
    public Layout() {
        this.m_bounds = null;
        this.m_anchor = null;
        this.m_margin = false;
        this.m_insets = new Insets(0, 0, 0, 0);
        this.m_bpts = new double[4];
        this.m_tmpb = new Rectangle2D.Double();
        this.m_tmpa = new Point2D.Double();
    }
    
    public Layout(final String s) {
        super(s);
        this.m_bounds = null;
        this.m_anchor = null;
        this.m_margin = false;
        this.m_insets = new Insets(0, 0, 0, 0);
        this.m_bpts = new double[4];
        this.m_tmpb = new Rectangle2D.Double();
        this.m_tmpa = new Point2D.Double();
    }
    
    public Layout(final String s, final long n) {
        super(s, n);
        this.m_bounds = null;
        this.m_anchor = null;
        this.m_margin = false;
        this.m_insets = new Insets(0, 0, 0, 0);
        this.m_bpts = new double[4];
        this.m_tmpb = new Rectangle2D.Double();
        this.m_tmpa = new Point2D.Double();
    }
    
    public void setMargin(final int top, final int left, final int bottom, final int right) {
        this.m_insets.top = top;
        this.m_insets.left = left;
        this.m_insets.bottom = bottom;
        this.m_insets.right = right;
        this.m_margin = true;
    }
    
    public Rectangle2D getLayoutBounds() {
        if (this.m_bounds != null) {
            return this.m_bounds;
        }
        if (this.m_vis != null && this.m_vis.getDisplayCount() > 0) {
            final Display display = this.m_vis.getDisplay(0);
            final Insets insets = this.m_margin ? this.m_insets : display.getInsets(this.m_insets);
            this.m_bpts[0] = insets.left;
            this.m_bpts[1] = insets.top;
            this.m_bpts[2] = display.getWidth() - insets.right;
            this.m_bpts[3] = display.getHeight() - insets.bottom;
            display.getInverseTransform().transform(this.m_bpts, 0, this.m_bpts, 0, 2);
            this.m_tmpb.setRect(this.m_bpts[0], this.m_bpts[1], this.m_bpts[2] - this.m_bpts[0], this.m_bpts[3] - this.m_bpts[1]);
            return this.m_tmpb;
        }
        return null;
    }
    
    public void setLayoutBounds(final Rectangle2D bounds) {
        this.m_bounds = bounds;
    }
    
    public Point2D getLayoutAnchor() {
        if (this.m_anchor != null) {
            return this.m_anchor;
        }
        this.m_tmpa.setLocation(0.0, 0.0);
        if (this.m_vis != null) {
            final Display display = this.m_vis.getDisplay(0);
            this.m_tmpa.setLocation(display.getWidth() / 2.0, display.getHeight() / 2.0);
            display.getInverseTransform().transform(this.m_tmpa, this.m_tmpa);
        }
        return this.m_tmpa;
    }
    
    public void setLayoutAnchor(final Point2D anchor) {
        this.m_anchor = anchor;
    }
    
    public void setX(final VisualItem visualItem, final VisualItem visualItem2, final double n) {
        PrefuseLib.setX(visualItem, visualItem2, n);
    }
    
    public void setY(final VisualItem visualItem, final VisualItem visualItem2, final double n) {
        PrefuseLib.setY(visualItem, visualItem2, n);
    }
}
