// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.d;

import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.ComponentEvent;
import flaxchat.i.b;
import flaxchat.e.r;
import java.awt.Component;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.ComponentListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Canvas;

public class d extends Canvas implements MouseListener, MouseMotionListener, Runnable, ComponentListener
{
    private final int a;
    private final Color b;
    private final Dimension c;
    private final Rectangle d;
    private final Rectangle e;
    private final Rectangle f;
    private final Rectangle g;
    private int h;
    private int i;
    private int j;
    private int k;
    private boolean l;
    private Image m;
    private Image n;
    private Image o;
    private Image p;
    private Component q;
    private r r;
    private static String z;
    
    public d(final int a) {
        this.c = new Dimension(15, 43);
        this.d = new Rectangle(0, 0, 15, 14);
        this.e = new Rectangle(0, 0, 15, 14);
        this.f = new Rectangle(0, 0, 15, 14);
        this.g = new Rectangle(0, 14, 15, 2);
        this.a = a;
        this.j = 0;
        this.h = 1;
        this.l = false;
        this.b = flaxchat.i.b.a(flaxchat.d.d.z, new Color(222, 222, 222));
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.n = flaxchat.i.d.p();
        this.o = flaxchat.i.d.q();
        this.p = flaxchat.i.d.r();
        this.addComponentListener(this);
    }
    
    public void componentResized(final ComponentEvent componentEvent) {
        this.m = null;
    }
    
    public void componentHidden(final ComponentEvent componentEvent) {
    }
    
    public void componentMoved(final ComponentEvent componentEvent) {
    }
    
    public void componentShown(final ComponentEvent componentEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (this.d.contains(mouseEvent.getX(), mouseEvent.getY())) {
            if (this.j > 0) {
                --this.j;
                this.repaint();
                this.e();
            }
        }
        else if (this.e.contains(mouseEvent.getX(), mouseEvent.getY())) {
            if (this.j < this.h - 1) {
                ++this.j;
                this.repaint();
                this.e();
            }
        }
        else if (this.g.contains(mouseEvent.getX(), mouseEvent.getY()) && this.h > 1) {
            if (mouseEvent.getY() < this.f.y) {
                this.j -= this.getSize().height / this.a;
                if (this.j < 0) {
                    this.j = 0;
                }
                this.repaint();
                this.e();
                return;
            }
            if (mouseEvent.getY() > this.f.y + 14) {
                this.j += this.getSize().height / this.a;
                if (this.j > this.h - 1) {
                    this.j = this.h - 1;
                }
                this.repaint();
                this.e();
            }
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        final Point point = mouseEvent.getPoint();
        if (this.d.contains(point)) {
            this.a();
            this.a(-1);
            return;
        }
        if (this.e.contains(point)) {
            this.a();
            this.a(1);
            return;
        }
        if (this.f.contains(point)) {
            this.l = true;
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        if (!this.l) {
            return;
        }
        final int j = (this.h - 1) * (mouseEvent.getY() - 14) / (this.getSize().height - 43);
        if (j == this.j) {
            return;
        }
        if (j < 0) {
            return;
        }
        if (j >= this.h) {
            return;
        }
        this.j = j;
        this.repaint();
        this.e();
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.l = false;
        this.b();
        if (this.q != null) {
            this.q.requestFocus();
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.a(mouseEvent);
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.a(mouseEvent);
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        this.a(mouseEvent);
    }
    
    public void a(final MouseEvent mouseEvent) {
        final Point point = mouseEvent.getPoint();
        if (this.d.contains(point)) {
            if (this.i == 0) {
                return;
            }
            this.i = 0;
            this.n = flaxchat.i.d.s();
            this.o = flaxchat.i.d.q();
            this.p = flaxchat.i.d.r();
            this.repaint();
        }
        else if (this.e.contains(point)) {
            if (this.i == 1) {
                return;
            }
            this.i = 1;
            this.n = flaxchat.i.d.p();
            this.o = flaxchat.i.d.t();
            this.p = flaxchat.i.d.r();
            this.repaint();
        }
        else if (this.f.contains(point)) {
            if (this.i == 2) {
                return;
            }
            this.i = 2;
            this.n = flaxchat.i.d.p();
            this.o = flaxchat.i.d.q();
            this.p = flaxchat.i.d.u();
            this.repaint();
        }
        else {
            if (this.i == 3) {
                return;
            }
            this.i = 3;
            this.n = flaxchat.i.d.p();
            this.o = flaxchat.i.d.q();
            this.p = flaxchat.i.d.r();
            this.repaint();
        }
    }
    
    private void a(final int n) {
        this.c(n);
        this.r.start();
    }
    
    private void a() {
        if (this.r != null) {
            this.r.a();
            this.r = null;
        }
        this.r = new r(this);
    }
    
    private void b() {
        if (this.r == null) {
            return;
        }
        this.r.a();
        this.r = null;
    }
    
    public void c() {
        if (this.j <= 0) {
            return;
        }
        --this.j;
        this.repaint();
        this.e();
    }
    
    public void d() {
        if (this.j >= this.h - 1) {
            return;
        }
        ++this.j;
        this.repaint();
        this.e();
    }
    
    public void e() {
        ((g)this.getParent()).a();
    }
    
    public void paint(final Graphics graphics) {
        final Graphics graphics2 = this.f().getGraphics();
        graphics2.setColor(this.b);
        final int height = this.getSize().height;
        graphics2.fillRect(0, 14, 15, height - 29);
        this.g.setSize(15, height - 29);
        graphics2.drawImage(this.n, 0, 0, this);
        graphics2.drawImage(this.o, 0, height - 15, this);
        this.e.setLocation(0, height - 15);
        if (this.h > 0) {
            final int n = (this.h <= 1) ? 14 : (this.j * (height - 43) / (this.h - 1) + 14);
            this.f.setLocation(0, n);
            graphics2.drawImage(this.p, 0, n, this);
        }
        graphics.drawImage(this.f(), 0, 0, this);
        graphics2.dispose();
    }
    
    protected Image f() {
        if (this.m == null) {
            final Dimension size = this.getSize();
            this.m = this.createImage(size.width, size.height);
        }
        return this.m;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void a(final int j, final int h) {
        this.j = j;
        this.h = h;
        this.repaint();
    }
    
    public void b(final int j) {
        this.j = j;
        this.repaint();
        this.e();
    }
    
    public int g() {
        return this.j;
    }
    
    public int h() {
        return this.h;
    }
    
    public void c(final int k) {
        this.k = k;
    }
    
    public void d(final int h) {
        this.h = h;
        this.repaint();
    }
    
    public Dimension getPreferredSize() {
        return this.c;
    }
    
    public Dimension getMinimumSize() {
        return this.c;
    }
    
    public void a(final Component q) {
        this.q = q;
    }
    
    public void run() {
        while (true) {
            Label_0077: {
                if (!flaxchat.d.i.g) {
                    break Label_0077;
                }
                try {
                    Thread.sleep(150L);
                }
                catch (InterruptedException ex) {
                    return;
                }
                if (this.k <= 0 || this.j >= this.h - 1) {
                    if (this.k >= 0) {
                        break Label_0077;
                    }
                    if (this.j <= 0) {
                        break Label_0077;
                    }
                }
                this.j += this.k;
                this.repaint();
                this.e();
            }
            if (!this.r.a || Thread.currentThread().isInterrupted()) {
                return;
            }
            continue;
        }
    }
    
    public void invalidate() {
        this.m = null;
        this.n = flaxchat.i.d.p();
        this.o = flaxchat.i.d.q();
        this.p = flaxchat.i.d.r();
        super.invalidate();
    }
    
    static {
        d.z = z(z("\u001fw6\u000f}\u0000v%\u0012R\u0003x+\u0012"));
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= '\u0011';
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
                    c2 = 'l';
                    break;
                }
                case 1: {
                    c2 = '\u0014';
                    break;
                }
                case 2: {
                    c2 = 'D';
                    break;
                }
                case 3: {
                    c2 = '`';
                    break;
                }
                default: {
                    c2 = '\u0011';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
