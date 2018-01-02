// 
// Decompiled by Procyon v0.5.30
// 

package a.a;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Canvas;

public class b extends Canvas implements MouseListener, MouseMotionListener, Runnable
{
    private String if;
    private String goto;
    private static final int v = -1;
    private static final int O = 1;
    private static final int h = 2;
    private static final int else = 5;
    private static final int g = 6;
    private static final int j = 10;
    public static final int z = 0;
    public static final int try = 10;
    private static final int[] m;
    private static final int[] o;
    private static final int[] H;
    private static final int[] J;
    private static final int[] t;
    private static final int[] u;
    private static final int[] b;
    private static final int[] d;
    private static final int void = 5;
    private static final int case = 3;
    private static final int D = 4;
    private int[] long;
    private int[] int;
    private int[] G;
    private int[] s;
    private a k;
    private Thread for;
    private Dimension C;
    private Image f;
    private Graphics E;
    private Color i;
    private Color q;
    private Color L;
    private Color F;
    private Color r;
    private Color a;
    private Color n;
    private int e;
    private int A;
    private int new;
    private int c;
    private int null;
    private int byte;
    private int l;
    private int p;
    private int N;
    private int M;
    private int B;
    private int do;
    private long char;
    private long K;
    private int w;
    private int I;
    
    public b(final int n, final int n2) {
        this.if = "Scroller v1.1 - (c) exsys GbR Emden - www.exsys.net";
        this.goto = "by Raul Molino Garcia";
        this.long = new int[3];
        this.int = new int[3];
        this.G = new int[3];
        this.s = new int[3];
        this.i = new Color(11184810);
        this.q = new Color(11184810);
        this.L = new Color(11184810);
        this.F = new Color(13421772);
        this.r = new Color(0);
        this.a = new Color(16711680);
        this.n = new Color(16777215);
        this.e = 10;
        this.new = -1;
        this.c = 11;
        this.null = 11;
        this.byte = 4;
        this.l = 1;
        this.p = 1;
        this.N = -1;
        this.M = -1;
        this.B = -1;
        if (n == 10) {
            this.e = 10;
        }
        else {
            this.e = 0;
        }
        if (n2 >= 7) {
            this.c = n2;
            this.null = n2;
        }
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }
    
    public synchronized void finalize() throws Throwable {
        this.for = null;
        this.removeMouseListener(this);
        this.removeMouseMotionListener(this);
        if (this.f != null) {
            this.f.flush();
            this.E.dispose();
            this.f = null;
            this.E = null;
        }
    }
    
    public synchronized void do() {
        final Graphics graphics = this.getGraphics();
        if (graphics == null) {
            return;
        }
        this.paint(graphics);
        graphics.dispose();
    }
    
    public synchronized void a(final a k) {
        this.k = k;
    }
    
    public synchronized void int() {
        this.k = null;
    }
    
    public synchronized void a(final Color i, final Color q, final Color l, final Color f, final Color r, final Color a, final Color n) {
        if (i != null) {
            this.i = i;
        }
        if (q != null) {
            this.q = q;
        }
        if (l != null) {
            this.L = l;
        }
        if (f != null) {
            this.F = f;
        }
        if (r != null) {
            this.r = r;
        }
        if (a != null) {
            this.a = a;
        }
        if (n != null) {
            this.n = n;
        }
    }
    
    public synchronized void int(final int w) {
        this.w = w;
    }
    
    public synchronized void byte(final int w) {
        this.w = w;
    }
    
    public synchronized void a(final int i) {
        this.I = i;
    }
    
    public synchronized void new(final int i) {
        this.I = i;
    }
    
    public synchronized void a(final long k) {
        if (k > 0L) {
            this.K = k;
        }
        else {
            this.K = 0L;
        }
        this.if();
        this.do();
    }
    
    public synchronized void if(final long char1) {
        this.char = char1;
        if (this.char < 0L) {
            this.char = 0L;
        }
        else if (this.char >= this.K) {
            this.char = this.K - 1L;
        }
        this.do();
    }
    
    public synchronized long a() {
        return this.char;
    }
    
    public synchronized void do(final int l) {
        if (l > 0) {
            this.l = l;
        }
    }
    
    public synchronized void try(final int p) {
        if (p > 0) {
            this.p = p;
        }
        this.if();
    }
    
    public synchronized void for(final int a) {
        if (a >= 0) {
            this.A = a;
        }
    }
    
    public synchronized void if(final int new1) {
        final Dimension size = this.getSize();
        if (this.new != new1) {
            this.new = new1;
            if (this.e == 0) {
                this.a(a.a.b.m, a.a.b.o, this.long, this.int, 0);
                this.a(a.a.b.H, a.a.b.J, this.G, this.s, this.if(size) - this.null);
            }
            else {
                this.a(a.a.b.t, a.a.b.u, this.long, this.int, 0);
                this.a(a.a.b.b, a.a.b.d, this.G, this.s, this.if(size) - this.null);
            }
            this.do();
        }
    }
    
    public Dimension getPreferredSize() {
        if (this.e == 0) {
            return new Dimension(this.c + this.w + this.I, this.getSize().height);
        }
        return new Dimension(this.getSize().width, this.c + this.w + this.I);
    }
    
    public Dimension getMinimumSize() {
        if (this.e == 0) {
            return new Dimension(0, this.getSize().height);
        }
        return new Dimension(this.getSize().width, 0);
    }
    
    private int a(final int n, final int n2) {
        if (this.e == 0) {
            return n;
        }
        return n2;
    }
    
    private int if(final int n, final int n2) {
        if (this.e == 0) {
            return n2;
        }
        return n;
    }
    
    private int if(final Dimension dimension) {
        if (dimension == null) {
            return 0;
        }
        if (this.e == 0) {
            if (this.new > 0 && this.new + this.A < dimension.height) {
                return this.new;
            }
            return dimension.height - this.A;
        }
        else {
            if (this.new > 0 && this.new + this.A < dimension.width) {
                return this.new;
            }
            return dimension.width - this.A;
        }
    }
    
    private int a(final Dimension dimension) {
        if (dimension == null) {
            return 0;
        }
        if (this.e == 0) {
            return dimension.width;
        }
        return dimension.height;
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public void update(final Graphics graphics) {
        final Image for1 = this.for();
        if (graphics != null && for1 != null) {
            graphics.drawImage(for1, 0, 0, this);
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        final Point point = mouseEvent.getPoint();
        this.N = point.x;
        this.M = point.y;
        if (this.B == 10) {
            final int x = point.x;
            final int y = point.y;
            if (((this.e == 0) ? y : x) - this.do < this.A + this.null) {
                this.char = 0L;
            }
            else {
                final int x2 = point.x;
                final int y2 = point.y;
                if (((this.e == 0) ? y2 : x2) - this.do >= this.A + this.if(this.C) - this.null - this.byte) {
                    this.char = this.K - this.p;
                }
                else {
                    if (this.if(this.C) <= 0) {
                        return;
                    }
                    final int n = this.if(this.C) - 2 * this.null - this.byte;
                    if (n <= 0) {
                        return;
                    }
                    final int x3 = point.x;
                    final int y3 = point.y;
                    this.char = (int)Math.rint((((this.e == 0) ? y3 : x3) - this.do - this.null - this.A) * (this.K - this.p) / n);
                }
            }
            if (this.char > this.K - this.p) {
                this.char = this.K - this.p;
            }
            if (this.char < 0L) {
                this.char = 0L;
            }
            if (this.k != null) {
                this.k.a(this, this.char);
            }
            this.do();
            return;
        }
        if (this.B != -1) {
            this.do();
        }
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        final Point point = mouseEvent.getPoint();
        this.N = point.x;
        this.M = point.y;
        this.do();
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.do();
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.N = -1;
        this.M = -1;
        this.do();
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        final Point point = mouseEvent.getPoint();
        this.N = point.x;
        this.M = point.y;
        if (this.k != null) {
            this.k.if();
        }
        if (this.K > 0L) {
            if (this.do(this.N, this.M, this.C)) {
                this.B = 1;
                (this.for = new Thread(this)).start();
            }
            else if (this.a(this.N, this.M, this.C)) {
                this.B = 2;
                (this.for = new Thread(this)).start();
            }
            else if (this.if(this.N, this.M, this.C)) {
                this.B = 10;
                final int do1 = this.do(this.C);
                if (do1 >= 0) {
                    final int x = point.x;
                    final int y = point.y;
                    this.do = ((this.e == 0) ? y : x) - do1;
                }
                else {
                    this.do = 0;
                }
            }
            else if (this.for(this.N, this.M, this.C)) {
                this.B = 5;
                (this.for = new Thread(this)).start();
            }
            else if (this.int(this.N, this.M, this.C)) {
                this.B = 6;
                (this.for = new Thread(this)).start();
            }
            this.do();
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.B = -1;
        this.for = null;
        this.do();
        if (this.k != null) {
            this.k.a();
        }
    }
    
    private boolean do(final int n, final int n2, final Dimension dimension) {
        return dimension != null && ((this.e == 0) ? n : n2) >= this.w && ((this.e == 0) ? n : n2) < this.c + this.w && ((this.e == 0) ? n2 : n) >= this.A && ((this.e == 0) ? n2 : n) < this.A + this.null;
    }
    
    private boolean a(final int n, final int n2, final Dimension dimension) {
        return dimension != null && ((this.e == 0) ? n : n2) >= this.w && ((this.e == 0) ? n : n2) < this.c + this.w && ((this.e == 0) ? n2 : n) >= this.A + this.if(dimension) - this.null && ((this.e == 0) ? n2 : n) < this.A + this.if(dimension);
    }
    
    private boolean if(final int n, final int n2, final Dimension dimension) {
        final int do1 = this.do(dimension);
        return this.byte > 0 && do1 >= this.A && ((this.e == 0) ? n : n2) >= this.w && ((this.e == 0) ? n : n2) < this.c + this.w && ((this.e == 0) ? n2 : n) >= do1 && ((this.e == 0) ? n2 : n) < do1 + this.byte;
    }
    
    private boolean for(final int n, final int n2, final Dimension dimension) {
        final int do1 = this.do(dimension);
        return this.byte > 0 && do1 >= 0 && ((this.e == 0) ? n : n2) >= this.w && ((this.e == 0) ? n : n2) < this.c + this.w && ((this.e == 0) ? n2 : n) < do1 && ((this.e == 0) ? n2 : n) >= this.A + this.null;
    }
    
    private boolean int(final int n, final int n2, final Dimension dimension) {
        final int do1 = this.do(dimension);
        return this.byte > 0 && do1 >= 0 && ((this.e == 0) ? n : n2) >= this.w && ((this.e == 0) ? n : n2) < this.c + this.w && ((this.e == 0) ? n2 : n) >= do1 + this.byte && ((this.e == 0) ? n2 : n) < this.A + this.if(dimension) - this.null;
    }
    
    private void if() {
        if (this.C != null && this.K != 0L) {
            this.byte = (int)(this.p * (this.if(this.C) - 2 * this.null) / this.K);
            if (this.byte < 4) {
                this.byte = 4;
            }
            if (this.if(this.C) <= 2 * this.null + this.byte) {
                this.byte = -1;
            }
        }
        else {
            this.byte = -1;
        }
    }
    
    private int do(final Dimension dimension) {
        if (this.K <= 1L || dimension == null) {
            return -1;
        }
        final int if1 = this.if(dimension);
        int n;
        if (this.K - this.p > 0L) {
            n = (int)Math.rint(this.A + this.null + this.char * (if1 - 2 * this.null - this.byte + 1) / (this.K - this.p));
        }
        else {
            n = 0;
        }
        if (n > this.A + if1 - this.null - this.byte) {
            n = this.A + if1 - this.null - this.byte;
        }
        if (n < this.A + this.null) {
            n = this.A + this.null;
        }
        return n;
    }
    
    public void run() {
        final Thread currentThread = Thread.currentThread();
        int n = 1;
        while (this.for == currentThread && this.B != -1 && this.B != 10) {
            if (this.B == 1 && this.do(this.N, this.M, this.C)) {
                if (this.char > 0L) {
                    this.char -= this.l;
                    if (this.char < 0L) {
                        this.char = 0L;
                    }
                    if (this.k != null) {
                        this.k.a(this, this.char);
                    }
                    this.do();
                }
            }
            else if (this.B == 2 && this.a(this.N, this.M, this.C)) {
                if (this.char < this.K - this.p) {
                    this.char += this.l;
                    if (this.char > this.K - this.p) {
                        this.char = this.K - this.p;
                    }
                    if (this.char < 0L) {
                        this.char = 0L;
                    }
                    if (this.k != null) {
                        this.k.a(this, this.char);
                    }
                    this.do();
                }
            }
            else if (this.B == 5 && this.for(this.N, this.M, this.C)) {
                if (this.char > 0L) {
                    this.char -= this.p;
                    if (this.char < 0L) {
                        this.char = 0L;
                    }
                    if (this.k != null) {
                        this.k.a(this, this.char);
                    }
                    this.do();
                }
            }
            else if (this.B == 6 && this.int(this.N, this.M, this.C) && this.char < this.K - this.p) {
                this.char += this.p;
                if (this.char > this.K - this.p) {
                    this.char = this.K - this.p;
                }
                if (this.k != null) {
                    this.k.a(this, this.char);
                }
                this.do();
            }
            try {
                if (n != 0) {
                    Thread.sleep(150L);
                }
                else {
                    Thread.sleep(10L);
                }
            }
            catch (InterruptedException ex) {}
            n = 0;
        }
    }
    
    private void a(final int[] array, final int[] array2, final int[] array3, final int[] array4, final int n) {
        int n2;
        int n3;
        if (this.e == 0) {
            n2 = (this.c - 5) / 2 + this.w;
            n3 = this.A + (this.null - 3) / 2 + n;
        }
        else {
            n3 = (this.c - 5) / 2 + this.w;
            n2 = this.A + (this.null - 3) / 2 + n;
        }
        for (int i = 0; i < 3; ++i) {
            array3[i] = array[i] + n2;
        }
        for (int j = 0; j < 3; ++j) {
            array4[j] = array2[j] + n3;
        }
    }
    
    private synchronized Image for() {
        final Dimension size = this.getSize();
        final Color r = this.r;
        if (size.width > 0 && size.height > 0) {
            if (this.f == null || this.C.width != size.width || this.C.height != size.height) {
                if (this.f != null) {
                    this.f.flush();
                    this.f = null;
                }
                this.C = size;
                this.f = this.createImage(size.width, size.height);
                if (this.f == null) {
                    return null;
                }
                if (this.E != null) {
                    this.E.dispose();
                }
                this.E = this.f.getGraphics();
                this.null = this.if(size) / 2;
                if (this.null > this.c) {
                    this.null = this.c;
                }
                else if (this.null < 7) {
                    this.null = 7;
                }
                if (this.e == 0) {
                    this.a(a.a.b.m, a.a.b.o, this.long, this.int, 0);
                    this.a(a.a.b.H, a.a.b.J, this.G, this.s, this.if(size) - this.null);
                }
                else {
                    this.a(a.a.b.t, a.a.b.u, this.long, this.int, 0);
                    this.a(a.a.b.b, a.a.b.d, this.G, this.s, this.if(size) - this.null);
                }
                this.if();
            }
            this.E.setClip(0, 0, size.width, size.height);
            this.E.setColor(this.n);
            this.E.fillRect(0, 0, size.width, size.height);
            final int n = (size != null) ? ((this.e == 0) ? size.width : size.height) : 0;
            final int if1 = this.if(size);
            this.E.setColor(this.F);
            if (this.e == 0) {
                this.E.fillRect(this.w, this.A, n - this.w - this.I, if1);
            }
            else {
                this.E.fillRect(this.A, this.w, if1, n - this.w - this.I);
            }
            this.E.setColor(this.L);
            if (this.e == 0) {
                this.E.fill3DRect((this.c - 3) / 2 + this.w, this.A, 3, if1, true);
            }
            else {
                this.E.fill3DRect(this.A, (this.c - 3) / 2 + this.w, if1, 3, true);
            }
            boolean b = true;
            Color color = this.r;
            if (this.K > 0L && this.do(this.N, this.M, this.C)) {
                if (this.B == 1) {
                    b = false;
                }
                color = this.a;
            }
            this.E.setColor(this.i);
            if (this.e == 0) {
                this.E.fill3DRect(this.w, this.A, this.c, this.null, b);
            }
            else {
                this.E.fill3DRect(this.A, this.w, this.null, this.c, b);
            }
            this.E.setColor(color);
            this.E.fillPolygon(this.long, this.int, 3);
            this.E.drawPolygon(this.long, this.int, 3);
            boolean b2 = true;
            Color color2 = this.r;
            if (this.K > 0L && this.a(this.N, this.M, this.C)) {
                if (this.B == 2) {
                    b2 = false;
                }
                color2 = this.a;
            }
            this.E.setColor(this.i);
            if (this.e == 0) {
                this.E.fill3DRect(this.w, this.A + if1 - this.null, this.c, this.null, b2);
            }
            else {
                this.E.fill3DRect(this.A + if1 - this.null, this.w, this.null, this.c, b2);
            }
            this.E.setColor(color2);
            this.E.fillPolygon(this.G, this.s, 3);
            this.E.drawPolygon(this.G, this.s, 3);
            if (this.K > 0L && this.byte > 0) {
                final int do1 = this.do(this.C);
                if (do1 >= this.A) {
                    this.E.setColor(this.q);
                    if (this.e == 0) {
                        this.E.fill3DRect(this.w, do1, this.c, this.byte, true);
                    }
                    else {
                        this.E.fill3DRect(do1, this.w, this.byte, this.c, true);
                    }
                }
            }
            return this.f;
        }
        this.f = null;
        this.C = null;
        this.E = null;
        return null;
    }
    
    static {
        m = new int[] { 0, 2, 4 };
        o = new int[] { 2, 0, 2 };
        H = new int[] { 0, 4, 2 };
        J = new int[] { 0, 0, 2 };
        t = new int[] { 0, 2, 2 };
        u = new int[] { 2, 0, 4 };
        b = new int[] { 0, 2, 0 };
        d = new int[] { 0, 2, 4 };
    }
}
