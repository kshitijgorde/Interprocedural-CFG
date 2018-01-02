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

public class l extends Canvas
{
    public Vector ce;
    public Vector cd;
    public Hashtable cc;
    public k ca;
    public Color b9;
    public Color b8;
    public Font b7;
    public Font b6;
    public int b5;
    public int b4;
    public Image b3;
    public Graphics b2;
    public FontMetrics b1;
    public FontMetrics b0;
    public int b_;
    public int bz;
    public int by;
    public int bx;
    public boolean bw;
    public String bv;
    public String bu;
    public String bt;
    public String bs;
    public String br;
    public String bq;
    public boolean bp;
    public Chat az;
    
    public l(final int n, final int n2) {
        this.b9 = Color.white;
        this.b8 = Color.black;
        this.b7 = new Font("Courier", 0, 12);
        this.b6 = new Font("Courier", 1, 12);
        this.bw = false;
        this.bv = "\u0003";
        this.bu = "\u0002";
        this.bt = "\u0016";
        this.bs = "\u001f";
        this.br = "\u000f";
        this.bq = "";
        this.bp = false;
        this.resize(n, n2);
        final boolean b = false;
        this.b4 = (b ? 1 : 0);
        this.b5 = (b ? 1 : 0);
        this.setFont(this.b7);
        this.ce = new Vector(100, 10);
        this.cd = new Vector();
        this.cc = new Hashtable();
    }
    
    public final void i(final Chat az) {
        this.az = az;
    }
    
    public final boolean ar() {
        final Dimension size = this.size();
        this.by = 10 * size.height;
        this.bx = size.width;
        final int height = size.height;
        this.b3 = this.createImage(this.bx + 3, this.by);
        if (this.b3 == null) {
            return false;
        }
        (this.b2 = this.b3.getGraphics()).setColor(this.b9);
        this.b2.fillRect(0, 0, this.bx + 3, this.by);
        return this.bw = true;
    }
    
    public final void setFont(final Font b7) {
        this.b7 = b7;
        this.b1 = this.getFontMetrics(this.b7);
        this.b_ = this.b1.getHeight() + 3;
        this.b6 = new Font(b7.getName(), 1, b7.getSize());
        this.b0 = this.getFontMetrics(this.b6);
    }
    
    public final int ba() {
        return this.b_;
    }
    
    public final int a9() {
        return this.bz;
    }
    
    public final void a8(final int n, final int b4) {
        this.b5 = 0;
        this.b4 = b4;
    }
    
    public final void a7() {
        final int size = this.ce.size();
        int bz = 15;
        if (!this.bw) {
            return;
        }
        this.b2.setColor(this.b9);
        this.b2.fillRect(0, 0, this.bx + 3, this.by);
        for (int i = 0; i < size; ++i) {
            final i j = this.ce.elementAt(i);
            final int size2 = j.a7.size();
            int n = 3;
            for (int k = 0; k < size2; ++k) {
                final d d = j.a7.elementAt(k);
                if (d.r) {
                    this.b2.setFont(this.b6);
                }
                else {
                    this.b2.setFont(this.b7);
                }
                final int n2 = bz - this.b1.getHeight();
                final int a3 = this.a3(d, d.u);
                if (d.s != null) {
                    this.b2.setColor(d.s);
                    this.b2.fillRect(n, n2, a3, this.b_);
                }
                this.b2.setColor(d.t);
                this.b2.drawString(d.u, n, bz);
                if (d.q || d.p) {
                    this.b2.setColor(d.t);
                    this.b2.drawLine(n, bz, n + a3, bz);
                }
                n += this.b1.stringWidth(d.u);
            }
            bz += this.b_;
        }
        this.bz = bz;
    }
    
    public final void paint(final Graphics graphics) {
        if (!this.bw && this.ar()) {
            for (int size = this.cd.size(), i = 0; i < size; ++i) {
                this.a6((String)this.cd.elementAt(i));
            }
            this.a7();
        }
        graphics.translate(-this.b5, -this.b4);
        if (this.b3 != null) {
            graphics.drawImage(this.b3, 0, 0, null);
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
    
    public final void a6(final String s) {
        this.a6(s, this.b8);
    }
    
    public final void a6(String s, final Color color) {
        Color b8 = color;
        String s2 = "";
        int stringWidth = 0;
        final int n = this.size().width - 3;
        i i = new i();
        if (!this.bw) {
            this.cd.addElement(s);
            return;
        }
        if (this.bp) {
            try {
                s = new String(s.getBytes(), this.bq);
            }
            catch (Exception ex) {
                ex.printStackTrace();
                this.bp = false;
            }
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(s, " ", true);
        if (color == null) {
            b8 = this.b8;
        }
        d d = new d(b8);
        final d d2 = new d(b8);
        while (stringTokenizer.hasMoreTokens()) {
            String bl = stringTokenizer.nextToken();
            if (stringWidth + this.b1.stringWidth(bl) >= n) {
                d.k(s2);
                i.aw(d);
                this.a5(i);
                i = new i();
                d = new d(d2.s, d2.t, d2.r, d2.q);
                s2 = "  ";
                stringWidth = this.b1.stringWidth("  ");
            }
            if (bl.startsWith("http://")) {
                this.ca = new k();
                this.ca.bn = stringWidth;
                this.ca.bm = stringWidth + this.a3(d, bl);
                this.ca.bl = bl;
                this.ca.bo = this.ce.size();
                this.cc.put(String.valueOf(this.ca.bo), this.ca);
                bl = String.valueOf(this.bs) + bl + this.bs;
            }
            int a2;
            while ((a2 = this.a2(bl.indexOf(this.bv), bl.indexOf(this.bs), bl.indexOf(this.bt), bl.indexOf(this.br), bl.indexOf(this.bu))) >= 0) {
                boolean b9 = false;
                int n2 = a2 + 1;
                if (bl.charAt(a2) == this.bu.charAt(0)) {
                    d2.r = !d2.r;
                    b9 = true;
                }
                else if (bl.charAt(a2) == this.bt.charAt(0)) {
                    final Color t = d2.t;
                    if (d2.s != null) {
                        d2.t = d2.s;
                    }
                    else {
                        d2.t = this.b9;
                    }
                    d2.s = t;
                    b9 = true;
                }
                else if (bl.charAt(a2) == this.bs.charAt(0)) {
                    d2.q = !d2.q;
                    b9 = true;
                }
                else if (bl.charAt(a2) == this.br.charAt(0)) {
                    d2.j();
                    d2.t = b8;
                    b9 = true;
                }
                else if (bl.charAt(a2) == this.bv.charAt(0)) {
                    n2 += this.a4(d2, bl.substring(a2));
                    b9 = true;
                }
                if (b9) {
                    final String substring = bl.substring(n2);
                    final String substring2 = bl.substring(0, a2);
                    d.k(String.valueOf(s2) + substring2);
                    i.aw(d);
                    stringWidth += this.a3(d, substring2);
                    bl = substring;
                    s2 = "";
                    d = new d(d2.s, d2.t, d2.r, d2.q);
                }
            }
            s2 = String.valueOf(s2) + bl;
            stringWidth += this.a3(d, bl);
        }
        if (s2.endsWith(" ")) {
            s2 = s2.substring(0, s2.length() - 1);
        }
        d.k(s2);
        i.aw(d);
        this.a5(i);
        this.a7();
    }
    
    public final void a5(final i i) {
        this.ce.addElement(i);
        if (this.bz + this.b_ >= this.by) {
            try {
                this.ce.removeElementAt(0);
                if (this.cc.containsKey("0")) {
                    System.out.println("remove hash at 0");
                    this.cc.remove("0");
                }
            }
            catch (Exception ex) {}
            if (this.cc.size() <= 0) {
                return;
            }
            final Hashtable<String, k> cc = new Hashtable<String, k>();
            final Enumeration<String> keys = (Enumeration<String>)this.cc.keys();
            while (keys.hasMoreElements()) {
                final String s = keys.nextElement();
                final k k = this.cc.get(s);
                int int1 = Integer.parseInt(s);
                --int1;
                cc.put(String.valueOf(int1), k);
            }
            this.cc = cc;
        }
    }
    
    public final boolean handleEvent(final Event event) {
        final k k;
        if (event.id == 501 && (k = this.cc.get(String.valueOf((this.b4 + event.y) / this.b_))) != null && event.x <= k.bm && event.x >= k.bn && this.az != null) {
            this.az.bx(k.bl);
        }
        return super.handleEvent(event);
    }
    
    public final void g() {
        this.ce.removeAllElements();
        this.a7();
    }
    
    public final int a4(final d d, final String s) {
        int int1 = -1;
        int n = -1;
        int n2 = 0;
        if (!s.startsWith(this.bv)) {
            return 0;
        }
        final int index = s.indexOf(",");
        if (index == 1) {
            return 0;
        }
        Label_0182: {
            if (index >= 0) {
                if (index <= 3) {
                    try {
                        n = Integer.parseInt(s.substring(index - 1, index));
                        n += Integer.parseInt(s.substring(index - 2, index - 1)) * 10;
                    }
                    catch (Exception ex) {}
                    try {
                        int1 = Integer.parseInt(s.substring(index + 1, index + 2));
                        int1 = int1 * 10 + Integer.parseInt(s.substring(index + 2, index + 3));
                    }
                    catch (Exception ex2) {}
                    break Label_0182;
                }
            }
            try {
                n = Integer.parseInt(s.substring(1, 2));
                n = n * 10 + Integer.parseInt(s.substring(2, 3));
            }
            catch (Exception ex3) {}
        }
        if (n >= 0 && n <= 15) {
            d.t = m.ColorArray[n];
            if (n <= 9) {
                n2 = 1;
            }
            else {
                n2 = 2;
            }
        }
        if (int1 >= 0 && int1 <= 15) {
            d.s = m.ColorArray[int1];
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
    
    public final int a3(final d d, final String s) {
        int n;
        if (d.r) {
            n = this.b0.stringWidth(s);
        }
        else {
            n = this.b1.stringWidth(s);
        }
        return n;
    }
    
    public final int a2(final int n, final int n2, final int n3, final int n4, final int n5) {
        return this.a2(this.a2(this.a2(this.a2(n, n2), n3), n4), n5);
    }
    
    public final int a2(final int n, final int n2) {
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
    
    public final void e(final String bq) {
        if (bq == null || bq.equals("")) {
            return;
        }
        this.bq = bq;
        this.bp = true;
    }
}
