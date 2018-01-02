// 
// Decompiled by Procyon v0.5.30
// 

package abc.ui.swing;

import java.awt.Graphics2D;
import abc.notation.MusicElement;
import java.awt.geom.Point2D;

class JClef extends JScoreElementAbstract
{
    public static final char G_CLEF = '\uf026';
    
    public JClef(final Point2D base, final ScoreMetrics c) {
        super(c);
        this.m_width = 3.0 * this.m_metrics.getNoteWidth();
        this.setBase(base);
    }
    
    protected void onBaseChanged() {
    }
    
    public MusicElement getMusicElement() {
        return null;
    }
    
    public double render(final Graphics2D context) {
        super.render(context);
        final char[] chars = { '\uf026' };
        context.drawChars(chars, 0, chars.length, (int)this.m_base.getX(), (int)(this.m_base.getY() - this.m_metrics.getNoteHeigth()));
        return this.m_width;
    }
}
