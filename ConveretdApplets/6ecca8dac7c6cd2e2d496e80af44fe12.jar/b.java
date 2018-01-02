import java.net.URL;
import java.awt.image.ImageObserver;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Frame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class b extends Canvas implements Runnable
{
    bd[] a;
    int b;
    Thread c;
    boolean d;
    Dimension e;
    int f;
    int g;
    Color h;
    Frame i;
    int j;
    int k;
    int[] l;
    int[] m;
    int[] n;
    int[] o;
    Image[] p;
    Graphics[] q;
    Dimension r;
    
    void a() {
        this.c.stop();
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        if (!irc.W) {
            return true;
        }
        this.i.setCursor(12);
        return true;
    }
    
    void a(final Dimension r, final int n) {
        if (this.r == null || this.r.width != r.width || this.r.height != r.height) {
            this.r = r;
            this.p[n] = this.createImage(r.width, r.height);
            if (this.p[n] != null) {
                this.q[n] = this.p[n].getGraphics();
            }
        }
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        if (!irc.W) {
            return true;
        }
        this.i.setCursor(0);
        return true;
    }
    
    b(final bd[] a, final Color h, final Frame i) {
        this.b = 0;
        this.d = true;
        this.e = new Dimension();
        this.j = 0;
        this.k = 0;
        this.l = new int[10];
        this.m = new int[10];
        this.n = new int[10];
        this.o = new int[10];
        this.p = new Image[10];
        this.q = new Graphics[10];
        this.r = null;
        int n = 0;
        int n2 = 0;
        this.a = a;
        this.i = i;
        this.h = h;
        if (h != null) {
            this.setBackground(this.h);
        }
        (this.c = new Thread(this)).start();
        Label_0204: {
            if (irc.bl != 0) {
                n2 = irc.bl;
                if (!bm.dX) {
                    break Label_0204;
                }
            }
            int n3 = 0;
            do {
                if (a[n3] != null && a[n3].c() > n2) {
                    n2 = a[n3].c();
                    n = n3;
                }
            } while (++n3 != 10);
        }
        this.setSize(a[n].d(), n2);
        try {
            Thread.sleep(100L);
        }
        catch (InterruptedException ex) {}
        if (!irc.W) {
            int n4 = 0;
            do {
                if (a[n4] != null) {
                    this.j += a[n4].b.getWidth(this);
                    ++this.k;
                }
            } while (++n4 != 9);
        }
    }
    
    public void paint(final Graphics graphics) {
        this.e = this.getPreferredSize();
        if (this.d) {
            this.d = false;
            if (this.h != null) {
                graphics.setColor(this.h);
            }
            graphics.fillRect(0, 0, this.e.width, this.e.height);
        }
        if (irc.W) {
            this.a(this.e, this.b);
            if (this.p[this.b] == null) {
                this.p[this.b] = this.createImage(this.e.width, this.e.height);
                this.q[this.b] = this.p[this.b].getGraphics();
            }
            this.f = (this.e.width - this.a[this.b].b.getWidth(this)) / 2;
            this.g = (this.e.height - this.a[this.b].b.getHeight(this)) / 2;
            if (this.f < 0) {
                this.f = 0;
            }
            if (this.g < 0) {
                this.g = 0;
            }
            this.q[this.b].drawImage(this.a[this.b].b, this.f, this.g, this);
            graphics.drawImage(this.p[this.b], 0, 0, this);
            return;
        }
        int n = 0;
        if (this.e.width > this.j) {
            n = (this.e.width - this.j) / (this.k + 1);
        }
        int n2 = n;
        int n3 = 0;
        do {
            this.l[n3] = -1;
            if (this.a[n3] != null) {
                int n4 = 0;
                final int height = this.a[n3].b.getHeight(this);
                final int width = this.a[n3].b.getWidth(this);
                if (this.e.height > height) {
                    n4 = (this.e.height - height) / 2;
                }
                graphics.drawImage(this.a[n3].b, n2, n4, this);
                this.l[n3] = n2;
                this.m[n3] = n4;
                this.n[n3] = n2 + width;
                this.o[n3] = n4 + height;
                n2 += this.a[n3].b.getWidth(this) + n;
            }
        } while (++n3 != 9);
    }
    
    int b() {
        if (this.b == 9) {
            return 0;
        }
        return this.b + 1;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    int a(final int n, final int n2) {
        int n3 = 0;
    Label_0002:
        while (this.l[n3] != -1) {
            int n4 = 0;
            while (n4 <= this.l[n3] || n >= this.n[n3] || n2 <= this.m[n3] || n2 >= this.o[n3]) {
                if (++n3 != 9) {
                    continue Label_0002;
                }
                n4 = -1;
                if (!bm.dX) {
                    return n4;
                }
            }
            return n3;
        }
        return -1;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        URL url = null;
        Label_0054: {
            if (!irc.W) {
                final int a = this.a(n, n2);
                if (a == -1) {
                    return true;
                }
                url = this.a[a].d;
                if (!bm.dX) {
                    break Label_0054;
                }
            }
            url = this.a[this.b].d;
        }
        if (url != null) {
            this.a[this.b].e().showDocument(url, a("z\u0012;\u001d2N"));
        }
        return true;
    }
    
    public void run() {
        while (true) {
            try {
                Thread.sleep(this.a[this.b].c);
            }
            catch (InterruptedException ex) {}
        Label_0020:
            while (true) {
                b b = this;
                do {
                    final bd[] a = b.a;
                    final int b2 = this.b();
                    this.b = b2;
                    if (a[b2] == null) {
                        continue Label_0020;
                    }
                    this.d = true;
                    b = this;
                } while (bm.dX);
                break;
            }
            this.repaint();
        }
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        if (irc.W) {
            return true;
        }
        if (this.a(n, n2) == -1) {
            this.i.setCursor(0);
            if (!bm.dX) {
                return true;
            }
        }
        this.i.setCursor(12);
        return true;
    }
    
    private static String a(final String s) {
        final char[] charArray = s.toCharArray();
        for (int length = charArray.length, i = 0; i < length; ++i) {
            final char[] array = charArray;
            final int n = i;
            final char c = array[n];
            char c2 = '\0';
            switch (i % 5) {
                case 0: {
                    c2 = '%';
                    break;
                }
                case 1: {
                    c2 = 'p';
                    break;
                }
                case 2: {
                    c2 = 'W';
                    break;
                }
                case 3: {
                    c2 = '|';
                    break;
                }
                default: {
                    c2 = '\\';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
