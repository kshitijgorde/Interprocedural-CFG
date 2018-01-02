// 
// Decompiled by Procyon v0.5.30
// 

package abc.ui.swing;

import java.awt.Graphics2D;
import abc.notation.BarLine;
import java.awt.geom.Point2D;
import abc.notation.RepeatBarLine;

class JRepeatBar extends JBar
{
    public static final char[][] DIGITS;
    
    public JRepeatBar(final RepeatBarLine barLine, final Point2D base, final ScoreMetrics c) {
        super(barLine, base, c);
    }
    
    public double render(final Graphics2D context) {
        final char[] ch = JRepeatBar.DIGITS[((RepeatBarLine)this.m_barLine).getRepeatNumber() - 1];
        context.drawChars(ch, 0, ch.length, (int)(this.m_base.getX() + this.m_metrics.getNoteWidth()), (int)(this.m_base.getY() - this.m_metrics.getStaffCharBounds().getHeight() * 1.3));
        context.drawLine((int)(this.m_base.getX() + this.m_metrics.getNoteWidth() / 2.0), (int)(this.m_base.getY() - this.m_metrics.getStaffCharBounds().getHeight() * 1.1), (int)(this.m_base.getX() + this.m_metrics.getNoteWidth() / 2.0), (int)(this.m_base.getY() - this.m_metrics.getStaffCharBounds().getHeight() * 1.7));
        context.drawLine((int)(this.m_base.getX() + this.m_metrics.getNoteWidth() / 2.0), (int)(this.m_base.getY() - this.m_metrics.getStaffCharBounds().getHeight() * 1.7), (int)(this.m_base.getX() + this.m_metrics.getNoteWidth() / 2.0 + this.m_metrics.getStaffCharBounds().getWidth()), (int)(this.m_base.getY() - this.m_metrics.getStaffCharBounds().getHeight() * 1.7));
        return super.render(context);
    }
    
    static {
        DIGITS = new char[][] { { '\uf0c1', '\uf02e' }, { '\uf0aa', '\uf02e' }, { '\uf0a3', '\uf02e' }, { '\uf0a2', '\uf02e' }, { '\uf0b0', '\uf02e' }, { '\uf0a4', '\uf02e' }, { '\uf0a6', '\uf02e' }, { '\uf0a5', '\uf02e' }, { '\uf0bb', '\uf02e' } };
    }
}
