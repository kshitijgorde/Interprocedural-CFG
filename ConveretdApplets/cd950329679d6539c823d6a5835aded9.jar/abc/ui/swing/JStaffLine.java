// 
// Decompiled by Procyon v0.5.30
// 

package abc.ui.swing;

import java.awt.Graphics2D;
import java.awt.Point;
import abc.notation.MusicElement;
import java.awt.geom.Point2D;
import java.util.Vector;

class JStaffLine extends JScoreElementAbstract
{
    protected Vector m_staffElements;
    
    public JStaffLine(final Point2D base, final ScoreMetrics c) {
        super(c);
        this.m_staffElements = null;
        this.m_staffElements = new Vector();
    }
    
    public Point2D getBase() {
        return this.m_staffElements.elementAt(0).getBase();
    }
    
    public MusicElement getMusicElement() {
        return null;
    }
    
    protected void onBaseChanged() {
    }
    
    public void addElement(final JScoreElementAbstract r) {
        this.m_staffElements.addElement(r);
        r.setStaffLine(this);
        this.computeWidth();
    }
    
    public JScoreElementAbstract[] toArray() {
        final JScoreElementAbstract[] r = new JScoreElementAbstract[this.m_staffElements.size()];
        this.m_staffElements.toArray(r);
        return r;
    }
    
    public JScoreElement getScoreElementAt(final Point point) {
        JScoreElement scoreEl = null;
        for (int i = 0; i < this.m_staffElements.size(); ++i) {
            scoreEl = this.m_staffElements.elementAt(i).getScoreElementAt(point);
            if (scoreEl != null) {
                return scoreEl;
            }
        }
        return scoreEl;
    }
    
    public double render(final Graphics2D g) {
        final JScoreElementAbstract[] elmts = this.toArray();
        for (int j = 0; j < elmts.length; ++j) {
            elmts[j].render(g);
        }
        final int staffCharNb = (int)(this.getWidth() / this.m_metrics.getStaffCharBounds().getWidth());
        final char[] staffS = new char[staffCharNb + 2];
        for (int i = 0; i < staffS.length; ++i) {
            staffS[i] = '\uf03d';
        }
        g.drawChars(staffS, 0, staffS.length, 0, (int)this.getBase().getY());
        return this.m_width;
    }
    
    public void scaleToWidth(final double newWidth) {
        for (int i = 0; i < this.m_staffElements.size(); ++i) {
            final JScoreElementAbstract elmt = this.m_staffElements.elementAt(i);
            if (!(elmt instanceof JClef) && !(elmt instanceof JKeySignature) && !(elmt instanceof JTimeSignature)) {
                final double newXpos = elmt.getBase().getX() * newWidth / this.getWidth();
                final Point2D base = elmt.getBase();
                base.setLocation(newXpos, base.getY());
                elmt.setBase(base);
            }
        }
        this.computeWidth();
    }
    
    private void computeWidth() {
        final JScoreElementAbstract lastElmt = this.m_staffElements.lastElement();
        this.m_width = lastElmt.getBase().getX() + lastElmt.getWidth();
    }
}
