// 
// Decompiled by Procyon v0.5.30
// 

package abc.ui.swing;

import java.awt.Graphics2D;
import java.awt.Point;
import abc.notation.MusicElement;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Point2D;

abstract class JScoreElementAbstract implements JScoreElement
{
    protected ScoreMetrics m_metrics;
    protected Point2D m_base;
    protected double m_width;
    protected Rectangle2D m_boundingBox;
    protected JStaffLine staffLine;
    
    protected JScoreElementAbstract(final ScoreMetrics mtrx) {
        this.m_metrics = null;
        this.m_base = null;
        this.m_width = -1.0;
        this.m_boundingBox = null;
        this.staffLine = null;
        this.m_metrics = mtrx;
    }
    
    public double getWidth() {
        return this.m_width;
    }
    
    public JStaffLine getStaffLine() {
        return this.staffLine;
    }
    
    public void setStaffLine(final JStaffLine staffLine) {
        this.staffLine = staffLine;
    }
    
    public abstract MusicElement getMusicElement();
    
    public Rectangle2D getBoundingBox() {
        final Rectangle2D bb = new Rectangle2D.Double(this.m_base.getX(), this.m_base.getY() - 50.0, this.m_width, 50.0);
        return bb;
    }
    
    public JScoreElement getScoreElementAt(final Point location) {
        if (this.getBoundingBox().contains(location)) {
            return this;
        }
        return null;
    }
    
    public Point2D getBase() {
        return this.m_base;
    }
    
    public void setBase(final Point2D base) {
        this.m_base = (Point2D)base.clone();
        this.onBaseChanged();
    }
    
    protected abstract void onBaseChanged();
    
    public double render(final Graphics2D g2) {
        return this.m_width;
    }
}
