// 
// Decompiled by Procyon v0.5.30
// 

package ABLwidgets;

import java.awt.Cursor;
import java.awt.Event;
import java.awt.Shape;
import java.awt.Point;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Button;
import java.awt.LayoutManager;
import java.awt.Frame;
import java.awt.Container;
import java.awt.Image;
import java.awt.FontMetrics;
import java.awt.Font;
import java.util.Vector;
import java.awt.Color;
import java.awt.Component;
import java.awt.Panel;

public class menu_panel extends Panel
{
    private Component a;
    public boolean b;
    private boolean c;
    private boolean d;
    private Color e;
    private Color f;
    private Color g;
    private Color h;
    private Color i;
    private Color j;
    private Vector k;
    private Vector l;
    private Vector m;
    private Font n;
    private Font o;
    private FontMetrics p;
    private int q;
    private int r;
    private int s;
    private int t;
    private int u;
    private int v;
    private int w;
    private int x;
    private int y;
    private int z;
    private int aa;
    private int ab;
    private Image ac;
    private int ad;
    private int ae;
    private int af;
    private int ag;
    private Image[] ah;
    private int[] ai;
    private int aj;
    private int ak;
    private int al;
    private int am;
    private pen an;
    private Container ao;
    private static Frame ap;
    
    public menu_panel(final Component component, final Container container, final Image image, final pen pen, final pen pen2, final pen pen3, final pen pen4, final pen pen5, final pen pen6, final boolean b, final int n, final int n2) {
        this.ah = new Image[2];
        this.ai = new int[2];
        this.a(component, container, image, pen, pen2, pen3, pen4, pen5, pen6, b, n, n2);
    }
    
    private void a(final Component a, final Container ao, final Image image, pen pen, pen pen2, pen pen3, final pen pen4, final pen pen5, final pen pen6, final boolean d, final int ag, final int af) {
        this.setLayout(null);
        final pen pen7 = new pen(new_font.a("Dialog", 0, 14), new Color(0, 0, 0), new Color(255, 255, 230));
        final pen pen8 = new pen(new_font.a("Dialog", 0, 14), new Color(255, 255, 230), new Color(0, 0, 0));
        if (pen == null) {
            pen = pen8;
        }
        if (pen2 == null) {
            pen2 = pen7;
        }
        if (pen3 == null) {
            pen3 = pen7;
        }
        this.a = a;
        this.ao = ao;
        this.d = d;
        this.ag = ag;
        this.af = af;
        this.aa = -1;
        this.ab = -1;
        this.v = 0;
        this.w = 0;
        this.x = 0;
        this.y = 0;
        this.c = false;
        this.e = pen.b;
        this.f = pen2.b;
        this.n = pen2.d;
        this.g = pen3.b;
        this.o = pen3.d;
        this.h = ((pen4 == null) ? null : pen4.b);
        this.i = ((pen5 == null) ? null : pen5.b);
        this.j = ((pen6 == null) ? null : pen6.b);
        this.k = new Vector();
        this.l = new Vector();
        this.m = new Vector();
        this.an = new pen(new Font("Dialog", 0, 11), new Color(0, 0, 0), new Color(255, 255, 230));
        this.p = this.getFontMetrics(this.n);
        this.r = this.p.getMaxAscent();
        this.s = this.r + this.p.getMaxDescent();
        if (this.d) {
            this.s += 8;
        }
        this.b(6);
    }
    
    public boolean a(final menu_panel menu_panel) {
        if (this.k.size() != menu_panel.k.size()) {
            return false;
        }
        for (int i = 0; i < this.k.size(); ++i) {
            if (!((menu_item)this.k.elementAt(i)).a((menu_item)menu_panel.k.elementAt(i))) {
                return false;
            }
        }
        return true;
    }
    
    private void b(final int aj) {
        this.aj = aj;
        if (this.aj < 0) {
            this.aj = 0;
        }
        this.z = this.aj / 2 - 1;
        if (this.z < 0) {
            this.z = 0;
        }
        this.q = this.s + this.aj;
    }
    
    public void a(final int n, final Image image, final int n2) {
        if (n < 1 || n > this.ah.length) {
            return;
        }
        this.ah[n - 1] = image;
        this.ai[n - 1] = n2;
        if (image != null) {
            if (this.al == 0) {
                this.al = 2;
            }
            if (this.am == 0) {
                this.am = 2;
            }
        }
    }
    
    public void a(final int n, final int n2, final int n3) {
        if (n3 > 0) {
            if (this.ag == 0 || this.ag == 2) {
                this.al = n3;
            }
            if (this.ag == 1 || this.ag == 2) {
                this.am = n3;
            }
        }
        if (n > 0) {
            this.ak = n - this.al - this.am;
        }
        if (n2 > 0) {
            this.b(n2 - this.s);
        }
    }
    
    public Component add(final Component component) {
        throw new RuntimeException("Base menu_panel add called");
    }
    
    public void a(final menu_item menu_item) {
        int n = 0;
        if (menu_item.b != null) {
            n = this.a(menu_item.b) + (this.d ? 8 : 1);
        }
        final int u = n + (this.al + this.am);
        this.k.addElement(menu_item);
        this.l.addElement(new Integer(u));
        this.m.addElement(this.b(menu_item.b));
        if (u > this.u) {
            this.u = u;
        }
        if (this.ak > 0) {
            this.u = this.ak + this.al + this.am;
        }
        if (this.d && menu_item.a == null) {
            final Button a = new Button(menu_item.b);
            super.add((Component)(menu_item.a = a), 0);
            a.setLocation(0, this.t);
            a.setBackground(this.e);
            a.setForeground(this.f);
            a.setFont(this.n);
            for (int i = 0; i < this.k.size(); ++i) {
                final menu_item menu_item2 = this.k.elementAt(i);
                final Object a2 = menu_item2.a;
                if (a2 != null && a2 instanceof Button) {
                    ((Button)menu_item2.a).setSize(this.u, this.s);
                }
            }
        }
        this.t += this.q;
    }
    
    public void a(final int n) {
        final int n2 = this.size().width - n;
        if (n2 <= 0) {
            return;
        }
        if (this.ag == 1) {}
        if (this.ag == 2) {}
        if (this.d) {
            for (int i = 0; i < this.k.size(); ++i) {
                final menu_item menu_item = this.k.elementAt(i);
                final Object a = menu_item.a;
                if (a != null && a instanceof Button) {
                    final Button button = (Button)menu_item.a;
                    button.setSize(button.size().width - n2, button.size().height);
                }
            }
        }
        this.u -= n2;
        this.setSize(this.size().width - n2, this.size().height);
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(this.v + this.u + this.w, this.x + this.z + this.t + this.y);
    }
    
    private void a() {
        final int width = this.size().width;
        final int height = this.size().height;
        if (width != this.ae || height != this.ad || this.ac == null) {
            this.ac = utils.a(this, width, height);
            this.ad = height;
            this.ae = width;
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        this.a();
        if (this.ac != null) {
            this.a(this.ac.getGraphics());
            graphics.drawImage(this.ac, 0, 0, this);
        }
        else {
            abljem.b("Menu not painted because doubleBuffer null");
        }
    }
    
    private void a(final Graphics graphics) {
        utils.a(graphics, this.e, 0, 0, this.size().width, this.size().height);
        try {
            final Image m = ((backgroundable)this.getParent()).m();
            if (m != null) {
                final Point location = this.getLocation();
                graphics.drawImage(m, -location.x, -location.y, this);
            }
        }
        catch (Throwable t) {}
        if (this.d) {
            return;
        }
        for (int i = 0, x = this.x; i < this.k.size(); ++i, x += this.q) {
            final menu_item menu_item = this.k.elementAt(i);
            final String b = menu_item.b;
            final int intValue = this.l.elementAt(i);
            final int[] array = this.m.elementAt(i);
            final int n = x + this.z + this.r;
            int v = this.v + this.al;
            switch (this.ag) {
                case 1: {
                    v += this.u - intValue;
                    break;
                }
                case 2: {
                    v += (this.u - intValue) / 2;
                    break;
                }
                default: {
                    v += 0;
                    break;
                }
            }
            if (v < this.v) {
                v = this.v;
            }
            if (this.i != null && !(menu_item.a instanceof Image)) {
                final int v2 = this.v;
                final int n2 = x + this.q;
                final int n3 = this.v + this.u;
                final int n4 = n2;
                graphics.setColor(this.i);
                graphics.drawLine(v2, n2, n3, n4);
                if (this.j != null) {
                    graphics.setColor(this.j);
                    graphics.drawLine(v2, n2 - 1, n3, n4 - 1);
                }
            }
            int n5 = this.ai[i == this.aa];
            Image image = this.ah[i == this.aa];
            if (menu_item.a != null && menu_item.a instanceof Image) {
                image = (Image)menu_item.a;
            }
            if (image != null) {
                final int v3 = this.v;
                final int n6 = x;
                if (n5 == 0) {
                    switch (this.ag) {
                        case 1: {
                            n5 = 6;
                            break;
                        }
                        case 2: {
                            n5 = 5;
                            break;
                        }
                        default: {
                            n5 = 4;
                            break;
                        }
                    }
                }
                final Point a = utils.a(n5, v3, n6, image, v3, n6, this.u, this.q);
                final Shape clip = graphics.getClip();
                graphics.clipRect(v3, n6, this.u, this.q);
                graphics.drawImage(image, a.x, a.y, this);
                graphics.setClip(clip);
            }
            if (b != null) {
                if (i != this.aa) {
                    graphics.setFont(this.n);
                }
                else {
                    graphics.setFont(this.o);
                }
                if (this.h != null) {
                    graphics.setColor(this.h);
                    this.a(graphics, b, v + 1, n + 1, array);
                }
                if (i != this.aa) {
                    graphics.setColor(this.f);
                }
                else {
                    graphics.setColor(this.g);
                }
                this.a(graphics, b, v, n, array);
            }
        }
    }
    
    public void a(int n, final int n2) {
        switch (this.ag) {
            case 1: {
                n -= this.getPreferredSize().width;
                break;
            }
            case 2: {
                n -= this.getPreferredSize().width / 2;
                break;
            }
        }
        if (n < 0) {
            n = 0;
            this.ag = 0;
            abljem.b("Not enough room to align menu - reverting to left-aligned");
        }
        this.move(n, n2);
    }
    
    public boolean handleEvent(final Event event) {
        if (event.target == null) {
            if (event.key == 127) {
                if (event.modifiers == 2) {
                    if (this.d) {
                        for (int i = 0; i < this.k.size(); ++i) {
                            final Object a = this.k.elementAt(i).a;
                            if (a != null && a instanceof Button) {
                                this.remove((Component)a);
                            }
                        }
                    }
                    this.k.removeAllElements();
                }
                else if (this.ab >= 0) {
                    this.a(event);
                }
                this.repaint();
            }
            return true;
        }
        if (this.a.action(event, this.a)) {
            return true;
        }
        if (event.id == 201 || event.id == 203 || event.id == 505) {
            if (!this.d) {
                this.a((Event)null);
                this.repaint();
            }
            return true;
        }
        if (event.id == 1004 || event.id == 1005 || event.id == 503 || event.id == 506 || event.id == 501 || event.id == 504) {
            if (!this.d) {
                this.a(event);
                this.repaint();
            }
            return true;
        }
        if (event.id == 401 || event.id == 403) {
            if ((event.id == 401 && event.modifiers == 0) || ((event.modifiers == 0 || event.modifiers == 2) && (event.key == 1004 || event.key == 1005))) {
                if (!this.d) {
                    this.a(event);
                    this.repaint();
                }
                if (event.key == 32 && this.ab == this.aa) {
                    this.a(event, this.aa);
                }
                if (event.key == 27) {
                    this.a.handleEvent(event);
                }
                return true;
            }
            if (event.modifiers > 1 || event.id == 403) {
                return this.a.handleEvent(event);
            }
        }
        else if (event.id == 502) {
            if (!this.d) {
                this.a(event);
                this.repaint();
                final Boolean a2 = this.a(event, this.aa);
                if (a2 != null) {
                    return a2;
                }
            }
        }
        else if (event.id == 1001) {
            for (int j = 0; j < this.k.size(); ++j) {
                if (event.target == ((menu_item)this.k.elementAt(j)).a) {
                    final Boolean a3 = this.a(event, j);
                    return a3 == null || a3;
                }
            }
        }
        return super.handleEvent(event);
    }
    
    public boolean keyDown(final Event event, final int n) {
        return this.a.action(event, this.a) || ((event.modifiers > 1 || event.id == 403) && this.a.keyDown(event, n));
    }
    
    private Boolean a(final Event event, final int n) {
        Boolean b = null;
        if (n >= 0 && n < this.k.size()) {
            event.arg = this.k.elementAt(n);
            this.setCursor(new Cursor(this.b ? 3 : 0));
            final long currentTimeMillis = System.currentTimeMillis();
            b = new Boolean(this.a.handleEvent(event));
            if (this.b && event.arg instanceof menu_item) {
                final String c = ((menu_item)event.arg).c;
                if (c.length() > 3 && c.substring(0, 3).equalsIgnoreCase("LNK")) {
                    while (System.currentTimeMillis() - currentTimeMillis < 5000L) {
                        try {
                            Thread.sleep(100L);
                        }
                        catch (InterruptedException ex) {}
                    }
                }
            }
            this.a.handleEvent(new Event(this, 0L, 401, 0, 0, 27, 0));
            this.setCursor(new Cursor(0));
        }
        return b;
    }
    
    private void a(final Event event) {
        int aa = -1;
        if (event != null) {
            if (event.target == null) {
                this.ab = -1;
            }
            else if (event.id == 1004) {
                aa = this.ab;
            }
            else if (event.id == 401 || event.id == 403) {
                switch (event.key) {
                    case 27: {
                        this.ab = -1;
                        break;
                    }
                    case 1004: {
                        int n = -1;
                        if (event.modifiers == 2 && (event.target != this || this.aa < 0)) {
                            n = this.k.size();
                        }
                        else if (this.aa >= 0) {
                            n = this.aa;
                        }
                        else if (this.ab >= 0) {
                            n = this.ab;
                        }
                        this.ab = ((n < 0) ? -1 : this.c(n));
                        aa = this.ab;
                        break;
                    }
                    case 1005: {
                        int n2 = -1;
                        if (event.modifiers == 2 && (event.target != this || this.aa < 0)) {
                            n2 = this.k.size();
                        }
                        else if (this.aa >= 0) {
                            n2 = this.aa;
                        }
                        else if (this.ab >= 0) {
                            n2 = this.ab;
                        }
                        this.ab = ((n2 < 0) ? -1 : this.d(n2));
                        aa = this.ab;
                        break;
                    }
                }
            }
            else if (event.id != 1005) {
                final int x = event.x;
                final int y = event.y;
                int i = 0;
                int x2 = this.x;
                while (i < this.k.size()) {
                    if (y >= x2 && y < x2 + this.q) {
                        aa = i;
                        if (this.ab >= 0 && this.e(i)) {
                            this.ab = i;
                            break;
                        }
                        break;
                    }
                    else {
                        ++i;
                        x2 += this.q;
                    }
                }
            }
        }
        if (aa >= this.k.size()) {
            aa = -1;
        }
        if (aa >= 0 && !this.e(aa)) {
            aa = -1;
        }
        if (aa >= 0 && event.id == 503) {
            final menu_item menu_item = this.k.elementAt(aa);
            if (!this.c) {
                this.c = true;
                this.setCursor(new Cursor(12));
            }
            tooltip.a(this.a, this.ao, this.an, menu_item, event);
        }
        else {
            if (this.c) {
                this.c = false;
                this.setCursor(new Cursor(0));
            }
            tooltip.a(this.a);
        }
        if ((event == null || (event.id != 502 && event.id != 1005)) && aa < 0 && this.ab >= 0) {
            aa = this.ab;
        }
        this.aa = aa;
    }
    
    private int c(final int n) {
        int n2;
        for (n2 = n - 1; n2 >= 0 && !this.e(n2); --n2) {}
        if (n2 < 0) {
            for (n2 = this.k.size() - 1; n2 >= 0 && !this.e(n2); --n2) {}
        }
        return n2;
    }
    
    private int d(final int n) {
        int n2;
        for (n2 = n + 1; n2 < this.k.size() && !this.e(n2); ++n2) {}
        if (n2 >= this.k.size()) {
            for (n2 = 0; n2 < this.k.size() && !this.e(n2); ++n2) {}
        }
        if (n2 >= this.k.size()) {
            n2 = -1;
        }
        return n2;
    }
    
    private boolean e(final int n) {
        if (n < 0 || n >= this.k.size()) {
            return false;
        }
        final menu_item menu_item = this.k.elementAt(n);
        return menu_item.a == null || !(menu_item.a instanceof Image);
    }
    
    private int a(final String s) {
        return this.p.stringWidth(s) + ((this.af < 1) ? 0 : (this.af * (s.length() - 1)));
    }
    
    private int[] b(final String s) {
        if (s == null) {
            return new int[0];
        }
        final int[] array = new int[s.length()];
        for (int i = 0; i < s.length(); ++i) {
            array[i] = this.p.charWidth(s.charAt(i));
        }
        return array;
    }
    
    private void a(final Graphics graphics, final String s, int n, final int n2, final int[] array) {
        if (this.af < 1) {
            graphics.drawString(s, n, n2);
            return;
        }
        final char[] charArray = s.toCharArray();
        for (int i = 0; i < s.length(); ++i) {
            graphics.drawChars(charArray, i, 1, n, n2);
            n += array[i] + this.af;
        }
    }
    
    static {
        menu_panel.ap = new Frame();
    }
}
