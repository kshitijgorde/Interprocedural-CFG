import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.MouseAdapter;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.image.ImageObserver;
import java.awt.image.PixelGrabber;
import java.awt.image.ColorModel;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.KeyListener;
import java.awt.Color;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class a extends Applet implements Runnable
{
    Thread e;
    Image c;
    Image goto;
    Image void;
    Graphics byte;
    MediaTracker for;
    boolean b;
    boolean d;
    int null;
    int new;
    h do;
    c char;
    int long;
    int if;
    boolean int;
    int else;
    int case;
    boolean try;
    d a;
    
    public a() {
        this.b = true;
        this.d = false;
        this.null = 0;
        this.new = 0;
        this.int = false;
        this.else = 0;
        this.case = 0;
        this.try = false;
    }
    
    public void init() {
        this.long = this.size().width;
        this.if = this.size().height;
        this.a = new d();
        this.c = this.createImage(this.long, this.if);
        this.byte = this.c.getGraphics();
        this.do = new h();
        this.char = new c(43, 83);
        this.for = new MediaTracker(this);
        this.goto = this.getImage(this.getDocumentBase(), this.getParameter("Bild"));
        this.for.addImage(this.goto, 0);
        this.for.checkID(0, true);
        this.void = this.getImage(this.getDocumentBase(), this.getParameter("Bild_c"));
        this.for.addImage(this.goto, 1);
        this.for.checkID(1, true);
        this.a();
        final double n = 0.0;
        final double n2 = 0.0;
        final int n3 = 40;
        for (int i = 0; i < 2; ++i) {
            final double n4 = n + i * 80;
            for (int j = 0; j < 10; ++j) {
                final double n5 = 3.141592653589793 * (j / 5.0);
                this.char.null[this.char.for] = new g(n4 + n3 * Math.cos(n5), n2 + n3 * Math.sin(n5), this.char.byte * this.do.for, new Color(0, 150, 0));
                final c char1 = this.char;
                ++char1.for;
            }
            for (int k = 0; k < 10; ++k) {
                this.char.case[this.char.if] = new f(k + 11 * i, (k + 1) % 10 + 11 * i, this.do.char * this.char.k, new Color(255, 200, 50));
                final c char2 = this.char;
                ++char2.if;
                this.char.case[this.char.if] = new f(k + 11 * i, (k + 3) % 10 + 11 * i, this.do.char * this.char.k, new Color(255, 200, 50));
                final c char3 = this.char;
                ++char3.if;
            }
            this.char.null[this.char.for] = new g(n4, n2, this.do.for * this.char.d, new Color(0, 150, 0));
            final c char4 = this.char;
            ++char4.for;
            for (int l = 0; l < 10; ++l) {
                this.char.case[this.char.if] = new f(l + 11 * i, this.char.for - 1, this.do.char * this.char.k, new Color(255, 200, 50));
                final c char5 = this.char;
                ++char5.if;
            }
        }
        this.char.null[this.char.for] = new g(n + 40.0, n2, this.do.for * this.char.f, new Color(0, 150, 0));
        final c char6 = this.char;
        ++char6.for;
        this.char.long = this.char.for - 1;
        this.char.case[this.char.if] = new f(10, this.char.for - 1, this.do.char * this.char.new, new Color(255, 0, 0));
        final c char7 = this.char;
        ++char7.if;
        this.char.case[this.char.if] = new f(21, this.char.for - 1, this.do.char * this.char.new, new Color(255, 0, 0));
        final c char8 = this.char;
        ++char8.if;
        this.char.case[this.char.if] = new f(10, 21, this.do.char * this.char.new, new Color(255, 0, 0));
        final c char9 = this.char;
        ++char9.if;
        this.char.else[0] = this.char.for;
        this.char.c[0] = this.char.if;
        for (int n6 = 0; n6 < this.char.void; ++n6) {
            this.char.null[this.char.for] = new g(n6, n6, this.char.g * this.do.for, new Color(0, 100, 0));
            if (n6 == this.char.void - 1) {
                this.char.null[this.char.for].a = this.do.for * this.char.i;
            }
            this.char.null[this.char.for].int = false;
            final c char10 = this.char;
            ++char10.for;
            if (n6 == 0) {
                this.char.case[this.char.if] = new f(22, this.char.else[0], this.char.j, new Color(0, 80, 185));
            }
            else {
                this.char.case[this.char.if] = new f(this.char.else[0] + n6 - 1, this.char.else[0] + n6, this.char.j, new Color(0, 80, 185));
            }
            this.char.case[this.char.if].a = this.do.l * Integer.parseInt(this.getParameter("l0Rope"));
            this.char.case[this.char.if].int = false;
            final c char11 = this.char;
            ++char11.if;
        }
        this.char.else[1] = this.char.for;
        this.char.c[1] = this.char.if;
        for (int n7 = 0; n7 < this.char.void; ++n7) {
            this.char.null[this.char.for] = new g(n7, n7, this.char.g * this.do.for, new Color(0, 170, 0));
            if (n7 == this.char.void - 1) {
                this.char.null[this.char.for].a = this.do.for * this.char.i;
            }
            this.char.null[this.char.for].int = false;
            final c char12 = this.char;
            ++char12.for;
            if (n7 == 0) {
                this.char.case[this.char.if] = new f(22, this.char.else[1], this.char.j, new Color(70, 170, 255));
            }
            else {
                this.char.case[this.char.if] = new f(this.char.else[1] + n7 - 1, this.char.else[1] + n7, this.char.j, new Color(70, 170, 255));
            }
            this.char.case[this.char.if].a = this.do.l * Integer.parseInt(this.getParameter("l0Rope"));
            this.char.case[this.char.if].int = false;
            final c char13 = this.char;
            ++char13.if;
        }
        for (int n8 = 0; n8 < this.char.for; ++n8) {
            final g g = this.char.null[n8];
            g.if *= this.do.l;
            final g g2 = this.char.null[n8];
            g2.try *= this.do.l;
            final g g3 = this.char.null[n8];
            g3.if += Integer.parseInt(this.getParameter("StartX"));
            final g g4 = this.char.null[n8];
            g4.try += Integer.parseInt(this.getParameter("StartY"));
        }
        for (int n9 = 0; n9 < this.char.if; ++n9) {
            final f f = this.char.case[n9];
            f.a *= this.do.l;
        }
        System.out.println("nf=" + this.char.if + ", np=" + this.char.for);
        this.do.d = this.char.null[this.char.long].if - this.long / 2;
        this.do.int = this.char.null[this.char.long].try - this.if / 2;
        this.addKeyListener(new e());
        this.addMouseListener(new a());
        this.addMouseMotionListener(new b());
        this.new = 1;
    }
    
    public void start() {
        if (this.e == null) {
            (this.e = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.e != null) {
            this.e.stop();
            this.e = null;
            this.b = false;
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void a() {
        final ColorModel rgBdefault = ColorModel.getRGBdefault();
        final int[] array = new int[this.do.null * this.do.f];
        while (!this.for.checkAll()) {
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException ex) {}
        }
        final PixelGrabber pixelGrabber = new PixelGrabber(this.goto, 0, 0, this.do.null, this.do.f, array, 0, this.do.null);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex2) {
            System.err.println("interrupted waiting for pixels!");
            return;
        }
        for (int i = 0; i < this.do.null * this.do.f; ++i) {
            array[i] = (rgBdefault.getRed(array[i]) << 16 | rgBdefault.getGreen(array[i]) << 8 | rgBdefault.getBlue(array[i]));
            if (array[i] == this.a.int) {
                this.do.n[i % this.do.null][i / this.do.null] = 'l';
            }
            else if (array[i] == this.a.for) {
                this.do.n[i % this.do.null][i / this.do.null] = 'w';
            }
            else if (array[i] == this.a.do) {
                this.do.n[i % this.do.null][i / this.do.null] = 'e';
            }
            else if (array[i] == this.a.if) {
                this.do.n[i % this.do.null][i / this.do.null] = 'f';
            }
            else if (array[i] == this.a.a) {
                this.do.n[i % this.do.null][i / this.do.null] = 'E';
            }
            else {
                this.do.n[i % this.do.null][i / this.do.null] = 'F';
            }
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.new > 0) {
            this.byte.setColor(Color.white);
            this.byte.fillRect(10, 10, 85, 18);
            if (this.case < 33 && this.else < 100) {
                this.byte.setColor(Color.black);
            }
            else {
                this.byte.setColor(new Color(100, 100, 100));
            }
            this.byte.drawRect(10, 10, 85, 18);
            this.byte.drawString("www.eigelb.at", 13, 23);
            graphics.drawImage(this.c, 0, 0, this);
        }
    }
    
    public void run() {
        double if1 = 0.0;
        double try1 = 0.0;
        while (this.b) {
            this.do.i = this.do.d;
            this.do.q = this.do.int;
            double n = 0.0;
            double n2 = 0.0;
            for (int i = 0; i < 20; ++i) {
                n += this.char.null[i].for;
                n2 += this.char.null[i].do;
            }
            final double n3 = n / 20.0;
            final double n4 = n2 / 20.0;
            this.do.d = 0.99 * this.do.i + 0.01 * (this.char.null[this.char.long].if + 3.0 * n3 - this.long / 2);
            this.do.int = 0.99 * this.do.q + 0.01 * (this.char.null[this.char.long].try + 3.0 * n4 - this.if / 2);
            this.do.k = (int)this.do.d;
            this.do.new = (int)this.do.int;
            if (this.do.k < 0) {
                this.do.k = 0;
            }
            else if (this.do.k > this.do.null - this.long) {
                this.do.k = this.do.null - this.long;
            }
            if (this.do.new < 0) {
                this.do.new = 0;
            }
            else if (this.do.new > this.do.f - this.if) {
                this.do.new = this.do.f - this.if;
            }
            this.byte.drawImage(this.void, 0, 0, this.long, this.if, this.do.k, this.do.new, this.do.k + this.long, this.do.new + this.if, this);
            this.char.a();
            this.byte.setColor(Color.black);
            this.byte.drawLine((int)this.char.null[this.char.long].if - this.do.k, (int)this.char.null[this.char.long].try - this.do.new, (int)(this.char.null[this.char.long].if - this.do.k + 10.0 * Math.cos(this.char.b)), (int)(this.char.null[this.char.long].try - this.do.new + 10.0 * Math.sin(this.char.b)));
            if (this.do.try && this.char.a[this.char.goto]) {
                for (int j = 0; j < this.char.void; ++j) {
                    this.char.null[this.char.else[this.char.goto] + j].int = false;
                    this.char.case[this.char.c[this.char.goto] + j].int = false;
                }
                this.char.a[this.char.goto] = false;
                this.do.try = false;
            }
            if (this.char.a[this.char.goto] && this.do.j) {
                for (int n5 = this.char.c[this.char.goto]; n5 < this.char.c[this.char.goto] + this.char.void && this.char.case[n5].do < this.char.l; ++n5) {
                    final f f = this.char.case[n5];
                    f.do *= 1.1;
                }
                this.do.j = false;
            }
            if (this.char.a[this.char.goto] && this.do.else) {
                for (int k = this.char.c[this.char.goto]; k < this.char.c[this.char.goto] + this.char.void; ++k) {
                    if (this.char.case[k].do > this.char.j) {
                        final f f2 = this.char.case[k];
                        f2.do *= 0.9;
                    }
                }
                this.do.else = false;
            }
            if (this.do.try && !this.char.a[this.char.goto]) {
                this.char.h[this.char.goto] = false;
                this.char.a[this.char.goto] = true;
                this.do.try = false;
                for (int l = 0; l < this.char.void; ++l) {
                    this.char.null[this.char.else[this.char.goto] + l].int = true;
                    this.char.case[this.char.c[this.char.goto] + l].int = true;
                    this.char.null[this.char.else[this.char.goto] + l].if = this.char.null[this.char.long].if + (1 + l) * 2 * Math.cos(this.char.b);
                    this.char.null[this.char.else[this.char.goto] + l].try = this.char.null[this.char.long].try + (1 + l) * 2 * Math.sin(this.char.b);
                    this.char.case[this.char.c[this.char.goto] + l].do = this.char.j;
                }
                this.char.null[this.char.else[this.char.goto] + this.char.void - 1].do = this.do.l * this.char.e * Math.sin(this.char.b);
                this.char.null[this.char.else[this.char.goto] + this.char.void - 1].for = this.do.l * this.char.e * Math.cos(this.char.b);
            }
            if (this.do.e || this.do.g) {
                int n6;
                if (this.do.e) {
                    n6 = 1;
                }
                else {
                    n6 = -1;
                }
                for (int n7 = 0; n7 < 2; ++n7) {
                    for (int n8 = 0; n8 < 10; ++n8) {
                        final int n9 = n8 + 11 * n7;
                        final g g = this.char.null[n9];
                        g.do += (this.char.null[10 + 11 * n7].if - this.char.null[n9].if) * this.char.try * n6;
                        final g g2 = this.char.null[n9];
                        g2.for += (this.char.null[n9].try - this.char.null[10 + 11 * n7].try) * this.char.try * n6;
                    }
                }
            }
            for (int n10 = 0; n10 < this.char.if; ++n10) {
                if (this.char.case[n10].int) {
                    final double n11 = this.char.null[this.char.case[n10].for].if - this.char.null[this.char.case[n10].if].if;
                    final double n12 = this.char.null[this.char.case[n10].for].try - this.char.null[this.char.case[n10].if].try;
                    final double sqrt = Math.sqrt(n11 * n11 + n12 * n12);
                    final double n13 = n11 / sqrt;
                    final double n14 = n12 / sqrt;
                    final double n15 = this.char.case[n10].do * (sqrt - this.char.case[n10].a);
                    final double n16 = n15 / this.char.null[this.char.case[n10].for].a;
                    final g g3 = this.char.null[this.char.case[n10].for];
                    g3.for -= this.do.c * n16 * n13;
                    final g g4 = this.char.null[this.char.case[n10].for];
                    g4.do -= this.do.c * n16 * n14;
                    final double n17 = n15 / this.char.null[this.char.case[n10].if].a;
                    final g g5 = this.char.null[this.char.case[n10].if];
                    g5.for += this.do.c * n17 * n13;
                    final g g6 = this.char.null[this.char.case[n10].if];
                    g6.do += this.do.c * n17 * n14;
                }
            }
            for (int n18 = 0; n18 < this.char.for; ++n18) {
                if (this.char.null[n18].int) {
                    if (this.do.a((int)this.char.null[n18].if, (int)this.char.null[n18].try) == 'w') {
                        final g g7 = this.char.null[n18];
                        g7.do += (0.5 - this.char.int) * this.do.c * this.do.h;
                        final g g8 = this.char.null[n18];
                        g8.for *= this.do.byte;
                        final g g9 = this.char.null[n18];
                        g9.do *= this.do.byte;
                    }
                    else {
                        final g g10 = this.char.null[n18];
                        g10.do += this.do.c * this.do.h;
                        final g g11 = this.char.null[n18];
                        g11.for *= this.do.goto;
                        final g g12 = this.char.null[n18];
                        g12.do *= this.do.goto;
                    }
                    if ((n18 != this.char.else[0] + this.char.void - 1 || !this.char.h[0]) && (n18 != this.char.else[1] + this.char.void - 1 || !this.char.h[1])) {
                        if1 = this.char.null[n18].if;
                        try1 = this.char.null[n18].try;
                        final g g13 = this.char.null[n18];
                        g13.if += this.do.c * this.char.null[n18].for;
                        final g g14 = this.char.null[n18];
                        g14.try += this.do.c * this.char.null[n18].do;
                    }
                    final char a = this.do.a((int)this.char.null[n18].if, (int)this.char.null[n18].try);
                    if ((a != 'l' && a != 'w') || this.char.null[n18].if < 0.0 || this.char.null[n18].if > this.do.null - 1 || this.char.null[n18].try < 0.0 || this.char.null[n18].try > this.do.f - 1) {
                        if (n18 == this.char.else[0] + this.char.void - 1) {
                            this.char.h[0] = true;
                        }
                        else if (n18 == this.char.else[1] + this.char.void - 1) {
                            this.char.h[1] = true;
                        }
                        else {
                            this.char.null[n18].if = if1;
                            this.char.null[n18].try = try1;
                            this.char.null[n18].for = 0.0;
                            this.char.null[n18].do = 0.0;
                        }
                    }
                }
            }
            ++this.null;
            this.null %= 2;
            if (this.null == 0) {
                this.repaint();
                if (this.do.o <= 0) {
                    continue;
                }
                try {
                    Thread.sleep(this.do.o);
                }
                catch (InterruptedException ex) {}
            }
        }
    }
    
    class e extends KeyAdapter
    {
        int a;
        
        public void keyPressed(final KeyEvent keyEvent) {
            this.a = keyEvent.getKeyCode();
            if (this.a == 37) {
                a.this.do.e = true;
            }
            if (this.a == 39) {
                a.this.do.g = true;
            }
            if (this.a == 68) {
                a.this.do.try = true;
            }
            if (this.a == 49) {
                a.this.char.goto = 0;
            }
            if (this.a == 50) {
                a.this.char.goto = 1;
            }
            if (this.a == 69) {
                a.this.do.j = true;
            }
            if (this.a == 67) {
                a.this.do.else = true;
            }
            if (this.a == 38 && a.this.char.int < 1.0) {
                final c char1 = a.this.char;
                char1.int += 0.15;
            }
            if (this.a == 40 && a.this.char.int > 0.0) {
                final c char2 = a.this.char;
                char2.int -= 0.15;
            }
            if (this.a == 83) {
                final c char3 = a.this.char;
                char3.b -= 0.1;
            }
            if (this.a == 70) {
                final c char4 = a.this.char;
                char4.b += 0.1;
            }
            if (this.a == 16) {
                a.this.try = true;
            }
        }
        
        public void keyReleased(final KeyEvent keyEvent) {
            this.a = keyEvent.getKeyCode();
            if (this.a == 37) {
                a.this.do.e = false;
            }
            if (this.a == 39) {
                a.this.do.g = false;
            }
            if (this.a == 16) {
                a.this.try = false;
            }
        }
    }
    
    class h
    {
        char[][] n;
        double h;
        double c;
        double goto;
        double byte;
        double void;
        double case;
        double if;
        int null;
        int f;
        int k;
        int new;
        double i;
        double q;
        double d;
        double int;
        boolean e;
        boolean r;
        boolean g;
        boolean long;
        boolean try;
        boolean j;
        boolean else;
        int b;
        double m;
        double p;
        double a;
        double do;
        double for;
        double l;
        double char;
        int o;
        
        h() {
            this.void = 0.0;
            this.case = 1.0;
            this.if = 1.0;
            this.k = 0;
            this.new = 0;
            this.e = false;
            this.r = false;
            this.g = false;
            this.long = false;
            this.try = false;
            this.j = false;
            this.else = false;
            this.b = 0;
            this.m = 0.0;
            this.p = a.this.long - 1;
            this.a = 0.0;
            this.do = a.this.if - 1;
            this.null = Integer.parseInt(a.this.getParameter("Bild_w"));
            this.f = Integer.parseInt(a.this.getParameter("Bild_h"));
            this.for = 0.01 * Integer.parseInt(a.this.getParameter("scaleM"));
            this.char = 0.01 * Integer.parseInt(a.this.getParameter("scaleF"));
            this.l = 0.01 * Integer.parseInt(a.this.getParameter("scaleSize"));
            this.c = 0.001 * Integer.parseInt(a.this.getParameter("dt"));
            this.o = Integer.parseInt(a.this.getParameter("delay"));
            this.h = Integer.parseInt(a.this.getParameter("Gravity"));
            this.goto = 0.001 * Integer.parseInt(a.this.getParameter("Reibung"));
            this.byte = 0.001 * Integer.parseInt(a.this.getParameter("ReibungW"));
            this.n = new char[this.null][this.f];
        }
        
        public char a(final int n, final int n2) {
            if (n < 0 || n2 < 0 || n >= this.null || n2 >= this.f) {
                return 'e';
            }
            return this.n[n][n2];
        }
    }
    
    class c
    {
        int long;
        boolean[] a;
        boolean[] h;
        double b;
        double int;
        double i;
        double g;
        double byte;
        double d;
        double f;
        double j;
        double l;
        double k;
        double new;
        double try;
        double e;
        int do;
        int char;
        int for;
        int if;
        int goto;
        int[] else;
        int[] c;
        int void;
        f[] case;
        g[] null;
        
        public c(final int do1, final int char1) {
            this.a = new boolean[2];
            this.h = new boolean[2];
            this.b = -1.5707963267948966;
            this.int = 1.0;
            this.do = 32;
            this.char = 64;
            this.for = 0;
            this.if = 0;
            this.goto = 0;
            this.else = new int[2];
            this.c = new int[2];
            this.void = 10;
            this.a[0] = false;
            this.a[1] = false;
            this.h[0] = false;
            this.h[1] = false;
            this.char = char1;
            this.do = do1;
            this.case = new f[this.char];
            this.null = new g[this.do];
            this.i = 0.01 * Integer.parseInt(a.this.getParameter("mHook"));
            this.g = 0.01 * Integer.parseInt(a.this.getParameter("mRope"));
            this.byte = 0.01 * Integer.parseInt(a.this.getParameter("mWheels"));
            this.d = 0.01 * Integer.parseInt(a.this.getParameter("mAxis"));
            this.f = 0.01 * Integer.parseInt(a.this.getParameter("mCorpus"));
            this.j = a.this.do.char * Integer.parseInt(a.this.getParameter("FRopeMin"));
            this.l = a.this.do.char * Integer.parseInt(a.this.getParameter("FRopeMax"));
            this.k = Integer.parseInt(a.this.getParameter("FWheels"));
            this.new = Integer.parseInt(a.this.getParameter("FCorpus"));
            this.try = 0.1 * a.this.do.l * a.this.do.c * Integer.parseInt(a.this.getParameter("FEngine"));
            this.e = Integer.parseInt(a.this.getParameter("v0Rope"));
        }
        
        public void a() {
            for (int i = 0; i < this.if; ++i) {
                if (this.case[i].int) {
                    this.case[i].a();
                }
            }
            for (int j = 0; j < this.for; ++j) {
                if (this.null[j].int) {
                    this.null[j].a();
                }
            }
        }
    }
    
    class f
    {
        int for;
        int if;
        double do;
        double a;
        Color new;
        boolean int;
        
        public f(final int for1, final int if1, final double do1, final Color new1) {
            this.int = true;
            this.for = for1;
            this.if = if1;
            this.do = do1;
            this.new = new1;
            final double n = a.this.char.null[this.for].if - a.this.char.null[this.if].if;
            final double n2 = a.this.char.null[this.for].try - a.this.char.null[this.if].try;
            this.a = Math.sqrt(n * n + n2 * n2);
        }
        
        public void a() {
            a.this.byte.setColor(this.new);
            a.this.byte.drawLine((int)a.this.char.null[this.for].if - a.this.do.k, (int)a.this.char.null[this.for].try - a.this.do.new, (int)a.this.char.null[this.if].if - a.this.do.k, (int)a.this.char.null[this.if].try - a.this.do.new);
        }
    }
    
    class g
    {
        double if;
        double try;
        double for;
        double do;
        double a;
        Color new;
        boolean int;
        
        public g(final double if1, final double try1, final double a, final Color new1) {
            this.if = if1;
            this.try = try1;
            this.a = a;
            this.for = 0.0;
            this.do = 0.0;
            this.new = new1;
            this.int = true;
        }
        
        public void a() {
            a.this.byte.setColor(this.new);
            a.this.byte.fillRect((int)(this.if - 1.0 - a.this.do.k), (int)(this.try - 1.0 - a.this.do.new), 3, 3);
        }
    }
    
    class b extends MouseMotionAdapter
    {
        public void mouseDragged(final MouseEvent mouseEvent) {
            a.this.else = mouseEvent.getX();
            a.this.case = mouseEvent.getY();
        }
        
        public void mouseMoved(final MouseEvent mouseEvent) {
            a.this.else = mouseEvent.getX();
            a.this.case = mouseEvent.getY();
            if (a.this.case < 33 && a.this.else < 100) {
                if (!a.this.d) {
                    a.this.setCursor(new Cursor(12));
                    a.this.d = true;
                }
            }
            else if (a.this.d) {
                a.this.setCursor(new Cursor(0));
                a.this.d = false;
            }
        }
    }
    
    class a extends MouseAdapter
    {
        public void mousePressed(final MouseEvent mouseEvent) {
            a.this.else = mouseEvent.getX();
            a.this.case = mouseEvent.getY();
            a.this.int = true;
            if (a.this.case < 33 && a.this.else < 100) {
                a.this.int = false;
                try {
                    a.this.getAppletContext().showDocument(new URL("http://www.eigelb.at"), "_blank");
                }
                catch (MalformedURLException ex) {}
            }
        }
        
        public void mouseReleased(final MouseEvent mouseEvent) {
            a.this.int = false;
        }
    }
    
    class d
    {
        int int;
        int for;
        int do;
        int if;
        int a;
        int new;
        
        d() {
            this.int = 16777215;
            this.for = 11129855;
            this.do = 10643504;
            this.if = 8552833;
            this.a = 8023138;
            this.new = 6389921;
        }
    }
}
