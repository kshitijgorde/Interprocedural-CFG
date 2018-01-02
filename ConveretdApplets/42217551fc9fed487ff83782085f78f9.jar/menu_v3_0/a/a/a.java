// 
// Decompiled by Procyon v0.5.30
// 

package menu_v3_0.a.a;

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

public class a extends Canvas implements MouseListener, MouseMotionListener, Runnable
{
    private String a;
    private String for;
    private static final int else = -1;
    private static final int int = 1;
    private static final int h = 2;
    private static final int do = 5;
    private static final int i = 6;
    private static final int B = 10;
    public static final int c = 0;
    public static final int null = 10;
    private static final int[] try;
    private static final int[] O;
    private static final int[] L;
    private static final int[] k;
    private static final int[] G;
    private static final int[] g;
    private static final int[] u;
    private static final int[] m;
    private static final int D = 5;
    private static final int o = 3;
    private static final int t = 4;
    private int[] new;
    private int[] H;
    private int[] long;
    private int[] byte;
    private b char;
    private Thread l;
    private Dimension M;
    private Image d;
    private Graphics b;
    private Color N;
    private Color n;
    private Color I;
    private Color w;
    private Color r;
    private Color q;
    private Color K;
    private int s;
    private int goto;
    private int j;
    private int A;
    private int E;
    private int C;
    private int J;
    private int if;
    private int z;
    private int v;
    private int void;
    private int f;
    private long case;
    private long F;
    private int e;
    private int p;
    
    public a(final int n, final int n2) {
        this.a = "Scroller v1.1 - (c) exsys GbR Emden - www.exsys.net";
        this.for = "by Raul Molino Garcia";
        this.new = new int[3];
        this.H = new int[3];
        this.long = new int[3];
        this.byte = new int[3];
        this.N = new Color(11184810);
        this.n = new Color(11184810);
        this.I = new Color(11184810);
        this.w = new Color(13421772);
        this.r = new Color(0);
        this.q = new Color(16711680);
        this.K = new Color(16777215);
        this.s = 10;
        this.j = -1;
        this.A = 11;
        this.E = 11;
        this.C = 4;
        this.J = 1;
        this.if = 1;
        this.z = -1;
        this.v = -1;
        this.void = -1;
        if (n == 10) {
            this.s = 10;
        }
        else {
            this.s = 0;
        }
        if (n2 >= 7) {
            this.A = n2;
            this.E = n2;
        }
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }
    
    public synchronized void finalize() throws Throwable {
        this.l = null;
        this.removeMouseListener(this);
        this.removeMouseMotionListener(this);
        if (this.d != null) {
            this.d.flush();
            this.d = null;
        }
    }
    
    public synchronized void int() {
        this.paint(this.getGraphics());
    }
    
    public synchronized void a(final b char1) {
        this.char = char1;
    }
    
    public synchronized void for() {
        this.char = null;
    }
    
    public synchronized void a(final Color n, final Color n2, final Color i, final Color w, final Color r, final Color q, final Color k) {
        if (n != null) {
            this.N = n;
        }
        if (n2 != null) {
            this.n = n2;
        }
        if (i != null) {
            this.I = i;
        }
        if (w != null) {
            this.w = w;
        }
        if (r != null) {
            this.r = r;
        }
        if (q != null) {
            this.q = q;
        }
        if (k != null) {
            this.K = k;
        }
    }
    
    public synchronized void do(final int e) {
        this.e = e;
    }
    
    public synchronized void if(final int e) {
        this.e = e;
    }
    
    public synchronized void try(final int p) {
        this.p = p;
    }
    
    public synchronized void int(final int p) {
        this.p = p;
    }
    
    public synchronized void a(final long f) {
        if (f > 0L) {
            this.F = f;
        }
        else {
            this.F = 0L;
        }
        this.if();
        this.paint(this.getGraphics());
    }
    
    public synchronized void if(final long case1) {
        this.case = case1;
        if (this.case < 0L) {
            this.case = 0L;
        }
        else if (this.case >= this.F) {
            this.case = this.F - 1L;
        }
        this.paint(this.getGraphics());
    }
    
    public synchronized long a() {
        return this.case;
    }
    
    public synchronized void byte(final int j) {
        if (j > 0) {
            this.J = j;
        }
    }
    
    public synchronized void for(final int if1) {
        if (if1 > 0) {
            this.if = if1;
        }
        this.if();
    }
    
    public synchronized void new(final int goto1) {
        if (goto1 >= 0) {
            this.goto = goto1;
        }
    }
    
    public synchronized void a(final int j) {
        final Dimension size = this.getSize();
        if (this.j != j) {
            this.j = j;
            if (this.s == 0) {
                this.a(menu_v3_0.a.a.a.try, menu_v3_0.a.a.a.O, this.new, this.H, 0);
                this.a(menu_v3_0.a.a.a.L, menu_v3_0.a.a.a.k, this.long, this.byte, this.do(size) - this.E);
            }
            else {
                this.a(menu_v3_0.a.a.a.G, menu_v3_0.a.a.a.g, this.new, this.H, 0);
                this.a(menu_v3_0.a.a.a.u, menu_v3_0.a.a.a.m, this.long, this.byte, this.do(size) - this.E);
            }
            this.paint(this.getGraphics());
        }
    }
    
    public Dimension getPreferredSize() {
        if (this.s == 0) {
            return new Dimension(this.A + this.e + this.p, this.getSize().height);
        }
        return new Dimension(this.getSize().width, this.A + this.e + this.p);
    }
    
    public Dimension getMinimumSize() {
        if (this.s == 0) {
            return new Dimension(0, this.getSize().height);
        }
        return new Dimension(this.getSize().width, 0);
    }
    
    private int a(final int n, final int n2) {
        if (this.s == 0) {
            return n;
        }
        return n2;
    }
    
    private int if(final int n, final int n2) {
        if (this.s == 0) {
            return n2;
        }
        return n;
    }
    
    private int do(final Dimension dimension) {
        if (dimension == null) {
            return 0;
        }
        if (this.s == 0) {
            if (this.j > 0 && this.j + this.goto < dimension.height) {
                return this.j;
            }
            return dimension.height - this.goto;
        }
        else {
            if (this.j > 0 && this.j + this.goto < dimension.width) {
                return this.j;
            }
            return dimension.width - this.goto;
        }
    }
    
    private int a(final Dimension dimension) {
        if (dimension == null) {
            return 0;
        }
        if (this.s == 0) {
            return dimension.width;
        }
        return dimension.height;
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public void update(final Graphics graphics) {
        final Image do1 = this.do();
        if (graphics != null && do1 != null) {
            graphics.drawImage(do1, 0, 0, this);
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        final Point point = mouseEvent.getPoint();
        this.z = point.x;
        this.v = point.y;
        if (this.void == 10) {
            final int x = point.x;
            final int y = point.y;
            if (((this.s == 0) ? y : x) - this.f < this.goto + this.E) {
                this.case = 0L;
            }
            else {
                final int x2 = point.x;
                final int y2 = point.y;
                if (((this.s == 0) ? y2 : x2) - this.f >= this.goto + this.do(this.M) - this.E - this.C) {
                    this.case = this.F - this.if;
                }
                else {
                    if (this.do(this.M) <= 0) {
                        return;
                    }
                    final int n = this.do(this.M) - 2 * this.E - this.C;
                    if (n <= 0) {
                        return;
                    }
                    final int x3 = point.x;
                    final int y3 = point.y;
                    this.case = (int)Math.rint((((this.s == 0) ? y3 : x3) - this.f - this.E - this.goto) * (this.F - this.if) / n);
                }
            }
            if (this.case > this.F - this.if) {
                this.case = this.F - this.if;
            }
            if (this.case < 0L) {
                this.case = 0L;
            }
            if (this.char != null) {
                this.char.a(this, this.case);
            }
            this.paint(this.getGraphics());
            return;
        }
        if (this.void != -1) {
            this.paint(this.getGraphics());
        }
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        final Point point = mouseEvent.getPoint();
        this.z = point.x;
        this.v = point.y;
        this.paint(this.getGraphics());
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.paint(this.getGraphics());
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.z = -1;
        this.v = -1;
        this.paint(this.getGraphics());
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        final Point point = mouseEvent.getPoint();
        this.z = point.x;
        this.v = point.y;
        if (this.char != null) {
            this.char.a();
        }
        if (this.F > 0L) {
            if (this.for(this.z, this.v, this.M)) {
                this.void = 1;
                (this.l = new Thread(this)).start();
            }
            else if (this.int(this.z, this.v, this.M)) {
                this.void = 2;
                (this.l = new Thread(this)).start();
            }
            else if (this.do(this.z, this.v, this.M)) {
                this.void = 10;
                final int if1 = this.if(this.M);
                if (if1 >= 0) {
                    final int x = point.x;
                    final int y = point.y;
                    this.f = ((this.s == 0) ? y : x) - if1;
                }
                else {
                    this.f = 0;
                }
            }
            else if (this.if(this.z, this.v, this.M)) {
                this.void = 5;
                (this.l = new Thread(this)).start();
            }
            else if (this.a(this.z, this.v, this.M)) {
                this.void = 6;
                (this.l = new Thread(this)).start();
            }
            this.paint(this.getGraphics());
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.void = -1;
        this.l = null;
        this.paint(this.getGraphics());
        if (this.char != null) {
            this.char.if();
        }
    }
    
    private boolean for(final int n, final int n2, final Dimension dimension) {
        return dimension != null && ((this.s == 0) ? n : n2) >= this.e && ((this.s == 0) ? n : n2) < this.A + this.e && ((this.s == 0) ? n2 : n) >= this.goto && ((this.s == 0) ? n2 : n) < this.goto + this.E;
    }
    
    private boolean int(final int n, final int n2, final Dimension dimension) {
        return dimension != null && ((this.s == 0) ? n : n2) >= this.e && ((this.s == 0) ? n : n2) < this.A + this.e && ((this.s == 0) ? n2 : n) >= this.goto + this.do(dimension) - this.E && ((this.s == 0) ? n2 : n) < this.goto + this.do(dimension);
    }
    
    private boolean do(final int n, final int n2, final Dimension dimension) {
        final int if1 = this.if(dimension);
        return this.C > 0 && if1 >= this.goto && ((this.s == 0) ? n : n2) >= this.e && ((this.s == 0) ? n : n2) < this.A + this.e && ((this.s == 0) ? n2 : n) >= if1 && ((this.s == 0) ? n2 : n) < if1 + this.C;
    }
    
    private boolean if(final int n, final int n2, final Dimension dimension) {
        final int if1 = this.if(dimension);
        return this.C > 0 && if1 >= 0 && ((this.s == 0) ? n : n2) >= this.e && ((this.s == 0) ? n : n2) < this.A + this.e && ((this.s == 0) ? n2 : n) < if1 && ((this.s == 0) ? n2 : n) >= this.goto + this.E;
    }
    
    private boolean a(final int n, final int n2, final Dimension dimension) {
        final int if1 = this.if(dimension);
        return this.C > 0 && if1 >= 0 && ((this.s == 0) ? n : n2) >= this.e && ((this.s == 0) ? n : n2) < this.A + this.e && ((this.s == 0) ? n2 : n) >= if1 + this.C && ((this.s == 0) ? n2 : n) < this.goto + this.do(dimension) - this.E;
    }
    
    private void if() {
        if (this.M != null && this.F != 0L) {
            this.C = (int)(this.if * (this.do(this.M) - 2 * this.E) / this.F);
            if (this.C < 4) {
                this.C = 4;
            }
            if (this.do(this.M) <= 2 * this.E + this.C) {
                this.C = -1;
            }
        }
        else {
            this.C = -1;
        }
    }
    
    private int if(final Dimension dimension) {
        if (this.F <= 1L || dimension == null) {
            return -1;
        }
        final int do1 = this.do(dimension);
        int n;
        if (this.F - this.if > 0L) {
            n = (int)Math.rint(this.goto + this.E + this.case * (do1 - 2 * this.E - this.C + 1) / (this.F - this.if));
        }
        else {
            n = 0;
        }
        if (n > this.goto + do1 - this.E - this.C) {
            n = this.goto + do1 - this.E - this.C;
        }
        if (n < this.goto + this.E) {
            n = this.goto + this.E;
        }
        return n;
    }
    
    public void run() {
        final Thread currentThread = Thread.currentThread();
        int n = 1;
        while (this.l == currentThread && this.void != -1 && this.void != 10) {
            if (this.void == 1 && this.for(this.z, this.v, this.M)) {
                if (this.case > 0L) {
                    this.case -= this.J;
                    if (this.case < 0L) {
                        this.case = 0L;
                    }
                    if (this.char != null) {
                        this.char.a(this, this.case);
                    }
                    this.paint(this.getGraphics());
                }
            }
            else if (this.void == 2 && this.int(this.z, this.v, this.M)) {
                if (this.case < this.F - this.if) {
                    this.case += this.J;
                    if (this.case > this.F - this.if) {
                        this.case = this.F - this.if;
                    }
                    if (this.case < 0L) {
                        this.case = 0L;
                    }
                    if (this.char != null) {
                        this.char.a(this, this.case);
                    }
                    this.paint(this.getGraphics());
                }
            }
            else if (this.void == 5 && this.if(this.z, this.v, this.M)) {
                if (this.case > 0L) {
                    this.case -= this.if;
                    if (this.case < 0L) {
                        this.case = 0L;
                    }
                    if (this.char != null) {
                        this.char.a(this, this.case);
                    }
                    this.paint(this.getGraphics());
                }
            }
            else if (this.void == 6 && this.a(this.z, this.v, this.M) && this.case < this.F - this.if) {
                this.case += this.if;
                if (this.case > this.F - this.if) {
                    this.case = this.F - this.if;
                }
                if (this.char != null) {
                    this.char.a(this, this.case);
                }
                this.paint(this.getGraphics());
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
        if (this.s == 0) {
            n2 = (this.A - 5) / 2 + this.e;
            n3 = this.goto + (this.E - 3) / 2 + n;
        }
        else {
            n3 = (this.A - 5) / 2 + this.e;
            n2 = this.goto + (this.E - 3) / 2 + n;
        }
        for (int i = 0; i < 3; ++i) {
            array3[i] = array[i] + n2;
        }
        for (int j = 0; j < 3; ++j) {
            array4[j] = array2[j] + n3;
        }
    }
    
    private synchronized Image do() {
        final Dimension size = this.getSize();
        final Color r = this.r;
        if (size.width > 0 && size.height > 0) {
            if (this.d == null || this.M.width != size.width || this.M.height != size.height) {
                if (this.d != null) {
                    this.d.flush();
                    this.d = null;
                }
                this.M = size;
                this.d = this.createImage(size.width, size.height);
                this.b = this.d.getGraphics();
                this.E = this.do(size) / 2;
                if (this.E > this.A) {
                    this.E = this.A;
                }
                else if (this.E < 7) {
                    this.E = 7;
                }
                if (this.s == 0) {
                    this.a(menu_v3_0.a.a.a.try, menu_v3_0.a.a.a.O, this.new, this.H, 0);
                    this.a(menu_v3_0.a.a.a.L, menu_v3_0.a.a.a.k, this.long, this.byte, this.do(size) - this.E);
                }
                else {
                    this.a(menu_v3_0.a.a.a.G, menu_v3_0.a.a.a.g, this.new, this.H, 0);
                    this.a(menu_v3_0.a.a.a.u, menu_v3_0.a.a.a.m, this.long, this.byte, this.do(size) - this.E);
                }
                this.if();
            }
            this.b.setClip(0, 0, size.width, size.height);
            this.b.setColor(this.K);
            this.b.fillRect(0, 0, size.width, size.height);
            final int n = (size != null) ? ((this.s == 0) ? size.width : size.height) : 0;
            final int do1 = this.do(size);
            this.b.setColor(this.w);
            if (this.s == 0) {
                this.b.fillRect(this.e, this.goto, n - this.e - this.p, do1);
            }
            else {
                this.b.fillRect(this.goto, this.e, do1, n - this.e - this.p);
            }
            this.b.setColor(this.I);
            if (this.s == 0) {
                this.b.fill3DRect((this.A - 3) / 2 + this.e, this.goto, 3, do1, true);
            }
            else {
                this.b.fill3DRect(this.goto, (this.A - 3) / 2 + this.e, do1, 3, true);
            }
            boolean b = true;
            Color color = this.r;
            if (this.F > 0L && this.for(this.z, this.v, this.M)) {
                if (this.void == 1) {
                    b = false;
                }
                color = this.q;
            }
            this.b.setColor(this.N);
            if (this.s == 0) {
                this.b.fill3DRect(this.e, this.goto, this.A, this.E, b);
            }
            else {
                this.b.fill3DRect(this.goto, this.e, this.E, this.A, b);
            }
            this.b.setColor(color);
            this.b.fillPolygon(this.new, this.H, 3);
            this.b.drawPolygon(this.new, this.H, 3);
            boolean b2 = true;
            Color color2 = this.r;
            if (this.F > 0L && this.int(this.z, this.v, this.M)) {
                if (this.void == 2) {
                    b2 = false;
                }
                color2 = this.q;
            }
            this.b.setColor(this.N);
            if (this.s == 0) {
                this.b.fill3DRect(this.e, this.goto + do1 - this.E, this.A, this.E, b2);
            }
            else {
                this.b.fill3DRect(this.goto + do1 - this.E, this.e, this.E, this.A, b2);
            }
            this.b.setColor(color2);
            this.b.fillPolygon(this.long, this.byte, 3);
            this.b.drawPolygon(this.long, this.byte, 3);
            if (this.F > 0L && this.C > 0) {
                final int if1 = this.if(this.M);
                if (if1 >= this.goto) {
                    this.b.setColor(this.n);
                    if (this.s == 0) {
                        this.b.fill3DRect(this.e, if1, this.A, this.C, true);
                    }
                    else {
                        this.b.fill3DRect(if1, this.e, this.C, this.A, true);
                    }
                }
            }
            return this.d;
        }
        this.d = null;
        this.M = null;
        this.b = null;
        return null;
    }
    
    static {
        try = new int[] { 0, 2, 4 };
        O = new int[] { 2, 0, 2 };
        L = new int[] { 0, 4, 2 };
        k = new int[] { 0, 0, 2 };
        G = new int[] { 0, 2, 2 };
        g = new int[] { 2, 0, 4 };
        u = new int[] { 0, 2, 0 };
        m = new int[] { 0, 2, 4 };
    }
}
