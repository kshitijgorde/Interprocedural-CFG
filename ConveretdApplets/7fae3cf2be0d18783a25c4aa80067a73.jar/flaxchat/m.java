// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat;

import java.awt.FontMetrics;
import java.awt.Shape;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Window;
import flaxchat.a.c;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import flaxchat.a.h;
import flaxchat.d.d;
import java.awt.event.MouseEvent;
import java.awt.event.ComponentEvent;
import flaxchat.d.b;
import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ComponentListener;
import java.awt.event.MouseListener;
import java.awt.Panel;

public class m extends Panel implements MouseListener, ComponentListener
{
    private String a;
    private FlaxChat b;
    private k c;
    private Rectangle d;
    private Image e;
    private static String[] z;
    
    public m(final FlaxChat b) {
        this.a = m.z[2];
        this.b = b;
        this.setForeground(Color.white);
        this.setFont(flaxchat.d.b.d(m.z[3]));
        this.addMouseListener(this);
        this.addComponentListener(this);
    }
    
    public void componentResized(final ComponentEvent componentEvent) {
        this.e = null;
    }
    
    public void componentHidden(final ComponentEvent componentEvent) {
    }
    
    public void componentMoved(final ComponentEvent componentEvent) {
    }
    
    public void componentShown(final ComponentEvent componentEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        final Point point = mouseEvent.getPoint();
        if (this.a(flaxchat.d.d.f()).contains(point)) {
            this.a(m.z[0]);
            return;
        }
        if (this.a(flaxchat.d.d.g()).contains(point)) {
            this.a(m.z[1]);
            return;
        }
        if (mouseEvent.getClickCount() > 1 && h.b(mouseEvent)) {
            this.a(m.z[0]);
        }
    }
    
    private Rectangle a(final Rectangle rectangle) {
        final Dimension size = this.getSize();
        final Rectangle k = flaxchat.d.d.k();
        rectangle.y -= k.y;
        rectangle.x -= k.x;
        rectangle.x += size.width - k.width;
        rectangle.y += size.height - k.height;
        return rectangle;
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        final Point point = mouseEvent.getPoint();
        final Rectangle f = flaxchat.d.d.f();
        if (this.a(f).contains(point)) {
            this.d = f;
        }
        final Rectangle g = flaxchat.d.d.g();
        if (this.a(g).contains(point)) {
            this.d = g;
        }
        this.repaint();
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.d = null;
        this.repaint();
    }
    
    public void a(final String s) {
        if (s.equals(m.z[0])) {
            if (this.c == null) {
                this.c = new k(this.b);
            }
            this.c.setTitle(this.a);
            this.c.setIconImage(flaxchat.d.d.v());
            this.c.add(this.b.o());
            flaxchat.a.c.a(null, this.c);
            this.c.setVisible(true);
            this.setVisible(false);
            return;
        }
        if (m.z[1].endsWith(s)) {
            this.b.o().o();
        }
    }
    
    protected Image a() {
        if (this.e == null) {
            final Dimension size = this.getSize();
            this.e = this.createImage(size.width, size.height);
        }
        return this.e;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        final Graphics graphics2 = this.a().getGraphics();
        this.a(graphics2);
        graphics.drawImage(this.a(), 0, 0, this);
        graphics2.dispose();
    }
    
    public void a(final Graphics graphics) {
        final Dimension size = this.getSize();
        final Image h = flaxchat.d.d.h();
        final Image i = flaxchat.d.d.i();
        final Image j = flaxchat.d.d.j();
        graphics.drawImage(h, 0, 0, h.getWidth(this), h.getHeight(this), this);
        graphics.drawImage(i, h.getWidth(this), 0, size.width - j.getWidth(this), i.getHeight(this), this);
        graphics.drawImage(j, size.width - j.getWidth(this), 0, j.getWidth(this), j.getHeight(this), this);
        if (this.d != null) {
            graphics.setXORMode(Color.gray);
            graphics.fillRect(this.d.x, this.d.y, this.d.width, this.d.height);
            graphics.setPaintMode();
        }
        int n = 4;
        final Image v = flaxchat.d.d.v();
        if (v != null) {
            graphics.drawImage(v, n, size.height / 2 - v.getHeight(this) / 2, v.getWidth(this), v.getHeight(this), this);
            n += v.getWidth(this) + 3;
        }
        if (this.a == null) {
            return;
        }
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        final int height = fontMetrics.getHeight();
        final int charsWidth = fontMetrics.charsWidth(this.a.toCharArray(), 0, this.a.length());
        final int n2 = size.height / 2 - height / 2 + fontMetrics.getAscent();
        if (charsWidth + 15 > size.width - 80) {
            int n3 = size.width - 80;
            if (n3 < 0) {
                n3 = 0;
            }
            final Rectangle clipBounds = graphics.getClipBounds();
            graphics.setClip(15, 0, n3, size.height);
            graphics.drawString(this.a, n, n2);
            graphics.setClip(clipBounds);
            graphics.drawString(m.z[4], n3 + 1, n2);
            return;
        }
        graphics.drawString(this.a, n, n2);
    }
    
    public void b(final String s) {
        this.a = s;
        this.repaint();
        if (this.c != null) {
            this.c.setTitle(s);
        }
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(300, flaxchat.d.d.h().getHeight(this));
    }
    
    public void b() {
        this.setVisible(true);
        this.c.dispose();
        this.c = null;
    }
    
    static {
        m.z = new String[] { z(z("2;1")), z(z("<6&\u000fr")), z(z("\u00196(\u0004T7;=")), z(z("+3=\u0010r\u00195'\b")), z(z("qtgR")) };
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= '\u0017';
        }
        return charArray;
    }
    
    private static String z(final char[] array) {
        final int i = array.length;
        for (int n = 0; i > n; ++n) {
            final int n2 = n;
            final char c = array[n2];
            char c2 = '\0';
            switch (n % 5) {
                case 0: {
                    c2 = '_';
                    break;
                }
                case 1: {
                    c2 = 'Z';
                    break;
                }
                case 2: {
                    c2 = 'I';
                    break;
                }
                case 3: {
                    c2 = '|';
                    break;
                }
                default: {
                    c2 = '\u0017';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
