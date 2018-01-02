// 
// Decompiled by Procyon v0.5.30
// 

package JAVACharter.ChartModules;

import JAVACharter.b.h;
import java.awt.Color;
import java.awt.Graphics;
import java.text.DecimalFormat;
import java.util.Hashtable;
import java.util.Vector;
import JAVACharter.StyleManage.c;
import JAVACharter.c.e;
import JAVACharter.c.g;
import JAVACharter.c.j;
import java.awt.Rectangle;
import JAVACharter.a.f;

public abstract class b
{
    public static String g;
    public static String l;
    public static String a;
    public static String do;
    protected f d;
    protected Rectangle if;
    protected j try;
    protected g j;
    protected e void;
    protected c o;
    protected String p;
    protected String[] m;
    protected JAVACharter.b.e i;
    protected JAVACharter.b.e[] null;
    protected float[] for;
    protected int k;
    protected int n;
    protected long s;
    protected float long;
    protected int c;
    protected int case;
    protected int b;
    protected int h;
    protected int f;
    protected Vector r;
    protected Hashtable new;
    protected boolean int;
    protected String byte;
    protected String[] q;
    protected JAVACharter.b.e e;
    protected JAVACharter.b.e[] else;
    protected String char;
    protected DecimalFormat goto;
    
    public abstract void paintData(final Graphics p0, final JAVACharter.c.b p1);
    
    public abstract void calc();
    
    public abstract void calc_draw();
    
    public abstract boolean isOverlay();
    
    public abstract String getName();
    
    public b(final f d, final Rectangle if1, final j try1, final g j, final e void1) {
        this.k = 0;
        this.n = 5;
        this.s = Long.MAX_VALUE;
        this.long = 9.223372E18f;
        this.c = -1;
        this.case = 0;
        this.b = 0;
        this.h = 0;
        this.f = 0;
        this.new = new Hashtable();
        this.int = true;
        this.d = d;
        this.if = if1;
        this.try = try1;
        this.j = j;
        this.void = void1;
        this.char = String.valueOf(void1.n);
        d.a(this.n);
    }
    
    public g a() {
        return this.j;
    }
    
    public void a(final boolean b) {
        this.try.a(b);
    }
    
    public void a(final Vector r) {
        this.r = r;
    }
    
    public void if() {
        this.o = this.void.a();
        this.d.a(this.d.d());
        final int length = this.m.length;
        this.i = this.d.else();
        for (int i = 0; i < length; ++i) {
            this.null[i] = this.d.goto(this.m[i]);
        }
        this.d.i();
        this.k = ((JAVACharter.b.f)this.i).a();
        this.do();
        this.try.a("###0.###");
        this.calc();
    }
    
    public void do() {
        if (this.q != null) {
            if (this.q.length > 0 && this.d.try()) {
                String do1;
                if (this.new.containsKey(this.d.d())) {
                    do1 = this.new.get(this.d.d());
                }
                else {
                    do1 = this.d.do(this.d.d());
                    this.new.put(this.d.d(), do1);
                }
                this.d.a(this.d.d(), do1, 1);
                this.d.a(do1);
                final int length = this.q.length;
                this.e = this.d.else();
                for (int i = 0; i < length; ++i) {
                    this.else[i] = this.d.goto(this.q[i]);
                }
                this.d.i();
                ((JAVACharter.b.f)this.e).a();
            }
            else if (!this.d.try()) {
                this.int = false;
            }
        }
    }
    
    public void mouseMove(final Graphics graphics, final int n, final int n2, final JAVACharter.c.c c, final boolean b) {
        if (n >= this.if.x && n <= this.if.x + this.if.width) {
            this.goto = this.try.new();
            final int if1 = this.j.if(n);
            if (if1 < this.k && if1 >= 0) {
                final float n3 = this.for[if1];
                this.b = this.try.do(n3);
                final Color color = graphics.getColor();
                graphics.setColor(this.o.a("mouseline", 0));
                graphics.drawLine(this.j.a(if1), this.if.y, this.j.a(if1), this.if.y + this.if.height);
                graphics.setColor(color);
                if (n3 != this.long && n3 != this.s) {
                    c.a(this.char + "field0", this.a(n3));
                }
            }
            this.c = if1;
        }
    }
    
    public String a(final float n) {
        float n2;
        String s;
        if (n >= 0.0f) {
            if (n >= 1.0E12f) {
                n2 = 1.0E12f;
                s = " T";
            }
            else if (n >= 1.0E9f) {
                n2 = 1.0E9f;
                s = " B";
            }
            else {
                n2 = 1.0f;
                s = "";
            }
        }
        else if (n <= -1.0E12f) {
            n2 = 1.0E12f;
            s = " T";
        }
        else if (n <= -1.0E9f) {
            n2 = 1.0E9f;
            s = " B";
        }
        else {
            n2 = 1.0f;
            s = "";
        }
        this.goto = new DecimalFormat("###,###,###.###");
        String string;
        if (n == 0.0f) {
            string = "0";
        }
        else {
            string = this.goto.format(n / n2) + s;
        }
        return string;
    }
    
    public void a(final int n, final int n2, final h h, final float[] array) {
        int n3 = n - 1;
        final int n4 = n * -1;
        float n5 = h.for(n3);
        ++n3;
        while (n5 == this.long && n3 <= n2) {
            array[n3 - n4] = this.long;
            n5 = h.for(n3);
            ++n3;
        }
        for (int i = n3; i <= n2; ++i) {
            final float for1 = h.for(i);
            if (for1 != this.long) {
                array[i - n4] = (for1 - n5) / n5 * 100.0f;
            }
            else {
                array[i - n4] = this.long;
            }
        }
    }
    
    public void a(int n, final int n2, final float[] array, final float[] array2) {
        float n3;
        for (n3 = array[n]; n3 == this.long; n3 = array[n]) {
            array2[n] = this.long;
            ++n;
        }
        array2[n] = 0.0f;
        for (int i = ++n; i <= n2; ++i) {
            final float n4 = array[i];
            if (n4 != this.long) {
                array2[i] = (n4 - n3) / n3 * 100.0f;
            }
            else {
                array2[i] = this.long;
            }
        }
    }
    
    public void a(final int n, final int n2, final int n3, final float[] array, final float[] array2) {
        int n4 = 0;
        if (array.length != array2.length) {
            n4 = n * -1;
        }
        for (int i = n; i <= n2; ++i) {
            if (array[i] != this.long) {
                float n5 = 0.0f;
                int j = 0;
                int n6 = 0;
                while (j < n3) {
                    if (array[i - j] != this.long) {
                        n5 += array[i - j];
                        ++n6;
                    }
                    ++j;
                }
                if (n6 == n3) {
                    array2[i + n4] = n5 / n3;
                }
                else {
                    array2[i + n4] = this.long;
                }
            }
            else {
                array2[i + n4] = this.long;
            }
        }
    }
    
    public void a(final int n, final int n2, final int n3, final h h, final float[] array) {
        final float n4 = 2.0f / (1.0f + n3);
        int n5 = n;
        final int n6 = n5 * -1;
        for (int i = n5; i <= n2; ++i) {
            if (h.for(i) != this.long) {
                int n7 = 0;
                for (int j = 0; j < n3; ++j) {
                    if (h.for(i - j) != this.long) {
                        ++n7;
                    }
                }
                if (n7 == n3) {
                    n5 = i;
                    break;
                }
                array[i + n6] = this.long;
            }
            else {
                array[i + n6] = this.long;
            }
        }
        float n8 = 0.0f;
        for (int k = 0; k < n3; ++k) {
            n8 += h.for(n5 - k);
        }
        final float n9 = n8 / n3;
        if (h.for(n5) != this.long && n9 != this.long && n4 != this.long) {
            array[n5 + n6] = (h.for(n5) - n9) * n4 + n9;
        }
        else {
            array[n5 + n6] = this.long;
        }
        for (int l = n5 + 1; l <= n2; ++l) {
            if (h.for(l) != this.long && array[l + n6 - 1] != this.long && n4 != this.long) {
                array[l + n6] = (h.for(l) - array[l + n6 - 1]) * n4 + array[l + n6 - 1];
            }
            else {
                array[l + n6] = this.long;
            }
        }
    }
    
    public void a(final int n, final int n2, final long n3, final h h, final float[] array) {
        for (int i = n; i <= n2; ++i) {
            float n4 = 0.0f;
            if (h.for(i) != this.long) {
                int n5 = 0;
                int n6 = 0;
                while (n5 < n3) {
                    if (h.for(i - n5) != this.long) {
                        n4 += h.for(i - n5);
                        ++n6;
                    }
                    ++n5;
                }
                if (n6 == n3) {
                    array[i] = n4 / n3;
                }
                else {
                    array[i] = this.long;
                }
            }
            else {
                array[i] = this.long;
            }
        }
    }
    
    static {
        b.g = "";
        b.l = "";
        b.a = "";
        b.do = "";
    }
}
