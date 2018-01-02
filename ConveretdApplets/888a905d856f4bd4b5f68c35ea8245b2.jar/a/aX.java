// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Font;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.image.ImageObserver;

public class aX implements ImageObserver
{
    private boolean q;
    private boolean w;
    private boolean e;
    private int q;
    private int w;
    private int e;
    private String q;
    private String w;
    private String e;
    private Object q;
    cc q;
    private Color q;
    
    public final void q(final Color q) {
        this.q = q;
    }
    
    public final void q(final int n) {
        this.e = 2;
        if (this.q != null) {
            this.q.e(this);
        }
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if ((n & 0x20) != 0x0) {
            this.q.e(this);
            return false;
        }
        return true;
    }
    
    public final void q(final String e, final String s) {
        this.w = null;
        this.e = e;
    }
    
    public final String q(final boolean b) {
        if (b) {
            return this.w;
        }
        return this.e;
    }
    
    public final int q() {
        return this.w;
    }
    
    public final int w() {
        return this.q;
    }
    
    public final void w(int n) {
        if (n < 15) {
            n = 15;
        }
        this.w = n;
        this.q = n;
        if (this.q != null) {
            this.q.repaint();
            this.q.t();
        }
    }
    
    public final void e(final int q) {
        this.q = q;
        if (this.q != null) {
            this.q.repaint();
        }
    }
    
    public final void r(final int w) {
        this.w = w;
        if (this.q != null) {
            this.q.t();
        }
    }
    
    public final boolean q() {
        return this.q;
    }
    
    public final void q(final boolean b) {
        this.q = true;
    }
    
    public final boolean w() {
        return this.w;
    }
    
    public final void w(final boolean w) {
        this.w = w;
    }
    
    public final boolean e() {
        return this.e;
    }
    
    public final void e(final boolean b) {
        this.e = true;
    }
    
    public final String q() {
        return this.q;
    }
    
    public final Object q() {
        return this.q;
    }
    
    public void q(final Graphics graphics, int n, int n2, final int n3, final boolean b, final boolean b2) {
        if (this.q instanceof Image) {
            final Image image;
            final int height = (image = (Image)this.q).getHeight(this.q);
            final int width = image.getWidth(this.q);
            if (height > 0 && height > 0) {
                n += (n2 - width) / 2;
                n2 = (n3 - height) / 2;
                graphics.drawImage(image, n, n2, this.q);
                return;
            }
            graphics.drawImage(image, -1, -1, 1, 1, this.q);
        }
        else if (this.q != null) {
            graphics.setColor(b ? Color.white : this.q.getForeground());
            cc.q(graphics, this.q.toString(), n + 7, 0, n2 - 12, n3, 0, false);
        }
    }
    
    public boolean q(final Event event, final bJ bj) {
        return false;
    }
    
    final boolean q(Graphics graphics, final cf cf, final Object o, int int1, int n, int n2, int n3, final boolean b) {
        if (o == null && cf.q instanceof p) {
            return false;
        }
        if (o instanceof Boolean) {
            final Graphics graphics2 = graphics;
            final int booleanValue = ((boolean)o) ? 1 : 0;
            final int n4 = int1;
            final int n5 = n;
            final int n6 = n2;
            final int n7 = n3;
            n3 = n6;
            n2 = n5;
            n = n4;
            int1 = booleanValue;
            final Graphics graphics3 = graphics2;
            if (int1 != 0) {
                final int n8 = n2 + n7 / 2 + 3;
                final int n9 = n + n3 / 2 - 1;
                if (cf.q) {
                    graphics3.setColor(this.q);
                }
                else {
                    graphics3.setColor(cc.q());
                }
                graphics3.drawLine(n9, n8, n9 - 4, n8 - 4);
                graphics3.drawLine(n9, n8 - 1, n9 - 3, n8 - 4);
                graphics3.drawLine(n9, n8, n9 + 6, n8 - 6);
                graphics3.drawLine(n9, n8 - 1, n9 + 6, n8 - 7);
            }
        }
        else if (o instanceof Image) {
            final Graphics graphics4 = graphics;
            final Image image = (Image)o;
            final int n10 = int1;
            final int n11 = n;
            final int n12 = n2;
            final int n13 = n3;
            n3 = n12;
            n2 = n11;
            n = n10;
            final Image image2 = image;
            final Graphics graphics5 = graphics4;
            final int height = image2.getHeight(this);
            final int width = image2.getWidth(this);
            if (height == 0 || height == 0) {
                graphics5.drawImage(image2, -1, -1, 1, 1, this);
            }
            else {
                n += (n3 - width) / 2;
                n3 = n2 + (n13 - height) / 2 - 1;
                graphics5.drawImage(image2, n, n3, this);
                if (this.q.q != null && dN.q()) {
                    int1 = 0;
                    if (this.q.q.q().containsKey("" + ((p)cf.q).s)) {
                        int1 = Integer.parseInt(this.q.q.q().get("" + ((p)cf.q).s));
                    }
                    if (int1 > 0) {
                        graphics5.setColor(Color.red);
                        graphics5.fillOval(2, n2 + 1, 15, 15);
                        graphics5.setColor(Color.yellow.brighter());
                        graphics5.drawOval(2, n2 + 1, 14, 14);
                        int n14 = 12;
                        int n15 = 6;
                        n = n2 + 14;
                        if (int1 > 9) {
                            n14 = 10;
                            n15 = 3;
                            n = n2 + 12;
                        }
                        graphics5.setFont(new Font(graphics5.getFont().getFamily(), 0, n14));
                        graphics5.drawString("" + int1, n15, n);
                    }
                }
            }
        }
        else if (o instanceof Color) {
            final Graphics graphics6 = graphics;
            final Color color = (Color)o;
            final int n16 = int1;
            final int n17 = n;
            final int n18 = n2;
            n2 = n3;
            n = n18;
            int1 = n17;
            final int n19 = n16;
            final Color color2 = color;
            graphics = graphics6;
            n = n19 + (n - 15) / 2;
            n3 = int1 + (n2 - 15) / 2 - 1;
            dS.q(graphics, 15, 15, color2, n, n3);
        }
        else if (o != null) {
            final Graphics graphics7 = graphics;
            final String string = o.toString();
            final int n20 = int1;
            final int n21 = n;
            final int n22 = n2;
            final int n23 = n3;
            n3 = n22;
            n2 = n21;
            n = n20;
            final String s = string;
            final Graphics graphics8 = graphics7;
            graphics8.setFont(this.q.getFont());
            graphics8.setFont(new Font(graphics8.getFont().getFamily(), ((bp)cf.q).e(), graphics8.getFont().getSize()));
            Color color3 = null;
            Label_0822: {
                Color q;
                if (b) {
                    if ((bC.w.Q.getRGB() & 0xFF) == 0x0) {
                        color3 = (cf.q ? cf.w : Color.lightGray);
                        break Label_0822;
                    }
                    q = bC.w.Q;
                }
                else {
                    q = ((cf.q instanceof bp && (((bp)cf.q).w() & 0xFF) != 0x0) ? new Color(((bp)cf.q).w()) : (cf.q ? cf.q : Color.gray));
                }
                color3 = q;
            }
            graphics8.setColor(color3);
            cc.q(graphics8, s, n + 5, n2, n3 - 8, n23, this.e, cf.w, cf);
        }
        return true;
    }
    
    public aX(final String s) {
        this(s, s);
    }
    
    public aX(final Object q, final String q2) {
        this.q = Color.red;
        this.q = false;
        this.w = false;
        this.q = 50;
        this.w = 50;
        this.q = q2;
        this.q = q;
    }
}
