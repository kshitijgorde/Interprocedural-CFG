import java.awt.image.ImageObserver;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Point;
import java.awt.Component;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class o extends Canvas implements Runnable, p
{
    private Graphics k;
    private Image case;
    private Dimension h;
    private boolean a;
    private w j;
    private s[] do;
    private Thread for;
    private String null;
    private Color void;
    private Color f;
    private Color l;
    private e new;
    private boolean try;
    private boolean long;
    private int o;
    private int n;
    private int c;
    private String e;
    private String goto;
    private String d;
    private s g;
    private u else;
    public String m;
    public String byte;
    public String int;
    public s char;
    public u i;
    private boolean if;
    private static byte[] b;
    
    public o() {
        this.a = false;
        this.void = new Color(16777215);
        this.f = new Color(0);
        this.l = new Color(16777215);
        this.try = true;
        this.long = false;
        this.o = -100;
        this.n = -100;
        this.c = 0;
        this.if = false;
    }
    
    protected synchronized void finalize() throws Throwable {
        this.for = null;
        this.case();
        super.finalize();
    }
    
    public synchronized void a(final boolean if1) {
        this.if = if1;
        if (this.do != null) {
            for (int i = 0; i < this.do.length; ++i) {
                this.do[i].for.a(if1);
            }
        }
    }
    
    public synchronized void a(final e e, final boolean b, final int n) {
        if (e != null) {
            e.a(b, n);
        }
    }
    
    public synchronized void new() {
        this.for = null;
        if (this.do != null) {
            for (int i = 0; i < this.do.length; ++i) {
                this.do[i].for.byte();
            }
        }
        if (this.case != null) {
            this.case.flush();
            this.k.dispose();
            this.case = null;
            this.k = null;
        }
    }
    
    public synchronized void a(final w j) {
        this.j = j;
    }
    
    public synchronized void a(final s[] do1) {
        if (do1 != null) {
            for (int i = 0; i < do1.length; ++i) {
                do1[i].for = new e(this, do1[i], this.getSize(), this);
            }
            this.do = do1;
        }
    }
    
    public synchronized void try() {
        if (this.for == null) {
            this.for = new Thread(this);
        }
        this.for.start();
    }
    
    public synchronized void do(final boolean b) {
        this.a = true;
        if (this.do != null) {
            for (int i = 0; i < this.do.length; ++i) {
                this.do[i].for.a(this.do[i], 10);
                if (!b) {
                    if (this.long) {
                        this.do[i].for.a(this.do[i], 60);
                    }
                    else {
                        this.do[i].for.a(this.do[i], 70);
                    }
                }
                if (this.do[i].do > 0) {
                    if (this.do[i].try == null || this.do[i].try.length() == 0) {
                        this.do[i].for.a(this.do[i].void, this.do[i].new, this.do[i].do);
                    }
                    else {
                        this.do[i].for.a(this.do[i].try, this.do[i].new, this.do[i].do);
                    }
                }
            }
            this.a();
        }
    }
    
    public void a() {
        this.try = true;
    }
    
    public Component do() {
        return this.j.try();
    }
    
    private int a(final s[] array, final e e) {
        if (this.do != null && e != null) {
            for (int i = 0; i < array.length; ++i) {
                if (array[i].for == e) {
                    return i;
                }
            }
        }
        return -1;
    }
    
    public void a(final s s, final int n) {
        final int a = this.a(this.do, s.for);
        if (a >= 0) {
            this.do[a] = s;
            if (s.for == this.new) {
                this.j.a(s.for);
            }
            this.a();
        }
    }
    
    public void a(final e e, final int n) {
        if (e != null) {
            e.if(n);
            this.try = true;
        }
    }
    
    public int a(final e e) {
        if (e != null) {
            return e.for();
        }
        return -1;
    }
    
    public int if(final e e) {
        if (e != null) {
            return e.try();
        }
        return -1;
    }
    
    public int for(final e e) {
        if (e != null) {
            return e.case();
        }
        return -1;
    }
    
    public Dimension do(final e e) {
        if (e != null) {
            return e.new();
        }
        return null;
    }
    
    public void int(final e new1) {
        this.new = new1;
    }
    
    public e char() {
        return this.new;
    }
    
    public void a(final int o, final int n) {
        this.o = o;
        this.n = n;
    }
    
    public Point else() {
        return new Point(this.o, this.n);
    }
    
    public void if(final boolean long1) {
        this.long = long1;
    }
    
    public Color int() {
        return this.l;
    }
    
    public void a(final Color l) {
        if (l != null) {
            this.l = l;
            this.a();
        }
    }
    
    public void a(final String null, final Color void1, final Color f) {
        this.null = null;
        this.void = void1;
        this.f = f;
    }
    
    public void case() {
        if (this.do != null) {
            for (int i = 0; i < this.do.length; ++i) {
                this.do[i].for.char();
            }
        }
    }
    
    public s a(final String s) {
        return this.a(this.do, s);
    }
    
    private s a(final s[] array, String upperCase) {
        if (array != null && upperCase != null) {
            upperCase = upperCase.replace('i', 'I').toUpperCase();
            for (int i = 0; i < array.length; ++i) {
                if (upperCase.equals(array[i].new)) {
                    return array[i];
                }
            }
        }
        return null;
    }
    
    public u a(final s s, final v v) {
        u u = null;
        if (this.do == null || s == null || v == null) {
            return null;
        }
        if (v.do == 1700) {
            if (v.try == 1000 && v.a != null) {
                final s a = this.a(this.do, v.a);
                if (a != null && a.for != null) {
                    final e for1 = a.for;
                    this.new = null;
                    this.new = for1;
                    this.j.a(for1);
                }
            }
        }
        else if (v.do == 2500) {
            this.a = false;
            this.case();
            this.new();
            this.do = null;
            this.try();
            this.j.a(v.byte);
        }
        else if (v.do == 1800) {
            this.l = h.a(v.byte, this.l);
            this.a();
        }
        else if (v.do == 5100) {
            if (v.for == 100 || v.for == 200) {
                this.j.a(v.for);
            }
            else if (v.for == 300) {
                this.j.for();
            }
            else if (v.for == 1) {
                this.j.int();
            }
            else {
                this.j.byte();
            }
        }
        else if (v.do == 5000) {
            if (v.for == 1) {
                this.j.new();
            }
            else {
                this.j.do();
            }
        }
        else if (v.try == -200) {
            for (int i = 0; i < this.do.length; ++i) {
                this.do[i].for.if(v);
            }
        }
        else if (v.try == -100) {
            u = s.for.if(v);
        }
        else if (v.try == 1000) {
            final s a2 = this.a(this.do, v.a);
            if (a2 != null) {
                u = a2.for.if(v);
            }
        }
        return u;
    }
    
    public void a(final s s, final u u) {
        if (u != null) {
            while (u.q == null && u.a != null && s != null) {
                s.for.a(u);
            }
            final v[] q = u.q;
            if (q != null) {
                for (int i = 0; i < q.length; ++i) {
                    final u a = this.a(s, q[i]);
                    if (a != null) {
                        u.q = a.q;
                        u.a = a.a;
                    }
                }
            }
        }
    }
    
    public e a(final int n, final int n2, final boolean b) {
        final e new1 = this.new;
        int n3 = -1;
        final boolean long1 = this.long;
        if (this.do == null || !this.a) {
            return null;
        }
        final int[] array = new int[this.do.length];
        for (int i = 0; i < this.do.length; ++i) {
            array[i] = this.do[i].for.for();
            if (n3 < 0 && this.do[i].for.a(array[i], long1, n, n2)) {
                n3 = i;
            }
            if (this.do[i].for != new1) {
                this.do[i].for.a(this.do[i], 40);
            }
        }
        if (new1 != null) {
            final int a = this.a(this.do, new1);
            if (b && (n3 < 0 || n3 >= a)) {
                this.a(new1, true, 0);
                return new1;
            }
            new1.a(new1.s, 40);
        }
        return null;
    }
    
    public void if(final e e, final int n) {
        if (this.do == null || !this.a) {
            return;
        }
        for (int i = 0; i < this.do.length; ++i) {
            if (e == null || this.do[i].for != e) {
                this.do[i].for.a(this.do[i], 50);
            }
        }
    }
    
    public void for() {
        if (this.do != null && this.a) {
            for (int i = 0; i < this.do.length; ++i) {
                this.do[i].for.a(this.do[i], 60);
            }
        }
    }
    
    public void goto() {
        if (this.do != null && this.a) {
            for (int i = 0; i < this.do.length; ++i) {
                this.do[i].for.a(this.do[i], 70);
            }
        }
    }
    
    private void a(final Graphics graphics, final Dimension dimension, final String s) {
        if (s != null && dimension.width > 0 && dimension.height > 0 && graphics != null) {
            final FontMetrics fontMetrics = this.getFontMetrics(new Font("Serif", 0, 9));
            final int height = fontMetrics.getHeight();
            final int n = (dimension.width - fontMetrics.stringWidth(s)) / 2;
            final int n2 = (dimension.height - height) / 2;
            graphics.setClip(0, 0, dimension.width, dimension.height);
            graphics.setColor(this.void);
            graphics.fillRect(0, 0, dimension.width, dimension.height);
            graphics.setColor(this.f);
            graphics.drawString(s, n, n2);
        }
    }
    
    public synchronized void if() {
        final s[] do1 = this.do;
        final e new1 = this.new;
        final Dimension size = this.getSize();
        final int o = this.o;
        final int n = this.n;
        int[] array = null;
        boolean b = false;
        int n2 = -1;
        if (size == null || size.width <= 0 || size.height <= 0) {
            this.c = 0;
            return;
        }
        if (do1 != null) {
            array = new int[do1.length];
        }
        if (this.case == null || this.h == null || this.h.width != size.width || this.h.height != size.height) {
            if (this.case != null) {
                this.case.flush();
                this.k.dispose();
                this.case = null;
                this.k = null;
            }
            this.case = this.createImage(size.width, size.height);
            this.h = size;
            if (this.k != null) {
                this.k.dispose();
            }
            this.k = this.case.getGraphics();
            if (do1 != null) {
                for (int i = 0; i < do1.length; ++i) {
                    do1[i].for.a(size);
                }
                if (this.j != null && new1 != null) {
                    this.j.a(new1);
                }
            }
        }
        if (!this.a) {
            this.a(this.k, size, this.null);
            this.c = 3;
            return;
        }
        if (this.case == null) {
            this.c = 0;
            return;
        }
        this.k.setClip(0, 0, size.width, size.height);
        this.k.setColor(this.l);
        this.k.fillRect(0, 0, size.width, size.height);
        if (do1 == null) {
            this.c = 0;
            return;
        }
        for (int j = 0; j < do1.length; ++j) {
            array[j] = do1[j].for.for();
            if (n2 < 0 && do1[j].for.a(array[j], this.long, o, n)) {
                n2 = j;
            }
        }
        if (n2 >= 0) {
            b = true;
        }
        for (int k = do1.length - 1; k >= 0; --k) {
            if (n2 == k) {
                b = false;
            }
            do1[k].for.a(this.k, do1[k], this.long, array[k], o, n, b);
        }
        final Font font = new Font("SansSerif", 1, 12);
        this.k.setClip(0, 0, size.width, size.height);
        this.k.setColor(Color.yellow);
        this.k.setFont(font);
        this.k.drawString(new String(o.b), 10, this.k.getFontMetrics().getAscent() + 5);
        if (n2 < 0) {
            this.d = null;
            this.goto = null;
            this.e = null;
            this.g = null;
            this.else = null;
            this.c = 0;
            return;
        }
        this.d = do1[n2].for.j;
        this.goto = do1[n2].for.l;
        this.e = do1[n2].for.w;
        this.g = do1[n2];
        this.else = do1[n2].for.t;
        if (e.a(this.else, this.e, this.d, this.goto)) {
            this.c = 12;
            return;
        }
        this.c = 0;
    }
    
    private synchronized void byte() {
        final s[] do1 = this.do;
        final e new1 = this.new;
        final Dimension size = this.getSize();
        int for1 = -1;
        if (this.case == null || this.h.width != size.width || this.h.height != size.height) {
            this.if();
        }
        final Graphics graphics = this.getGraphics();
        if (this.for == null || graphics == null || this.case == null || this.h.width == 0 || this.h.height == 0) {
            return;
        }
        graphics.drawImage(this.case, 0, 0, this);
        graphics.dispose();
        this.m = this.e;
        this.byte = this.goto;
        this.int = this.d;
        this.char = this.g;
        this.i = this.else;
        if (do1 != null && this.j != null) {
            if (new1 != null) {
                for1 = new1.for();
            }
            this.j.a(for1, this.c);
        }
    }
    
    public void paint(final Graphics graphics) {
        if (graphics != null) {
            this.update(graphics);
        }
    }
    
    public void update(final Graphics graphics) {
        if (graphics != null) {
            this.try = true;
        }
    }
    
    public void run() {
        final Thread currentThread = Thread.currentThread();
        Thread.currentThread().setPriority(5);
        while (this.for == currentThread) {
            if (this.try) {
                this.try = false;
                if (this.for == currentThread) {
                    this.if();
                }
                Thread.yield();
                if (this.for == currentThread) {
                    this.byte();
                }
            }
            try {
                Thread.sleep(5L);
            }
            catch (InterruptedException ex) {}
            if (this.for == currentThread && this.if) {
                try {
                    Thread.sleep(200L);
                }
                catch (InterruptedException ex2) {}
            }
        }
    }
    
    static {
        o.b = new byte[] { 45, 32, 85, 78, 82, 69, 71, 73, 83, 84, 69, 82, 69, 68, 32, 45 };
    }
}
