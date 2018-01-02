import java.awt.Color;
import java.awt.Graphics;
import java.awt.Cursor;
import java.awt.Event;
import java.awt.FontMetrics;
import java.awt.Dimension;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class EK extends Panel
{
    public static final int NT = 0;
    public static final int OT = 1;
    public static final int PT = 2;
    private String HT;
    private String GT;
    private int FT;
    private boolean MT;
    private boolean KT;
    
    public EK() {
        this("", 0);
    }
    
    public EK(final String s) {
        this(s, 0);
    }
    
    public EK(final String s, final int n) {
        this.DT(s);
        this.ET(n);
    }
    
    public int LT() {
        return this.FT;
    }
    
    public String IT() {
        return this.GT;
    }
    
    public String JT() {
        final String ht = this.HT;
        return (ht != null) ? ht : "";
    }
    
    public Dimension minimumSize() {
        final FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
        return new Dimension(Math.max(fontMetrics.stringWidth(this.IT()), fontMetrics.stringWidth(this.JT())), fontMetrics.getHeight());
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        if (this.KT) {
            this.setCursor(new Cursor(12));
        }
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.KT) {
            this.postEvent(new Event(this, 1001, null));
        }
        return true;
    }
    
    public void paint(final Graphics graphics) {
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        final int lt = this.LT();
        final String it = this.IT();
        final int stringWidth = fontMetrics.stringWidth(it);
        final int n = (lt == 1) ? ((this.size().width - stringWidth) / 2) : ((lt == 2) ? (this.size().width - stringWidth) : 0);
        final int n2 = fontMetrics.getLeading() + fontMetrics.getAscent();
        graphics.drawString(it, n, n2);
        if (this.MT) {
            graphics.drawLine(0, n2, this.size().width, n2);
        }
    }
    
    public Dimension preferredSize() {
        return this.minimumSize();
    }
    
    public void ET(final int ft) {
        this.FT = ft;
        this.repaint();
    }
    
    public void setBackground(final Color background) {
        super.setBackground(background);
        this.repaint();
    }
    
    public void KS(final boolean kt) {
        this.KT = kt;
    }
    
    public void setForeground(final Color foreground) {
        super.setForeground(foreground);
        this.repaint();
    }
    
    public void QT(final boolean mt) {
        this.MT = mt;
    }
    
    public void DT(final String gt) {
        this.GT = gt;
        this.invalidate();
        this.repaint();
    }
    
    public void RT(final String ht) {
        this.HT = ht;
    }
}
