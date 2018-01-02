// 
// Decompiled by Procyon v0.5.30
// 

package JAVACharter.c;

import java.awt.Cursor;
import JAVACharter.util.ReflectionHelper;
import java.awt.LayoutManager;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.FontMetrics;
import java.util.Vector;
import JAVACharter.ChartModules.b;
import java.awt.Rectangle;
import JAVACharter.StyleManage.c;
import JAVACharter.StyleManage.a;
import JAVACharter.Charter;
import java.awt.Panel;

public class e extends Panel
{
    private Charter parent;
    private f long;
    private a new;
    private g b;
    private j try;
    private c z;
    private JAVACharter.c.a if;
    public d byte;
    public JAVACharter.a.f j;
    public int n;
    private int A;
    private int i;
    private int d;
    private int goto;
    private int int;
    private int v;
    private int l;
    private int u;
    private int m;
    private int t;
    private int null;
    private Rectangle g;
    private int c;
    private int C;
    private int f;
    private int r;
    private int p;
    private int e;
    public b o;
    private Vector h;
    private i B;
    private JAVACharter.c.c q;
    private FontMetrics k;
    private int for;
    private int s;
    private Image void;
    private Image case;
    private Image do;
    private Graphics char;
    private Graphics a;
    private Graphics w;
    private boolean else;
    
    public e(final a new1, final JAVACharter.a.f j, final int a, final JAVACharter.c.c q, final f long1, final Image case1, final Charter parent) {
        this.v = 50;
        this.l = 35;
        this.C = 0;
        this.f = 0;
        this.r = 0;
        this.p = 0;
        this.e = 0;
        this.h = new Vector();
        this.new = new1;
        this.q = q;
        this.j = j;
        this.parent = parent;
        this.addMouseListener(this.long = long1);
        this.addMouseMotionListener(long1);
        this.A = a;
        this.n = new1.f;
        this.u = new1.e;
        this.t = new1.d;
        this.i = new1.try;
        this.d = new1.m;
        this.resize(this.i, this.d);
        this.goto = new1.char;
        this.int = new1.new;
        final Font font = new Font("xFont", 0, 10);
        this.k = this.getFontMetrics(font);
        this.m = this.u + this.goto;
        this.null = this.t + this.int;
        this.g = new Rectangle(this.u, this.t, this.goto, this.int);
        final Rectangle rectangle = new Rectangle(new1.int, new1.for, this.i - this.c - this.u - 1, this.int);
        final Rectangle rectangle2 = new Rectangle(new1.if, new1.p, this.goto, this.d - this.int - this.t - 1);
        this.try = new j(this.g, rectangle);
        this.b = new g(this.g, rectangle2, font, this.k, j.case(), this);
        final Color color = new Color(102, 153, 204);
        this.setBackground(Color.white);
        this.case = case1;
        this.void = this.createImage(this.i, this.d);
        this.else = false;
        this.prepareImage(case1, null);
        this.byte = new d(this.g, this.try, this.b);
        this.setLayout(null);
    }
    
    public void a(final c z) {
        this.z = z;
    }
    
    public c a() {
        return this.z;
    }
    
    public a do() {
        return this.new;
    }
    
    public void if(final String s) {
        this.try.if(false);
        this.o = this.a(this.j, s, new Integer(0));
    }
    
    public void a(final String s, final int n) {
        this.try.if(false);
        this.if(s);
        this.j.h();
        this.new();
    }
    
    public void if(final String s, final int n) {
        this.h.addElement(this.a(this.j, s, new Integer(n)));
        this.j.h();
        this.else = false;
        this.repaint();
    }
    
    public void a(final String s) {
        for (int i = 0; i < this.h.size(); ++i) {
            try {
                if (((b)this.h.elementAt(i)).getName().compareTo(s) == 0) {
                    this.h.removeElementAt(i);
                    --i;
                }
            }
            catch (Exception ex) {
                break;
            }
        }
    }
    
    public void a(final String s, final boolean b) {
        final Graphics graphics = this.void.getGraphics();
        final Font font = graphics.getFont();
        this.k = this.getFontMetrics(font);
        if (graphics != null) {
            final int stringWidth = this.k.stringWidth(s);
            final int height = this.k.getHeight();
            if (stringWidth < this.goto) {
                final int n = this.goto / 2 - this.k.stringWidth(s) / 2;
                final int n2 = this.int / 2;
                if (b) {
                    graphics.setColor(Color.white);
                    graphics.fillRect(n - 2, n2 + 2, stringWidth + 4, height + 2);
                }
                graphics.setColor(Color.black);
                graphics.drawString(s, n, this.int / 2 + height);
            }
            else {
                graphics.setFont(new Font("smaller " + font.getName(), font.getStyle(), font.getSize() - 1));
                this.a(s, b);
            }
        }
    }
    
    public void a(final String s, final boolean b, final boolean b2) {
        Graphics graphics;
        if (b2) {
            graphics = this.void.getGraphics();
        }
        else {
            graphics = this.getGraphics();
        }
        final Font font = graphics.getFont();
        this.k = this.getFontMetrics(font);
        if (graphics != null) {
            int n = this.k.stringWidth(s);
            int n2 = this.k.getHeight();
            if (n > this.goto) {
                final Font font2 = new Font("smaller " + font.getName(), font.getStyle(), font.getSize() - 1);
                graphics.setFont(font2);
                this.k = this.getFontMetrics(font2);
                n = this.k.stringWidth(s);
                n2 = this.k.getHeight();
            }
            final int n3 = this.goto / 2 - this.k.stringWidth(s) / 2;
            final int n4 = this.int / 2;
            if (b) {
                graphics.setColor(Color.white);
                graphics.fillRect(n3 - 2, n4 + 2, n + 4, n2 + 2);
            }
            graphics.setColor(Color.black);
            graphics.drawString(s, n3, this.int / 2 + n2);
        }
    }
    
    public void a(final Vector vector) {
        this.o.a(vector);
    }
    
    public void if() {
        this.h.removeAllElements();
        this.else = false;
        this.repaint();
    }
    
    public b a(final JAVACharter.a.f f, final String s, final Integer n) {
        final b b = (b)ReflectionHelper.a("JAVACharter.ChartModules." + s, new Object[] { f, new Rectangle(this.u, this.t, this.goto, this.int), this.try, this.b, this, n }, this.parent.getClass());
        if (b == null) {
            System.out.println("Chart.java - Chart Engine Object Not Created!");
        }
        return b;
    }
    
    public void paint(final Graphics graphics) {
        if (!this.parent.innerIsError()) {
            if (!this.else) {
                this.void = this.createImage(this.i, this.d);
                (this.char = this.void.getGraphics()).drawImage(this.case, 0, 0, null);
                this.a(this.char);
                graphics.drawImage(this.void, 0, 0, this);
            }
            else {
                graphics.drawImage(this.void, 0, 0, this);
            }
        }
        else {
            graphics.drawImage(this.case, 0, 0, null);
            this.parent.innerShowLastError();
        }
    }
    
    public void a(final boolean b) {
        this.o.a(b);
    }
    
    private void a(final Graphics graphics) {
        if (this.void == null) {
            System.out.println("ChartImage Is NULL!");
        }
        if (this.n == 0) {
            this.z.if("ma");
        }
        this.else = true;
        graphics.drawImage(this.case, 0, 0, null);
        this.try.if();
        this.byte();
        this.j.a(this.j.d());
        this.b.a(this.j.j(), this.j.e(), this.j.else());
        this.j.i();
        this.try.do(false);
        this.for();
        final Color color = graphics.getColor();
        this.try.a(graphics, this.new);
        this.b.a(graphics, this.A, this.n);
        graphics.setColor(color);
        this.q.a(this.j.j(), this.j.e());
        this.if(graphics);
        this.do(graphics);
    }
    
    public void do(final Graphics graphics) {
        graphics.setColor(this.z.a("trendlines", 0));
        final Vector if1 = this.if.if(this.j.d());
        if (if1 != null) {
            this.j.a(this.j.d());
            for (int i = 0; i < if1.size(); ++i) {
                final i j = if1.elementAt(i);
                if (j.int() == this.n) {
                    final int a = ((JAVACharter.b.f)this.j.else()).a(j.if());
                    final int a2 = ((JAVACharter.b.f)this.j.else()).a(j.do());
                    graphics.setClip(this.u, this.t, this.goto, this.int);
                    graphics.drawLine(this.b.a(a), this.try.do(j.for()), this.b.a(a2), this.try.do(j.a()));
                    graphics.setClip(0, 0, this.i, this.d);
                }
            }
            this.j.i();
        }
    }
    
    public void byte() {
        this.o.if();
        for (int i = 0; i < this.h.size(); ++i) {
            ((b)this.h.elementAt(i)).if();
        }
    }
    
    public void for() {
        this.o.calc_draw();
        for (int i = 0; i < this.h.size(); ++i) {
            ((b)this.h.elementAt(i)).calc_draw();
        }
    }
    
    public void if(final Graphics graphics) {
        final JAVACharter.c.b b = new JAVACharter.c.b(this.new.long, this.new.goto, this.new.i, this.new.h, this.q.int(this.new.c), this.new.n, this.new.l);
        this.o.paintData(graphics, b);
        for (int i = 0; i < this.h.size(); ++i) {
            ((b)this.h.elementAt(i)).paintData(graphics, b);
        }
    }
    
    public void try() {
        final Graphics graphics = this.getGraphics();
        if (graphics != null) {
            graphics.drawImage(this.case, 0, 0, this);
        }
    }
    
    public g int() {
        return this.b;
    }
    
    public JAVACharter.c.c case() {
        return this.q;
    }
    
    public void a(final int n) {
        this.setCursor(new Cursor(n));
    }
    
    public void else() {
        this.j.a();
        this.j.h();
    }
    
    public void goto() {
        this.j.char();
        this.j.h();
    }
    
    public void new() {
        this.else = false;
        this.paint(this.getGraphics());
    }
    
    public void a(final JAVACharter.c.a if1) {
        this.if = if1;
    }
    
    public boolean char() {
        return this.try.try();
    }
    
    public boolean for(final int n, final int n2) {
        if (this.a == null) {
            this.a = this.getGraphics();
        }
        if (this.do == null) {
            this.do = this.createImage(this.i, this.d);
        }
        if (this.w == null) {
            this.w = this.do.getGraphics();
        }
        this.paint(this.w);
        this.o.mouseMove(this.w, n, n2, this.q, this.long.try);
        this.q.a(this.w, n, n2, this.g, this);
        this.a.drawImage(this.do, 0, 0, this);
        return true;
    }
    
    public boolean int(final int n, final int n2) {
        if (this.a == null) {
            this.a = this.getGraphics();
        }
        if (this.do == null) {
            this.do = this.createImage(this.i, this.d);
        }
        if (this.w == null) {
            this.w = this.do.getGraphics();
        }
        this.paint(this.w);
        final int a = ((JAVACharter.b.f)this.j.else()).a(this.B.if());
        final int a2 = ((JAVACharter.b.f)this.j.else()).a(((JAVACharter.b.f)this.j.else()).do(n));
        this.q.a(this.w, n, n2, this.g, this);
        this.w.setColor(this.z.a("trendlines", 0));
        this.w.setClip(this.u, this.t, this.goto, this.int);
        this.w.drawLine(this.b.a(a), this.try.do(this.B.for()), this.b.a(a2), n2);
        this.w.setClip(0, 0, this.i, this.d);
        this.a.drawImage(this.do, 0, 0, this);
        return true;
    }
    
    public boolean do(final int n, final int n2) {
        this.B = this.if.a(this.j.d(), this.n);
        this.j.a(this.j.d());
        this.B.if(((JAVACharter.b.f)this.j.else()).do(n), this.try.a(n2));
        return true;
    }
    
    public boolean if(final int n, final int n2) {
        if (this.a == null) {
            this.a = this.getGraphics();
        }
        if (this.do == null) {
            this.do = this.createImage(this.i, this.d);
        }
        if (this.w == null) {
            this.w = this.do.getGraphics();
        }
        this.paint(this.w);
        this.B.a(((JAVACharter.b.f)this.j.else()).do(n), this.try.a(n2));
        final int a = ((JAVACharter.b.f)this.j.else()).a(this.B.if());
        final int a2 = ((JAVACharter.b.f)this.j.else()).a(this.B.do());
        this.w.setColor(this.z.a("trendlines", 0));
        this.w.setClip(this.u, this.t, this.goto, this.int);
        this.w.drawLine(this.b.a(a), this.try.do(this.B.for()), this.b.a(a2), this.try.do(this.B.a()));
        this.w.setClip(0, 0, this.i, this.d);
        this.j.i();
        this.char.drawImage(this.do, 0, 0, this);
        return true;
    }
    
    public boolean a(final int r, final int p3, final int for1) {
        this.r = r;
        this.p = p3;
        this.for = for1;
        this.j.a(this.j.d());
        this.q.a(((JAVACharter.b.f)this.j.else()).do(this.for));
        this.j.i();
        return true;
    }
    
    public boolean a(final int e, final int n) {
        if (this.a == null) {
            this.a = this.getGraphics();
        }
        if (this.do == null) {
            this.do = this.createImage(this.i, this.d);
        }
        if (this.w == null) {
            this.w = this.do.getGraphics();
        }
        if (this.e != e) {
            this.e = e;
            this.paint(this.w);
            this.w.setColor(this.z.a("mouseline", 0));
            this.w.setXORMode(Color.yellow);
            if (e > this.r) {
                this.w.fillRect(this.r, this.t + 1, e - this.r, this.null - this.t - 1);
            }
            else {
                this.w.fillRect(e, this.t + 1, this.r - e, this.null - this.t - 1);
            }
            this.a.drawImage(this.do, 0, 0, this);
            this.w.setPaintMode();
            this.j.a(this.j.d());
            this.q.if(((JAVACharter.b.f)this.j.else()).do(this.b.if(e)));
            this.j.i();
        }
        return true;
    }
    
    public boolean if(final int n, final int n2, final int s) {
        if (this.r != n) {
            this.void.getGraphics();
            this.s = s;
            if (this.for == this.s) {
                this.s = this.for + 1;
            }
            if (this.for < this.s) {
                this.j.a(this.for, this.s);
            }
            else {
                this.j.a(this.s, this.for);
            }
            this.j.h();
            this.new();
        }
        return true;
    }
}
