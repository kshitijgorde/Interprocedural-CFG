// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

import java.awt.Image;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.PixelGrabber;
import java.awt.Font;
import java.awt.Color;
import java.util.Vector;

public class a1 extends bm
{
    static final char[] b9;
    static final char[] ca;
    static final char[] cb;
    static final char[] b6;
    static final char[] bT;
    static final char[] bV;
    static final char[] bS;
    static final char[] bP;
    static final char[] bX;
    private char[] bR;
    boolean b5;
    private Vector b3;
    private String bY;
    private int bZ;
    private Color b2;
    private Color b0;
    private boolean b1;
    private boolean bW;
    private String a2;
    private int bO;
    ad bQ;
    Font b8;
    int bU;
    int b7;
    boolean b4;
    
    static {
        b9 = new char[] { 'f', 'o', 'n', 't', '\0' };
        ca = new char[] { 's', 'i', 'z', 'e', '\0' };
        cb = new char[] { 'b', 'o', 'l', 'd', '\0' };
        b6 = new char[] { 'i', 't', 'a', 'l', 'i', 'c', '\0' };
        bT = new char[] { 'u', 'n', 'd', 'e', 'r', 'l', 'i', 'n', 'e', '\0' };
        bV = new char[] { 'f', 'o', 'r', 'e', 'g', 'r', 'o', 'u', 'n', 'd', 'c', 'o', 'l', 'o', 'r', '\0' };
        bS = new char[] { 's', 'h', 'a', 'd', 'o', 'w', 'c', 'o', 'l', 'o', 'r', '\0' };
        bP = new char[] { 'b', 'g', 'c', 'o', 'l', 'o', 'r', '\0' };
        bX = new char[] { 'b', 'o', 'r', 'd', 'e', 'r', 'c', 'o', 'l', 'o', 'r', '\0' };
    }
    
    public a1() {
        this.bR = new char[] { 't', 'e', 'x', 't', '\0' };
        this.b5 = true;
        this.b3 = null;
        this.bY = "Arial";
        this.bZ = 12;
        this.b2 = new Color(-16777216);
        this.b0 = null;
        this.b1 = false;
        this.bW = false;
        this.a2 = "";
        this.bO = 0;
        this.bQ = null;
        this.b8 = null;
        this.bU = 0;
        this.b7 = 1;
        this.b4 = false;
    }
    
    public void a(final ac void1, final a2 a2, final ae ae, final v bh) {
        super.void = void1;
        super.a0 = this;
        super.byte = this.bR;
        super.a8 = 0;
        super.bh = bh;
        super.a(a2, ae);
        this.b3 = new Vector();
        for (int i = 0; i < a2.do; ++i) {
            if (a2.try[i].toLowerCase().compareTo("foregroundcolor") == 0) {
                this.b2 = new Color(0xFF000000 | ac.a(a2.new[i]));
            }
            else if (a2.try[i].toLowerCase().compareTo("shadowcolor") == 0) {
                this.b0 = new Color(0xFF000000 | ac.a(a2.new[i]));
            }
            else if (a2.try[i].toLowerCase().compareTo("italic") == 0) {
                this.bW = Boolean.valueOf(a2.new[i]);
            }
            else if (a2.try[i].toLowerCase().compareTo("bold") == 0) {
                this.b1 = Boolean.valueOf(a2.new[i]);
            }
            else if (a2.try[i].toLowerCase().compareTo("size") == 0) {
                this.bZ = new Integer(a2.new[i]);
            }
            else if (a2.try[i].toLowerCase().compareTo("font") == 0) {
                this.bY = a2.new[i];
            }
            else if (a2.try[i].toLowerCase().compareTo("bordercolor") == 0) {
                this.bO = ac.a(a2.new[i]);
                if ((this.bO & 0xFF000000) == 0x0) {
                    this.bO |= 0xFF000000;
                }
            }
            else if (a2.try[i].toLowerCase().compareTo("bgcolor") == 0) {
                super.a8 = ac.a(a2.new[i]);
                if ((super.a8 & 0xFF000000) == 0x0) {
                    super.a8 |= 0xFF000000;
                }
            }
        }
        this.if(a2);
        if (a2.case != null) {
            this.a2 = a2.case.if;
        }
        this.a2 = this.a2.trim();
        this.l();
        super.try = true;
    }
    
    public void l() {
        this.b5 = true;
        this.b3.removeAllElements();
        int i = 0;
        int n = 0;
        while (i != -1) {
            i = this.a2.indexOf("\n", n);
            if (i != -1) {
                this.b3.addElement(this.a2.substring(n, i));
                n = i + 1;
            }
        }
        if (i == 0) {
            this.b3.addElement(this.a2);
        }
        else if (n < this.a2.length()) {
            this.b3.addElement(this.a2.substring(n, this.a2.length()));
        }
        if (this.b1) {
            this.bU = 1;
        }
        else {
            this.bU = 0;
        }
        if (this.bW) {
            this.bU |= 0x2;
        }
        this.b8 = new Font(this.bY, this.bU, this.bZ);
        final Graphics graphics = ac.F.getGraphics();
        graphics.setFont(this.b8);
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        this.bQ = new ad();
        this.bQ.i = 0;
        this.bQ.t = 0;
        for (int j = 0; j < this.b3.size(); ++j) {
            final int stringWidth = fontMetrics.stringWidth(this.b3.elementAt(j));
            if (stringWidth > this.bQ.i) {
                this.bQ.i = stringWidth;
            }
        }
        this.bQ.t = fontMetrics.getHeight();
        if (this.bQ.t <= 0 || this.bQ.i <= 0) {
            return;
        }
        if (this.b0 != null) {
            final ad bq = this.bQ;
            bq.i += this.b7;
            final ad bq2 = this.bQ;
            bq2.t += this.b7;
        }
        final ad bq3 = this.bQ;
        bq3.t *= this.b3.size();
        final ad bq4 = this.bQ;
        bq4.t += 8;
        final ad bq5 = this.bQ;
        bq5.i += 8;
        this.bQ.u = true;
        final int n2 = this.bQ.i * this.bQ.t;
        final Image image = ac.F.createImage(this.bQ.i, this.bQ.t);
        super.ay = this.bQ.i;
        super.aA = this.bQ.t;
        int n3 = 0;
        if (ac.ab < 3 || !ac.Y) {
            this.bQ.p = new int[n2];
            final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, this.bQ.i, this.bQ.t, this.bQ.p, 0, this.bQ.i);
            try {
                pixelGrabber.grabPixels();
            }
            catch (Exception ex) {}
            n3 = (this.bQ.p[0] & 0xFFFFFF);
            int n4 = this.b2.getRGB() & 0xFFFFFF;
            if (n4 == n3) {
                if (n4 == 16777215) {
                    this.b2 = new Color(-65537);
                }
                if (n4 == 0) {
                    this.b2 = new Color(-16777215);
                }
                else {
                    this.b2 = new Color(0xFF000000 | --n4);
                }
            }
            if (this.b0 != null) {
                final int n5 = this.b0.getRGB() & 0xFFFFFF;
                if (n5 == n3) {
                    if (n5 == 16777215) {
                        this.b0 = new Color(-65537);
                    }
                    else if (n5 == 0) {
                        this.b0 = new Color(-16777215);
                    }
                    else {
                        this.b0 = new Color(0xFF000000 | --n4);
                    }
                }
            }
        }
        else {
            this.bQ.p = s.a(image, n2);
        }
        final Graphics graphics2 = image.getGraphics();
        graphics2.setFont(this.b8);
        for (int k = 0; k < this.b3.size(); ++k) {
            final String s = this.b3.elementAt(k);
            final int n6 = (this.bQ.t - 8) * (k + 1) / this.b3.size() - 2;
            if (this.b0 != null) {
                graphics2.setColor(this.b0);
                graphics2.drawString(s, this.b7 + 4, n6 - 4);
                graphics2.setColor(this.b2);
                graphics2.drawString(s, 4, n6 - this.b7 - 4);
            }
            else {
                graphics2.setColor(this.b2);
                graphics2.drawString(s, 4, n6 - 4);
            }
        }
        Label_1256: {
            if (ac.ab >= 3) {
                if (ac.Y) {
                    break Label_1256;
                }
            }
            try {
                final PixelGrabber pixelGrabber2 = new PixelGrabber(image, 0, 0, this.bQ.i, this.bQ.t, this.bQ.p, 0, this.bQ.i);
                pixelGrabber2.grabPixels();
                pixelGrabber2.getStatus();
                final int n7 = (super.a8 < 0) ? super.a8 : 0;
                for (int l = 0; l < n2; ++l) {
                    if ((this.bQ.p[l] & 0xFFFFFF) == n3) {
                        this.bQ.p[l] = n7;
                    }
                }
                if (this.bO < 0) {
                    for (int n8 = 0; n8 < this.bQ.i; ++n8) {
                        this.bQ.p[n8] = this.bO;
                    }
                    final int n9 = (this.bQ.t - 1) * this.bQ.i;
                    for (int n10 = n9 + this.bQ.i, n11 = n9; n11 < n10; ++n11) {
                        this.bQ.p[n11] = this.bO;
                    }
                    int n12 = this.bQ.i - 1;
                    for (int n13 = 0; n13 < this.bQ.t - 1; ++n13) {
                        this.bQ.p[n12] = this.bO;
                        this.bQ.p[n12 + 1] = this.bO;
                        n12 += this.bQ.i;
                    }
                }
                super.ay = this.bQ.i;
                super.aA = this.bQ.t;
            }
            catch (Exception ex2) {}
        }
        this.b5 = false;
    }
    
    public boolean a(final long n) {
        if (super.else) {
            if (this.b4) {
                this.l();
                this.b4 = false;
                super.if = true;
            }
            final boolean if1 = super.if;
            super.if = false;
            return (this.for(n) & super.do) | if1;
        }
        return false;
    }
    
    public void e() {
        if (super.do && !this.b5) {
            ap.a(this.bQ, 0, this.bQ.i, super.void.else, super.a7, super.a6, this.bQ.i, this.bQ.t);
        }
    }
    
    void a(final ab ab) {
    }
    
    public a3 a(final char[] array) {
        if (g.if(array, ac.k) == 0) {
            super.int.char = 4;
            super.int.int = (String.valueOf(this.a2) + "\u0000").toCharArray();
            return super.int;
        }
        if (g.if(array, a1.b9) == 0) {
            super.int.char = 4;
            super.int.int = (String.valueOf(this.bY) + "\u0000").toCharArray();
            return super.int;
        }
        if (g.if(array, a1.b6) == 0) {
            super.int.char = 1;
            super.int.long = this.bW;
            return super.int;
        }
        if (g.if(array, a1.cb) == 0) {
            super.int.char = 1;
            super.int.long = this.b1;
            return super.int;
        }
        if (g.if(array, a1.ca) == 0) {
            super.int.char = 2;
            super.int.case = this.bZ;
            return super.int;
        }
        if (g.if(array, a1.bV) == 0) {
            super.int.char = 2;
            super.int.case = this.b2.getRGB();
            return super.int;
        }
        if (g.if(array, a1.bS) == 0) {
            super.int.char = 2;
            super.int.case = this.b0.getRGB();
            return super.int;
        }
        if (g.if(array, a1.bP) == 0) {
            super.int.char = 2;
            super.int.case = super.a8;
            return super.int;
        }
        if (g.if(array, a1.bX) == 0) {
            super.int.char = 2;
            super.int.case = this.bO;
            return super.int;
        }
        return super.a(array);
    }
    
    public void a(final char[] array, final a3 a3) {
        if (g.if(array, ac.k) == 0) {
            if (a3.char == 4) {
                this.a2 = new String(a3.int, 0, g.a(a3.int));
                this.l();
                super.if = true;
            }
        }
        else if (g.if(array, a1.b9) == 0) {
            if (a3.char == 4) {
                this.bY = new String(a3.int, 0, g.a(a3.int));
                this.l();
                super.if = true;
            }
        }
        else if (g.if(array, a1.b6) == 0) {
            if (a3.char == 1) {
                this.bW = a3.long;
                this.l();
                super.if = true;
            }
        }
        else if (g.if(array, a1.cb) == 0) {
            if (a3.char == 1) {
                this.b1 = a3.long;
                this.l();
                super.if = true;
            }
        }
        else if (g.if(array, a1.ca) == 0) {
            if (a3.char == 2) {
                this.bZ = (int)a3.case;
                this.l();
                super.if = true;
            }
        }
        else if (g.if(array, a1.bV) == 0) {
            if (a3.char == 2) {
                this.b2 = new Color((int)a3.case);
                this.b4 = true;
            }
        }
        else if (g.if(array, a1.bS) == 0) {
            if (a3.char == 2) {
                this.b0 = new Color((int)a3.case);
                this.b4 = true;
            }
        }
        else if (g.if(array, a1.bP) == 0) {
            if (a3.char == 2) {
                super.a8 = (int)a3.case;
                this.b4 = true;
            }
        }
        else if (g.if(array, a1.bX) == 0) {
            if (a3.char == 2) {
                this.bO = (int)a3.case;
                this.b4 = true;
            }
        }
        else {
            super.a(array, a3);
        }
    }
}
