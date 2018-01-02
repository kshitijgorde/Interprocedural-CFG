import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.MouseAdapter;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.ImageObserver;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.image.ImageProducer;
import java.awt.MediaTracker;
import java.awt.image.ColorModel;
import java.awt.image.PixelGrabber;
import java.awt.image.MemoryImageSource;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class a extends Applet implements Runnable
{
    Thread try;
    Image byte;
    Graphics e;
    MemoryImageSource m;
    int[] l;
    PixelGrabber c;
    ColorModel if;
    MediaTracker a;
    int[] new;
    int else;
    int f;
    int h;
    int case;
    boolean do;
    boolean void;
    int b;
    int null;
    char[] k;
    int g;
    int j;
    int n;
    int long;
    int int;
    int p;
    char d;
    a goto;
    c char;
    int o;
    int for;
    boolean i;
    
    public a() {
        this.if = ColorModel.getRGBdefault();
        this.do = false;
        this.void = false;
        this.b = 77;
        this.null = 12;
        this.k = new char[] { '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', 'º', 'U', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', 'º', 'V', '\0', 'º', 'V', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\'', 'v', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', 'U', '\'', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\u00ed', 'v', '\0', '\u00ee', 'v', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '©', '¨', '\0', '\u0087', '6', '\0', '6', '¨', '\0', '\0', 'v', 'w', '\u0087', '6', '\0', '7', '¨', '\0', '\0', 'v', 'v', '\u0087', '7', '\0', '7', '¨', '\0', '\0', 'v', 'v', '\0', '\0', '\0', '\0', '\u0087', '\u00ed', '\u00dc', 'w', '\0', '\0', '\u0087', '6', '\0', '\0', '¨', '\u00ed', '\u00ca', 'v', 'v', '\0', '\0', '\u0087', '\u00ee', '\u00dc', 'v', '\0', '\0', '\u00ed', 'v', '\0', '\u00ed', '\u0097', '\u00dc', '\u00dc', 'v', '\0', '\0', '\0', '\0', '\0', '\0', '\u0098', '\u00ed', '\u00ee', '©', '\0', '6', '\u00ee', '\u00ed', 'v', 'º', '\u0098', '\0', '\u0087', '\u00ff', 'V', '\0', '\u00dc', 'v', 'º', '\u0097', '\0', '\u0087', '\u00ff', 'V', '\0', '\u00dc', 'v', '¹', '\u0097', '\0', '\u0086', '\u00ff', 'V', '\0', '\u00dc', 'v', '\0', '\0', '\0', '\u0098', '\u00ff', 'w', 'v', '\u00ff', 'v', '\0', '\u00ee', 'v', '\0', '¨', '\u00ff', 'v', '¨', '\u00ff', '¨', '\0', '\u0098', '\u00ff', 'v', 'v', '\u00ff', 'w', '\0', '\u00ed', 'w', '\0', '\u00ed', '\u00ff', '\u0087', 'v', '\u00ff', 'v', '\0', '\0', '\0', '\0', '©', '\u00ff', 'v', 'v', '\u00ff', '\u0087', '6', '\u00ee', '\u00ed', 'v', 'v', '\u00ed', '\0', '\u00ca', '\u00ed', '\u0098', '\0', '\u00ff', '\0', 'w', '\u00ed', '\0', '\u00cb', '\u00ee', '\u0097', '\0', '\u00ff', '\0', 'v', '\u00ed', '\0', '\u00cb', '\u00ed', '\u0097', '\0', '\u00ff', '\0', '\0', '\0', '\0', '\u00ff', '6', '\0', '\0', '\u0087', '\u00ca', '\0', '\u00ed', 'v', '\0', '\u00ff', '6', '\0', '\0', '\u00cb', '¨', '\0', '\u00ff', '6', '\0', '\0', '\u0087', '\u00cb', '\0', '\u00ed', 'v', '\0', '\u00ed', '\u0087', '\0', '\0', '\u0087', '\u00cb', '\0', '\0', '\0', '\0', 'v', '6', '\0', '\0', '\u00ca', '¨', '\0', '©', '¨', '\0', '\0', '\u00ff', '\'', '\u00ff', 'v', '\u00dc', 'v', '\u00dc', '\0', '\0', '\u00ff', '\'', '\u00ff', 'v', '\u00dc', 'w', '\u00dc', '\0', '\0', '\u00ff', '\'', '\u00ff', 'v', '\u00dc', 'v', '\u00dd', '\0', '\0', '\0', '\0', '\u00ff', '\u00ff', '\u00ff', '\u00ff', '\u00ff', '\u00ee', '\0', '\u00ed', 'v', '\0', '\u00ff', '\0', '\0', '\0', '©', '¨', '\0', '\u00ff', '\u00ff', '\u00ff', '\u00ff', '\u00ff', '\u00ed', '\0', '\u00ee', 'w', '\0', '\u00ed', 'v', '\0', '\0', 'v', '\u00ee', '\0', '\0', '\0', '\0', 'U', '\u00dc', '\u00ff', '\u00ff', '\u00ff', '¨', '\0', '©', '¨', '\0', '\0', '\u00cb', '\u0097', '\u00ee', '\'', '\u00ff', '\u00cb', '\u0087', '\0', '\0', '\u00ca', '\u0098', '\u00ed', '\'', '\u00ff', '\u00cb', '\u0087', '\0', '\0', '\u00cb', '\u0097', '\u00ee', '\'', '\u00ff', '\u00ca', '\u0086', '\0', '\0', '\0', '\0', '\u00ff', '\'', '\0', '\0', '\'', '\'', '\0', '\u00ed', 'v', '\0', '\u00ff', '\'', '\0', '\0', '¹', '¨', '\0', '\u00ff', '\'', '\0', '\0', '\'', '\'', '\0', '\u00ee', 'w', '\0', '\u00ed', 'v', '\0', '\0', 'v', '\u00dc', '\0', '\0', '\0', '\0', '\u00ff', '\u0098', '6', '\0', '©', '¨', '\0', '¨', '¨', '\0', '\0', 'v', '\u00ff', '¨', '\0', '\u00ff', '\u00ff', '\'', '\0', '\0', 'w', '\u00ff', '¨', '\0', '\u00ff', '\u00ff', '\'', '\0', '\0', 'w', '\u00ff', '©', '\0', '\u00ff', '\u00ff', '\'', '\0', '\0', '\0', '\0', '\u00cb', '\u00cb', '\'', '\'', '\u00dc', '©', '\0', '\u00ed', 'v', '\0', '\u00cb', '\u00ca', '\0', 'V', '\u00ff', '©', '\0', '\u00cb', '\u00ca', '\'', '\'', '\u00dc', '©', '\0', '\u00ee', 'v', '\0', '\u00ee', '\u00ff', '&', '6', '\u00ff', 'w', '\0', '\0', '\0', '\0', '\u00ff', 'V', '\0', 'w', '\u00ff', '¨', '\0', '¨', 'º', '\0', '\0', '\0', '\u00ff', 'v', '\0', 'º', '\u00dc', '\0', '\0', '\0', '\0', '\u00ff', 'v', '\0', '¹', '\u00dc', '\0', '\0', '\0', '\0', '\u00ff', 'v', '\0', 'º', '\u00dc', '\0', '\0', '©', '©', '\0', '\'', '\u00cb', '\u00ff', '\u00ff', '\u00cb', '\'', '\0', '\u00ed', 'v', '\0', '\'', '\u00dc', '\u00ff', '\u00ff', '\u00dc', '©', '\0', '\'', '\u00cb', '\u00ff', '\u00ff', '\u00cb', '\'', '\0', '\u00ed', 'v', '\0', '\u00ed', '\u00cb', '\u00ff', '\u00ff', '©', '\0', '\0', '©', '©', '\0', '\u0087', '\u00ff', '\u00ff', '\u00ff', '\u00ca', '\u00cb', '\0', 'v', '\u00ff', '\u00cb', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', 'v', '\'', '\0', '\0', '\u00cb', '\u0087', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '©', '\u00ff', '¨', '\u00ca', '\u00ff', '\'', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', 'v', '©', '\u0097', '\'', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0' };
        this.i = true;
    }
    
    public void init() {
        this.o = this.size().width;
        this.for = this.size().height;
        this.g = Integer.parseInt(this.getParameter("delay"));
        this.j = Integer.parseInt(this.getParameter("rotspeed"));
        this.n = Integer.parseInt(this.getParameter("blur"));
        if (this.n < 0) {
            this.n = 0;
        }
        else if (this.n > 255) {
            this.n = 255;
        }
        this.n = 255 - this.n << 24;
        this.long = Integer.parseInt(this.getParameter("brightness"));
        this.int = Integer.parseInt(this.getParameter("heightx"));
        this.d = this.getParameter("mode").charAt(0);
        this.p = Integer.parseInt(this.getParameter("nObjects"));
        this.else = Integer.parseInt(this.getParameter("key1"));
        this.f = Integer.parseInt(this.getParameter("key2"));
        this.h = Integer.parseInt(this.getParameter("key3"));
        final String host = this.getDocumentBase().getHost();
        this.case = 45471;
        for (int i = 0; i < host.length(); ++i) {
            this.case += 2 + i + host.charAt(i) * host.charAt(i);
        }
        this.case %= 50000;
        System.out.println("Domain: <" + host + ">");
        if (this.case == this.else || this.case == this.f || this.case == this.h) {
            this.do = true;
        }
        this.e = this.getGraphics();
        this.new = new int[this.o * this.for];
        this.goto = new a(45, 1.08);
        this.char = new c(this.p);
        (this.m = new MemoryImageSource(this.o, this.for, this.new, 0, this.o)).setAnimated(true);
        this.byte = this.createImage(this.m);
        this.addMouseListener(new b());
        this.addMouseMotionListener(new e());
    }
    
    public void run() {
        double n = 0.0;
        final double n2 = this.j * 0.001;
        this.if();
        this.m.newPixels();
        this.e.drawImage(this.byte, 0, 0, this);
        while (this.i) {
            n += n2;
            this.do();
            this.char.a(n);
            this.char.a();
            if (!this.do) {
                this.a();
            }
            this.m.newPixels();
            this.e.drawImage(this.byte, 0, 0, this);
            if (this.g > 0) {
                try {
                    Thread.sleep(this.g);
                }
                catch (InterruptedException ex) {}
            }
        }
    }
    
    public void a() {
        final int n = 10;
        final int n2 = 10;
        for (int i = 0; i < this.b; ++i) {
            for (int j = 0; j < this.null; ++j) {
                this.new[(j + n2) * this.o + i + n] = (0xFF000000 | this.k[i + j * this.b] << 16 | this.k[i + j * this.b] << 8 | this.k[i + j * this.b]);
            }
        }
        for (int k = -2; k < this.b + 2; ++k) {
            this.new[(n2 - 3) * this.o + k + n] = -986896;
            this.new[(n2 - 2) * this.o + k + n] = -16777216;
            this.new[(n2 - 1) * this.o + k + n] = -16777216;
            this.new[(n2 + this.null) * this.o + k + n] = -16777216;
            this.new[(n2 + this.null + 1) * this.o + k + n] = -986896;
        }
        for (int l = -3; l < this.null + 2; ++l) {
            this.new[(l + n2) * this.o + n - 3] = -986896;
            if (l >= 0 && l <= this.null) {
                this.new[(l + n2) * this.o + n - 2] = -16777216;
                this.new[(l + n2) * this.o + n - 1] = -16777216;
                this.new[(l + n2) * this.o + n + this.b] = -16777216;
                this.new[(l + n2) * this.o + n + this.b + 1] = -16777216;
            }
            this.new[(l + n2) * this.o + n + this.b + 2] = -986896;
        }
    }
    
    public void do() {
        for (int n = this.o * this.for, i = 0; i < n; ++i) {
            this.new[i] = this.n;
        }
    }
    
    public void if() {
        for (int n = this.o * this.for, i = 0; i < n; ++i) {
            this.new[i] = -16777216;
        }
    }
    
    public void update(final Graphics graphics) {
    }
    
    public void paint(final Graphics graphics) {
    }
    
    public void start() {
        if (this.try == null) {
            (this.try = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.try != null) {
            this.try.stop();
            this.try = null;
            this.i = false;
        }
    }
    
    class e extends MouseMotionAdapter
    {
        public void mouseMoved(final MouseEvent mouseEvent) {
            if (mouseEvent.getX() < 89 && mouseEvent.getY() < 24) {
                if (!a.this.void) {
                    a.this.setCursor(new Cursor(12));
                    a.this.void = true;
                }
            }
            else if (a.this.void) {
                a.this.setCursor(new Cursor(0));
                a.this.void = false;
            }
        }
        
        public void mouseDragged(final MouseEvent mouseEvent) {
            this.mouseMoved(mouseEvent);
        }
    }
    
    class b extends MouseAdapter
    {
        public void mousePressed(final MouseEvent mouseEvent) {
            if (!a.this.do && mouseEvent.getX() < 89 && mouseEvent.getY() < 24) {
                try {
                    a.this.getAppletContext().showDocument(new URL("http://www.eigelb.at"), "_blank");
                }
                catch (MalformedURLException ex) {}
            }
        }
    }
    
    class d
    {
        double if;
        double a;
        double do;
        
        public void a(final double if1, final double a, final double do1) {
            this.if = if1;
            this.a = a;
            this.do = do1;
        }
    }
    
    class c
    {
        int if;
        int case;
        int goto;
        int do;
        int a;
        int for;
        int byte;
        double long;
        double else;
        double b;
        double int;
        double char;
        double try;
        double void;
        d[] new;
        d[] null;
        
        c(final int if1) {
            this.long = 0.0;
            this.else = 200.0;
            this.if = if1;
            this.new = new d[this.if];
            this.null = new d[this.if];
            this.b = this.else - this.long;
            this.a = a.this.o / 2;
            this.for = a.this.for / 2 - 100;
            this.case = 0;
            while (this.case < this.if) {
                this.new[this.case] = new d();
                this.null[this.case] = new d();
                final double random = Math.random();
                final double n = (random * random + 0.1 * Math.random()) * 160.0;
                final double n2 = Math.random() * 3.141592653589793 * 2.0;
                this.new[this.case].a(n * Math.cos(n2), n * Math.sin(n2), 75.0 + a.this.int * 0.01 * 40.0 * (Math.random() - 0.5) * Math.cos(1.5707963267948966 - 1.5707963267948966 / (n * 0.1 - 1.0)));
                ++this.case;
            }
        }
        
        public void a(final double n) {
            this.try = Math.cos(n);
            this.void = Math.sin(n);
            this.case = 0;
            while (this.case < this.if) {
                this.null[this.case].if = this.new[this.case].if * this.try - this.new[this.case].a * this.void;
                this.null[this.case].a = this.new[this.case].a * this.try + this.new[this.case].if * this.void;
                this.null[this.case].do = this.new[this.case].do;
                ++this.case;
            }
        }
        
        public void a() {
            this.case = 0;
            while (this.case < this.if) {
                this.char = this.else - this.null[this.case].if;
                this.int = this.b / this.char;
                this.goto = this.a + (int)(this.null[this.case].a * this.int);
                this.do = this.for + (int)(this.null[this.case].do * this.int);
                this.byte = 50 - (int)(0.11 * this.char);
                if (this.byte < 0) {
                    this.byte = 0;
                }
                if (this.byte >= a.this.goto.if) {
                    this.byte = a.this.goto.if - 1;
                }
                a.this.goto.a(this.goto, this.do, this.byte);
                ++this.case;
            }
        }
    }
    
    class a
    {
        int if;
        int byte;
        int a;
        int goto;
        int b;
        int long;
        int else;
        int char;
        int case;
        int null;
        int for;
        double void;
        int[][][] c;
        int new;
        int[] do;
        int[] int;
        int[] try;
        int d;
        
        a(final int if1, final double void1) {
            this.d = 4096;
            this.if = if1;
            this.void = void1;
            this.new = 2 * (int)Math.pow(this.void, this.if);
            this.c = new int[this.if][this.new][this.new];
            this.do = new int[this.if];
            this.try = new int[this.d];
            this.int = new int[256];
            for (int i = 0; i < this.d; ++i) {
                this.try[i] = (int)(1.24 * (256.0 - 256.0 / (i * 0.001 + 1.0)));
            }
            for (int j = 0; j < 256; ++j) {
                this.int[j] = (int)(1000.0 * (256.0 / (-j / 1.24 + 256.0) - 1.0));
            }
            System.out.println("max. blob width/height: " + this.new);
            double void2 = this.void;
            for (int k = 0; k < this.if; ++k) {
                this.byte = 0;
                while (this.byte < this.new) {
                    this.a = 0;
                    while (this.a < this.new) {
                        this.do[k] = (int)(2.0 * void2);
                        if (this.do[k] > this.new) {
                            this.do[k] = this.new;
                        }
                        final double n = this.new / 2.0 - this.byte;
                        final double n2 = this.new / 2.0 - this.a;
                        double n3 = (void2 - Math.sqrt(n * n + n2 * n2)) / void2;
                        if (n3 > 1.0) {
                            n3 = 1.0;
                        }
                        if (n3 < 0.0) {
                            n3 = 0.0;
                        }
                        double n4;
                        if (a.this.d == 'r') {
                            n4 = 0.5 - 0.5 * Math.cos(2.0 * n3 * 3.141592653589793);
                        }
                        else {
                            n4 = 0.5 - 0.5 * Math.cos(n3 * 3.141592653589793);
                        }
                        if (a.this.d == 'b' || a.this.d == 'r') {
                            this.c[k][this.byte][this.a] = (int)(n4 * 255.0 * 2.0 * 0.01 * a.this.long);
                        }
                        else if (Math.random() < 0.4) {
                            this.c[k][this.byte][this.a] = (int)(n4 * 255.0 * 2.0 * Math.random() * 2.0 * 0.01 * a.this.long);
                        }
                        else {
                            this.c[k][this.byte][this.a] = 0;
                        }
                        ++this.a;
                    }
                    ++this.byte;
                }
                void2 *= this.void;
            }
        }
        
        public void a(final int n, final int n2, final int n3) {
            final int n4 = this.do[n3] / 2;
            if (n >= -n4 && n < a.this.o + n4 && n2 >= -n4 && n2 < a.this.for + n4) {
                this.null = this.new / 2 - n4;
                this.for = this.new / 2 + n4;
                this.byte = this.null;
                while (this.byte < this.for) {
                    this.a = this.null;
                    while (this.a < this.for) {
                        this.else = n + this.byte - this.new / 2;
                        this.char = n2 + this.a - this.new / 2;
                        if (this.c[n3][this.byte][this.a] > 0 && this.else >= 0 && this.char >= 0 && this.else < a.this.o && this.char < a.this.for) {
                            this.case = this.else + this.char * a.this.o;
                            this.goto = (a.this.new[this.case] & 0xFF0000) >> 16;
                            this.long = (a.this.new[this.case] & 0xFF);
                            this.goto = this.int[this.goto];
                            this.long = this.int[this.long];
                            this.goto += this.c[n3][this.byte][this.a];
                            this.long += this.c[n3][this.byte][this.a] << 2;
                            if (this.goto > 4095) {
                                this.goto = 4095;
                            }
                            if (this.long > 4095) {
                                this.long = 4095;
                            }
                            this.goto = this.try[this.goto];
                            this.long = this.try[this.long];
                            a.this.new[this.case] = (a.this.n | this.goto << 16 | this.goto << 8 | this.long);
                        }
                        ++this.a;
                    }
                    ++this.byte;
                }
            }
        }
    }
}
