// 
// Decompiled by Procyon v0.5.30
// 

package abc.ui.swing;

import java.awt.Graphics2D;
import abc.notation.MusicElement;
import java.awt.geom.Point2D;
import abc.notation.BarLine;

class JBar extends JScoreElementAbstract
{
    protected BarLine m_barLine;
    protected int m_barWidth;
    protected int m_barDotsSpacing;
    protected int m_dotsRadius;
    protected int m_topDotY;
    protected int m_bottomDotY;
    
    public JBar(final BarLine barLine, final Point2D base, final ScoreMetrics c) {
        super(c);
        this.m_barLine = null;
        this.m_barWidth = -1;
        this.m_barDotsSpacing = -1;
        this.m_dotsRadius = -1;
        this.m_topDotY = -1;
        this.m_bottomDotY = -1;
        this.m_barLine = barLine;
        this.setBase(base);
    }
    
    public MusicElement getMusicElement() {
        return this.m_barLine;
    }
    
    protected void onBaseChanged() {
        this.m_dotsRadius = (int)(this.m_metrics.getNoteWidth() * 0.3);
        this.m_barWidth = (int)(this.m_metrics.getNoteWidth() * 0.5);
        this.m_barDotsSpacing = (int)(this.m_metrics.getNoteWidth() * 0.2);
        final int height = (int)this.m_metrics.getStaffCharBounds().getHeight();
        this.m_topDotY = (int)(this.m_base.getY() - height * 0.61);
        this.m_bottomDotY = (int)(this.m_base.getY() - height * 0.4);
        switch (this.m_barLine.getType()) {
            case 0: {
                this.m_width = 0.0;
                break;
            }
            case 1: {
                this.m_width = this.m_barWidth + 2 * this.m_barDotsSpacing + this.m_dotsRadius;
                break;
            }
            case 2: {
                this.m_width = this.m_barWidth + 2 * this.m_barDotsSpacing + this.m_dotsRadius;
                break;
            }
        }
    }
    
    public double render(final Graphics2D context) {
        super.render(context);
        final int height = (int)this.m_metrics.getStaffCharBounds().getHeight();
        switch (this.m_barLine.getType()) {
            case 1: {
                context.fillRect((int)this.m_base.getX(), (int)this.m_base.getY() - height, this.m_barWidth, height);
                context.drawLine((int)(this.m_base.getX() + this.m_barWidth + this.m_barDotsSpacing), (int)(this.m_base.getY() - height), (int)(this.m_base.getX() + this.m_barWidth + this.m_barDotsSpacing), (int)this.m_base.getY());
                context.fillOval((int)(this.m_base.getX() + this.m_barWidth + 2 * this.m_barDotsSpacing), this.m_topDotY, this.m_dotsRadius, this.m_dotsRadius);
                context.fillOval((int)(this.m_base.getX() + this.m_barWidth + 2 * this.m_barDotsSpacing), this.m_bottomDotY, this.m_dotsRadius, this.m_dotsRadius);
                break;
            }
            case 2: {
                context.fillOval((int)this.m_base.getX(), (int)(this.m_base.getY() - height * 0.61), this.m_dotsRadius, this.m_dotsRadius);
                context.fillOval((int)this.m_base.getX(), (int)(this.m_base.getY() - height * 0.4), this.m_dotsRadius, this.m_dotsRadius);
                context.drawLine((int)(this.m_base.getX() + this.m_dotsRadius + this.m_barDotsSpacing), (int)(this.m_base.getY() - height), (int)(this.m_base.getX() + this.m_dotsRadius + this.m_barDotsSpacing), (int)this.m_base.getY());
                context.fillRect((int)(this.m_base.getX() + this.m_dotsRadius + 2 * this.m_barDotsSpacing), (int)this.m_base.getY() - height, this.m_barWidth, height);
                break;
            }
            case 0: {
                context.drawChars(ScoreMetrics.BAR_LINE, 0, 1, (int)(this.m_base.getX() + this.m_metrics.getNoteWidth() / 3.0), (int)this.m_base.getY());
                break;
            }
        }
        return this.m_width;
    }
}
