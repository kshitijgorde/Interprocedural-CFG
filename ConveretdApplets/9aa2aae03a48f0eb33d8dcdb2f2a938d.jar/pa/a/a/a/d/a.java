// 
// Decompiled by Procyon v0.5.30
// 

package pa.a.a.a.d;

import java.awt.LayoutManager;
import java.awt.Component;
import java.net.URL;
import java.applet.AppletContext;

public class a
{
    public static final int if = 0;
    public static final int for = 1;
    public static final int b = 3;
    public static final int new = 2;
    public static final int byte = 0;
    public static final int else = 1;
    public static final int case = 2;
    public static final int goto = 3;
    public static final int char = 4;
    public int a;
    public static final int int = 0;
    public static final int do = 1;
    public static final int try = 2;
    private AppletContext e;
    private pa.a.a.a.a.a long;
    private d d;
    private g c;
    private e null;
    private int void;
    
    public a(final pa.a.a.a.a.a long1, final int n, final AppletContext e, final int n2, final URL url, final String s, final boolean b, final boolean b2) {
        this.a = 6;
        this.d = null;
        this.c = null;
        this.null = null;
        this.void = 0;
        if (b2) {
            this.a = 5;
        }
        else {
            this.a = 6;
        }
        this.long = long1;
        this.e = e;
        if (s != null && s.equalsIgnoreCase("none")) {
            return;
        }
        if (b2) {
            this.void = 2;
        }
        this.c = new g(this, b, b2);
        long1.add(this.d = new d(this.long));
        this.d.reshape(0, n - this.d.for, this.d.a, this.d.for);
        this.c.setLayout(null);
        long1.add(this.c);
        this.c.reshape(this.d.a, n - 24, 24 * this.a, 24);
        this.c.if();
        final int n3 = long1.size().width - this.d.a - 24 * this.a;
        long1.add(this.null = new e(n3));
        this.null.reshape(this.d.a + 24 * this.a, n - 24, n3, 24);
    }
    
    void a(final int n, final int n2) {
        switch (n) {
            case 0: {
                if (n2 == 2) {
                    this.long.byte();
                    break;
                }
                break;
            }
            case 1: {
                if (n2 == 3) {
                    this.long.if();
                    break;
                }
                if (n2 == 4) {
                    this.long.char();
                    break;
                }
                break;
            }
            case 2: {
                if (n2 == 3) {
                    this.long.case();
                    break;
                }
                if (n2 == 4) {
                    this.long.try();
                    break;
                }
                break;
            }
        }
    }
    
    public void a(final int n) {
        if (this.c != null) {
            this.c.a(n);
        }
    }
    
    public void if(final int n) {
        if (this.c != null) {
            this.c.if(n);
        }
    }
    
    public int do() {
        return this.void;
    }
    
    public boolean if() {
        return this.c != null && (this.c.isShowing() ^ true);
    }
    
    public boolean for() {
        return this.d != null && this.d.a();
    }
    
    public void a() {
        if (this.c != null) {
            this.c.show();
        }
    }
    
    public void new() {
        this.void = 0;
        if (this.c.a) {
            this.void = 2;
        }
        if (this.c != null) {
            this.c.for();
        }
    }
    
    void do(final int void1) {
        this.void = void1;
        this.long.a(void1);
    }
    
    public void if(final int n, final int n2) {
        if (this.c != null) {
            this.c.if(n, n2);
        }
    }
    
    void a(final String s) {
        this.e.showStatus(s);
    }
    
    public void int() {
        this.e = null;
        if (this.c != null) {
            this.c.do();
            this.c = null;
        }
        if (this.d != null) {
            this.d.if();
            this.d = null;
        }
        this.long = null;
    }
}
