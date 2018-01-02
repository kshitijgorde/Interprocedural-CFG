import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.util.Vector;
import java.awt.Font;
import java.awt.Point;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Memory extends Applet
{
    Image cb;
    Graphics db;
    Dimension size;
    FontMetrics eb;
    Color fb;
    Color gb;
    boolean hb;
    boolean ib;
    boolean jb;
    Image[] kb;
    Image lb;
    Point mb;
    int nb;
    int ob;
    int pb;
    int qb;
    int rb;
    int sb;
    int tb;
    int _;
    int a;
    int b;
    int c;
    int d;
    int e;
    int[][] f;
    v ba;
    abstract g;
    private static String Q = "\ufc71\ufc79\ufc6f\ufc6f\ufc7d\ufc7b\ufc79\ufc7a\ufc75\ufc70\ufc79";
    private static String R = "\ufc71\ufc79\ufc6f\ufc6f\ufc7d\ufc7b\ufc79\ufc6f\ufc43\ufc79\ufc72\ufc32\ufc78\ufc7d\ufc68";
    private static String S = "\ufc7e\ufc7d\ufc7f\ufc77\ufc5f\ufc73\ufc70\ufc73\ufc6e";
    private static String T = "\ufc7a\ufc73\ufc72\ufc68\ufc5f\ufc73\ufc70\ufc73\ufc6e";
    private static String U = "\ufc5d\ufc6e\ufc75\ufc7d\ufc70";
    private static String V = "\ufc6c\ufc7d\ufc65\ufc73\ufc7a\ufc7a\ufc43\ufc75\ufc71\ufc7d\ufc7b\ufc79";
    private static String W = "\ufc75\ufc71\ufc7d\ufc7b\ufc79";
    private static String ea = "\ufc78\ufc79\ufc70\ufc7d\ufc65";
    private static String fa = "\ufc7f\ufc73\ufc70\ufc6f";
    private static String ga = "\ufc6e\ufc73\ufc6b\ufc6f";
    private static String ha = "\ufc5f\ufc73\ufc6c\ufc65\ufc6e\ufc75\ufc7b\ufc74\ufc68\ufc3c\ufc34\ufc7f\ufc35\ufc3c\ufc2e\ufc2c\ufc2c\ufc2d\ufc30\ufc3c";
    
    public void init() {
        String s = this.getParameter(Memory.Q);
        if (s == null) {
            s = Memory.R;
        }
        this.ba = new v(this.getCodeBase(), s);
        this.g = new abstract(this, this.ba);
        this.size = this.getSize();
        this.fb = this.g._(Memory.S, Color.white);
        this.gb = this.g._(Memory.T, Color.black);
        this.setBackground(this.fb);
        this.setFont(new Font(Memory.U, 1, 12));
        this.eb = this.getFontMetrics(this.getFont());
    }
    
    private boolean g() {
        if (this.g._() && !this.g.b(abstract.Z)) {
            this.g.a(true);
            return false;
        }
        if (!this.h() || !this.i()) {
            return false;
        }
        this.cb = this.createImage(this.size.width, this.size.height);
        this.db = this.cb.getGraphics();
        this._();
        this.a();
        this.enableEvents(48L);
        return !this.g.f();
    }
    
    private boolean i() {
        if (this.g.b(Memory.V, (String)null) != null) {
            final Vector<Image> vector = new Vector<Image>();
            vector.addElement(this.lb = this.getImage(this.getCodeBase(), Memory.V));
            this.g._(vector, 0);
            if (!this.g.f()) {
                this.mb = new Point((this.size.width - this.lb.getWidth(this)) / 2, (this.size.height - this.lb.getHeight(this)) / 2);
            }
        }
        this.kb = this.g.b(Memory.W, 1);
        this.qb = this.kb[0].getWidth(this);
        this.rb = this.kb[0].getHeight(this);
        return !this.g.f();
    }
    
    private boolean h() {
        this.e = this.g.a(Memory.ea, 1) * 1000;
        this.nb = this.g.b(Memory.fa);
        this.ob = this.g.b(Memory.ga);
        this.pb = this.nb * this.ob;
        this.f = new int[this.ob][this.nb];
        return !this.g.f();
    }
    
    void _() {
        int n = 1;
        for (int i = 0; i < this.ob; ++i) {
            for (int j = 0; j < this.nb; ++j, ++n) {
                if (n == this.pb / 2 + 1) {
                    n = 1;
                }
                this.f[i][j] = n;
            }
        }
    }
    
    void a() {
        for (int i = 0; i < this.ob; ++i) {
            for (int j = 0; j < this.nb; ++j) {
                int n;
                int n2;
                do {
                    n = (int)(Math.random() * this.ob);
                    n2 = (int)(Math.random() * this.nb);
                } while (n == i && n2 == j);
                final int n3 = this.f[n][n2];
                this.f[n][n2] = this.f[i][j];
                this.f[i][j] = n3;
            }
        }
    }
    
    void a(final Graphics graphics) {
        int i = 0;
        int n = 0;
        while (i < this.pb) {
            for (int j = 0; j < this.ob; ++j) {
                for (int k = 0; k < this.nb; ++k, ++n) {
                    final int n2 = (int)(Math.random() * this.ob) + this.ob;
                    if (this.f[j][k] == -1 && n % n2 == 0) {
                        ++i;
                        this.f[j][k] = 0;
                        graphics.drawImage(this.kb[0], k * this.qb, j * this.rb, this);
                        this.a(400);
                    }
                }
            }
        }
    }
    
    void a(final int n) {
        try {
            Thread.sleep(n);
        }
        catch (InterruptedException ex) {}
    }
    
    void _(final int n, final int n2) {
        this.hb = true;
        this.ib = false;
        final boolean b = false;
        this.c = (b ? 1 : 0);
        this.b = (b ? 1 : 0);
        if (n == n2) {
            this.f[this.sb][this.tb] = -1;
            this.f[this._][this.a] = -1;
        }
        this.a(this.e);
        this.repaint();
    }
    
    private void b(final Graphics graphics) {
        this.g._(graphics);
        final String a = this.ba.a();
        graphics.setColor(this.gb);
        graphics.drawString(a, this.g.a(a, true, graphics), this.g.a(a, false, graphics));
        if (this.g()) {
            this.jb = true;
            this.g._(graphics);
            this.repaint();
            return;
        }
        this.g._(graphics);
        String s;
        if (this.g.b()) {
            s = Memory.ha + abstract.X;
        }
        else {
            s = this.ba.b();
        }
        graphics.setColor(this.gb);
        graphics.drawString(s, this.g.a(s, true, graphics), this.g.a(s, false, graphics));
    }
    
    public void update(final Graphics graphics) {
        if (!this.jb) {
            this.b(graphics);
            return;
        }
        this.g._(this.db);
        this.d = 0;
        for (int i = 0; i < this.ob; ++i) {
            for (int j = 0; j < this.nb; ++j) {
                if (this.f[i][j] == -1) {
                    ++this.d;
                }
                else if (i == this.sb && j == this.tb) {
                    this.db.drawImage(this.kb[this.b], this.tb * this.qb, this.sb * this.rb, this);
                }
                else if (i == this._ && j == this.a) {
                    this.db.drawImage(this.kb[this.c], this.a * this.qb, this._ * this.rb, this);
                }
                else {
                    this.db.drawImage(this.kb[0], j * this.qb, i * this.rb, this);
                }
            }
        }
        graphics.drawImage(this.cb, 0, 0, this);
        if (this.ib) {
            this._(this.b, this.c);
        }
        if (this.d == this.pb) {
            if (this.lb != null) {
                graphics.drawImage(this.lb, this.mb.x, this.mb.y, this);
                this.a(3000);
                this.g._(graphics);
            }
            this.a(graphics);
            this._();
            this.a();
        }
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public void processMouseEvent(final MouseEvent mouseEvent) {
        switch (mouseEvent.getID()) {
            case 501: {
                this.b(mouseEvent, mouseEvent.getX(), mouseEvent.getY());
            }
            default: {
                mouseEvent.consume();
            }
        }
    }
    
    public boolean b(final MouseEvent mouseEvent, final int n, final int n2) {
        final int n3 = n2 / this.rb;
        final int n4 = n / this.qb;
        if (this.f[n3][n4] == -1) {
            return true;
        }
        if (this.hb) {
            this.sb = n3;
            this.tb = n4;
            this.b = this.f[this.sb][this.tb];
            this.hb = false;
        }
        else {
            this._ = n3;
            this.a = n4;
            if (this._ == this.sb && this.a == this.tb) {
                return true;
            }
            this.c = this.f[this._][this.a];
            this.ib = true;
        }
        this.repaint();
        return true;
    }
    
    public Memory() {
        this.hb = true;
    }
    
    private static String b(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFFFC1C);
        }
        return new String(array);
    }
    
    static {
        Memory.Q = b(Memory.Q);
        Memory.R = b(Memory.R);
        Memory.S = b(Memory.S);
        Memory.T = b(Memory.T);
        Memory.U = b(Memory.U);
        Memory.V = b(Memory.V);
        Memory.W = b(Memory.W);
        Memory.ea = b(Memory.ea);
        Memory.fa = b(Memory.fa);
        Memory.ga = b(Memory.ga);
        Memory.ha = b(Memory.ha);
    }
}
