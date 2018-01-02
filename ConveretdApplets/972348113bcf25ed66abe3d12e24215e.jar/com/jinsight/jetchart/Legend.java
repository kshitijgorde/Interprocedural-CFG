// 
// Decompiled by Procyon v0.5.30
// 

package com.jinsight.jetchart;

import java.awt.Toolkit;
import java.awt.FontMetrics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.util.Vector;
import java.awt.Font;
import java.awt.Color;

public class Legend
{
    public static final int VERTICAL = 0;
    public static final int HORIZONTAL = 1;
    public static final int RIGHT = 0;
    public static final int BOTTOM = 1;
    public static final int LEFT = 2;
    static final int a = 3;
    private Color b;
    private Color c;
    private Font d;
    private Vector e;
    private k f;
    int g;
    boolean h;
    private int i;
    private int j;
    Graphics k;
    Graph l;
    Rectangle[] m;
    private int n;
    private Point o;
    private Rectangle p;
    private int q;
    private int r;
    private int s;
    private Color t;
    private String u;
    private int v;
    private boolean w;
    private boolean x;
    boolean y;
    private int z;
    private int A;
    
    public void setFont(final Font d) {
        this.d = d;
        this.l.cg = true;
        this.y = true;
    }
    
    public void setOrientation(final int g) {
        this.g = g;
        this.l.cg = true;
        this.y = true;
    }
    
    public void setAutoOrientationEnabled(final boolean h) {
        this.h = h;
        this.l.cg = true;
        this.y = true;
    }
    
    public void setPosition(final int n) {
        this.n = n;
        this.l.cg = true;
        this.y = true;
    }
    
    public int getPosition() {
        return this.n;
    }
    
    public void setBackground(final Color b) {
        this.b = b;
    }
    
    public void setOpacityEnabled(final boolean w) {
        this.w = w;
    }
    
    public void setForeground(final Color c) {
        this.c = c;
    }
    
    public void setHGap(final int i) {
        this.i = i;
        this.l.cg = true;
    }
    
    public void setVGap(final int j) {
        this.j = j;
        this.l.cg = true;
    }
    
    void a(final Graphics k) {
        final boolean g = GraphSerie.G;
        if (this.l.bI != null && this.l.bI.v()) {
            return;
        }
        this.k = k;
        final Color color = k.getColor();
        final Font font = k.getFont();
        k.setColor(this.b);
        k.setFont(this.d);
        int x = 0;
        int y = 0;
        final int d = this.d();
        final int e = this.e();
        Label_0513: {
            if (this.n == 1) {
                final FontMetrics fontMetrics = this.l.getToolkit().getFontMetrics(this.l.getYAxis().r);
                final String s = this.l.bf ? this.l.v : this.l.s;
                Label_0254: {
                    if (!this.l.c() && this.g == 0) {
                        x = ((s != null) ? (15 + fontMetrics.getMaxAscent() + fontMetrics.getMaxDescent()) : 0) + (this.l.k + this.l.bI.a(this.l.bf ? this.l.g : this.l.bI.i()));
                        if (!g) {
                            break Label_0254;
                        }
                    }
                    x = (this.l.getSize().width - d) / 2;
                }
                y = this.l.getSize().height - this.l.n - e;
                this.o.x = x;
                this.o.y = y;
                if (!g) {
                    break Label_0513;
                }
            }
            if (this.n == 0) {
                x = this.l.getSize().width - this.l.l - d;
                Label_0377: {
                    if (!this.l.c()) {
                        y = this.l.bI.k().y;
                        if (!g) {
                            break Label_0377;
                        }
                    }
                    y = (this.l.getSize().height - e) / 2;
                }
                this.o.x = x;
                this.o.y = y;
                if (!g) {
                    break Label_0513;
                }
            }
            if (this.n == 2) {
                x = this.l.k;
                Label_0464: {
                    if (!this.l.c()) {
                        y = this.l.bI.k().y;
                        if (!g) {
                            break Label_0464;
                        }
                    }
                    y = (this.l.getSize().height - e) / 2;
                }
                this.o.x = x;
                this.o.y = y;
                if (!g) {
                    break Label_0513;
                }
            }
            if (this.n == 3) {
                x = this.o.x;
                y = this.o.y;
            }
        }
        if (this.w) {
            k.setColor(this.b);
            k.fillRect(x, y, d, e);
            if (!this.x) {
                k.setColor(Color.black);
                k.drawRect(x, y, d, e);
            }
        }
        if (this.x) {
            k.setColor(Color.red);
            k.drawRect(x, y, d, e);
            k.drawRect(x - 1, y - 1, d + 2, e + 2);
            k.drawRect(x - 2, y - 2, d + 4, e + 4);
        }
        this.p.setBounds(x, y, d, e);
        final FontMetrics fontMetrics2 = k.getFontMetrics(this.d);
        final int n = fontMetrics2.getMaxAscent() + fontMetrics2.getMaxDescent();
        this.q = x + 5;
        this.s = this.a(10);
        this.r = 0;
        this.v = y + 5 + fontMetrics2.getLeading() + fontMetrics2.getMaxAscent();
        Label_0792: {
            if (this.g == 0) {
                this.r = y + 5 + fontMetrics2.getLeading() + (fontMetrics2.getLeading() + fontMetrics2.getMaxAscent() + fontMetrics2.getMaxDescent() + fontMetrics2.getLeading() - this.s) / 2;
                if (!g) {
                    break Label_0792;
                }
            }
            this.r = y + fontMetrics2.getLeading() + (e - this.s) / 2;
        }
        if (!this.l.c()) {
            this.m = new Rectangle[this.e.size()];
            int n2 = 0;
            while (true) {
                Label_0975: {
                    if (!g) {
                        break Label_0975;
                    }
                    final GraphSerie graphSerie = this.e.elementAt(n2);
                    if ((!(graphSerie instanceof h) && (!(graphSerie instanceof OHLCSerie) || ((OHLCSerie)graphSerie).J != 4) && (!(graphSerie instanceof OHLCSerie) || ((OHLCSerie)graphSerie).J != 5) && (!(graphSerie instanceof BarSerie) || graphSerie instanceof StackBarSerie || ((BarSerie)graphSerie).U == null)) || g) {
                        this.t = graphSerie.s;
                        this.u = graphSerie.x;
                        this.m[n2] = new Rectangle(this.q, this.r, this.s, this.s);
                        this.a(fontMetrics2);
                    }
                    ++n2;
                }
                if (n2 < this.e.size()) {
                    continue;
                }
                break;
            }
        }
        else {
            final Slice[] slices = this.e.elementAt(0).getSlices();
            this.m = new Rectangle[slices.length];
            int n3 = 0;
            while (true) {
                Label_1089: {
                    if (!g) {
                        break Label_1089;
                    }
                    this.t = slices[n3].getColor();
                    this.u = this.l.g[n3];
                    this.m[n3] = new Rectangle(this.q, this.r, this.s, this.s);
                    this.a(fontMetrics2);
                    ++n3;
                }
                if (n3 < slices.length) {
                    continue;
                }
                break;
            }
        }
        this.y = false;
        k.setColor(color);
        k.setFont(font);
        if (this.f == null) {
            this.f = new k();
        }
        this.f.a(this.l, k);
    }
    
    Font a() {
        return this.d;
    }
    
    int b() {
        return this.i;
    }
    
    int c() {
        return this.j;
    }
    
    int d() {
        final boolean g = GraphSerie.G;
        if (!this.y) {
            return this.z;
        }
        int n = 0;
        o o;
        if (!this.l.c()) {
            o = this.l.bI;
        }
        else {
            o = this.l.bJ;
        }
        final int a = this.a(10);
        final boolean b = this.n == 2 || this.n == 0;
        if ((this.h && (b || (this.n == 3 && this.g == 0))) || (!this.h && this.g == 0)) {
            n = 5 + a + 5 + o.b() + 5;
            if (!g) {
                return this.z = n;
            }
        }
        if ((this.h && (this.n == 1 || (this.n == 3 && this.g == 1))) || (!this.h && this.g == 1)) {
            final FontMetrics fontMetrics = this.l.getGraphics().getFontMetrics(this.d);
            if (this.l.bJ == null) {
                int n2 = 0;
                while (true) {
                    Label_0344: {
                        if (!g) {
                            break Label_0344;
                        }
                        final GraphSerie graphSerie = this.e.elementAt(n2);
                        if ((!(graphSerie instanceof h) && (!(graphSerie instanceof OHLCSerie) || ((OHLCSerie)graphSerie).J != 4) && (!(graphSerie instanceof OHLCSerie) || ((OHLCSerie)graphSerie).J != 5) && (!(graphSerie instanceof BarSerie) || graphSerie instanceof StackBarSerie || ((BarSerie)graphSerie).U == null)) || g) {
                            n += 5 + a + 5 + fontMetrics.stringWidth(graphSerie.x) + 5;
                        }
                        ++n2;
                    }
                    if (n2 < this.e.size()) {
                        continue;
                    }
                    break;
                }
            }
            else {
                int n3 = 0;
                while (true) {
                    Label_0396: {
                        if (!g) {
                            break Label_0396;
                        }
                        n += 5 + a + 5 + fontMetrics.stringWidth(this.l.g[n3]) + 5;
                        ++n3;
                    }
                    if (n3 < this.l.g.length) {
                        continue;
                    }
                    break;
                }
            }
        }
        return this.z = n;
    }
    
    int e() {
        final boolean g = GraphSerie.G;
        if (this.l.bI != null && this.l.bI.v()) {
            return 0;
        }
        if (!this.y) {
            return this.A;
        }
        final FontMetrics fontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(this.d);
        final int n = fontMetrics.getLeading() + fontMetrics.getMaxAscent() + fontMetrics.getMaxDescent() + fontMetrics.getLeading();
        int n2 = 0;
        int n3 = 0;
        Label_0119: {
            if (this.l.bJ != null) {
                n3 = this.e.elementAt(0).getSlices().length;
                if (!g) {
                    break Label_0119;
                }
            }
            n3 = this.e.size();
        }
        int n4 = 0;
        Label_0231: {
            if ((this.h && (this.n == 1 || (this.n == 3 && this.g == 1))) || (!this.h && this.g == 1)) {
                n4 = 1;
                if (!g) {
                    break Label_0231;
                }
            }
            if ((this.h && (this.n == 2 || this.n == 0 || (this.n == 3 && this.g == 0))) || (!this.h && this.g == 0)) {
                n4 = n3;
            }
        }
        if (this.g == 1) {
            n2 = n;
            if (!g) {
                return this.A = n2 + 10;
            }
        }
        int n5 = 0;
        while (true) {
            Label_0365: {
                if (!g) {
                    break Label_0365;
                }
                Label_0362: {
                    if (this.l.bJ == null) {
                        final AbstractSerie abstractSerie = this.e.elementAt(n5);
                        if ((abstractSerie instanceof h || (abstractSerie instanceof OHLCSerie && ((OHLCSerie)abstractSerie).J == 4) || (abstractSerie instanceof OHLCSerie && ((OHLCSerie)abstractSerie).J == 5) || (abstractSerie instanceof BarSerie && !(abstractSerie instanceof StackBarSerie) && ((BarSerie)abstractSerie).U != null)) && !g) {
                            break Label_0362;
                        }
                    }
                    n2 += n;
                }
                ++n5;
            }
            if (n5 < n4) {
                continue;
            }
            break;
        }
        return this.A = n2 + 10;
    }
    
    void a(final Point o) {
        this.o = o;
    }
    
    boolean b(final Point point) {
        return this.p.contains(point);
    }
    
    Rectangle f() {
        return this.p;
    }
    
    void a(final boolean x) {
        this.x = x;
    }
    
    private void a(final FontMetrics fontMetrics) {
        this.k.setColor(this.t);
        this.k.fill3DRect(this.q, this.r, this.s, this.s, true);
        this.k.setColor(this.c);
        this.k.drawString(this.u, this.q + this.a(10) + 5, this.v);
        if (this.g == 0) {
            this.v += fontMetrics.getLeading() + fontMetrics.getMaxDescent() + fontMetrics.getMaxAscent() + fontMetrics.getLeading();
            this.r += fontMetrics.getLeading() + fontMetrics.getMaxAscent() + fontMetrics.getMaxDescent() + fontMetrics.getLeading();
            if (!GraphSerie.G) {
                return;
            }
        }
        this.q += this.a(10) + 10 + fontMetrics.stringWidth(this.u) + 5;
    }
    
    void g() {
        if (this.h) {
            if (this.n == 2 || this.n == 0) {
                this.g = 0;
                if (!GraphSerie.G) {
                    return;
                }
            }
            if (this.n == 1) {
                this.g = 1;
            }
        }
    }
    
    int h() {
        if (this.l.bd && this.n == 1) {
            return this.e() + this.j;
        }
        return 0;
    }
    
    int i() {
        if (this.l.bd && this.n == 0) {
            return this.d() + this.i;
        }
        return 0;
    }
    
    int j() {
        if (this.l.bd && this.n == 2) {
            return this.d() + this.i;
        }
        return 0;
    }
    
    private int a(int n) {
        final int screenResolution = Toolkit.getDefaultToolkit().getScreenResolution();
        if (screenResolution == 480000) {
            n *= 1;
            if (!GraphSerie.G) {
                return n;
            }
        }
        if (screenResolution == 786432) {
            n *= 1;
        }
        return n;
    }
    
    Legend(final Graph l) {
        this.b = Color.white;
        this.c = Color.black;
        this.d = new Font(a("_7/k\"i$(~"), 1, 10);
        this.g = 0;
        this.h = true;
        this.i = 10;
        this.j = 10;
        this.n = 1;
        this.o = new Point();
        this.p = new Rectangle();
        this.w = true;
        this.y = true;
        this.l = l;
        this.e = l.i;
    }
    
    private static String a(final String s) {
        final char[] charArray = s.toCharArray();
        final int length = charArray.length;
        int n = 0;
        while (true) {
            Label_0089: {
                if (length > 1) {
                    break Label_0089;
                }
                char[] array2;
                char[] array = array2 = charArray;
                int n3;
                int n2 = n3 = n;
                while (true) {
                    final char c = array2[n3];
                    char c2 = '\0';
                    switch (n % 5) {
                        case 0: {
                            c2 = '\f';
                            break;
                        }
                        case 1: {
                            c2 = 'V';
                            break;
                        }
                        case 2: {
                            c2 = 'A';
                            break;
                        }
                        case 3: {
                            c2 = '\u0018';
                            break;
                        }
                        default: {
                            c2 = 'q';
                            break;
                        }
                    }
                    array[n2] = (char)(c ^ c2);
                    ++n;
                    if (length != 0) {
                        break;
                    }
                    array = (array2 = charArray);
                    n2 = (n3 = length);
                }
            }
            if (n >= length) {
                return new String(charArray);
            }
            continue;
        }
    }
}
