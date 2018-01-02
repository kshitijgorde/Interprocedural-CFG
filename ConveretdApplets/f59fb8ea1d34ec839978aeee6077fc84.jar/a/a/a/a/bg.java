// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

import java.awt.image.PixelGrabber;
import java.awt.Color;
import java.awt.Image;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Font;
import java.util.Vector;

public class bg extends b5
{
    static final char[] cp;
    static final char[] cq;
    static final char[] cr;
    static final char[] cm;
    static final char[] b8;
    static final char[] ca;
    static final char[] b7;
    static final char[] b4;
    static final char[] cd;
    private char[] b6;
    boolean cl;
    private Vector ci;
    private String ce;
    private int cf;
    private boolean cg;
    private int cs;
    private int cc;
    private boolean ch;
    private boolean cb;
    private String ck;
    private int b3;
    ap b5;
    Font co;
    int b9;
    int cn;
    boolean cj;
    
    static {
        cp = new char[] { 'f', 'o', 'n', 't', '\0' };
        cq = new char[] { 's', 'i', 'z', 'e', '\0' };
        cr = new char[] { 'b', 'o', 'l', 'd', '\0' };
        cm = new char[] { 'i', 't', 'a', 'l', 'i', 'c', '\0' };
        b8 = new char[] { 'u', 'n', 'd', 'e', 'r', 'l', 'i', 'n', 'e', '\0' };
        ca = new char[] { 'f', 'o', 'r', 'e', 'g', 'r', 'o', 'u', 'n', 'd', 'c', 'o', 'l', 'o', 'r', '\0' };
        b7 = new char[] { 's', 'h', 'a', 'd', 'o', 'w', 'c', 'o', 'l', 'o', 'r', '\0' };
        b4 = new char[] { 'b', 'g', 'c', 'o', 'l', 'o', 'r', '\0' };
        cd = new char[] { 'b', 'o', 'r', 'd', 'e', 'r', 'c', 'o', 'l', 'o', 'r', '\0' };
    }
    
    public bg() {
        this.b6 = new char[] { 't', 'e', 'x', 't', '\0' };
        this.cl = true;
        this.ci = null;
        this.ce = "Arial";
        this.cf = 12;
        this.cg = false;
        this.cs = -16777216;
        this.cc = 0;
        this.ch = false;
        this.cb = false;
        this.ck = "";
        this.b3 = 0;
        this.b5 = null;
        this.co = null;
        this.b9 = 0;
        this.cn = 1;
        this.cj = false;
    }
    
    public void if() {
        super.if();
        this.b6 = null;
        this.ci.removeAllElements();
        if (this.b5 != null) {
            this.b5.if();
        }
        this.b5 = null;
        this.co = null;
        this.ce = null;
        this.ck = null;
        this.co = null;
    }
    
    public void a(final an b, final bh bh, final aq aq, final ae bs) {
        super.b = b;
        super.ba = this;
        super.case = this.b6;
        super.bi = 0;
        super.bs = bs;
        super.a(bh, aq);
        this.ci = new Vector();
        for (int i = 0; i < bh.do; ++i) {
            if (bh.try[i].toLowerCase().compareTo("foregroundcolor") == 0) {
                this.cs = an.a(bh.new[i]);
            }
            else if (bh.try[i].toLowerCase().compareTo("shadowcolor") == 0) {
                this.cc = an.a(bh.new[i]);
                this.cg = true;
            }
            else if (bh.try[i].toLowerCase().compareTo("italic") == 0) {
                this.cb = Boolean.valueOf(bh.new[i]);
            }
            else if (bh.try[i].toLowerCase().compareTo("bold") == 0) {
                this.ch = Boolean.valueOf(bh.new[i]);
            }
            else if (bh.try[i].toLowerCase().compareTo("size") == 0) {
                this.cf = new Integer(bh.new[i]);
            }
            else if (bh.try[i].toLowerCase().compareTo("font") == 0) {
                this.ce = bh.new[i];
            }
            else if (bh.try[i].toLowerCase().compareTo("bordercolor") == 0) {
                this.b3 = an.a(bh.new[i]);
            }
            else if (bh.try[i].toLowerCase().compareTo("bgcolor") == 0) {
                super.bi = an.a(bh.new[i]);
            }
        }
        this.if(bh);
        if (bh.case != null) {
            this.ck = bh.case.do;
        }
        this.ck = this.ck.trim();
        this.k();
        super.byte = true;
    }
    
    public void k() {
        this.cl = true;
        this.ci.removeAllElements();
        int i = 0;
        int n = 0;
        while (i != -1) {
            i = this.ck.indexOf("\n", n);
            if (i != -1) {
                this.ci.addElement(this.ck.substring(n, i));
                n = i + 1;
            }
        }
        if (i == 0) {
            this.ci.addElement(this.ck);
        }
        else if (n < this.ck.length()) {
            this.ci.addElement(this.ck.substring(n, this.ck.length()));
        }
        if (this.ch) {
            this.b9 = 1;
        }
        else {
            this.b9 = 0;
        }
        if (this.cb) {
            this.b9 |= 0x2;
        }
        this.co = new Font(this.ce, this.b9, this.cf);
        final Graphics graphics = super.b.L.getGraphics();
        graphics.setFont(this.co);
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        this.b5 = new ap();
        this.b5.s = 0;
        this.b5.r = 0;
        for (int j = 0; j < this.ci.size(); ++j) {
            final int stringWidth = fontMetrics.stringWidth(this.ci.elementAt(j));
            if (stringWidth > this.b5.s) {
                this.b5.s = stringWidth;
            }
        }
        this.b5.r = fontMetrics.getHeight();
        if (this.b5.r <= 0 || this.b5.s <= 0) {
            return;
        }
        if (this.cg) {
            final ap b5 = this.b5;
            b5.s += this.cn;
            final ap b6 = this.b5;
            b6.r += this.cn;
        }
        final ap b7 = this.b5;
        b7.r *= this.ci.size();
        final ap b8 = this.b5;
        b8.s += 8;
        this.b5.v = true;
        final int n2 = this.b5.s * this.b5.r;
        final Image image = super.b.L.createImage(this.b5.s, this.b5.r);
        super.aI = this.b5.s;
        super.aK = this.b5.r;
        final int[] array = null;
        this.b5.x = new int[n2];
        int[] a;
        if (an.aj < 3 || !an.ah) {
            a = new int[n2];
        }
        else {
            a = z.a(image, n2);
        }
        for (int k = 0; k < n2; ++k) {
            this.b5.x[k] = super.bi;
        }
        final Graphics graphics2 = image.getGraphics();
        graphics2.setFont(this.co);
        if (this.cg) {
            this.a(graphics2, image, a, this.b5.x, super.aI, super.aK, this.ci, this.cc, true, fontMetrics.getHeight(), fontMetrics.getAscent());
        }
        this.a(graphics2, image, a, this.b5.x, super.aI, super.aK, this.ci, this.cs, false, fontMetrics.getHeight(), fontMetrics.getAscent());
        if ((this.b3 & 0xFF000000) != 0x0) {
            for (int l = 0; l < this.b5.s; ++l) {
                this.b5.x[l] = this.b3;
            }
            final int n3 = (this.b5.r - 1) * this.b5.s;
            for (int n4 = n3 + this.b5.s, n5 = n3; n5 < n4; ++n5) {
                this.b5.x[n5] = this.b3;
            }
            int n6 = this.b5.s - 1;
            for (int n7 = 0; n7 < this.b5.r - 1; ++n7) {
                this.b5.x[n6] = this.b3;
                this.b5.x[n6 + 1] = this.b3;
                n6 += this.b5.s;
            }
        }
        super.aI = this.b5.s;
        super.aK = this.b5.r;
        this.cl = false;
    }
    
    private void a(final Graphics graphics, final Image image, final int[] array, final int[] array2, final int n, final int n2, final Vector vector, final int n3, final boolean b, final int n4, final int n5) {
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, n, n2);
        graphics.setColor(Color.white);
        for (int i = 0; i < vector.size(); ++i) {
            final String s = vector.elementAt(i);
            final int n6 = n4 * i + n5;
            if (b) {
                graphics.drawString(s, this.cn + 4, this.cn + n6);
            }
            else {
                graphics.drawString(s, 4, n6);
            }
        }
        Label_0165: {
            if (an.aj >= 3) {
                if (an.ah) {
                    break Label_0165;
                }
            }
            try {
                final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, this.b5.s, this.b5.r, array, 0, this.b5.s);
                pixelGrabber.grabPixels();
                pixelGrabber.getStatus();
            }
            catch (Exception ex) {}
        }
        final int n7 = array[0];
        for (int n8 = n * n2, j = 0; j < n8; ++j) {
            if (array[j] != n7) {
                array2[j] = n3;
            }
        }
    }
    
    public boolean a(final long n) {
        if (super.goto) {
            if (this.cj) {
                this.k();
                this.cj = false;
                super.do = true;
            }
            final boolean do1 = super.do;
            super.do = false;
            return (this.for(n) & super.for) | do1;
        }
        this.void();
        return false;
    }
    
    public void c() {
        if (super.for && !this.cl) {
            if (((super.bi & 0xFF000000) != 0x0 && (super.bi & 0xFF000000) != 0xFF000000) || ((this.cs & 0xFF000000) != 0x0 && (this.cs & 0xFF000000) != 0xFF000000) || (this.cg && (this.cc & 0xFF000000) != 0x0 && (this.cc & 0xFF000000) != 0xFF000000) || ((this.b3 & 0xFF000000) != 0x0 && (this.b3 & 0xFF000000) != 0xFF000000)) {
                this.b5.i = true;
            }
            else {
                this.b5.i = false;
            }
            a.a.a.a.a3.a(this.b5, 0, this.b5.s, super.b.goto, super.bh, super.bg, this.b5.s, this.b5.r);
        }
    }
    
    void a(final am am) {
    }
    
    public bi a(final char[] array) {
        if (i.do(array, an.o) == 0) {
            super.new.char = 4;
            super.new.int = (String.valueOf(this.ck) + "\u0000").toCharArray();
            return super.new;
        }
        if (i.do(array, bg.cp) == 0) {
            super.new.char = 4;
            super.new.int = (String.valueOf(this.ce) + "\u0000").toCharArray();
            return super.new;
        }
        if (i.do(array, bg.cm) == 0) {
            super.new.char = 1;
            super.new.long = this.cb;
            return super.new;
        }
        if (i.do(array, bg.cr) == 0) {
            super.new.char = 1;
            super.new.long = this.ch;
            return super.new;
        }
        if (i.do(array, bg.cq) == 0) {
            super.new.char = 2;
            super.new.case = this.cf;
            return super.new;
        }
        if (i.do(array, bg.ca) == 0) {
            super.new.char = 2;
            super.new.case = this.cs;
            return super.new;
        }
        if (i.do(array, bg.b7) == 0) {
            super.new.char = 2;
            super.new.case = this.cc;
            return super.new;
        }
        if (i.do(array, bg.b4) == 0) {
            super.new.char = 2;
            super.new.case = super.bi;
            return super.new;
        }
        if (i.do(array, bg.cd) == 0) {
            super.new.char = 2;
            super.new.case = this.b3;
            return super.new;
        }
        return super.a(array);
    }
    
    public void a(final char[] array, final bi bi) {
        if (i.do(array, an.o) == 0) {
            if (bi.char == 4) {
                this.ck = new String(bi.int, 0, i.a(bi.int));
                this.k();
                super.do = true;
            }
        }
        else if (i.do(array, bg.cp) == 0) {
            if (bi.char == 4) {
                this.ce = new String(bi.int, 0, i.a(bi.int));
                this.k();
                super.do = true;
            }
        }
        else if (i.do(array, bg.cm) == 0) {
            if (bi.char == 1) {
                this.cb = bi.long;
                this.k();
                super.do = true;
            }
        }
        else if (i.do(array, bg.cr) == 0) {
            if (bi.char == 1) {
                this.ch = bi.long;
                this.k();
                super.do = true;
            }
        }
        else if (i.do(array, bg.cq) == 0) {
            if (bi.char == 2) {
                this.cf = (int)bi.case;
                this.k();
                super.do = true;
            }
        }
        else if (i.do(array, bg.ca) == 0) {
            if (bi.char == 2) {
                this.cs = (int)bi.case;
                this.cj = true;
            }
        }
        else if (i.do(array, bg.b7) == 0) {
            if (bi.char == 2) {
                this.cc = (int)bi.case;
                this.cg = true;
                this.cj = true;
            }
        }
        else if (i.do(array, bg.b4) == 0) {
            if (bi.char == 2) {
                super.bi = (int)bi.case;
                this.cj = true;
            }
        }
        else if (i.do(array, bg.cd) == 0) {
            if (bi.char == 2) {
                this.b3 = (int)bi.case;
                this.cj = true;
            }
        }
        else {
            super.a(array, bi);
        }
    }
}
