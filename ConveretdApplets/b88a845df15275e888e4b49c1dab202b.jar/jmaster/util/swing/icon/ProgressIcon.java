// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.swing.icon;

import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Component;
import jmaster.util.log.B;
import javax.swing.ImageIcon;
import jmaster.util.log.A;

public class ProgressIcon extends ResizingIcon
{
    protected A i;
    private double j;
    private ImageIcon h;
    private ImageIcon o;
    private ImageIcon q;
    private ImageIcon g;
    private ImageIcon l;
    private ImageIcon p;
    private ImageIcon k;
    private ImageIcon m;
    private long n;
    
    public ProgressIcon() {
        this.i = jmaster.util.log.B.getInstance().getLog(this.getClass());
        this.j = 0.0;
        this.n = 8L;
    }
    
    public double getCompleted() {
        return this.j;
    }
    
    public ImageIcon getIconLeftEmpty() {
        return this.h;
    }
    
    public void setIconLeftEmpty(final ImageIcon h) {
        this.h = h;
    }
    
    public ImageIcon getIconLeftFilled() {
        return this.o;
    }
    
    public void setIconLeftFilled(final ImageIcon o) {
        this.o = o;
    }
    
    public ImageIcon getIconMiddleEmpty() {
        return this.l;
    }
    
    public void setIconMiddleEmpty(final ImageIcon l) {
        this.l = l;
    }
    
    public ImageIcon getIconMiddleFilled() {
        return this.p;
    }
    
    public void setIconMiddleFilled(final ImageIcon p) {
        this.p = p;
    }
    
    public ImageIcon getIconPos() {
        return this.m;
    }
    
    public void setIconPos(final ImageIcon m) {
        this.m = m;
    }
    
    public ImageIcon getIconRightEmpty() {
        return this.q;
    }
    
    public void setIconRightEmpty(final ImageIcon q) {
        this.q = q;
    }
    
    public ImageIcon getIconRightFilled() {
        return this.g;
    }
    
    public void setIconRightFilled(final ImageIcon g) {
        this.g = g;
    }
    
    public void setCompleted(final double j) {
        this.j = j;
    }
    
    public ImageIcon getIconMiddleStripes() {
        return this.k;
    }
    
    public void setIconMiddleStripes(final ImageIcon k) {
        this.k = k;
    }
    
    public long getStripesSpeed() {
        return this.n;
    }
    
    public void setStripesSpeed(final long n) {
        this.n = n;
    }
    
    public void paintIcon(final Component component, final Graphics graphics, final int n, final int n2) {
        final ImageObserver imageObserver = null;
        if (this.j < 0.0) {
            graphics.drawImage(this.h.getImage(), n, n2, imageObserver);
            graphics.drawImage(this.q.getImage(), n + this.getWidth() - this.q.getIconWidth(), n2, imageObserver);
            final int iconWidth = this.k.getIconWidth();
            final int iconHeight = this.k.getIconHeight();
            final int n3 = (int)(System.currentTimeMillis() * this.n / 1000L % iconWidth);
            final int n4 = n + this.h.getIconWidth();
            final int n5 = n + this.getWidth() - this.q.getIconWidth();
            final int n6 = n2 + iconHeight;
            final int n7 = 0;
            final int n8 = iconHeight;
            for (int i = n4 - ((n3 > 0) ? (iconWidth - n3) : 0); i < n5; i += iconWidth) {
                int n9 = i;
                int n10 = n9 + iconWidth;
                int n11 = 0;
                int n12 = iconWidth;
                if (n9 < n4) {
                    n11 = n4 - i;
                    n9 = n4;
                }
                if (n10 > n5) {
                    n12 = iconWidth - (n10 - n5);
                    n10 = n5;
                }
                graphics.drawImage(this.k.getImage(), n9, n2, n10, n6, n11, n7, n12, n8, imageObserver);
            }
        }
        else {
            final boolean b = this.j > 0.0;
            final boolean b2 = this.j >= 1.0;
            final ImageIcon imageIcon = b ? this.o : this.h;
            graphics.drawImage(imageIcon.getImage(), n, n2, imageObserver);
            final ImageIcon imageIcon2 = b2 ? this.g : this.q;
            graphics.drawImage(imageIcon2.getImage(), n + this.getWidth() - imageIcon2.getIconWidth(), n2, imageObserver);
            final int iconWidth2 = imageIcon.getIconWidth();
            final int n13 = this.getIconWidth() - imageIcon2.getIconWidth();
            int n14 = (int)(this.j * this.getWidth());
            if (n14 < iconWidth2) {
                n14 = iconWidth2;
            }
            if (n14 > n13) {
                n14 = n13;
            }
            if (b2) {
                graphics.drawImage(this.p.getImage(), n + iconWidth2, n2, n + n13, n2 + this.getIconHeight(), 0, 0, this.p.getIconWidth(), this.p.getIconHeight(), imageObserver);
            }
            else if (!b) {
                graphics.drawImage(this.l.getImage(), n + iconWidth2, n2, n + n13, n2 + this.getIconHeight(), 0, 0, this.p.getIconWidth(), this.p.getIconHeight(), imageObserver);
            }
            else {
                graphics.drawImage(this.p.getImage(), n + iconWidth2, n2, n + n14, n2 + this.getIconHeight(), 0, 0, this.p.getIconWidth(), this.p.getIconHeight(), imageObserver);
                graphics.drawImage(this.l.getImage(), n + n14, n2, n + n13, n2 + this.getIconHeight(), 0, 0, this.l.getIconWidth(), this.l.getIconHeight(), imageObserver);
                graphics.drawImage(this.m.getImage(), n + n14, n2, n + n14 + this.m.getIconWidth(), n2 + this.getIconHeight(), 0, 0, this.m.getIconWidth(), this.m.getIconHeight(), imageObserver);
            }
        }
    }
}
