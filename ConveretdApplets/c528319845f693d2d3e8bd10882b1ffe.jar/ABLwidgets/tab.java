// 
// Decompiled by Procyon v0.5.30
// 

package ABLwidgets;

import java.awt.Cursor;
import java.awt.Event;
import java.awt.Point;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.LayoutManager;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.FontMetrics;
import java.util.Vector;
import java.awt.Color;
import java.awt.Font;
import java.awt.Component;
import java.awt.Image;
import java.awt.Panel;

public class tab extends Panel
{
    public boolean a;
    public boolean b;
    public int c;
    public Image d;
    public boolean e;
    public char f;
    public char g;
    public char h;
    public char i;
    public char j;
    public edge k;
    public Component l;
    public int m;
    public int n;
    public Font o;
    public Font p;
    public Font q;
    public Color r;
    public Color s;
    public Color t;
    public Color u;
    public Color v;
    public Color w;
    public Color x;
    public Color y;
    public int z;
    private char aa;
    private char ab;
    private Component ac;
    private edge ad;
    private edge ae;
    private int af;
    private int ag;
    private Vector ah;
    private int ai;
    private int aj;
    private int ak;
    private int al;
    private int am;
    private int an;
    private int ao;
    private int ap;
    private int aq;
    private int ar;
    private int as;
    private int at;
    private int au;
    private int av;
    private boolean aw;
    private FontMetrics ax;
    private int ay;
    private pen az;
    private Container a0;
    private boolean a1;
    private int a2;
    private int a3;
    private int a4;
    private Dimension a5;
    
    public tab(final char f, final char g) {
        this.b = true;
        this.e = false;
        this.f = '0';
        this.g = '?';
        this.h = '1';
        this.i = '0';
        this.j = '0';
        this.aa = '0';
        this.a1 = true;
        this.f = f;
        this.g = g;
        this.ab = this.f;
        this.aa = '0';
        if (this.f == '0') {
            this.aa = '1';
        }
        if (this.f == '2' || this.f == '3' || this.f == '4' || this.f == '6') {
            this.aa = '2';
        }
        if (this.g == '6') {
            this.ab = '6';
        }
    }
    
    public void a(final Component ac, final Container a0, final pen pen, final pen pen2, final pen pen3, final pen pen4, final pen pen5, final pen pen6, final pen pen7) {
        this.ac = ac;
        this.a0 = a0;
        this.z = 4;
        if (this.ab == '3' || this.ab == '4') {
            this.z = 0;
        }
        this.au = 14;
        if (this.ab == '3' || this.ab == '4' || this.ab == '6') {
            this.au = 6;
        }
        this.aw = false;
        if (pen != null) {
            this.r = pen.b;
        }
        if (pen2 != null) {
            this.s = pen2.b;
        }
        if (pen3 != null) {
            this.x = pen3.b;
        }
        if (pen4 != null) {
            this.t = pen4.b;
        }
        if (pen5 != null) {
            this.w = pen5.b;
        }
        if (pen6 != null) {
            this.v = pen6.b;
        }
        if (pen7 != null) {
            this.y = pen7.b;
        }
        else {
            this.y = this.t;
        }
        if (pen4 != null) {
            this.o = pen4.d;
        }
        if (pen5 != null) {
            this.p = pen5.d;
        }
        if (pen7 != null) {
            this.q = pen7.d;
        }
        else {
            this.q = this.o;
        }
        this.e();
    }
    
    public void a(final Component ac, final Container a0, final pen pen, final pen pen2, final pen pen3) {
        this.ac = ac;
        this.a0 = a0;
        this.z = 0;
        this.au = 0;
        this.aw = false;
        if (pen != null) {
            this.r = pen.b;
        }
        if (pen != null) {
            this.s = pen.b;
        }
        if (pen2 != null) {
            this.t = pen2.b;
        }
        if (pen3 != null) {
            this.u = pen3.b;
        }
        if (pen2 != null) {
            this.o = pen2.d;
        }
        this.e();
    }
    
    private void e() {
        this.af = -1;
        this.ag = -1;
        this.ap = -1;
        this.aq = 0;
        this.ar = 0;
        this.as = 0;
        this.at = 0;
        if (this.r == null) {
            this.r = Color.white;
        }
        if (this.s == null) {
            this.s = Color.lightGray;
        }
        if (this.x == null) {
            this.x = Color.gray;
        }
        if (this.t == null) {
            this.t = Color.black;
        }
        if (this.y == null) {
            this.y = this.t;
        }
        if (this.w == null) {
            this.w = Color.black;
        }
        if (this.v == null) {
            this.v = this.s;
        }
        if (this.u == null) {
            this.u = this.t;
        }
        if (this.o == null) {
            this.o = new_font.a("Dialog", 0, 11);
        }
        if (this.p == null) {
            this.p = new_font.a("Dialog", 0, 11);
        }
        if (this.q == null) {
            this.q = this.o;
        }
        this.setLayout(null);
        this.ah = new Vector();
        this.az = new pen(new_font.a("Dialog", 0, 11), new Color(0, 0, 0), new Color(255, 255, 230));
        this.a(this.o, this.p, 0);
        this.k = null;
        this.a1 = true;
    }
    
    public void a(final Font o, final Font p3, final int ay) {
        if (o != null) {
            this.o = o;
        }
        this.p = this.o;
        if (p3 != null) {
            this.p = p3;
        }
        this.ax = this.getFontMetrics(this.o);
        this.aj = this.ax.getMaxAscent();
        this.ai = this.ax.getHeight();
        this.ak = this.ai + this.au / 2;
        this.ay = ay;
        this.am = ((ay > 0) ? ay : this.ax.stringWidth(" "));
    }
    
    public void a() {
        if (this.e) {
            return;
        }
        this.a1 = false;
        this.repaint();
    }
    
    public void a(final int n) {
        if (n == 0) {
            return;
        }
        this.av = n - this.au / 2 - this.ax.stringWidth(" ");
    }
    
    public void a(final int n, final pen pen, final pen pen2) {
        int n2 = 0;
        int n3 = 0;
        switch (n) {
            case 0: {
                n2 = 0;
                n3 = 3;
                break;
            }
            case 1: {
                n2 = 1;
                n3 = 3;
                break;
            }
            case 2: {
                n2 = 2;
                n3 = 3;
                break;
            }
            case 3: {
                n2 = 3;
                n3 = 0;
                break;
            }
            default: {
                n2 = 0;
                n3 = 0;
                break;
            }
        }
        if (n2 > 0) {
            (this.ad = new edge()).a(n2, pen, pen2);
            if (n2 != 3) {
                this.ad.b();
            }
        }
        if (n3 > 0) {
            (this.ae = new edge()).a(n3, pen, pen2);
            if (n3 != 3) {
                this.ad.b();
            }
        }
        if (this.ae == null) {
            this.ae = ((this.ad == null) ? this.k : this.ad);
        }
        if (this.ad == null) {
            this.ad = this.k;
        }
    }
    
    private int f(final int n) {
        for (int i = 0; i < this.ah.size(); ++i) {
            try {
                if (((tabItem)this.ah.elementAt(i)).d == n) {
                    return i;
                }
            }
            catch (Throwable t) {}
        }
        return -1;
    }
    
    public void b(final int n) {
        this.d(this.f(n));
    }
    
    public void c(final int n) {
        this.e(this.f(n));
    }
    
    public void d(final int n) {
        final int c = this.c;
        this.e(n);
        if (this.c != c) {
            this.repaint();
        }
    }
    
    public void e(final int c) {
        if (this.c == c) {
            return;
        }
        if (c >= 0 && c < this.ah.size()) {
            this.c = c;
            return;
        }
        this.b();
    }
    
    public void b() {
        this.c = this.ah.size();
    }
    
    public void removeAll() {
        if (this.ah != null) {
            this.ah.removeAllElements();
        }
        this.al = 0;
    }
    
    public void a(final menu_item menu_item) {
        this.a(menu_item, -1);
    }
    
    public void a(final menu_item menu_item, final int n) {
        this.al += ((this.al == 0) ? 0 : this.z);
        this.al += this.au + this.ax.stringWidth(menu_item.b) + this.av;
        if (this.ab == '6') {
            this.al += this.ax.stringWidth("  ");
        }
        this.ah.addElement(new tabItem(menu_item, null, n));
    }
    
    public void a(final Insets insets) {
        this.as = insets.top;
        this.aq = insets.left;
        this.at = insets.bottom;
        this.ar = insets.right;
    }
    
    public Dimension getPreferredSize() {
        if (this.ab == '2') {
            this.a2 = this.au / 2 * 3 / 4;
        }
        if (this.ab == '3' || this.ab == '6') {
            this.a2 = 3;
            this.a3 = 0;
            this.a4 = 5;
            if (this.k != null && this.k.j > 0) {
                this.a3 = 2;
            }
            if (this.ab == '6') {
                this.a2 += 2;
                this.a4 = 3;
            }
        }
        if (this.ab == '4') {
            this.a2 = 3;
            this.a3 = 0;
            this.a4 = 0;
            if (this.k != null && this.k.j > 0) {
                this.a3 = 2;
                this.a4 = 2;
            }
        }
        this.ak = this.au / 2 + this.ai + this.a2;
        if (this.a5 != null) {
            return this.a5;
        }
        return new Dimension(this.aq + this.al + this.ar, this.as + this.a3 + this.ak + this.a4 + this.at);
    }
    
    public void a(final Dimension a5) {
        if (this.a5 == null) {
            this.a5 = new Dimension();
        }
        if (this.j == '1') {
            this.a5.height = a5.height;
            if (a5.width > this.a5.width) {
                this.a5.width = a5.width;
            }
        }
        else {
            this.a5 = a5;
        }
    }
    
    void c() {
        final int width = this.size().width;
        final int height = this.size().height;
        if (width != this.ao || height != this.an || this.d == null) {
            this.d = utils.a(this, width, height);
            this.an = height;
            this.ao = width;
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        this.c();
        if (this.d != null) {
            this.a(this.d.getGraphics());
            graphics.drawImage(this.d, 0, 0, this);
        }
    }
    
    void a(final Graphics graphics) {
        final int width = this.size().width;
        final int height = this.size().height;
        final int n = this.au / 2;
        int aq = this.aq;
        int as = this.as;
        int a4 = this.a4;
        this.m = -1;
        this.n = -1;
        utils.a(graphics, this.r, 0, 0, width, height);
        try {
            Image m = null;
            if (this.j == '1') {
                m = ((backgroundable)this.getParent()).m();
            }
            if (m != null) {
                final Point location = this.getLocation();
                graphics.drawImage(m, -location.x, -location.y, this);
            }
        }
        catch (Throwable t) {}
        if (this.ab == '2' || this.ab == '3' || this.ab == '6') {
            final int n2 = as + this.a3 + this.ak;
            if (height > n2) {
                as += height - n2;
            }
        }
        if (this.ab == '3' || this.ab == '6') {
            as -= a4;
            if (as < 0) {
                a4 += as;
                as = 0;
            }
        }
        if (this.ab == '3' || this.ab == '4') {
            as += this.a3 - 2;
            if (as < 0) {
                as = 0;
            }
            if (this.k != null && this.k.j > 0 && this.k.f != null) {
                graphics.setColor(this.k.f);
                graphics.drawLine(0, as, width, as);
            }
            ++as;
            if (this.k != null && this.k.j > 0 && this.k.g != null) {
                graphics.setColor(this.k.g);
                graphics.drawLine(0, as, width, as);
            }
            ++as;
        }
        if (this.ab == '6') {
            as += this.a3;
        }
        for (int i = 0; i < this.ah.size(); ++i) {
            final int n3 = aq;
            aq = aq + this.a(graphics, i, ((tabItem)this.ah.elementAt(i)).b.b, aq, as, this.a2) + this.z;
            if (this.ab == '6') {
                final int n4 = n3 + 1;
                final int n5 = as + 1;
                final int n6 = aq - this.z - 1 - 1;
                final int n7 = height - a4 - 2;
                edge edge = this.k;
                if (i == this.af) {
                    edge = this.ad;
                }
                if (i == this.ag) {
                    edge = this.ae;
                }
                utils.a(graphics, edge, n4, n5, n6, n7);
            }
        }
        if (this.ab == '3' || this.ab == '4') {
            this.a(graphics, this.ah.size() + 1, "", aq, as, this.a2);
        }
        if (this.ab == '3' || this.ab == '4') {
            int n8 = height - a4;
            if (n8 >= height) {
                n8 = height - 2;
            }
            if (this.k != null && this.k.j > 0 && this.k.i != null) {
                graphics.setColor(this.k.i);
                graphics.drawLine(0, n8, width, n8);
            }
            ++n8;
            if (this.k != null && this.k.j > 0 && this.k.h != null) {
                graphics.setColor(this.k.h);
                graphics.drawLine(0, n8, width, n8);
            }
            ++n8;
        }
        if (this.l != null) {
            this.l.doLayout();
        }
    }
    
    private int a(final Graphics graphics, final int n, final String s, final int n2, final int n3, final int n4) {
        final int n5 = this.au / 2;
        String string = s;
        if (this.ab == '3' || this.ab == '4' || this.ab == '6') {
            string = " " + s + " ";
        }
        final int n6 = (n < this.ah.size()) ? this.ax.stringWidth(string) : (this.size().width - n2);
        if (n6 <= 0) {
            return 0;
        }
        final int n7 = (this.ay == 0) ? n6 : (this.ay * string.length());
        final int width = this.size().width;
        final int height = this.size().height;
        final int n8 = n5 + n7 + n5 + 2 * this.av;
        final int n9 = n5 + this.ai + ((n4 > 0) ? n4 : 0);
        int n10 = n2 + n5 + this.av;
        final int n11 = n3 + n5 + this.aj;
        int n12 = 0;
        if (n4 < 3) {
            n12 = ((n4 < 0) ? 3 : (3 - n4));
        }
        int n13 = n11 - n12;
        if (this.ab == '6') {
            edge edge = this.k;
            if (n == this.af) {
                edge = this.ad;
            }
            if (n == this.ag) {
                edge = this.ae;
            }
            if (edge != null) {
                switch (edge.j) {
                    case 2: {
                        --n10;
                        --n13;
                        break;
                    }
                    case 3: {
                        ++n10;
                        ++n13;
                        break;
                    }
                }
            }
        }
        if (this.ab == '1' || this.ab == '5') {
            switch (this.i) {
                case '1': {
                    n10 = width - n6;
                    break;
                }
                case '2': {
                    n10 = (width - n6) / 2;
                    break;
                }
                default: {
                    n10 = 0;
                    break;
                }
            }
        }
        if (this.ab == '0' || this.ab == '2') {
            Color color = this.s;
            if (n == this.ap) {
                color = this.v;
            }
            if (n == this.c) {
                color = this.x;
            }
            utils.a(graphics, color, n2 + n5, n3, n7, n5 + this.ai);
            utils.a(graphics, color, n2, n3 + n5, n5, this.ai);
            utils.a(graphics, color, n2 + n5 + n7, n3 + n5, n5, this.ai);
            if (n4 > 0) {
                utils.a(graphics, color, n2, n3 + n5 + this.ai, n8, n4);
            }
            graphics.setColor(color);
            graphics.fillArc(n2, n3, this.au, this.au, -180, -90);
            graphics.fillArc(n2 + n7, n3, this.au, this.au, 0, 90);
        }
        if (this.ab == '3' || this.ab == '4' || this.ab == '6') {
            final int n14 = n2 + n8 - 1;
            final int n15 = n3 + n5;
            final int n16 = n15 + this.ai;
            final int n17 = n15 - n12 / 2;
            int n18 = n16 - n12;
            if (n18 > height - 5) {
                n18 = height - 5;
            }
            if (n == this.c) {
                this.m = n2 + ((n == 0) ? 1 : -1);
            }
            Color color2 = this.s;
            if (n == this.ap) {
                color2 = this.v;
            }
            if (n == this.c) {
                color2 = this.x;
            }
            utils.a(graphics, color2, n2, n3, n8, n5 + this.ai + n4);
            if (this.ab == '3' || this.ab == '4') {
                if (this.k != null && this.k.d != null && n > 0 && n < this.ah.size()) {
                    graphics.setColor(this.k.d);
                    graphics.drawLine(n2, n17, n2, n18);
                }
                if (this.k != null && this.k.c != null && n < this.ah.size() - 1) {
                    graphics.setColor(this.k.c);
                    graphics.drawLine(n14, n17, n14, n18);
                }
            }
        }
        if (this.ab == '1' || this.ab == '5') {
            int n19 = 0;
            int n20 = 0;
            switch (this.h) {
                case '2': {
                    n19 = width;
                    n20 = 0;
                    break;
                }
                case '1': {
                    n19 = n6;
                    n20 = n10;
                    break;
                }
                default: {
                    n19 = 0;
                    n20 = 0;
                    break;
                }
            }
            if (n19 > 0) {
                graphics.setColor(this.u);
                if (this.a1) {
                    graphics.drawLine(n20, n3 + n5 + this.aj + 2, n20 + n19, n3 + n5 + this.aj + 2);
                }
            }
            graphics.setColor(this.t);
            graphics.setFont(this.o);
        }
        else if (n == this.c) {
            graphics.setColor(this.y);
            graphics.setFont(this.q);
        }
        else if (n != this.ap) {
            graphics.setColor(this.t);
            graphics.setFont(this.o);
        }
        else {
            graphics.setColor(this.w);
            graphics.setFont(this.p);
            int n21 = n10;
            int n22 = n10 + n6;
            if (this.ab == '3' || this.ab == '4' || this.ab == '6') {
                n21 += this.am;
                n22 -= this.am;
            }
            if (this.a1 && n22 > n21 && this.h == '1') {
                graphics.drawLine(n21, n3 + n5 + this.aj + 2, n22, n3 + n5 + this.aj + 2);
            }
            if (n == this.c) {
                this.n = n21;
            }
        }
        if (this.a1) {
            graphics.drawString(string, n10, n13);
        }
        if (n >= 0 && n < this.ah.size()) {
            final int n23 = (n8 > 8) ? 4 : 0;
            this.ah.elementAt(n).c = new Rectangle(n2 + n23 / 2, n3, n8 - n23, n9);
        }
        return n8;
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201 || event.id == 1005 || event.id == 203 || event.id == 505) {
            this.b(null);
            this.repaint();
            return true;
        }
        if (event.id == 1004 || event.id == 503 || event.id == 506 || event.id == 501 || event.id == 504) {
            this.b(event);
            this.repaint();
            return true;
        }
        if (event.id == 502) {
            this.b(event);
            this.repaint();
            if (this.ap >= 0 && this.ap < this.ah.size() && this.c != this.ap) {
                if (this.aa == '1') {
                    event.arg = this.ah.elementAt(this.ap).b;
                    if (this.b) {
                        this.c = this.ap;
                    }
                    if (this.a) {
                        this.setCursor(new Cursor(3));
                    }
                    final boolean handleEvent = this.ac.handleEvent(event);
                    this.setCursor(new Cursor(0));
                    return handleEvent;
                }
                if (this.aa == '2') {
                    this.c = this.ap;
                    return this.a(event);
                }
            }
        }
        return super.handleEvent(event);
    }
    
    public boolean d() {
        return this.a(new Event(this, 1001, null));
    }
    
    public boolean a(final Event event) {
        if (this.c < 0 || this.c >= this.ah.size()) {
            return false;
        }
        try {
            event.arg = this.ah.elementAt(this.c).b;
            return this.ac.handleEvent(event);
        }
        catch (Throwable t) {
            abljem.b("action_curtab failed");
            t.printStackTrace();
            return false;
        }
    }
    
    void b(final Event event) {
        this.ap = -1;
        if (event != null) {
            final Point point = new Point(event.x, event.y);
            for (int i = 0; i < this.ah.size(); ++i) {
                final Rectangle c = this.ah.elementAt(i).c;
                if (c != null && c.contains(point)) {
                    this.ap = i;
                    break;
                }
            }
        }
        if (this.ap == -1) {
            this.af = -1;
            this.ag = -1;
        }
        else {
            switch (event.id) {
                case 502: {
                    this.af = this.ap;
                    this.ag = -1;
                    break;
                }
                case 501: {
                    this.af = -1;
                    this.ag = this.ap;
                    break;
                }
                case 503: {
                    this.af = this.ap;
                    this.ag = -1;
                    break;
                }
                case 506: {
                    this.af = -1;
                    this.ag = this.ap;
                    break;
                }
                case 504: {
                    this.af = this.ap;
                    this.ag = -1;
                    break;
                }
            }
        }
        if (this.ap >= 0 && this.ap != this.c && this.ap < this.ah.size()) {
            if (!this.aw) {
                this.aw = true;
                this.setCursor(new Cursor(12));
            }
            tooltip.a(this.ac, this.a0, this.az, this.ah.elementAt(this.ap).b, event);
        }
        else {
            if (this.aw) {
                this.aw = false;
                this.setCursor(new Cursor(0));
            }
            tooltip.a(this.ac);
        }
    }
    
    class tabItem
    {
        menu_item b;
        Rectangle c;
        int d;
        
        tabItem(final menu_item b, final Rectangle c, final int d) {
            this.b = b;
            this.c = c;
            this.d = d;
        }
    }
}
