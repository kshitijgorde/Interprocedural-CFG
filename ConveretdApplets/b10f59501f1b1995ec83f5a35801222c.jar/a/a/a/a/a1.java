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

public class a1 extends bm
{
    static final char[] co;
    static final char[] cp;
    static final char[] cq;
    static final char[] cl;
    static final char[] b7;
    static final char[] b9;
    static final char[] b6;
    static final char[] b3;
    static final char[] cc;
    private char[] b5;
    boolean ck;
    private Vector ch;
    private String cd;
    private int ce;
    private boolean cf;
    private int cr;
    private int cb;
    private boolean cg;
    private boolean ca;
    private String cj;
    private int b2;
    ad b4;
    Font cn;
    int b8;
    int cm;
    boolean ci;
    
    static {
        co = new char[] { 'f', 'o', 'n', 't', '\0' };
        cp = new char[] { 's', 'i', 'z', 'e', '\0' };
        cq = new char[] { 'b', 'o', 'l', 'd', '\0' };
        cl = new char[] { 'i', 't', 'a', 'l', 'i', 'c', '\0' };
        b7 = new char[] { 'u', 'n', 'd', 'e', 'r', 'l', 'i', 'n', 'e', '\0' };
        b9 = new char[] { 'f', 'o', 'r', 'e', 'g', 'r', 'o', 'u', 'n', 'd', 'c', 'o', 'l', 'o', 'r', '\0' };
        b6 = new char[] { 's', 'h', 'a', 'd', 'o', 'w', 'c', 'o', 'l', 'o', 'r', '\0' };
        b3 = new char[] { 'b', 'g', 'c', 'o', 'l', 'o', 'r', '\0' };
        cc = new char[] { 'b', 'o', 'r', 'd', 'e', 'r', 'c', 'o', 'l', 'o', 'r', '\0' };
    }
    
    public a1() {
        this.b5 = new char[] { 't', 'e', 'x', 't', '\0' };
        this.ck = true;
        this.ch = null;
        this.cd = "Arial";
        this.ce = 12;
        this.cf = false;
        this.cr = -16777216;
        this.cb = 0;
        this.cg = false;
        this.ca = false;
        this.cj = "";
        this.b2 = 0;
        this.b4 = null;
        this.cn = null;
        this.b8 = 0;
        this.cm = 1;
        this.ci = false;
    }
    
    public void if() {
        super.if();
        this.b5 = null;
        this.ch.removeAllElements();
        if (this.b4 != null) {
            this.b4.if();
        }
        this.b4 = null;
        this.cn = null;
        this.cd = null;
        this.cj = null;
        this.cn = null;
    }
    
    public void a(final ac b, final a2 a2, final ae ae, final v br) {
        super.b = b;
        super.a9 = this;
        super.case = this.b5;
        super.bh = 0;
        super.br = br;
        super.a(a2, ae);
        this.ch = new Vector();
        for (int i = 0; i < a2.do; ++i) {
            if (a2.try[i].toLowerCase().compareTo("foregroundcolor") == 0) {
                this.cr = ac.a(a2.new[i]);
            }
            else if (a2.try[i].toLowerCase().compareTo("shadowcolor") == 0) {
                this.cb = ac.a(a2.new[i]);
                this.cf = true;
            }
            else if (a2.try[i].toLowerCase().compareTo("italic") == 0) {
                this.ca = Boolean.valueOf(a2.new[i]);
            }
            else if (a2.try[i].toLowerCase().compareTo("bold") == 0) {
                this.cg = Boolean.valueOf(a2.new[i]);
            }
            else if (a2.try[i].toLowerCase().compareTo("size") == 0) {
                this.ce = new Integer(a2.new[i]);
            }
            else if (a2.try[i].toLowerCase().compareTo("font") == 0) {
                this.cd = a2.new[i];
            }
            else if (a2.try[i].toLowerCase().compareTo("bordercolor") == 0) {
                this.b2 = ac.a(a2.new[i]);
            }
            else if (a2.try[i].toLowerCase().compareTo("bgcolor") == 0) {
                super.bh = ac.a(a2.new[i]);
            }
        }
        this.if(a2);
        if (a2.case != null) {
            this.cj = a2.case.do;
        }
        this.cj = this.cj.trim();
        this.m();
        super.byte = true;
    }
    
    public void m() {
        this.ck = true;
        this.ch.removeAllElements();
        int i = 0;
        int n = 0;
        while (i != -1) {
            i = this.cj.indexOf("\n", n);
            if (i != -1) {
                this.ch.addElement(this.cj.substring(n, i));
                n = i + 1;
            }
        }
        if (i == 0) {
            this.ch.addElement(this.cj);
        }
        else if (n < this.cj.length()) {
            this.ch.addElement(this.cj.substring(n, this.cj.length()));
        }
        if (this.cg) {
            this.b8 = 1;
        }
        else {
            this.b8 = 0;
        }
        if (this.ca) {
            this.b8 |= 0x2;
        }
        this.cn = new Font(this.cd, this.b8, this.ce);
        final Graphics graphics = ac.K.getGraphics();
        graphics.setFont(this.cn);
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        this.b4 = new ad();
        this.b4.j = 0;
        this.b4.u = 0;
        for (int j = 0; j < this.ch.size(); ++j) {
            final int stringWidth = fontMetrics.stringWidth(this.ch.elementAt(j));
            if (stringWidth > this.b4.j) {
                this.b4.j = stringWidth;
            }
        }
        this.b4.u = fontMetrics.getHeight();
        if (this.b4.u <= 0 || this.b4.j <= 0) {
            return;
        }
        if (this.cf) {
            final ad b4 = this.b4;
            b4.j += this.cm;
            final ad b5 = this.b4;
            b5.u += this.cm;
        }
        final ad b6 = this.b4;
        b6.u *= this.ch.size();
        final ad b7 = this.b4;
        b7.j += 8;
        this.b4.v = true;
        final int n2 = this.b4.j * this.b4.u;
        final Image image = ac.K.createImage(this.b4.j, this.b4.u);
        super.aH = this.b4.j;
        super.aJ = this.b4.u;
        final int[] array = null;
        this.b4.q = new int[n2];
        int[] a;
        if (ac.ah < 3 || !ac.af) {
            a = new int[n2];
        }
        else {
            a = s.a(image, n2);
        }
        for (int k = 0; k < n2; ++k) {
            this.b4.q[k] = super.bh;
        }
        final Graphics graphics2 = image.getGraphics();
        graphics2.setFont(this.cn);
        if (this.cf) {
            this.a(graphics2, image, a, this.b4.q, super.aH, super.aJ, this.ch, this.cb, true, fontMetrics.getHeight(), fontMetrics.getAscent());
        }
        this.a(graphics2, image, a, this.b4.q, super.aH, super.aJ, this.ch, this.cr, false, fontMetrics.getHeight(), fontMetrics.getAscent());
        if ((this.b2 & 0xFF000000) != 0x0) {
            for (int l = 0; l < this.b4.j; ++l) {
                this.b4.q[l] = this.b2;
            }
            final int n3 = (this.b4.u - 1) * this.b4.j;
            for (int n4 = n3 + this.b4.j, n5 = n3; n5 < n4; ++n5) {
                this.b4.q[n5] = this.b2;
            }
            int n6 = this.b4.j - 1;
            for (int n7 = 0; n7 < this.b4.u - 1; ++n7) {
                this.b4.q[n6] = this.b2;
                this.b4.q[n6 + 1] = this.b2;
                n6 += this.b4.j;
            }
        }
        super.aH = this.b4.j;
        super.aJ = this.b4.u;
        this.ck = false;
    }
    
    private void a(final Graphics graphics, final Image image, final int[] array, final int[] array2, final int n, final int n2, final Vector vector, final int n3, final boolean b, final int n4, final int n5) {
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, n, n2);
        graphics.setColor(Color.white);
        for (int i = 0; i < vector.size(); ++i) {
            final String s = vector.elementAt(i);
            final int n6 = n4 * i + n5;
            if (b) {
                graphics.drawString(s, this.cm + 4, this.cm + n6);
            }
            else {
                graphics.drawString(s, 4, n6);
            }
        }
        Label_0165: {
            if (ac.ah >= 3) {
                if (ac.af) {
                    break Label_0165;
                }
            }
            try {
                final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, this.b4.j, this.b4.u, array, 0, this.b4.j);
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
            if (this.ci) {
                this.m();
                this.ci = false;
                super.do = true;
            }
            final boolean do1 = super.do;
            super.do = false;
            return (this.for(n) & super.for) | do1;
        }
        this.d();
        return false;
    }
    
    public void f() {
        if (super.for && !this.ck) {
            if (((super.bh & 0xFF000000) != 0x0 && (super.bh & 0xFF000000) != 0xFF000000) || ((this.cr & 0xFF000000) != 0x0 && (this.cr & 0xFF000000) != 0xFF000000) || (this.cf && (this.cb & 0xFF000000) != 0x0 && (this.cb & 0xFF000000) != 0xFF000000) || ((this.b2 & 0xFF000000) != 0x0 && (this.b2 & 0xFF000000) != 0xFF000000)) {
                this.b4.p = true;
            }
            else {
                this.b4.p = false;
            }
            ap.a(this.b4, 0, this.b4.j, super.b.goto, super.bg, super.bf, this.b4.j, this.b4.u);
        }
    }
    
    void a(final ab ab) {
    }
    
    public a3 a(final char[] array) {
        if (g.do(array, ac.o) == 0) {
            super.new.char = 4;
            super.new.int = (String.valueOf(this.cj) + "\u0000").toCharArray();
            return super.new;
        }
        if (g.do(array, a1.co) == 0) {
            super.new.char = 4;
            super.new.int = (String.valueOf(this.cd) + "\u0000").toCharArray();
            return super.new;
        }
        if (g.do(array, a1.cl) == 0) {
            super.new.char = 1;
            super.new.long = this.ca;
            return super.new;
        }
        if (g.do(array, a1.cq) == 0) {
            super.new.char = 1;
            super.new.long = this.cg;
            return super.new;
        }
        if (g.do(array, a1.cp) == 0) {
            super.new.char = 2;
            super.new.case = this.ce;
            return super.new;
        }
        if (g.do(array, a1.b9) == 0) {
            super.new.char = 2;
            super.new.case = this.cr;
            return super.new;
        }
        if (g.do(array, a1.b6) == 0) {
            super.new.char = 2;
            super.new.case = this.cb;
            return super.new;
        }
        if (g.do(array, a1.b3) == 0) {
            super.new.char = 2;
            super.new.case = super.bh;
            return super.new;
        }
        if (g.do(array, a1.cc) == 0) {
            super.new.char = 2;
            super.new.case = this.b2;
            return super.new;
        }
        return super.a(array);
    }
    
    public void a(final char[] array, final a3 a3) {
        if (g.do(array, ac.o) == 0) {
            if (a3.char == 4) {
                this.cj = new String(a3.int, 0, g.a(a3.int));
                this.m();
                super.do = true;
            }
        }
        else if (g.do(array, a1.co) == 0) {
            if (a3.char == 4) {
                this.cd = new String(a3.int, 0, g.a(a3.int));
                this.m();
                super.do = true;
            }
        }
        else if (g.do(array, a1.cl) == 0) {
            if (a3.char == 1) {
                this.ca = a3.long;
                this.m();
                super.do = true;
            }
        }
        else if (g.do(array, a1.cq) == 0) {
            if (a3.char == 1) {
                this.cg = a3.long;
                this.m();
                super.do = true;
            }
        }
        else if (g.do(array, a1.cp) == 0) {
            if (a3.char == 2) {
                this.ce = (int)a3.case;
                this.m();
                super.do = true;
            }
        }
        else if (g.do(array, a1.b9) == 0) {
            if (a3.char == 2) {
                this.cr = (int)a3.case;
                this.ci = true;
            }
        }
        else if (g.do(array, a1.b6) == 0) {
            if (a3.char == 2) {
                this.cb = (int)a3.case;
                this.cf = true;
                this.ci = true;
            }
        }
        else if (g.do(array, a1.b3) == 0) {
            if (a3.char == 2) {
                super.bh = (int)a3.case;
                this.ci = true;
            }
        }
        else if (g.do(array, a1.cc) == 0) {
            if (a3.char == 2) {
                this.b2 = (int)a3.case;
                this.ci = true;
            }
        }
        else {
            super.a(array, a3);
        }
    }
}
