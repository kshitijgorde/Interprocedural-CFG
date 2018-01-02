// 
// Decompiled by Procyon v0.5.30
// 

package com.alphatrade.applet;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.image.ImageObserver;
import java.util.Enumeration;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.Font;
import java.awt.BorderLayout;
import java.net.URL;
import java.awt.LayoutManager;
import java.awt.Button;
import java.awt.Panel;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Vector;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;

public class TickerToolbarApplet extends BaseBannerApplet implements MouseListener, MouseMotionListener, ActionListener
{
    private static final Color h;
    private static Vector i;
    private p j;
    private Vector k;
    private String[] l;
    private int m;
    private boolean n;
    private boolean o;
    private Image p;
    private Graphics q;
    private int r;
    private boolean s;
    private int t;
    private int u;
    private int v;
    private Color w;
    private Color x;
    private Panel y;
    private Button z;
    private Image A;
    private Image B;
    private static boolean C;
    
    public TickerToolbarApplet() {
        this.l = new String[0];
        this.w = TickerToolbarApplet.b;
        this.x = Color.black;
    }
    
    public void init() {
        TickerToolbarApplet.i.addElement(this);
        this.m = 0;
        this.o = false;
        this.n = false;
        this.p = null;
        this.q = null;
        this.r = 0;
        this.v = 0;
        this.s = true;
        super.init();
        this.setLayout(null);
        this.m = 0;
        try {
            if (this.getParameter("symbols") != null) {
                this.l = BaseBannerApplet.e(this.getParameter("symbols"));
                this.m = this.l.length;
            }
            if (this.getParameter("bgColor") != null) {
                this.w = Color.decode(this.getParameter("bgColor"));
            }
            if (this.getParameter("fgColor") != null) {
                this.x = Color.decode(this.getParameter("fgColor"));
            }
        }
        catch (Exception ex) {
            System.err.println("AlphaTicker - ERROR SETTING PARAMETERS: " + ex);
            ex.printStackTrace();
        }
        try {
            this.A = this.getImage(new URL(this.getCodeBase(), "alphalogo-25x25.gif"));
            this.B = this.getImage(new URL(this.getCodeBase(), "alphalogo-30x30.gif"));
        }
        catch (Exception ex2) {
            System.err.println("AlphaTicker - ERROR FINDING IMAGE: " + ex2);
        }
        final Dimension size = this.getSize();
        this.y = new Panel(new BorderLayout());
        (this.z = new Button("Delayed at least 15 min.")).setFont(new Font("Dialog", 0, 9));
        this.z.setBackground(this.w);
        this.z.setForeground(this.x);
        this.z.addActionListener(this);
        this.z.addMouseListener(this);
        this.y.add(this.z, "Center");
        (this.j = new p(this.A, Color.white)).setSize(size.height, size.height);
        this.j.addMouseListener(this);
        this.y.add(this.j, "East");
        if (this.m > 0) {
            this.a(this.l);
        }
        this.add(this.y);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.u = this.z.getPreferredSize().width;
        super.e = 20L;
    }
    
    public void destroy() {
        TickerToolbarApplet.i.removeElement(this);
        super.destroy();
    }
    
    private static void a(final TickerToolbarApplet tickerToolbarApplet, final int n, final String s) {
        if (TickerToolbarApplet.C || TickerToolbarApplet.i.size() < 2) {
            return;
        }
        TickerToolbarApplet.C = true;
        final Enumeration<TickerToolbarApplet> elements = TickerToolbarApplet.i.elements();
        while (elements.hasMoreElements()) {
            final TickerToolbarApplet tickerToolbarApplet2;
            if ((tickerToolbarApplet2 = elements.nextElement()) != tickerToolbarApplet) {
                switch (n) {
                    case 0: {
                        tickerToolbarApplet2.a(s);
                        continue;
                    }
                    case 1: {
                        tickerToolbarApplet2.b(s);
                        continue;
                    }
                    case 2: {
                        tickerToolbarApplet2.c(s);
                        continue;
                    }
                    case 3: {
                        final Dimension size = tickerToolbarApplet.getSize();
                        tickerToolbarApplet2.resize(size.width, size.height);
                        continue;
                    }
                    case 4: {
                        tickerToolbarApplet2.r = tickerToolbarApplet.r;
                        tickerToolbarApplet2.s = tickerToolbarApplet.s;
                        continue;
                    }
                }
            }
        }
        TickerToolbarApplet.C = false;
    }
    
    public final void a(final String s) {
        if (s == null || s.length() == 0) {
            return;
        }
        boolean b = true;
        final String[] e = BaseBannerApplet.e(s);
        this.m = e.length;
        if (this.m > 0) {
            if (this.m == this.l.length) {
                b = false;
                for (int i = 0; i < this.m; ++i) {
                    if (!e[i].equals(this.l[i])) {
                        b = true;
                        break;
                    }
                }
            }
            if (b) {
                this.a(this.l = e);
                this.o = true;
                a(this, 0, s);
            }
        }
    }
    
    public final void b(final String s) {
        if (s == null || s.length() == 0) {
            return;
        }
        final Color decode;
        if (!(decode = Color.decode(s)).equals(this.x)) {
            this.x = decode;
            this.z.setForeground(this.x);
            this.n = true;
            a(this, 1, s);
        }
    }
    
    public final void c(final String s) {
        if (s == null || s.length() == 0) {
            return;
        }
        final Color decode;
        if (!(decode = Color.decode(s)).equals(this.w)) {
            this.w = decode;
            this.z.setBackground(this.w);
            this.n = true;
            a(this, 2, s);
        }
    }
    
    public void resize(final int n, int n2) {
        if (n2 < 10) {
            n2 = 10;
        }
        super.resize(n, n2);
        if (this.y != null) {
            this.y.setBounds(n - this.u - n2, 0, this.u + n2, n2);
        }
        if (this.j != null) {
            if (n2 < 29) {
                this.j.a(this.A);
            }
            else {
                this.j.a(this.B);
            }
            this.j.setSize(n2, n2);
        }
    }
    
    public void invalidate() {
        super.invalidate();
        this.o = true;
    }
    
    public final synchronized void a() {
        this.k = super.g;
        final int size;
        if ((size = this.k.size()) < 0) {
            return;
        }
        if (size > 0) {
            this.t = size * 140;
        }
        else {
            this.t = 140;
        }
        if (this.o || this.m != size) {
            this.m = size;
            this.o = false;
            this.p = null;
            if (this.q != null) {
                this.q.dispose();
                this.q = null;
            }
        }
        if (this.p == null) {
            this.p = this.createImage(this.getSize().width + this.t, this.getSize().height);
            if (this.p == null) {
                return;
            }
            this.q = this.p.getGraphics();
        }
        if (size == 0) {
            this.q.setColor(this.w);
            this.q.fillRect(0, 0, this.getSize().width + this.t, this.getSize().height);
        }
        try {
            final Image image;
            final Graphics graphics;
            (graphics = (image = this.createImage(this.t, this.getSize().height)).getGraphics()).setColor(this.w);
            graphics.fillRect(0, 0, this.t, this.getSize().height);
            final Font font = new Font("Dialog", 1, 11);
            graphics.setFont(font);
            final int n = graphics.getFontMetrics().getHeight() - 1;
            final int n2 = (this.getSize().height - n) / 2 + n;
            for (int i = 0; i < this.k.size(); ++i) {
                final String[] array = this.k.elementAt(i);
                graphics.setColor(this.x);
                graphics.setFont(font);
                final String upperCase = array[0].toUpperCase();
                graphics.drawString(upperCase, i * 140, n2);
                final int n3 = 8 + graphics.getFontMetrics().stringWidth(upperCase);
                graphics.setFont(new Font("Dialog", 0, 10));
                String substring;
                final char char1;
                if ((char1 = (substring = array[1]).charAt(0)) == '+' || char1 == '-') {
                    substring = substring.substring(1);
                }
                graphics.drawString(substring, n3 + i * 140, n2);
                final int n4 = 5 + n3 + graphics.getFontMetrics().stringWidth(substring);
                String string;
                final char char2;
                if ((char2 = (string = array[2]).charAt(0)) == '-') {
                    graphics.setColor(Color.red);
                }
                else {
                    if (char2 != '+') {
                        string = '+' + string;
                    }
                    graphics.setColor(TickerToolbarApplet.h);
                }
                graphics.drawString(string, n4 + i * 140, n2);
            }
            for (int n5 = this.getSize().width / this.t + 2, j = 0; j < n5; ++j) {
                this.q.drawImage(image, j * this.t, 0, null);
            }
        }
        catch (Exception ex) {
            System.err.println("AlphaTicker - ERROR DRAWING BUFFER: " + ex);
            ex.printStackTrace();
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.b();
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        if (this.t > 0) {
            this.s = false;
            this.r -= x - this.v;
            if (this.r < 0) {
                this.r += this.t;
            }
            this.r %= this.t;
            this.repaint();
            a(this, 4, null);
        }
        this.v = x;
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof p) {
            this.b();
            return;
        }
        if (mouseEvent.getSource() == this) {
            this.v = mouseEvent.getX();
            this.s = !this.s;
            a(this, 4, null);
        }
    }
    
    private void b() {
        try {
            this.getAppletContext().showDocument(new URL("http", "www.alphatrade.com/", "index.html?siteName=alphaticker"), "_blank");
        }
        catch (Exception ex) {
            System.err.println("AlphaTicker - ERROR FINDING URL: " + ex);
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == this) {
            this.v = mouseEvent.getX();
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.setCursor(new Cursor(12));
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.setCursor(new Cursor(0));
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.o || this.n) {
            this.a();
        }
        if (this.p != null) {
            final Dimension size = this.getSize();
            if (this.s) {
                ++this.r;
            }
            this.r %= this.t;
            graphics.clipRect(0, 0, size.width - (this.u + size.height), size.height);
            graphics.drawImage(this.p, -this.r, 0, null);
        }
    }
    
    static {
        h = new Color(49152);
        TickerToolbarApplet.i = new Vector();
        TickerToolbarApplet.C = false;
    }
}
