// 
// Decompiled by Procyon v0.5.30
// 

package ABLwidgets;

import java.net.URL;
import java.awt.Cursor;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Point;
import java.awt.LayoutManager;
import java.awt.MediaTracker;
import java.util.Vector;
import java.awt.Image;
import java.awt.Container;
import java.awt.Component;
import java.awt.Color;
import java.awt.Font;
import java.awt.Panel;

public class quadrant extends Panel implements backgroundable
{
    public boolean a;
    public edge b;
    public Font[] c;
    public Font[] d;
    public Color[] e;
    public Color[] f;
    public Color[] g;
    public Color[] h;
    public boolean i;
    public Font j;
    web_context k;
    Component l;
    Container m;
    Color n;
    public Image o;
    int p;
    int q;
    int r;
    int s;
    Image t;
    Vector u;
    int v;
    int w;
    pen x;
    boolean y;
    private int z;
    private int aa;
    public int ab;
    public boolean ac;
    private int ad;
    private int ae;
    private MediaTracker af;
    
    public quadrant() {
        this.b = new edge();
        this.v = -1;
        this.w = -1;
        this.ac = false;
        this.a(null, null, 0, 0, 0, 0);
    }
    
    public quadrant(final pen pen, final Image image, final int n, final int n2, final int n3, final int n4) {
        this.b = new edge();
        this.v = -1;
        this.w = -1;
        this.ac = false;
        this.a(pen, image, n, n2, n3, n4);
    }
    
    void a(final pen pen, final Image o, final int z, final int aa, final int ad, final int ae) {
        this.o = o;
        this.z = z;
        this.aa = aa;
        if (ad > this.ad) {
            this.ad = ad;
        }
        if (ae > this.ae) {
            this.ae = ae;
        }
        if (pen != null) {
            this.setBackground(this.n = pen.b);
            this.j = pen.d;
        }
        if (this.n == null) {
            this.n = new Color(199, 199, 199);
        }
        this.setLayout(null);
        this.u = new Vector();
        this.af = new MediaTracker(this);
        if (o != null) {
            this.af.addImage(o, 0);
            this.af.checkAll(true);
        }
        this.c = new Font[32];
        this.e = new Color[32];
        this.g = new Color[32];
        this.d = new Font[32];
        this.f = new Color[32];
        this.h = new Color[32];
        for (int i = 0; i < 32; ++i) {
            this.e[i] = Color.black;
            this.g[i] = Color.white;
        }
        this.x = new pen(new_font.a("Dialog", 0, 11), new Color(0, 0, 0), new Color(255, 255, 230));
        if (ad == 0 && ae == 0 && o == null) {
            abljem.b("Warning: quadrant defined with zero width and height, and no image to size to.");
        }
    }
    
    public boolean a() {
        return this.ac;
    }
    
    public Image n() {
        this.c();
        return this.t;
    }
    
    public boolean a(final long n) {
        boolean waitForAll = true;
        if (this.o != null) {
            try {
                waitForAll = this.af.waitForAll(n);
            }
            catch (InterruptedException ex) {
                waitForAll = false;
            }
        }
        return waitForAll;
    }
    
    public Image m() {
        this.c();
        if (this.o != null) {
            return this.t;
        }
        return null;
    }
    
    public Point a(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        final Point a = utils.a(this.ab, n, n2, this.o, n3, n4, n5, n6);
        this.z = a.x;
        this.aa = a.y;
        return a;
    }
    
    public void a(final Insets insets) {
        this.b.k = ((insets == null) ? null : ((Insets)insets.clone()));
    }
    
    public Insets b() {
        return (this.b.k == null) ? null : ((Insets)this.b.k.clone());
    }
    
    public void a(final int j, final pen pen, final pen pen2) {
        Color b = null;
        Color c = null;
        Color b2 = null;
        Color c2 = null;
        if (pen != null) {
            b = pen.b;
            c = pen.c;
        }
        if (pen2 != null) {
            b2 = pen2.b;
            c2 = pen2.c;
        }
        if (b != null) {
            this.b.g = b;
        }
        if (c != null) {
            this.b.f = c;
        }
        if (b2 != null) {
            this.b.i = b2;
        }
        if (c2 != null) {
            this.b.h = c2;
        }
        this.b.j = j;
    }
    
    public void a(final int ad, final int ae) {
        this.ad = ad;
        this.ae = ae;
    }
    
    public void b(final int p2, final int q) {
        this.p = p2;
        this.q = q;
        this.resize(this.p, this.q);
    }
    
    public Dimension getPreferredSize() {
        Dimension minimumSize;
        if (this.p > 0 && this.q > 0) {
            minimumSize = new Dimension(this.p, this.q);
        }
        else {
            minimumSize = this.getMinimumSize();
        }
        return minimumSize;
    }
    
    public Dimension getMinimumSize() {
        int n = 0;
        int n2 = 0;
        if (this.ad > 0 && this.ae > 0) {
            n = this.ad;
            n2 = this.ae;
        }
        else if (this.o != null) {
            n = this.o.getWidth(this);
            n2 = this.o.getHeight(this);
            if (n < 0) {
                n = 0;
            }
            if (n2 < 0) {
                n2 = 0;
            }
        }
        final Component[] components = this.getComponents();
        for (int i = 0; i < components.length; ++i) {
            final Component component = this.getComponent(i);
            if (component instanceof menu_panel) {
                final menu_panel menu_panel = (menu_panel)component;
                final int n3 = menu_panel.location().x + menu_panel.size().width + 5;
                final int n4 = menu_panel.location().y + menu_panel.size().height + 5;
                if (n < n3) {
                    if (this.i) {
                        menu_panel.a(n - menu_panel.location().x - 5);
                    }
                    else {
                        n = n3;
                    }
                }
                if (n2 < n4) {
                    n2 = n4;
                }
            }
        }
        return new Dimension(this.b.k.left + n + this.b.k.right, this.b.k.top + n2 + this.b.k.bottom);
    }
    
    public void a(final web_context k, final String s, final Component l, final Container m) {
        final String s2 = "Syntax error: Missing ";
        final String s3 = "space after ";
        final String s4 = "URL: ";
        final String s5 = "x1,y1:";
        final String s6 = "comma in ";
        final String s7 = "x2,y2: ";
        final String s8 = "This appears to be a CERN-format image map (not supported).";
        this.l = l;
        this.m = m;
        this.k = k;
        final lines lines = new lines(s);
        String substring = "";
        String a;
        while ((a = lines.a()) != null) {
            if (a.startsWith("#")) {
                substring = a.substring(1);
            }
            else {
                final String s9 = substring;
                substring = "";
                if (!a.startsWith("rect ")) {
                    continue;
                }
                int char1 = 32;
                if (a.indexOf("(") != -1) {
                    abljem.b(s8);
                }
                else {
                    if (a.length() > 5) {
                        char1 = a.charAt(5);
                    }
                    if ("'\"".indexOf(char1) < 0) {
                        char1 = 32;
                    }
                    int n;
                    if (char1 == 32) {
                        n = a.indexOf(" ", 5);
                    }
                    else {
                        n = a.indexOf(char1, 6);
                        if (n > 0) {
                            ++n;
                        }
                    }
                    if (n == -1) {
                        abljem.b(String.valueOf(s2) + s3 + s4 + a);
                    }
                    else {
                        String s10 = a.substring(5, n);
                        final int index = a.indexOf(" ", n + 1);
                        if (index == -1) {
                            abljem.b(String.valueOf(s2) + s3 + s5 + a);
                        }
                        else {
                            final String substring2 = a.substring(n + 1, index);
                            final String substring3 = a.substring(index + 1);
                            final int index2 = substring2.indexOf(",");
                            if (index2 == -1) {
                                abljem.b(String.valueOf(s2) + s6 + s5 + a);
                            }
                            else {
                                final int g = utils.g(substring2.substring(0, index2));
                                final int g2 = utils.g(substring2.substring(index2 + 1));
                                final int index3 = substring3.indexOf(",");
                                if (index3 == -1) {
                                    abljem.b(String.valueOf(s2) + s6 + s7 + a);
                                }
                                else {
                                    final int g3 = utils.g(substring3.substring(0, index3));
                                    final int g4 = utils.g(substring3.substring(index3 + 1));
                                    if (char1 != 32) {
                                        s10 = s10.substring(1, s10.length() - 1);
                                    }
                                    final int length = s10.length();
                                    if ((length <= 0 || "'\"".indexOf(s10.charAt(0)) < 0) && length != 3 && (length <= 3 || s10.charAt(3) != ' ') && !s10.startsWith("LNKVAR:") && s10.indexOf(".") >= 0) {
                                        s10 = "QDRLNK:" + s10;
                                    }
                                    final menu_item menu_item = new menu_item(null, s10, s9);
                                    menu_item.setLocation(g, g2);
                                    menu_item.setSize(g3 - g + 1, g4 - g2 + 1);
                                    this.u.addElement(menu_item);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    void c() {
        int r = this.size().width;
        int s = this.size().height;
        if (r <= 0 && s <= 0) {
            super.setSize(this.getPreferredSize());
            r = this.size().width;
            s = this.size().height;
        }
        if (r != this.r || s != this.s || this.t == null) {
            this.t = utils.a(this, r, s);
            if (this.t == null) {
                abljem.b("quadrant: createImage() failed!");
            }
            this.r = r;
            this.s = s;
            if (this.t != null) {
                this.a(this.t.getGraphics());
            }
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        this.c();
        if (this.ac) {
            this.a(graphics);
        }
        else if (this.t != null) {
            graphics.drawImage(this.t, 0, 0, this);
        }
    }
    
    void a(final Graphics graphics) {
        if (!this.ac) {
            utils.a(graphics, this.n, 0, 0, this.size().width, this.size().height);
        }
        if (this.o != null) {
            this.a(this.z, this.aa, 0, 0, this.size().width, this.size().height);
            graphics.drawImage(this.o, this.z, this.aa, this);
        }
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201 || event.id == 1005 || event.id == 203 || event.id == 505) {
            this.a((Event)null);
            return true;
        }
        if (event.id == 1004 || event.id == 503 || event.id == 506 || event.id == 501 || event.id == 504) {
            this.a(event);
            return true;
        }
        if (event.id == 502) {
            this.a(event);
            if (this.v >= 0 && this.v < this.u.size() && this.l != null) {
                Cursor cursor = new Cursor(0);
                event.arg = this.u.elementAt(this.v);
                String s = ((menu_item)event.arg).c;
                if (this.a) {
                    this.setCursor(new Cursor(3));
                }
                if (!s.startsWith("QDRLNK:")) {
                    final boolean handleEvent = this.l.handleEvent(event);
                    this.setCursor(new Cursor(0));
                    return handleEvent;
                }
                if (s.startsWith("QDRLNK:")) {
                    s = s.substring(7);
                }
                try {
                    if (this.k != null) {
                        String s2 = s;
                        String substring = "_blank";
                        String substring2 = "";
                        final int index = s2.indexOf(",");
                        if (index != -1) {
                            substring = s2.substring(0, index);
                            s2 = s2.substring(index + 1);
                        }
                        final int index2 = s2.indexOf(",");
                        if (index2 != -1) {
                            substring2 = s2.substring(0, index2);
                            s2 = s2.substring(index2 + 1);
                        }
                        if (substring2.equalsIgnoreCase("reloads")) {
                            cursor = new Cursor(3);
                            this.setCursor(cursor);
                        }
                        this.k.b(new URL(this.k.c(), s2), substring);
                    }
                }
                catch (Throwable t) {
                    abljem.b("Bad URL: " + s + " " + t.toString());
                }
                this.setCursor(cursor);
                return true;
            }
        }
        return super.handleEvent(event);
    }
    
    void a(final Event event) {
        this.v = -1;
        if (event != null) {
            final int x = event.x;
            final int y = event.y;
            for (int i = 0; i < this.u.size(); ++i) {
                final menu_item menu_item = this.u.elementAt(i);
                final Point location = menu_item.getLocation();
                final Dimension size = menu_item.getSize();
                final int x2 = location.x;
                final int y2 = location.y;
                final int n = x2 + size.width;
                final int n2 = y2 + size.height;
                if (x >= x2 && x < n && y >= y2 && y < n2) {
                    this.v = i;
                }
            }
        }
        if (this.v >= 0 && this.v < this.u.size()) {
            if (!this.y) {
                this.y = true;
                this.setCursor(new Cursor(12));
            }
            tooltip.a(this.l, this.m, this.x, this.u.elementAt(this.v), event);
        }
        else {
            if (this.y) {
                this.y = false;
                this.setCursor(new Cursor(0));
            }
            tooltip.a(this.l);
        }
    }
}
