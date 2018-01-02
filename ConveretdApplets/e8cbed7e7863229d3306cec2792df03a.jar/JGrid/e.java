// 
// Decompiled by Procyon v0.5.30
// 

package JGrid;

import java.awt.Point;
import java.awt.Container;
import java.awt.Frame;
import java.awt.Event;
import java.awt.Insets;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Rectangle;
import java.util.Enumeration;
import java.util.Vector;
import java.awt.Component;
import java.awt.LayoutManager;
import java.util.Hashtable;
import java.awt.Scrollbar;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Button;
import java.awt.Panel;

public class e extends Panel
{
    Button B;
    String[] l;
    Object[][] p;
    int[] f;
    int[] char;
    boolean g;
    Font font;
    FontMetrics L;
    private boolean if;
    private boolean do;
    private boolean case;
    private boolean C;
    private boolean t;
    private int o;
    private int c;
    private int w;
    private int byte;
    private boolean n;
    private int I;
    private int s;
    private int a;
    private f[] r;
    private Image goto;
    private Graphics new;
    private Image h;
    private Image z;
    private int j;
    private int D;
    public static int d;
    private int q;
    private int F;
    private int e;
    private int[] E;
    private int[] int;
    private int else;
    private int try;
    private int J;
    private int i;
    private int K;
    private int k;
    private Scrollbar M;
    private Scrollbar b;
    private int G;
    private int H;
    private int m;
    private int long;
    private int void;
    private int null;
    private a v;
    private Hashtable A;
    public static final int for = 0;
    public static final int u = 1;
    public static final int O = 2;
    private int N;
    
    public e(final String[] l, final boolean if1, final int n) {
        this.B = new Button();
        this.font = new Font("Dialog", 0, 12);
        this.if = true;
        this.do = false;
        this.case = false;
        this.C = false;
        this.t = false;
        this.a = 20;
        this.r = new f[this.a];
        this.j = 20;
        this.D = 22;
        this.A = new Hashtable();
        this.l = l;
        if (!(this.if = if1)) {
            this.j = 0;
        }
        JGrid.f.do = (n % 2 == 0);
        JGrid.f.try = (n > 0);
        this.setLayout(null);
        this.M = new Scrollbar(0);
        this.b = new Scrollbar(1);
        this.add(this.M);
        this.add(this.b);
        this.add(this.B);
        JGrid.b.b = -1;
    }
    
    public void a(final Object[][] array, final boolean b) {
        if (this.p == null || b) {
            this.p = new Object[array.length][this.F];
            final Enumeration<String> keys = (Enumeration<String>)this.A.keys();
            while (keys.hasMoreElements()) {
                final String s = keys.nextElement();
                if (s.charAt(0) == ',') {
                    final String s2 = this.A.get(s);
                    final int int1 = Integer.parseInt(s.substring(1));
                    final String substring = s2.substring(0, s2.indexOf(44));
                    if (substring.length() == 0) {
                        continue;
                    }
                    try {
                        final d d = (d)Class.forName("JGrid.J" + substring + "Cell").newInstance();
                        for (int i = 0; i < array.length; ++i) {
                            this.p[i][int1] = d.getObject();
                        }
                    }
                    catch (Exception ex) {}
                }
            }
            this.q = array.length;
            this.char = new int[this.q];
            for (int j = 0; j < this.q; ++j) {
                this.char[j] = j;
            }
            if (this.a > this.q) {
                this.a = this.q;
            }
            if (this.s >= this.q) {
                this.s = -1;
            }
        }
        for (int k = 0; k < this.q; ++k) {
            for (int l = 0; l < this.F; ++l) {
                if (this.p[k][l] instanceof Vector) {
                    ((Vector)this.p[k][l]).addElement(array[k][l]);
                }
                else {
                    this.p[k][l] = array[k][l];
                }
            }
        }
        if (!b) {
            this.do();
        }
    }
    
    public void a(final int[] array) {
        this.E = array;
        this.F = array.length;
        this.K = this.j;
        this.int = new int[this.F];
        int n = this.else + this.j - 1;
        for (int i = 0; i < this.F; ++i) {
            this.K += array[i];
            this.int[i] = n;
            n += array[i];
        }
        final int[] int1 = this.int;
        JGrid.f.goto = array;
        JGrid.f.null = array.length;
        JGrid.f.if = int1;
        this.if();
        this.h = null;
        this.goto = null;
    }
    
    public void a(final int n, final int n2) {
        final int n3 = (JGrid.b.b != -1) ? this.f[JGrid.b.b] : -1;
        final int n4 = this.f[n];
        final int n5 = this.E[n];
        if (n2 < n) {
            for (int i = n - 1; i >= n2; --i) {
                this.f[i + 1] = this.f[i];
                this.E[i + 1] = this.E[i];
            }
            this.f[n2] = n4;
            this.E[n2] = n5;
        }
        else if (n2 > n) {
            for (int j = n + 1; j < n2; ++j) {
                this.f[j - 1] = this.f[j];
                this.E[j - 1] = this.E[j];
            }
            this.f[n2 - 1] = n4;
            this.E[n2 - 1] = n5;
        }
        this.a(this.E);
        this.h = null;
        this.do();
        if (n3 != -1) {
            for (int k = 0; k < this.F; ++k) {
                if (n3 == this.f[k]) {
                    JGrid.b.b = k;
                    break;
                }
            }
        }
        this.repaint();
    }
    
    public void paint(final Graphics graphics) {
        if (!this.do) {
            graphics.setFont(this.font);
            this.L = graphics.getFontMetrics();
            this.void = this.L.charWidth(' ');
            if (JGrid.e.d <= 0) {
                JGrid.e.d = this.L.getHeight() + 4;
            }
            this.null = JGrid.e.d;
            if (this.i / JGrid.e.d > this.a / 2 && this.q > this.a) {
                this.a = (this.i / JGrid.e.d + 1) * 2;
                if (this.a > this.q) {
                    this.a = this.q;
                }
                this.r = new f[this.a];
                this.goto = null;
            }
            this.e = (this.i - this.D) / JGrid.e.d + 1;
            this.k = this.q * JGrid.e.d + this.D;
            this.f = new int[this.F];
            for (int i = 0; i < this.F; ++i) {
                this.f[i] = i;
            }
            this.do = true;
            this.if();
            this.a();
            this.B.requestFocus();
        }
        this.m = this.G * this.void;
        this.m = ((this.J + this.m - this.K > 0) ? (this.K - this.J) : this.m);
        this.m = ((this.m < 0) ? 0 : this.m);
        this.long = this.H * this.null;
        this.long = ((this.i - this.k > -this.long) ? (this.k - this.i) : this.long);
        this.long = ((this.long < 0) ? 0 : this.long);
        final int n = this.else - this.m;
        int n2 = 0;
        final int n3 = this.D + 1;
        if (this.goto == null) {
            this.do();
        }
        if (this.h == null) {
            this.h = this.createImage(this.K, n3);
            new b(this.l, this.f).a(this.h.getGraphics(), new Rectangle(n2, 0, this.K, n3), this);
        }
        graphics.drawImage(this.h, n, this.try, this);
        final int n4 = this.else + this.K - this.m;
        graphics.setColor(Color.white);
        graphics.fillRect(n4, this.try, this.J - n4, this.D + 1);
        if (this.if) {
            n2 += this.j;
            if (this.z == null) {
                this.a(graphics);
            }
            graphics.drawImage(this.z, this.else, this.try, this);
        }
        if (this.n && this.t) {
            graphics.setColor(new Color(0, 0, 128));
            this.byte = this.F;
            final int n5 = this.o - this.c;
            if (n5 < this.int[0]) {
                this.byte = 0;
            }
            else {
                for (int j = 0; j < this.F - 1; ++j) {
                    if (n5 >= this.int[j] && n5 <= this.int[j] + this.E[j]) {
                        this.byte = j + 1;
                        break;
                    }
                }
            }
            if (this.byte < this.F) {
                graphics.drawRect(this.int[this.byte] - this.m - 1, this.try, 2, this.D);
            }
            else {
                graphics.drawRect(this.int[this.F - 1] + this.E[this.F - 1] - this.m - 1, this.try, 2, this.D);
            }
            graphics.setColor(Color.gray);
            graphics.drawRect(this.o - this.c - this.m, 2, this.E[this.w], this.D - 4);
            final String s = this.l[this.f[this.w]];
            final int n6 = (this.E[this.w] - 8) / this.void;
            final int n7 = (n6 > s.length()) ? s.length() : n6;
            graphics.drawString(s.substring(0, (n7 < 0) ? 0 : n7), n5 - this.m + 4, (JGrid.e.d + this.L.getHeight()) / 2 - this.L.getDescent() + 3);
        }
        if (this.C && this.case) {
            graphics.setColor(new Color(0, 0, 128));
            graphics.drawLine(this.o - this.m, 0, this.o - this.m, this.D);
        }
        final int n8 = -(this.H - this.I) * JGrid.e.d;
        graphics.clipRect(n2, n3, this.J - n2, this.i - n3);
        graphics.drawImage(this.goto, n, n8 + n3, this);
        graphics.setColor(Color.white);
        graphics.fillRect(n4, this.D, this.J - n4, this.i);
        final int n9 = this.a * JGrid.e.d + n8 + n3;
        graphics.fillRect(this.else, n9, this.K - this.m, this.i - n9);
        if (this.C && this.case) {
            graphics.setColor(new Color(0, 0, 128));
            graphics.drawLine(this.o - this.m, 0, this.o - this.m, this.i);
        }
    }
    
    private void do() {
        if (this.goto == null) {
            if (this.new != null) {
                this.new = null;
                System.gc();
            }
            this.goto = this.createImage(this.K, this.a * JGrid.e.d);
            this.new = this.goto.getGraphics();
        }
        for (int i = 0; i < this.a; ++i) {
            try {
                final Graphics create = this.new.create(0, i * JGrid.e.d, this.K, JGrid.e.d);
                final int n = this.char[i + this.I];
                this.r[i] = new f(this.p[n], this.f, n, this.A);
                if (i + this.I == this.s) {
                    this.r[i].a(true);
                }
                this.r[i].a(create, this);
                create.dispose();
            }
            catch (Exception ex) {}
        }
        this.new.setColor(Color.lightGray);
        this.new.setFont(new Font("Dialog", 2, 28));
        this.new.drawString("Thank you for trying NetTable.", 100, 260);
    }
    
    private void a(final Graphics graphics) {
        this.z = this.createImage(this.j, this.i);
        final Graphics graphics2 = this.z.getGraphics();
        graphics2.setColor(Color.black);
        graphics2.drawLine(this.else, this.try, this.else, this.i);
        graphics2.drawLine(this.else + this.j - 1, this.try, this.else + this.j - 1, this.i);
        int try1 = this.try;
        for (int i = 0; i <= this.e; ++i) {
            final int n = (i == 0) ? this.D : JGrid.e.d;
            graphics2.setColor(Color.lightGray);
            graphics2.fillRect(this.else + 1, try1, this.j, n);
            graphics2.setColor(Color.white);
            graphics2.drawLine(this.else + 2, try1, this.else + 2, try1 + n);
            graphics2.drawLine(this.else + 1, try1 + 1, this.else + this.j, try1 + 1);
            graphics2.setColor(Color.black);
            graphics2.drawLine(this.j - 1, try1, this.j - 1, try1 + n);
            graphics2.drawLine(this.else, try1, this.j - 1, try1);
            try1 += n;
        }
    }
    
    protected void if() {
        if (this.do) {
            final int n = this.J / this.void;
            this.M.setValues(this.G, n, 0, this.K / this.void);
            this.M.setPageIncrement((int)(n * 0.9));
        }
    }
    
    protected void a() {
        if (this.do) {
            final int n = (this.i - this.D) / this.null;
            this.b.setValues(this.H, n, 0, this.q);
            this.b.setPageIncrement((int)(n * 0.9));
        }
    }
    
    public void reshape(final int n, final int n2, final int n3, final int n4) {
        super.reshape(n, n2, n3, n4);
        final Insets insets = this.insets();
        this.else = insets.left;
        this.try = insets.top;
        final int height = this.M.minimumSize().height;
        final int width = this.b.minimumSize().width;
        this.J = n3 - (insets.left + insets.right) - width - 1;
        this.i = n4 - (insets.top + insets.bottom) - height - 1;
        if (this.do) {
            this.e = (this.i - this.D) / JGrid.e.d + 1;
            if (this.i / JGrid.e.d > this.a / 2 && this.q > this.a) {
                this.a = (this.i / JGrid.e.d + 1) * 2;
                if (this.a > this.q) {
                    this.a = this.q;
                }
                this.r = new f[this.a];
                this.goto = null;
            }
        }
        this.b.reshape(this.J + insets.left + 1, this.try, width, this.i);
        this.M.reshape(this.else, this.i + insets.top + 1, this.J, height);
        this.z = null;
        this.if();
        this.a();
        this.for();
        this.repaint();
    }
    
    public boolean keyDown(final Event event, final int n) {
        int value = 0;
        switch (n) {
            case 1002: {
                value = ((this.s - this.e + 2 >= 0) ? (this.s - this.e + 2) : 0);
                break;
            }
            case 1003: {
                value = ((this.s + this.e - 2 >= this.q) ? (this.q - 1) : (this.s + this.e - 2));
                break;
            }
            case 1004: {
                value = ((this.s == 0) ? 0 : (this.s - 1));
                break;
            }
            case 1005: {
                value = ((this.s == this.q - 1) ? this.s : (this.s + 1));
                break;
            }
        }
        if (n > 1001 && n < 1006) {
            if (value < this.H) {
                this.b.setValue(value);
            }
            else if (value >= this.H + this.e - 1) {
                this.b.setValue(value - this.e + 2);
            }
            this.postEvent(new Event(this.b, 0, null));
            this.a(value);
        }
        return false;
    }
    
    public boolean handleEvent(final Event event) {
        if (event.target == this.M) {
            this.G = this.M.getValue();
            this.repaint();
            return true;
        }
        if (event.target != this.b) {
            return super.handleEvent(event);
        }
        if (this.H == this.b.getValue()) {
            return true;
        }
        this.for();
        this.repaint();
        return true;
    }
    
    private void for() {
        this.H = this.b.getValue();
        final int n = this.H / this.b.getMaximum() * this.q;
        if (n < this.I && this.I != 0) {
            this.I = ((n - this.a / 2 >= 0) ? (n - this.a / 2) : 0);
            this.do();
            return;
        }
        if (n + this.e > this.I + this.a) {
            this.I = ((n + this.e > this.q - this.a / 2) ? (this.q - this.a) : (n + this.e - this.a / 2));
            this.do();
        }
    }
    
    private void if(final int cursor) {
        Container container;
        for (container = this.getParent(); container != null && !(container instanceof Frame); container = ((Frame)container).getParent()) {}
        if (container != null) {
            ((Frame)container).setCursor(cursor);
        }
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        this.case = true;
        this.t = false;
        if (n2 <= this.D) {
            for (int i = this.F - 1; i >= 0; --i) {
                if (n + this.m > this.int[i] + 1 && n + this.m < this.int[i] + this.E[i] - 1) {
                    this.t = true;
                    this.n = false;
                    this.w = i;
                    this.byte = i;
                    this.c = n + this.m - this.int[this.w];
                }
            }
        }
        return true;
    }
    
    public boolean mouseDrag(final Event event, int o, final int n) {
        o += this.m;
        this.o = o;
        if (this.case && this.C) {
            this.N = ((o - this.int[this.w] > 1) ? (o - this.int[this.w]) : 2);
            this.repaint();
        }
        if (this.case && this.t) {
            this.n = true;
            this.repaint();
        }
        return true;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        this.C = false;
        this.if(0);
        if (n2 <= this.D) {
            for (int i = 0; i < this.F; ++i) {
                if (Math.abs(n + this.m - (this.E[i] + this.int[i])) <= 1) {
                    this.if(11);
                    this.C = true;
                    this.w = i;
                    break;
                }
            }
        }
        else {
            final Point if1 = this.if(n, n2);
            if (if1 != null) {
                final f f = this.r[if1.y - this.I];
                if (f == null) {
                    return false;
                }
                if (f.else[if1.x].a != null) {
                    this.if(12);
                }
            }
        }
        return false;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.t) {
            this.t = false;
            if (this.n) {
                this.a(this.w, this.byte);
            }
            else {
                final Point if1 = this.if(n, n2);
                if (if1 != null) {
                    final int b = JGrid.b.b;
                    if (if1.x == b) {
                        this.a(!JGrid.b.c, b);
                    }
                    else {
                        this.a(true, if1.x);
                    }
                }
            }
        }
        else if (this.C) {
            this.E[this.w] = this.N;
            this.h = null;
            this.a(this.E);
            this.goto = null;
            this.repaint();
        }
        else {
            final int n3 = (n2 - this.D) / JGrid.e.d + this.H;
            if (n3 < this.q) {
                this.a(n3);
                if (this.v != null && n >= this.j) {
                    final Point if2 = this.if(n, n2);
                    if (if2 != null) {
                        this.v.clickedCell(if2.y, if2.x, this);
                    }
                }
            }
        }
        this.case = false;
        return true;
    }
    
    private void a(final int s) {
        if (this.s < this.I + this.a && this.s >= this.I) {
            this.r[this.s - this.I].a(false);
        }
        final int s2 = this.s;
        this.s = s;
        this.r[this.s - this.I].a(true);
        for (int i = 0; i < this.a; ++i) {
            if (i == this.s - this.I || i == s2 - this.I) {
                final Graphics create = this.new.create(0, i * JGrid.e.d, this.K, JGrid.e.d);
                this.r[i].a(create, this);
                create.dispose();
            }
        }
        this.repaint();
    }
    
    private Point if(final int n, final int n2) {
        int n3;
        for (n3 = this.F - 1; n3 >= 0 && (n + this.m < this.int[n3] || n + this.m >= this.int[n3] + this.E[n3]); --n3) {}
        if (n3 < 0 || JGrid.e.d == 0) {
            return null;
        }
        final int n4 = (n2 - this.D) / JGrid.e.d + this.H;
        if (n4 >= this.q || n4 < this.H) {
            return null;
        }
        return new Point(n3, n4);
    }
    
    public d do(final int n, final int n2) {
        return this.r[n - this.I].else[n2];
    }
    
    public void a(final a v) {
        this.v = v;
    }
    
    public void a(final boolean c, final int b) {
        b.c = c;
        b.b = b;
        this.h = null;
        try {
            this.g = false;
            new Float(this.p[0][this.f[b.b]].toString());
        }
        catch (Exception ex) {
            this.g = true;
        }
        final int n = (this.s < 0) ? -1 : this.char[this.s];
        this.a(this.char, 0, this.q - 1);
        if (!c) {
            for (int i = 0; i < this.q / 2; ++i) {
                final int n2 = this.char[i];
                this.char[i] = this.char[this.q - i - 1];
                this.char[this.q - i - 1] = n2;
            }
        }
        for (int j = 0; j < this.q; ++j) {
            if (n == this.char[j]) {
                this.s = j;
                break;
            }
        }
        this.do();
        this.repaint();
    }
    
    void a(final int[] array, final int n, final int n2) {
        int i = n;
        int n3 = n2;
        final int n4 = this.f[JGrid.b.b];
        if (i >= n3) {
            return;
        }
        if (i == n3 - 1) {
            final String string = this.p[array[i]][n4].toString();
            final String string2 = this.p[array[n3]][n4].toString();
            if ((this.g && new Float(string) > new Float(string2)) || (this.g && string.compareTo(string2) > 0)) {
                final int n5 = array[i];
                array[i] = array[n3];
                array[n3] = n5;
            }
            return;
        }
        final int n6 = array[(i + n3) / 2];
        array[(i + n3) / 2] = array[n3];
        array[n3] = n6;
        while (i < n3) {
            for (String s = this.p[array[i]][n4].toString(), string3 = this.p[n6][n4].toString(); ((!this.g && new Float(s) <= new Float(string3)) || (this.g && s.compareTo(string3) <= 0)) && i < n3; ++i, s = this.p[array[i]][n4].toString()) {}
            for (String string4 = this.p[n6][n4].toString(), s2 = this.p[array[n3]][n4].toString(); ((!this.g && new Float(string4) <= new Float(s2)) || (this.g && string4.compareTo(s2) <= 0)) && i < n3; --n3, s2 = this.p[array[n3]][n4].toString()) {}
            if (i < n3) {
                final int n7 = array[i];
                array[i] = array[n3];
                array[n3] = n7;
            }
        }
        array[n2] = array[n3];
        array[n3] = n6;
        this.a(array, n, i - 1);
        this.a(array, n3 + 1, n2);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void a(final String s, final String s2) {
        if (this.A.get(s) == null) {
            this.A.put(s, s2);
        }
    }
    
    static {
        e.d = -1;
    }
}
