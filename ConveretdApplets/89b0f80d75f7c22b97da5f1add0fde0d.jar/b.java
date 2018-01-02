import java.awt.image.ImageObserver;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class b extends Canvas implements MouseListener, MouseMotionListener
{
    private final esChat a;
    Image b;
    Graphics c;
    final Dimension d;
    eb e;
    int f;
    final Rectangle g;
    Rectangle h;
    Rectangle i;
    Rectangle j;
    boolean k;
    boolean l;
    boolean m;
    boolean n;
    boolean o;
    int p;
    int q;
    nb r;
    
    b(final esChat a, final int f) {
        this.a = a;
        this.d = new Dimension(15, 43);
        this.g = new Rectangle(0, 0, 15, 14);
        this.setBackground(a.f);
        this.f = f;
        this.p = 0;
        this.q = 1;
        this.l = false;
        this.k = false;
        this.h = new Rectangle(0, 0, 15, 15);
        this.i = new Rectangle(0, 0, 15, 15);
        this.j = new Rectangle(0, 15, 15, 15);
        (this.r = new nb(this)).start();
        Thread.interrupted();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }
    
    public int a() {
        return this.q;
    }
    
    public Dimension getMinimumSize() {
        return this.d;
    }
    
    public Dimension getPreferredSize() {
        return this.d;
    }
    
    public int b() {
        return this.p;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        final int m = fb.m;
        int k;
        final int n = k = (this.k ? 1 : 0);
        if (m == 0) {
            if (n == 0) {
                return;
            }
            k = (this.q - 1) * (mouseEvent.getY() - 14) / (this.getSize().height - 43);
        }
        final int p = k;
        int n3;
        final int n2 = n3 = p;
        int n5;
        final int n4 = n5 = this.p;
        if (m == 0) {
            if (n2 == n4) {
                return;
            }
            final int n6;
            n3 = (n6 = p);
            final int n7;
            n5 = (n7 = -1);
        }
        if (m == 0) {
            if (n2 <= n4) {
                return;
            }
            n3 = p;
            n5 = this.q;
        }
        if (n3 < n5) {
            this.p = p;
            this.repaint();
            this.c();
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        final int m = fb.m;
        final Point point = mouseEvent.getPoint();
        boolean b4;
        boolean contains;
        boolean b3;
        boolean b2;
        final boolean b = b2 = (b3 = (contains = (b4 = this.g.contains(point))));
        Label_0147: {
            Label_0132: {
                if (m == 0) {
                    if (b) {
                        this.l = true;
                        this.m = true;
                        this.r.a(-1);
                        Thread.interrupted();
                        this.repaint();
                        if (m == 0) {
                            break Label_0132;
                        }
                    }
                    final boolean b5;
                    b2 = (b5 = (b3 = (contains = (b4 = this.h.contains(point)))));
                }
                if (m == 0) {
                    if (b) {
                        this.l = true;
                        this.n = true;
                        this.r.a(1);
                        Thread.interrupted();
                        this.repaint();
                        if (m == 0) {
                            break Label_0132;
                        }
                    }
                    b3 = (b2 = (contains = (b4 = this.i.contains(point))));
                }
                if (m != 0) {
                    break Label_0147;
                }
                if (b2) {
                    this.k = true;
                    this.o = true;
                    Thread.interrupted();
                    this.repaint();
                }
            }
            contains = (b3 = (b4 = this.g.contains(mouseEvent.getX(), mouseEvent.getY())));
        }
        b b6 = null;
        Label_0422: {
            Label_0417: {
                if (m == 0) {
                    if (b3) {
                        b6 = this;
                        if (m != 0) {
                            break Label_0422;
                        }
                        if (this.p > 0) {
                            --this.p;
                            this.repaint();
                            this.c();
                            return;
                        }
                        break Label_0417;
                    }
                    else {
                        b4 = (contains = this.h.contains(mouseEvent.getX(), mouseEvent.getY()));
                    }
                }
                if (m == 0) {
                    if (contains) {
                        b6 = this;
                        if (m != 0) {
                            break Label_0422;
                        }
                        if (this.p < this.q - 1) {
                            ++this.p;
                            this.repaint();
                            this.c();
                            return;
                        }
                        break Label_0417;
                    }
                    else {
                        b6 = this;
                        if (m != 0) {
                            break Label_0422;
                        }
                        b4 = this.j.contains(mouseEvent.getX(), mouseEvent.getY());
                    }
                }
                if (b4) {
                    b6 = this;
                    if (m != 0) {
                        break Label_0422;
                    }
                    if (this.q > 1) {
                        int n2;
                        final int n = n2 = mouseEvent.getY();
                        int y;
                        final int n3 = y = this.i.y;
                        if (m == 0) {
                            if (n < n3) {
                                this.p -= this.getSize().height / this.f;
                                b b7 = this;
                                if (m == 0) {
                                    if (this.p < 0) {
                                        this.p = 0;
                                    }
                                    this.repaint();
                                    b7 = this;
                                }
                                b7.c();
                                return;
                            }
                            final int y2;
                            n2 = (y2 = mouseEvent.getY());
                            final int n4;
                            y = (n4 = this.i.y + 14);
                        }
                        b b8 = null;
                        Label_0414: {
                            if (m == 0) {
                                if (n <= n3) {
                                    break Label_0417;
                                }
                                this.p += this.getSize().height / this.f;
                                b8 = this;
                                if (m != 0) {
                                    break Label_0414;
                                }
                                n2 = this.p;
                                y = this.q - 1;
                            }
                            if (n2 > y) {
                                this.p = this.q - 1;
                            }
                            this.repaint();
                            b8 = this;
                        }
                        b8.c();
                    }
                }
            }
            this.repaint();
            b6 = this;
        }
        b6.c();
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.l = false;
        this.m = false;
        this.n = false;
        this.o = false;
        this.k = false;
        Thread.interrupted();
        b b = this;
        if (fb.m == 0) {
            if (this.e != null) {
                this.e.requestFocus();
            }
            this.repaint();
            b = this;
        }
        b.c();
    }
    
    public void paint(final Graphics graphics) {
        final int m = fb.m;
        b b = this;
        int n2 = 0;
        Label_0093: {
            Label_0086: {
                Label_0079: {
                    if (m == 0) {
                        if (this.b != null) {
                            b = this;
                            if (m != 0) {
                                break Label_0079;
                            }
                            if (this.c != null) {
                                final int n = n2 = (this.a.k ? 1 : 0);
                                if (m != 0) {
                                    break Label_0093;
                                }
                                if (n == 0) {
                                    break Label_0086;
                                }
                            }
                        }
                        this.b = this.a.Db.createImage(16, this.getSize().height);
                        this.c = this.b.getGraphics();
                        b = this;
                    }
                }
                b.a.k = false;
            }
            n2 = this.getSize().height;
        }
        final int n3 = n2;
        this.c.drawImage(this.a.x, 0, 0, 16, n3, 75, 57, 92, 64, null);
        this.j.setSize(16, n3 - 32);
        final boolean i = this.m;
        Label_0213: {
            if (m == 0) {
                if (i) {
                    this.c.drawImage(this.a.x, 0, 0, 16, 16, 109, 39, 125, 55, null);
                    if (m == 0) {
                        break Label_0213;
                    }
                }
                this.c.drawImage(this.a.x, 0, 0, 16, 16, 93, 39, 109, 55, null);
            }
        }
        final int q = this.q;
        if (m == 0) {
            Label_0356: {
                if (q > 0) {
                    final int q2 = this.q;
                    int n4;
                    if (m == 0 && q2 > 1) {
                        n4 = this.p * (n3 - 46) / (this.q - 1) + 16;
                        if (m != 0) {
                            goto Label_0263;
                        }
                    }
                    else {
                        n4 = q2;
                    }
                    this.i.setLocation(0, n4);
                    final boolean o = this.o;
                    if (m == 0) {
                        if (o) {
                            this.c.drawImage(this.a.x, 0, n4, 16, n4 + 16, 109, 55, 125, 71, this);
                            if (m == 0) {
                                break Label_0356;
                            }
                        }
                        this.c.drawImage(this.a.x, 0, n4, 16, n4 + 16, 93, 55, 109, 71, this);
                    }
                }
            }
            final boolean n5 = this.n;
        }
        Label_0437: {
            if (m == 0) {
                if (q != 0) {
                    this.c.drawImage(this.a.x, 0, n3 - 16, 16, n3, 109, 71, 125, 87, this);
                    if (m == 0) {
                        break Label_0437;
                    }
                }
                this.c.drawImage(this.a.x, 0, n3 - 16, 16, n3, 93, 71, 109, 87, this);
            }
        }
        this.h.setLocation(0, n3 - 16);
        graphics.drawImage(this.b, 0, 0, this);
    }
    
    public void c() {
        ((y)this.getParent()).a();
    }
    
    public void a(final int q) {
        this.q = q;
        this.repaint();
    }
    
    public void a(final int p4, final int n, final int n2, final int q) {
        final int m = fb.m;
        this.p = p4;
        this.q = q;
        this.repaint();
        final int d = ((y)this.getParent()).b.d ? 1 : 0;
        b b = null;
        Label_0060: {
            if (m == 0) {
                if (d == 0) {
                    return;
                }
                b = this;
                if (m != 0) {
                    break Label_0060;
                }
                final int p5 = this.p;
            }
            if (d >= this.q - 1) {
                return;
            }
            b = this;
        }
        b.c();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
