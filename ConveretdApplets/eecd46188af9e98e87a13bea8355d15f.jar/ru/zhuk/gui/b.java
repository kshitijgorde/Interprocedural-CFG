// 
// Decompiled by Procyon v0.5.30
// 

package ru.zhuk.gui;

import java.awt.Font;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Cursor;
import ru.zhuk.graphics.a;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.LayoutManager;
import java.awt.Polygon;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.awt.Container;

public class b extends Container implements MouseListener, Runnable
{
    private Image q;
    private Image x;
    private Image b;
    private Image t;
    private Image o;
    private int l;
    private int s;
    private MouseListener i;
    private int H;
    private int C;
    private Color u;
    private int y;
    private int E;
    private int c;
    private int m;
    private int f;
    private int p;
    private int v;
    private int g;
    private Polygon r;
    public int d;
    private boolean e;
    private ru.zhuk.graphics.b D;
    private int h;
    private String B;
    private Color z;
    private int k;
    private long F;
    private boolean n;
    private final int G;
    private Image[] A;
    private int j;
    private int w;
    private int a;
    
    public b(final Color background, final Image t, final int g) {
        this.H = 0;
        this.u = Color.black;
        this.E = -1;
        this.z = Color.black;
        this.F = 0L;
        this.setLayout(null);
        this.G = g;
        this.A = new Image[this.G];
        this.setBackground(background);
        this.t = t;
        this.enableEvents(48L);
        ((Component)(this.i = this)).addMouseListener(this.i);
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        final long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.F < 300) {
            final Component component = (Component)mouseEvent.getSource();
            if (component != this) {
                this.a(component);
                this.F = 0L;
            }
            else {
                this.f();
            }
        }
        else {
            this.F = currentTimeMillis;
        }
    }
    
    public void f() {
        if (this.r != null) {
            this.r.addPoint(this.r.xpoints[0], this.r.ypoints[0]);
            final Graphics graphics = this.q.getGraphics();
            graphics.setColor(this.u);
            graphics.fillPolygon(this.r);
            this.r = null;
            this.repaint();
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    private int c(final int n) {
        if (this.d > 1 && this.C != 9 && this.C != 7 && this.C != 0 && this.C != 8 && this.C != 12) {
            final int n2 = (n << 1) / this.d;
            return (((n2 & 0x1) != 0x0) ? ((n2 >> 1) + 1) : (n2 >> 1)) * this.d;
        }
        return n;
    }
    
    protected void processMouseEvent(final MouseEvent mouseEvent) {
        switch (mouseEvent.getID()) {
            case 501: {
                if (this.a()) {
                    this.a((Component)null);
                    return;
                }
                if (this.o != null) {
                    this.g();
                    this.e();
                    return;
                }
                this.n = true;
                if (this.C == 7) {
                    this.E = this.y;
                    this.y = 14;
                }
                if (this.C != 11) {
                    if (this.C != 18 || this.r == null) {
                        this.g();
                    }
                }
                else {
                    final g g = new g();
                    g.a(this.H);
                    g.a(true);
                    g.setLocation(mouseEvent.getPoint());
                    final d d = new d(g);
                    g.addMouseListener(this.i);
                    g.setForeground(this.u);
                    this.add(g, 0);
                    g.requestFocus();
                }
                this.f = this.c(mouseEvent.getX());
                this.p = this.c(mouseEvent.getY());
                this.v = this.f;
                this.g = this.p;
                if (this.C == 18 && this.r == null) {
                    (this.r = new Polygon()).addPoint(this.f, this.p);
                }
                this.e = true;
                this.D.a().setColor(this.u);
                this.D.a(this.y);
                this.repaint();
                break;
            }
            case 502: {
                if (this.o != null) {
                    this.o = null;
                    this.n = false;
                    return;
                }
                if (this.n) {
                    this.n = false;
                    this.v = this.c(mouseEvent.getX());
                    this.g = this.c(mouseEvent.getY());
                    if (this.C == 18 && this.r != null) {
                        this.r.addPoint(this.v, this.g);
                    }
                    switch (this.C) {
                        case 7: {
                            if (this.E >= 0) {
                                this.y = this.E;
                                this.E = -1;
                                break;
                            }
                            break;
                        }
                        case 9: {
                            try {
                                final a a = new a(this, this.x.getSource(), 0, 0, this.x.getWidth(null), this.x.getHeight(null));
                                a.a(this.f, this.p, this.u);
                                this.x.getGraphics().drawImage(a.a(), 0, 0, a.b(), a.c(), this);
                            }
                            catch (Exception ex) {}
                            break;
                        }
                        case 8:
                        case 12: {
                            final int min = Math.min(this.f, this.v);
                            final int min2 = Math.min(this.p, this.g);
                            final int n = Math.abs(this.f - this.v) + 1;
                            final int n2 = Math.abs(this.p - this.g) + 1;
                            if (n <= 1) {
                                if (n2 <= 1) {
                                    break;
                                }
                            }
                            try {
                                this.a(new a(this, this.q.getSource(), min, min2, n, n2).a(), min, min2, 0, false);
                                if (this.C == 12) {
                                    final Graphics graphics = this.x.getGraphics();
                                    graphics.setColor(this.u);
                                    graphics.fillRect(min, min2, n, n2);
                                }
                            }
                            catch (Exception ex2) {}
                            break;
                        }
                    }
                    this.e();
                    this.repaint();
                    break;
                }
                return;
            }
        }
        super.processMouseEvent(mouseEvent);
    }
    
    protected void processMouseMotionEvent(final MouseEvent mouseEvent) {
        if (mouseEvent.getID() == 506 && this.n) {
            final int c = this.c(mouseEvent.getX());
            final int c2 = this.c(mouseEvent.getY());
            if (c != this.v || c2 != this.g) {
                this.v = c;
                this.g = c2;
            }
            this.e = true;
        }
        else if (mouseEvent.getID() == 503 && this.o != null) {
            this.f = mouseEvent.getX();
            this.p = mouseEvent.getY();
            this.e = true;
        }
        this.repaint();
        super.processMouseMotionEvent(mouseEvent);
    }
    
    public void d(int c) {
        if (c == 13) {
            c = 8;
        }
        this.setCursor(Cursor.getPredefinedCursor((int)((c != 0 && c != 7 && c != 9) ? 1 : 0)));
        this.f();
        this.C = c;
        this.a((Component)null);
    }
    
    public void a(final Color color) {
        this.u = color;
        final Component[] components = this.getComponents();
        for (int i = 0; i < components.length; ++i) {
            components[i].setForeground(color);
        }
    }
    
    public void a(final int y) {
        this.y = y;
    }
    
    public void b(final int h) {
        this.H = h;
        final Component[] components = this.getComponents();
        for (int i = 0; i < components.length; ++i) {
            if (components[i] instanceof g) {
                ((g)components[i]).a(h);
            }
        }
    }
    
    public void a(final Image b, final int l, final int s) {
        this.b = b;
        this.l = l;
        this.s = s;
        this.r = null;
        this.removeAll();
        this.h = 0;
        this.repaint();
    }
    
    public Image a(int n, int n2) {
        this.a((Component)null);
        this.f();
        Image image;
        if (n >= 0 && n2 >= 0) {
            if (n == 0) {
                n = 1;
            }
            if (n2 == 0) {
                n2 = 1;
            }
            image = this.q.getScaledInstance(n, n2, 1);
        }
        else {
            image = this.q;
        }
        this.h = 0;
        return image;
    }
    
    public void a(final Image o, final int n, final int n2, final int n3, final boolean b) {
        if (n != Integer.MAX_VALUE && n2 != Integer.MAX_VALUE) {
            final ru.zhuk.gui.a a = new ru.zhuk.gui.a();
            a.a(o);
            a.a(n3);
            a.a(b);
            a.setForeground(this.u);
            int max = Math.max(o.getWidth(null), 1);
            int max2 = Math.max(o.getHeight(null), 1);
            if (max > this.c || max2 > this.m) {
                final double min = Math.min(this.c / max, this.m / max2);
                max = (int)Math.round(min * max);
                max2 = (int)Math.round(min * max2);
            }
            a.setBounds(n, n2, max, max2);
            this.a((Component)null);
            this.add(a, 0);
            a.b(true);
            new d(a).a(a.getSize());
            a.addMouseListener(this.i);
        }
        else {
            this.a((Component)null);
            this.o = o;
        }
        this.f();
    }
    
    public void b(final Color z) {
        if (z != null) {
            this.z = z;
        }
    }
    
    public void a(final String b) {
        this.B = b;
        if (this.B != null) {
            final Thread thread = new Thread(this);
            this.k = 0;
            thread.start();
        }
    }
    
    private boolean a() {
        return this.getComponents().length > 0;
    }
    
    private void a(final Component component) {
        Component[] components;
        if (component != null) {
            components = new Component[] { component };
        }
        else {
            components = this.getComponents();
        }
        int i = components.length;
        if (i > 0) {
            final Graphics graphics = this.q.getGraphics();
            int x = 0;
            int y = 0;
            this.g();
            while (i > 0) {
                final Component component2 = components[i - 1];
                final Rectangle bounds = component2.getBounds();
                graphics.setClip(bounds.x, bounds.y, bounds.width, bounds.height);
                graphics.translate(bounds.x - x, bounds.y - y);
                x = bounds.x;
                y = bounds.y;
                if (component2 instanceof ru.zhuk.gui.a) {
                    ((ru.zhuk.gui.a)component2).b(false);
                }
                else if (component2 instanceof g) {
                    ((g)component2).a(false);
                }
                component2.paint(graphics);
                this.remove(component2);
                this.repaint(bounds.x, bounds.y, bounds.width, bounds.height);
                --i;
            }
        }
    }
    
    public void c() {
        if (this.q != null) {
            this.g();
        }
        this.q = null;
        this.b = null;
        this.r = null;
        this.removeAll();
        this.h = 0;
        this.repaint();
    }
    
    public boolean d() {
        return this.h > 0;
    }
    
    private void g() {
        if (this.A[this.j] == null) {
            this.A[this.j] = this.createImage(this.c, this.m);
        }
        this.A[this.j].getGraphics().drawImage(this.q, 0, 0, this.c, this.m, null);
        if (this.j < this.G - 1) {
            ++this.j;
        }
        else {
            this.j = 0;
        }
        if (this.w < this.G) {
            ++this.w;
        }
        this.a = 0;
        ++this.h;
    }
    
    public void h() {
        final Component[] components = this.getComponents();
        if (components.length > 0) {
            final Rectangle bounds = components[0].getBounds();
            this.remove(0);
            this.repaint(bounds.x, bounds.y, bounds.width, bounds.height);
            if (this.C != 12) {
                return;
            }
        }
        if (this.w > 0) {
            if (this.j > 0) {
                --this.j;
            }
            else {
                this.j = this.G - 1;
            }
            final Image q = this.q;
            this.q = this.A[this.j];
            this.A[this.j] = q;
            this.repaint();
            --this.w;
            ++this.a;
            ++this.h;
        }
    }
    
    public void b() {
        if (this.a > 0) {
            final Image q = this.q;
            this.q = this.A[this.j];
            this.A[this.j] = q;
            this.repaint();
            if (this.j < this.G - 1) {
                ++this.j;
            }
            else {
                this.j = 0;
            }
            --this.a;
            ++this.w;
        }
    }
    
    private void e() {
        this.q.getGraphics().drawImage(this.x, 0, 0, null);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.c == 0) {
            final Dimension size = this.getSize();
            this.c = size.width;
            this.m = size.height;
        }
        if (this.q == null) {
            this.q = this.createImage(this.c, this.m);
            final Graphics graphics2 = this.q.getGraphics();
            final Image image = this.createImage(this.c, this.m);
            if (this.t != null) {
                image.getGraphics().drawImage(this.t, 0, 0, this.t.getWidth(null) - 1, this.t.getHeight(null) - 1, 0, 0, this.t.getWidth(null) - 1, this.t.getHeight(null) - 1, null);
            }
            graphics2.drawImage(this.t = image, 0, 0, this.c - 1, this.m - 1, 0, 0, this.c - 1, this.m - 1, null);
        }
        if (this.x == null) {
            this.x = this.createImage(this.c, this.m);
            this.D = new ru.zhuk.graphics.b(this.x.getGraphics());
        }
        if (this.b != null) {
            final int width = this.b.getWidth(null);
            final int height = this.b.getHeight(null);
            switch (this.l) {
                case 2147483646: {
                    this.l = (this.c - width) / 2;
                    break;
                }
                case 2147483645: {
                    this.l = this.c - width;
                    break;
                }
            }
            switch (this.s) {
                case 2147483646: {
                    this.s = (this.m - height) / 2;
                    break;
                }
                case 2147483645: {
                    this.s = this.m - height;
                    break;
                }
            }
            this.q.getGraphics().drawImage(this.b, this.l, this.s, this);
            this.D.a().drawImage(this.b, this.l, this.s, this);
            this.b = null;
        }
        if (this.e) {
            final int min = Math.min(this.f, this.v);
            final int min2 = Math.min(this.p, this.g);
            final int n = Math.abs(this.f - this.v) + 1;
            final int n2 = Math.abs(this.p - this.g) + 1;
            final int n3 = 16;
            final Graphics a = this.D.a();
            a.drawImage(this.q, 0, 0, this);
            if (this.o == null) {
                switch (this.C) {
                    case 7: {
                        this.D.a(this.t);
                    }
                    case 0: {
                        this.D.b(this.f, this.p, this.v, this.g);
                        this.f = this.v;
                        this.p = this.g;
                        this.D.a(null);
                        this.e();
                        break;
                    }
                    case 1: {
                        this.D.b(this.f, this.p, this.v, this.g);
                        break;
                    }
                    case 18: {
                        if (this.r != null) {
                            final int npoints = this.r.npoints;
                            a.drawLine(this.r.xpoints[npoints - 1], this.r.ypoints[npoints - 1], this.v, this.g);
                            break;
                        }
                        break;
                    }
                    case 14: {
                        this.D.d(this.f, this.p, this.v, this.g, 2);
                        break;
                    }
                    case 15: {
                        this.D.d(this.f, this.p, this.v, this.g, 3);
                        break;
                    }
                    case 16: {
                        this.D.d(this.f, this.p, this.v, this.g, 4);
                        break;
                    }
                    case 2: {
                        this.D.c(this.f, this.p, this.v, this.g, 0);
                        break;
                    }
                    case 3: {
                        this.D.b(this.f, this.p, this.v, this.g, 0);
                        break;
                    }
                    case 19: {
                        this.D.c(this.f, this.p, this.v, this.g, n3);
                        break;
                    }
                    case 20: {
                        this.D.b(this.f, this.p, this.v, this.g, n3);
                        break;
                    }
                    case 4:
                    case 5: {
                        final int n4 = n / 2;
                        final int n5 = n2 / 2;
                        this.D.a(min + n4, min2 + n5, n4, n5, this.C == 5);
                        break;
                    }
                }
            }
            if (this.o != null) {
                a.drawImage(this.o, this.f - this.o.getWidth(null), this.p - this.o.getHeight(null), this);
            }
            graphics.drawImage(this.x, 0, 0, this);
            super.paint(graphics);
            if (this.o == null) {
                this.a(graphics);
                switch (this.C) {
                    case 7: {
                        graphics.setColor(Color.black);
                        graphics.drawRect(this.v - 4, this.g - 4, 8, 8);
                        break;
                    }
                    case 8:
                    case 12: {
                        graphics.setColor(Color.white);
                        graphics.setXORMode(Color.black);
                        ru.zhuk.graphics.b.a(graphics, min, min2, n - 1, n2 - 1, 0);
                        graphics.setPaintMode();
                        break;
                    }
                }
            }
            this.e = false;
        }
        else {
            graphics.drawImage(this.q, 0, 0, this);
            super.paint(graphics);
            this.a(graphics);
        }
    }
    
    private void a(final Graphics graphics) {
        if (this.B != null) {
            final Dimension size = this.getSize();
            final Font font = new Font("Dialog", 1, 16);
            graphics.setFont(font);
            final int max = Math.max(graphics.getFontMetrics().stringWidth(this.B), 32);
            final int size2 = font.getSize();
            final int n = (size.width - max) / 2;
            final int n2 = (size.height - size2) / 2;
            graphics.setColor(this.z);
            graphics.fillRoundRect(n - size2, n2 - 4 * size2 / 3, max + size2 * 2, 3 * size2, size2 / 4, size2 / 4);
            graphics.setColor(Color.white);
            graphics.drawString(this.B, n, n2);
            graphics.drawRect(n, n2 + size2 / 2, max - 1, size2 / 2);
            graphics.fillRect(n + ((this.k & 0x7) * max >>> 3), n2 + size2 / 2, max >>> 3, size2 / 2);
        }
    }
    
    public void run() {
        while (this.B != null) {
            this.repaint();
            try {
                Thread.sleep(1000L);
                this.k = (this.k + 1 & 0xFF);
                continue;
            }
            catch (InterruptedException ex) {}
            break;
        }
        this.repaint();
    }
}
