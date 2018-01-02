import java.awt.Event;
import java.util.Enumeration;
import java.util.StringTokenizer;
import java.awt.image.ImageObserver;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Font;
import java.awt.Color;
import java.util.Hashtable;
import java.util.Vector;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class m extends Canvas
{
    public Vector cj;
    public Vector ci;
    public Hashtable ch;
    public l cg;
    public Color cf;
    public Color ce;
    public Font cd;
    public Font cc;
    public int ca;
    public int b9;
    public Image b8;
    public Graphics b7;
    public FontMetrics b6;
    public FontMetrics b5;
    public int b4;
    public int b3;
    public int b2;
    public int b1;
    public int b0;
    public boolean b_;
    public String a2;
    public String bz;
    public String by;
    public String bx;
    public String bw;
    public String bv;
    public String bu;
    public boolean bt;
    public Chat a0;
    public Dimension bs;
    
    public m(final int n, final int n2) {
        this.cf = Color.white;
        this.ce = Color.black;
        this.cd = new Font("Courier", 0, 12);
        this.cc = new Font("Courier", 1, 12);
        this.b2 = 3;
        this.b_ = false;
        this.a2 = "\u0003";
        this.bz = "\u0002";
        this.by = "\u0016";
        this.bx = "\u001f";
        this.bw = "\u000f";
        this.bv = "\u0010";
        this.bu = "";
        this.bt = false;
        this.resize(n, n2);
        final boolean b = false;
        this.b9 = (b ? 1 : 0);
        this.ca = (b ? 1 : 0);
        this.setFont(this.cd);
        this.cj = new Vector(100, 10);
        this.ci = new Vector();
        this.ch = new Hashtable();
        this.bs = this.size();
    }
    
    public final void j(final Chat a0) {
        this.a0 = a0;
    }
    
    public final boolean ar() {
        this.bc();
        return this.bd();
    }
    
    public final boolean bd() {
        this.b8 = this.createImage(this.b0 + this.b2, this.b1);
        if (this.b8 == null) {
            return this.b_ = false;
        }
        (this.b7 = this.b8.getGraphics()).setColor(this.cf);
        this.b7.fillRect(0, 0, this.b0 + this.b2, this.b1);
        return this.b_ = true;
    }
    
    public final void bc() {
        this.bs = this.size();
        this.b1 = 5 * this.bs.height;
        this.b0 = this.bs.width;
        final int height = this.bs.height;
    }
    
    public final void setFont(final Font cd) {
        this.cd = cd;
        this.b6 = this.getFontMetrics(this.cd);
        this.b4 = this.b6.getHeight() + this.b2;
        this.cc = new Font(cd.getName(), 1, cd.getSize());
        this.b5 = this.getFontMetrics(this.cc);
    }
    
    public final int bb() {
        return this.b4;
    }
    
    public final int ba() {
        return this.b3;
    }
    
    public final void a9(final int n, final int b9) {
        this.ca = 0;
        this.b9 = b9;
    }
    
    public final void a8() {
        final int size = this.cj.size();
        int n = 0;
        if (!this.b_) {
            return;
        }
        this.b7.setColor(this.cf);
        this.b7.fillRect(0, 0, this.b0 + this.b2, this.b1);
        for (int i = 0; i < size; ++i) {
            final i j = this.cj.elementAt(i);
            final int size2 = j.ba.size();
            int n2 = 3;
            if (j.v > this.b4) {
                final int v = j.v;
                final int n3 = (v + n) % this.b4;
                if (n3 > 0) {
                    j.v = v + (this.b4 - n3);
                }
                n += j.v;
            }
            else {
                n += this.b4;
            }
            for (int k = 0; k < size2; ++k) {
                final d d = j.ba.elementAt(k);
                final int n4 = n - this.b6.getHeight();
                if (d.p) {
                    final Image image = this.a0.h0.get(d.w);
                    if (image != null) {
                        this.b7.drawImage(image, n2, n - d.v + 2 * this.b2, this);
                        n2 += image.getWidth(null);
                        continue;
                    }
                }
                if (d.s) {
                    this.b7.setFont(this.cc);
                }
                else {
                    this.b7.setFont(this.cd);
                }
                final int a4 = this.a4(d, d.w);
                if (d.t != null) {
                    this.b7.setColor(d.t);
                    this.b7.fillRect(n2, n4, a4, this.b4);
                }
                this.b7.setColor(d.u);
                this.b7.drawString(d.w, n2, n);
                if (d.r || d.q) {
                    this.b7.setColor(d.u);
                    this.b7.drawLine(n2, n, n2 + a4, n);
                }
                n2 += this.b6.stringWidth(d.w);
            }
        }
        this.b3 = n + this.b4;
    }
    
    public final void paint(final Graphics graphics) {
        if (!this.b_ && this.ar()) {
            for (int size = this.ci.size(), i = 0; i < size; ++i) {
                this.a7((String)this.ci.elementAt(i));
            }
            this.a8();
            this.ci.removeAllElements();
        }
        graphics.translate(-this.ca, -this.b9);
        if (this.b8 != null) {
            graphics.drawImage(this.b8, 0, 0, null);
        }
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final void repaint() {
        final Graphics graphics = this.getGraphics();
        if (graphics != null) {
            this.update(graphics);
        }
    }
    
    public final void a7(final String s) {
        this.a7(s, this.ce);
    }
    
    public final synchronized void a7(String s, final Color color) {
        Color ce = color;
        String s2 = "";
        int stringWidth = 0;
        final int n = this.size().width - this.b2;
        i i = new i();
        if (!this.b_) {
            this.ci.addElement(s);
            return;
        }
        final Dimension size = this.size();
        if (!size.equals(this.bs)) {
            this.bc();
            this.bd();
            this.bs = size;
        }
        if (this.bt) {
            try {
                s = new String(s.getBytes(), this.bu);
            }
            catch (Exception ex) {
                ex.printStackTrace();
                this.bt = false;
            }
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(s, " ", true);
        if (color == null) {
            ce = this.ce;
        }
        d d = new d(ce);
        final d d2 = new d(ce);
        while (stringTokenizer.hasMoreTokens()) {
            String bo = stringTokenizer.nextToken();
            final Image image = this.a0.h0.get(bo);
            int height = -1;
            boolean b;
            int n2;
            if (image == null) {
                b = false;
                n2 = this.b6.stringWidth(bo);
            }
            else {
                b = true;
                n2 = image.getWidth(null);
                height = image.getHeight(null);
                if (n2 == -1) {
                    n2 = this.b6.stringWidth(bo);
                }
            }
            if (stringWidth + n2 >= n) {
                d.l(s2);
                i.ax(d);
                this.a6(i);
                i = new i();
                d = new d(d2.t, d2.u, d2.s, d2.r);
                s2 = "  ";
                stringWidth = this.b6.stringWidth("  ");
            }
            if (bo.startsWith("http://")) {
                this.cg = new l();
                this.cg.bq = stringWidth;
                this.cg.bp = stringWidth + this.a4(d, bo);
                this.cg.bo = bo;
                this.cg.br = this.cj.size();
                this.ch.put(String.valueOf(this.cg.br), this.cg);
                bo = String.valueOf(this.bx) + bo + this.bx;
            }
            if (b) {
                bo = String.valueOf(this.bv) + bo + this.bv;
            }
            int a3;
            while ((a3 = this.a3(bo.indexOf(this.a2), bo.indexOf(this.bx), bo.indexOf(this.by), bo.indexOf(this.bw), bo.indexOf(this.bv), bo.indexOf(this.bz))) >= 0) {
                boolean b2 = false;
                int n3 = a3 + 1;
                if (bo.charAt(a3) == this.bz.charAt(0)) {
                    d2.s = !d2.s;
                    b2 = true;
                }
                else if (bo.endsWith(this.bv)) {
                    if (bo.startsWith(this.bv)) {
                        d2.p = true;
                    }
                    else {
                        d2.p = false;
                    }
                    b2 = true;
                }
                else if (bo.charAt(a3) == this.by.charAt(0)) {
                    final Color u = d2.u;
                    if (d2.t != null) {
                        d2.u = d2.t;
                    }
                    else {
                        d2.u = this.cf;
                    }
                    d2.t = u;
                    b2 = true;
                }
                else if (bo.charAt(a3) == this.bx.charAt(0)) {
                    d2.r = !d2.r;
                    b2 = true;
                }
                else if (bo.charAt(a3) == this.bw.charAt(0)) {
                    d2.k();
                    d2.u = ce;
                    b2 = true;
                }
                else if (bo.charAt(a3) == this.a2.charAt(0)) {
                    n3 += this.a5(d2, bo.substring(a3));
                    b2 = true;
                }
                if (b2) {
                    final String substring = bo.substring(n3);
                    final String substring2 = bo.substring(0, a3);
                    d.l(String.valueOf(s2) + substring2);
                    i.ax(d);
                    stringWidth += this.a4(d, substring2);
                    bo = substring;
                    s2 = "";
                    d = new d(d2.t, d2.u, d2.s, d2.r);
                    d.p = d2.p;
                    if (!d.p) {
                        continue;
                    }
                    d.v = height + this.b2;
                }
            }
            s2 = String.valueOf(s2) + bo;
            stringWidth += this.a4(d, bo);
        }
        if (s2.endsWith(" ")) {
            s2 = s2.substring(0, s2.length() - 1);
        }
        d.l(s2);
        i.ax(d);
        this.a6(i);
        this.a8();
    }
    
    public final void a6(final i i) {
        int b3 = this.b3;
        int n;
        if (i.v > this.b4) {
            n = i.v;
        }
        else {
            n = this.b4;
        }
        while (b3 + n >= this.b1) {
            try {
                if (this.cj.elementAt(0).v > this.b4) {
                    b3 -= i.v;
                }
                else {
                    b3 -= this.b4;
                }
                this.cj.removeElementAt(0);
                if (this.ch.containsKey("0")) {
                    this.ch.remove("0");
                }
            }
            catch (Exception ex) {}
            if (this.ch.size() > 0) {
                final Hashtable<String, l> ch = new Hashtable<String, l>();
                final Enumeration<String> keys = (Enumeration<String>)this.ch.keys();
                while (keys.hasMoreElements()) {
                    final String s = keys.nextElement();
                    final l l = this.ch.get(s);
                    int int1 = Integer.parseInt(s);
                    --int1;
                    ch.put(String.valueOf(int1), l);
                }
                this.ch = ch;
            }
            if (this.cj.size() <= 0) {
                break;
            }
        }
        this.cj.addElement(i);
    }
    
    public final boolean handleEvent(final Event event) {
        final l l;
        if (event.id == 501 && (l = this.ch.get(String.valueOf((this.b9 + event.y) / this.b4))) != null && event.x <= l.bp && event.x >= l.bq && this.a0 != null) {
            this.a0.b2(l.bo);
        }
        return super.handleEvent(event);
    }
    
    public final void h() {
        this.cj.removeAllElements();
        this.a8();
    }
    
    public final int a5(final d d, final String s) {
        int n = 1;
        int n2 = -1;
        int n3 = -1;
        final int length = s.length();
        if (!s.startsWith(this.a2)) {
            return 0;
        }
        if (n < length && Character.isDigit(s.charAt(n))) {
            if (n + 1 < length && Character.isDigit(s.charAt(n + 1))) {
                n2 = Integer.parseInt(s.substring(n, n + 2));
                ++n;
            }
            else {
                n2 = Integer.parseInt(s.substring(n, n + 1));
            }
            if (++n < length && s.charAt(n) == ',' && ++n < length && Character.isDigit(s.charAt(n))) {
                if (n + 1 < length && Character.isDigit(s.charAt(n + 1))) {
                    n3 = Integer.parseInt(s.substring(n, n + 2));
                    ++n;
                }
                else {
                    n3 = Integer.parseInt(s.substring(n, n + 1));
                }
                ++n;
            }
        }
        if (n2 >= 0 && n2 <= 15) {
            d.u = o.ColorArray[n2];
        }
        if (n3 >= 0 && n3 <= 15) {
            d.t = o.ColorArray[n3];
        }
        return n - 1;
    }
    
    public final int a4(final d d, final String s) {
        int n;
        if (d.s) {
            n = this.b5.stringWidth(s);
        }
        else {
            n = this.b6.stringWidth(s);
        }
        if (d.p) {
            final Image image = this.a0.h0.get(s);
            if (image != null) {
                n = image.getWidth(null);
            }
        }
        return n;
    }
    
    public final int a3(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        return this.a3(this.a3(this.a3(this.a3(this.a3(n, n2), n3), n4), n5), n6);
    }
    
    public final int a3(final int n, final int n2) {
        int min = n;
        if (n2 >= 0) {
            if (n >= 0) {
                min = Math.min(n, n2);
            }
            else {
                min = n2;
            }
        }
        return min;
    }
    
    public final void f(final String bu) {
        if (bu == null || bu.equals("")) {
            return;
        }
        this.bu = bu;
        this.bt = true;
    }
}
