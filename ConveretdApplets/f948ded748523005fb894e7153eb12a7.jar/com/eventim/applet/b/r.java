// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.applet.b;

import java.awt.Point;
import javax.swing.Icon;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Shape;
import java.awt.Paint;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.GridLayout;
import java.awt.Component;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import com.eventim.applet.EventimApplet;
import javax.swing.JLabel;
import java.awt.Stroke;
import java.awt.Color;
import javax.swing.JPanel;

public final class r extends JPanel
{
    public static int a;
    public static int b;
    private static Color c;
    private static Stroke d;
    private static Color e;
    private static Stroke f;
    private JPanel g;
    private JLabel h;
    private int i;
    private JPanel j;
    private int k;
    private JLabel l;
    private EventimApplet m;
    private BufferedImage n;
    private Rectangle o;
    private JLabel p;
    private BufferedImage q;
    private Rectangle r;
    
    static {
        new Dimension(128, 128);
        r.a = 1;
        r.b = 1;
        r.e = new Color(1.0f, 0.0f, 0.0f, 0.5f);
        r.f = new BasicStroke(1.0f);
        r.c = new Color(1.0f, 0.0f, 0.0f, 0.5f);
        r.d = new BasicStroke(1.0f);
    }
    
    public r(final int k, final boolean b, final Color color, final Color color2, final Color color3, final Color color4, final Color background, final EventimApplet m) {
        super(new BorderLayout());
        this.h = new JLabel();
        this.g = new JPanel(null);
        this.j = new JPanel();
        this.k = k;
        this.m = m;
        if (k == 0) {
            final y y = new y(color2, color);
            this.p = new JLabel("<html><b>" + m.d().A().a("\u00dcbersicht") + "</b></html>");
            if (m.d().T()) {
                this.p.setForeground(Color.white);
            }
            this.p.setBorder(new EmptyBorder(0, 6, 0, 0));
            y.add(this.p);
            y.setPreferredSize(new Dimension(Integer.MAX_VALUE, 20));
            y.setBackground(background);
            this.add(y, "North");
        }
        else {
            if (k != com.eventim.applet.b.r.a) {
                throw new IllegalArgumentException("Unrecognised style: " + k);
            }
            final d d = new d(this, new GridLayout(1, 2), color2, color, color4, color3);
            if (k == com.eventim.applet.b.r.a) {
                this.l = new JLabel("<html><b>" + m.d().A().a("\u00dcbersicht") + "</b></html>");
                if (m.d().T()) {
                    this.l.setForeground(Color.white);
                }
                this.l.setBorder(new EmptyBorder(0, 6, 0, 0));
                this.l.addMouseListener(new a(this));
                d.add(this.l);
            }
            this.p = new JLabel(m.d().A().a("Detailansicht"));
            if (m.d().T()) {
                this.p.setForeground(Color.white);
            }
            this.p.setBorder(new EmptyBorder(0, 6, 0, 0));
            this.p.addMouseListener(new aa(this));
            d.setBackground(background);
            d.add(this.p);
            d.setPreferredSize(new Dimension(Integer.MAX_VALUE, 20));
            this.add(d, "North");
        }
        this.g.add(this.h);
        this.g.setAlignmentX(0.0f);
        this.g.setAlignmentY(0.0f);
        this.add(this.g, "Center");
        this.h.addMouseListener(new af(this));
        this.h.addMouseMotionListener(new i(this));
        this.g.setBackground(m.d().B());
        (this.j = new ab()).setPreferredSize(new Dimension(Integer.MAX_VALUE, 6));
        this.j.setBackground(background);
        this.add(this.j, "South");
        this.setPreferredSize(new Dimension(160, 164));
    }
    
    static int a(final r r) {
        return r.i;
    }
    
    static EventimApplet b(final r r) {
        return r.m;
    }
    
    static BufferedImage c(final r r) {
        return r.n;
    }
    
    private static double a(final int n, final int n2, final int n3, final int n4) {
        return Math.min(n3 / n, n4 / n2);
    }
    
    public final void paint(final Graphics graphics) {
        super.paint(graphics);
        final Graphics2D graphics2D;
        (graphics2D = (Graphics2D)graphics).translate(this.h.getX(), this.h.getY());
        if (this.i == 0 && this.r != null) {
            graphics2D.setPaint(com.eventim.applet.b.r.e);
            graphics2D.setStroke(com.eventim.applet.b.r.f);
            graphics2D.draw(this.r);
        }
        if (this.i == com.eventim.applet.b.r.b && this.o != null) {
            graphics2D.setPaint(com.eventim.applet.b.r.c);
            graphics2D.setStroke(com.eventim.applet.b.r.d);
            graphics2D.draw(this.o);
        }
        graphics2D.translate(0, 0);
    }
    
    private void b() {
        final BufferedImage bufferedImage;
        final int width = (bufferedImage = ((this.i == 0) ? this.q : this.n)).getWidth();
        final int height = bufferedImage.getHeight();
        Dimension size;
        if ((size = this.g.getSize()).width <= 20 && size.height <= 20) {
            size = new Dimension(156, 134);
        }
        this.h.setBounds(size.width / 2 - width / 2, size.height / 2 - height / 2, width, height);
    }
    
    public final void a(final BufferedImage n) {
        this.n = n;
    }
    
    public final void b(final BufferedImage q) {
        this.q = q;
    }
    
    public final void a(final int i) {
        this.i = i;
        this.h.setIcon(new ImageIcon((i == 0) ? this.q : this.n));
        this.b();
        if (i == 0) {
            if (this.k == com.eventim.applet.b.r.a) {
                this.l.setText("<html><b>" + this.m.d().A().a("\u00dcbersicht") + "</b></html>");
                this.p.setText(this.m.d().A().a("Detailansicht"));
            }
        }
        else if (this.k == com.eventim.applet.b.r.a) {
            this.l.setText(this.m.d().A().a("\u00dcbersicht"));
            this.p.setText("<html><b>" + this.m.d().A().a("Detailansicht") + "</b></html>");
        }
        this.repaint();
    }
    
    public final Point a(final Point point) {
        final int n = this.m.n() ? this.q.getWidth() : this.n.getWidth();
        final int n2 = this.m.n() ? this.q.getHeight() : this.n.getHeight();
        final BufferedImage b = this.m.b().b();
        final double a = a(b.getWidth(), b.getHeight(), n, n2);
        final double n3 = point.x;
        final double n4 = point.y - (20 + this.h.getY()) * a;
        double n5;
        double n6;
        double n7;
        double n8;
        if (this.i == 0) {
            if (this.r == null) {
                return null;
            }
            n5 = n3 - this.r.width / 2;
            n6 = n4 - this.r.height / 2;
            n7 = n - this.r.getWidth();
            n8 = n2 - this.r.getHeight();
        }
        else {
            if (this.o == null) {
                return null;
            }
            n5 = n3 - this.o.width / 2;
            n6 = n4 - this.o.height / 2;
            n7 = n - this.o.getWidth();
            n8 = n2 - this.o.getHeight();
        }
        return new Point((int)(Math.min(Math.max(n5, 0.0), n7) / a), (int)(Math.min(Math.max(n6, 0.0), n8) / a));
    }
    
    public final void a(final int n, Rectangle rectangle, int n2, final int n3, final boolean b) {
        if ((n == 0 && this.q == null) || (n == com.eventim.applet.b.r.b && this.n == null)) {
            return;
        }
        if (!b) {
            Rectangle rectangle2;
            if (rectangle == null) {
                rectangle2 = null;
            }
            else {
                final Rectangle rectangle3 = rectangle;
                final int n4 = (n == 0) ? this.q.getWidth() : this.n.getWidth();
                final int n5 = (n == 0) ? this.q.getHeight() : this.n.getHeight();
                final double a = a(n2, n3, n4, n5);
                n2 = (int)Math.round(rectangle3.x * a);
                rectangle2 = new Rectangle(n2, (int)Math.round(rectangle3.y * a) + 20, Math.min((int)Math.round(rectangle3.width * a), n4 - 1), Math.min((int)Math.round(rectangle3.height * a), n5 - 1));
            }
            rectangle = rectangle2;
            if (n == 0) {
                this.r = rectangle;
            }
            else {
                this.o = rectangle;
            }
        }
        else if (n == 0) {
            this.r = null;
        }
        else {
            this.o = null;
        }
        this.repaint();
    }
    
    public final void a() {
        this.h.setIcon(new ImageIcon((this.i == 0) ? this.q : this.n));
        this.b();
        this.repaint();
    }
}
