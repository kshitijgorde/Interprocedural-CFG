// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.action.animate;

import prefuse.visual.VisualItem;
import prefuse.Display;
import java.awt.geom.Point2D;
import prefuse.action.ItemAction;

public class PolarLocationAnimator extends ItemAction
{
    private Point2D m_anchor;
    private String m_linear;
    private double ax;
    private double ay;
    private double sx;
    private double sy;
    private double ex;
    private double ey;
    private double x;
    private double y;
    private double dt1;
    private double dt2;
    private double sr;
    private double st;
    private double er;
    private double et;
    private double r;
    private double t;
    private double stt;
    private double ett;
    
    public PolarLocationAnimator() {
        this.m_anchor = new Point2D.Double();
        this.m_linear = null;
    }
    
    public PolarLocationAnimator(final String s) {
        super(s);
        this.m_anchor = new Point2D.Double();
        this.m_linear = null;
    }
    
    public PolarLocationAnimator(final String s, final String linear) {
        super(s);
        this.m_anchor = new Point2D.Double();
        this.m_linear = null;
        this.m_linear = linear;
    }
    
    private void setAnchor() {
        final Display display = this.getVisualization().getDisplay(0);
        this.m_anchor.setLocation(display.getWidth() / 2, display.getHeight() / 2);
        display.getAbsoluteCoordinate(this.m_anchor, this.m_anchor);
        this.ax = this.m_anchor.getX();
        this.ay = this.m_anchor.getY();
    }
    
    public void run(final double n) {
        this.setAnchor();
        super.run(n);
    }
    
    public void process(final VisualItem visualItem, final double n) {
        if (this.m_linear != null && visualItem.isInGroup(this.m_linear)) {
            final double startX = visualItem.getStartX();
            visualItem.setX(startX + n * (visualItem.getEndX() - startX));
            final double startY = visualItem.getStartY();
            visualItem.setY(startY + n * (visualItem.getEndY() - startY));
            return;
        }
        this.sx = visualItem.getStartX() - this.ax;
        this.sy = visualItem.getStartY() - this.ay;
        this.ex = visualItem.getEndX() - this.ax;
        this.ey = visualItem.getEndY() - this.ay;
        this.sr = Math.sqrt(this.sx * this.sx + this.sy * this.sy);
        this.st = Math.atan2(this.sy, this.sx);
        this.er = Math.sqrt(this.ex * this.ex + this.ey * this.ey);
        this.et = Math.atan2(this.ey, this.ex);
        this.stt = ((this.st < 0.0) ? (this.st + 6.283185307179586) : this.st);
        this.ett = ((this.et < 0.0) ? (this.et + 6.283185307179586) : this.et);
        this.dt1 = this.et - this.st;
        this.dt2 = this.ett - this.stt;
        if (Math.abs(this.dt1) < Math.abs(this.dt2)) {
            this.t = this.st + n * this.dt1;
        }
        else {
            this.t = this.stt + n * this.dt2;
        }
        this.r = this.sr + n * (this.er - this.sr);
        this.x = Math.round(this.ax + this.r * Math.cos(this.t));
        this.y = Math.round(this.ay + this.r * Math.sin(this.t));
        visualItem.setX(this.x);
        visualItem.setY(this.y);
    }
}
