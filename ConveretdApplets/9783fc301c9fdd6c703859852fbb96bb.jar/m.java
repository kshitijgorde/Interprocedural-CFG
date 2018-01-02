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
    public Vector ck;
    public Vector cj;
    public Hashtable ci;
    public l ch;
    public Color cg;
    public Color cf;
    public Font ce;
    public Font cd;
    public int cc;
    public int ca;
    public Image b9;
    public Graphics b8;
    public FontMetrics b7;
    public FontMetrics b6;
    public int b5;
    public int b4;
    public int b3;
    public int b2;
    public int b1;
    public boolean b0;
    public String a3;
    public String b_;
    public String bz;
    public String by;
    public String bx;
    public String bw;
    public String bv;
    public boolean bu;
    public Chat a1;
    public Dimension bt;
    
    public m(final int n, final int n2) {
        this.cg = Color.white;
        this.cf = Color.black;
        this.ce = new Font("Courier", 0, 12);
        this.cd = new Font("Courier", 1, 12);
        this.b3 = 3;
        this.b0 = false;
        this.a3 = "\u0003";
        this.b_ = "\u0002";
        this.bz = "\u0016";
        this.by = "\u001f";
        this.bx = "\u000f";
        this.bw = "\u0010";
        this.bv = "";
        this.bu = false;
        this.resize(n, n2);
        final boolean b = false;
        this.ca = (b ? 1 : 0);
        this.cc = (b ? 1 : 0);
        this.setFont(this.ce);
        this.ck = new Vector(100, 10);
        this.cj = new Vector();
        this.ci = new Hashtable();
        this.bt = this.getSize();
    }
    
    public final void j(final Chat a1) {
        this.a1 = a1;
    }
    
    public final boolean ap() {
        this.a9();
        return this.ba();
    }
    
    public final boolean ba() {
        this.b9 = this.createImage(this.b1 + this.b3, this.b2);
        if (this.b9 == null) {
            return this.b0 = false;
        }
        (this.b8 = this.b9.getGraphics()).setColor(this.cg);
        this.b8.fillRect(0, 0, this.b1 + this.b3, this.b2);
        return this.b0 = true;
    }
    
    public final void a9() {
        this.bt = this.size();
        this.b2 = 5 * this.bt.height;
        this.b1 = this.bt.width;
        final int height = this.bt.height;
    }
    
    public final void setFont(final Font ce) {
        this.ce = ce;
        this.b7 = this.getFontMetrics(this.ce);
        this.b5 = this.b7.getHeight() + this.b3;
        this.cd = new Font(ce.getName(), 1, ce.getSize());
        this.b6 = this.getFontMetrics(this.cd);
    }
    
    public final int a8() {
        return this.b5;
    }
    
    public final int a7() {
        return this.b4;
    }
    
    public final void a6(final int n, final int ca) {
        this.cc = 0;
        this.ca = ca;
    }
    
    public final void a5() {
        final int size = this.ck.size();
        int n = 0;
        if (!this.b0) {
            return;
        }
        this.b8.setColor(this.cg);
        this.b8.fillRect(0, 0, this.b1 + this.b3, this.b2);
        for (int i = 0; i < size; ++i) {
            final i j = this.ck.elementAt(i);
            final int size2 = j.bc.size();
            int n2 = 3;
            if (j.v > this.b5) {
                final int v = j.v;
                final int n3 = (v + n) % this.b5;
                if (n3 > 0) {
                    j.v = v + (this.b5 - n3);
                }
                n += j.v;
            }
            else {
                n += this.b5;
            }
            for (int k = 0; k < size2; ++k) {
                final d d = j.bc.elementAt(k);
                final int n4 = n - this.b7.getHeight();
                if (d.p) {
                    final Image image = this.a1.hk.get(d.w);
                    if (image != null) {
                        this.b8.drawImage(image, n2, n - d.v + 2 * this.b3, this);
                        n2 += image.getWidth(null);
                        continue;
                    }
                }
                if (d.s) {
                    this.b8.setFont(this.cd);
                }
                else {
                    this.b8.setFont(this.ce);
                }
                final int a1 = this.a1(d, d.w);
                if (d.t != null) {
                    this.b8.setColor(d.t);
                    this.b8.fillRect(n2, n4, a1, this.b5);
                }
                this.b8.setColor(d.u);
                this.b8.drawString(d.w, n2, n);
                if (d.r || d.q) {
                    this.b8.setColor(d.u);
                    this.b8.drawLine(n2, n, n2 + a1, n);
                }
                n2 += this.b7.stringWidth(d.w);
            }
        }
        this.b4 = n + this.b5;
    }
    
    public final void paint(final Graphics graphics) {
        if (!this.b0 && this.ap()) {
            for (int size = this.cj.size(), i = 0; i < size; ++i) {
                this.a4((String)this.cj.elementAt(i));
            }
            this.a5();
            this.cj.removeAllElements();
        }
        graphics.translate(-this.cc, -this.ca);
        if (this.b9 != null) {
            graphics.drawImage(this.b9, 0, 0, null);
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
    
    public final void a4(final String s) {
        this.a4(s, this.cf);
    }
    
    public final synchronized void a4(String s, final Color color) {
        Color cf = color;
        String s2 = "";
        int stringWidth = 0;
        final int n = this.size().width - this.b3;
        i i = new i();
        if (!this.b0) {
            this.cj.addElement(s);
            return;
        }
        final Dimension size = this.size();
        if (!size.equals(this.bt)) {
            this.a9();
            this.ba();
            this.bt = size;
        }
        if (this.bu) {
            try {
                s = new String(s.getBytes(), this.bv);
            }
            catch (Exception ex) {
                ex.printStackTrace();
                this.bu = false;
            }
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(s, " ", true);
        if (color == null) {
            cf = this.cf;
        }
        d d = new d(cf);
        final d d2 = new d(cf);
        while (stringTokenizer.hasMoreTokens()) {
            String bp = stringTokenizer.nextToken();
            final Image image = this.a1.hk.get(bp);
            int height = -1;
            boolean b;
            int n2;
            if (image == null) {
                b = false;
                n2 = this.b7.stringWidth(bp);
            }
            else {
                b = true;
                n2 = image.getWidth(null);
                height = image.getHeight(null);
                if (n2 == -1) {
                    n2 = this.b7.stringWidth(bp);
                }
            }
            if (stringWidth + n2 >= n) {
                d.l(s2);
                i.au(d);
                this.a3(i);
                i = new i();
                d = new d(d2.t, d2.u, d2.s, d2.r);
                s2 = "  ";
                stringWidth = this.b7.stringWidth("  ");
            }
            if (bp.startsWith("http://")) {
                this.ch = new l();
                this.ch.br = stringWidth;
                this.ch.bq = stringWidth + this.a1(d, bp);
                this.ch.bp = bp;
                this.ch.bs = this.ck.size();
                this.ci.put(String.valueOf(this.ch.bs), this.ch);
                bp = String.valueOf(this.by) + bp + this.by;
            }
            if (b) {
                bp = String.valueOf(this.bw) + bp + this.bw;
            }
            int a0;
            while ((a0 = this.a0(bp.indexOf(this.a3), bp.indexOf(this.by), bp.indexOf(this.bz), bp.indexOf(this.bx), bp.indexOf(this.bw), bp.indexOf(this.b_))) >= 0) {
                boolean b2 = false;
                int n3 = a0 + 1;
                if (bp.charAt(a0) == this.b_.charAt(0)) {
                    d2.s = !d2.s;
                    b2 = true;
                }
                else if (bp.endsWith(this.bw)) {
                    if (bp.startsWith(this.bw)) {
                        d2.p = true;
                    }
                    else {
                        d2.p = false;
                    }
                    b2 = true;
                }
                else if (bp.charAt(a0) == this.bz.charAt(0)) {
                    final Color u = d2.u;
                    if (d2.t != null) {
                        d2.u = d2.t;
                    }
                    else {
                        d2.u = this.cg;
                    }
                    d2.t = u;
                    b2 = true;
                }
                else if (bp.charAt(a0) == this.by.charAt(0)) {
                    d2.r = !d2.r;
                    b2 = true;
                }
                else if (bp.charAt(a0) == this.bx.charAt(0)) {
                    d2.k();
                    d2.u = cf;
                    b2 = true;
                }
                else if (bp.charAt(a0) == this.a3.charAt(0)) {
                    n3 += this.a2(d2, bp.substring(a0));
                    b2 = true;
                }
                if (b2) {
                    final String substring = bp.substring(n3);
                    final String substring2 = bp.substring(0, a0);
                    d.l(String.valueOf(s2) + substring2);
                    i.au(d);
                    stringWidth += this.a1(d, substring2);
                    bp = substring;
                    s2 = "";
                    d = new d(d2.t, d2.u, d2.s, d2.r);
                    d.p = d2.p;
                    if (!d.p) {
                        continue;
                    }
                    d.v = height + this.b3;
                }
            }
            s2 = String.valueOf(s2) + bp;
            stringWidth += this.a1(d, bp);
        }
        if (s2.endsWith(" ")) {
            s2 = s2.substring(0, s2.length() - 1);
        }
        d.l(s2);
        i.au(d);
        this.a3(i);
        this.a5();
    }
    
    public final void a3(final i i) {
        int b4 = this.b4;
        int n;
        if (i.v > this.b5) {
            n = i.v;
        }
        else {
            n = this.b5;
        }
        while (b4 + n >= this.b2) {
            try {
                if (this.ck.elementAt(0).v > this.b5) {
                    b4 -= i.v;
                }
                else {
                    b4 -= this.b5;
                }
                this.ck.removeElementAt(0);
                if (this.ci.containsKey("0")) {
                    System.out.println("remove hash at 0");
                    this.ci.remove("0");
                }
            }
            catch (Exception ex) {}
            if (this.ci.size() > 0) {
                final Hashtable<String, l> ci = new Hashtable<String, l>();
                final Enumeration<String> keys = (Enumeration<String>)this.ci.keys();
                while (keys.hasMoreElements()) {
                    final String s = keys.nextElement();
                    final l l = this.ci.get(s);
                    int int1 = Integer.parseInt(s);
                    --int1;
                    ci.put(String.valueOf(int1), l);
                }
                this.ci = ci;
            }
            if (this.ck.size() <= 0) {
                break;
            }
        }
        this.ck.addElement(i);
    }
    
    public final boolean handleEvent(final Event event) {
        final l l;
        if (event.id == 501 && (l = this.ci.get(String.valueOf((this.ca + event.y) / this.b5))) != null && event.x <= l.bq && event.x >= l.br && this.a1 != null) {
            this.a1.bx(l.bp);
        }
        return super.handleEvent(event);
    }
    
    public final void h() {
        this.ck.removeAllElements();
        this.a5();
    }
    
    public final int a2(final d d, final String s) {
        int int1 = -1;
        int n = -1;
        int n2 = 0;
        if (!s.startsWith(this.a3)) {
            return 0;
        }
        final int index = s.indexOf(",");
        if (index == 1) {
            return 0;
        }
        Label_0199: {
            if (index >= 0) {
                if (index <= 3) {
                    try {
                        n = Integer.parseInt(s.substring(index - 1, index));
                        final int int2 = Integer.parseInt(s.substring(index - 2, index - 1));
                        if (int2 == 1) {
                            n += int2 * 10;
                        }
                    }
                    catch (Exception ex) {}
                    try {
                        int1 = Integer.parseInt(s.substring(index + 1, index + 2));
                        final int int3 = Integer.parseInt(s.substring(index + 2, index + 3));
                        if (int1 == 1) {
                            int1 = int1 * 10 + int3;
                        }
                    }
                    catch (Exception ex2) {}
                    break Label_0199;
                }
            }
            try {
                n = Integer.parseInt(s.substring(1, 2));
                final int int4 = Integer.parseInt(s.substring(2, 3));
                if (n == 1) {
                    n = n * 10 + int4;
                }
            }
            catch (Exception ex3) {}
        }
        if (n >= 0 && n <= 15) {
            d.u = o.ColorArray[n];
            if (n <= 9) {
                n2 = 1;
            }
            else {
                n2 = 2;
            }
        }
        if (int1 >= 0 && int1 <= 15) {
            d.t = o.ColorArray[int1];
            ++n2;
            if (int1 <= 9) {
                ++n2;
            }
            else {
                n2 += 2;
            }
        }
        return n2;
    }
    
    public final int a1(final d d, final String s) {
        int n;
        if (d.s) {
            n = this.b6.stringWidth(s);
        }
        else {
            n = this.b7.stringWidth(s);
        }
        if (d.p) {
            final Image image = this.a1.hk.get(s);
            if (image != null) {
                n = image.getWidth(null);
            }
        }
        return n;
    }
    
    public final int a0(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        return this.a0(this.a0(this.a0(this.a0(this.a0(n, n2), n3), n4), n5), n6);
    }
    
    public final int a0(final int n, final int n2) {
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
    
    public final void f(final String bv) {
        if (bv == null || bv.equals("")) {
            return;
        }
        this.bv = bv;
        this.bu = true;
    }
}
