// 
// Decompiled by Procyon v0.5.30
// 

package abc.ui.swing;

import java.awt.Graphics2D;
import abc.notation.MusicElement;
import java.awt.geom.Point2D;
import abc.notation.TimeSignature;

class JTimeSignature extends JScoreElementAbstract
{
    public static final char[][] DIGITS;
    protected TimeSignature m_ts;
    protected char[] m_numChars;
    protected char[] m_denomChars;
    protected int m_topNumY;
    protected int m_bottomNumY;
    
    public JTimeSignature(final TimeSignature ts, final Point2D base, final ScoreMetrics c) {
        super(c);
        this.m_ts = null;
        this.m_numChars = null;
        this.m_denomChars = null;
        this.m_topNumY = -1;
        this.m_bottomNumY = -1;
        this.m_ts = ts;
        this.m_numChars = JTimeSignature.DIGITS[this.m_ts.getNumerator()];
        this.m_denomChars = JTimeSignature.DIGITS[this.m_ts.getDenominator()];
        if (this.m_ts.getNumerator() >= 10 || this.m_ts.getDenominator() >= 10) {
            this.m_width = 2.0 * this.m_metrics.getNoteWidth();
        }
        else {
            this.m_width = this.m_metrics.getNoteWidth();
        }
        this.setBase(base);
    }
    
    public MusicElement getMusicElement() {
        return this.m_ts;
    }
    
    protected void onBaseChanged() {
        this.m_topNumY = (int)(this.m_base.getY() - this.m_metrics.getNoteHeigth() * 3.0);
        this.m_bottomNumY = (int)(this.m_base.getY() - this.m_metrics.getNoteHeigth() * 0.9);
    }
    
    public double render(final Graphics2D context) {
        super.render(context);
        context.drawChars(this.m_numChars, 0, this.m_numChars.length, (int)this.m_base.getX(), this.m_topNumY);
        context.drawChars(this.m_denomChars, 0, this.m_denomChars.length, (int)this.m_base.getX(), this.m_bottomNumY);
        return this.m_width;
    }
    
    static {
        DIGITS = new char[][] { { '\uf030' }, { '\uf031' }, { '\uf032' }, { '\uf033' }, { '\uf034' }, { '\uf035' }, { '\uf036' }, { '\uf037' }, { '\uf038' }, { '\uf039' }, { '\uf031', '\uf030' }, { '\uf031', '\uf031' }, { '\uf031', '\uf032' }, { '\uf031', '\uf033' } };
    }
}
