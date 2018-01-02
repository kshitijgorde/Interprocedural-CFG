// 
// Decompiled by Procyon v0.5.30
// 

package zp.a.a.a.c;

import java.awt.LayoutManager;
import java.awt.Component;
import java.net.URL;
import java.applet.AppletContext;

public class a
{
    public static final int c = 0;
    public static final int byte = 1;
    public static final int goto = 3;
    public static final int void = 2;
    public static final int null = 0;
    public static final int b = 1;
    public static final int case = 2;
    public static final int try = 3;
    public static final int if = 4;
    public int d;
    public static final int e = 0;
    public static final int for = 1;
    public static final int new = 2;
    private AppletContext char;
    private zp.a.a.a.b.a do;
    private f a;
    private g else;
    private d long;
    private int int;
    
    public a(final zp.a.a.a.b.a do1, final int n, final AppletContext char1, final int n2, final URL url, final String s, final boolean b, final boolean b2, final boolean b3) {
        this.d = 1;
        this.a = null;
        this.else = null;
        this.long = null;
        this.int = 0;
        this.d = 1;
        if (b2) {
            this.d = 1;
        }
        if (!b2 && !b && !b3) {
            this.d = 1;
        }
        this.do = do1;
        this.char = char1;
        if (s != null && s.equalsIgnoreCase("none")) {
            return;
        }
        if (b2) {
            this.int = 2;
        }
        this.else = new g(this, b, b2, b3);
        do1.add(this.a = new f(this.do));
        this.a.reshape(0, n - this.a.for, this.a.do, this.a.for);
        this.else.setLayout(null);
        do1.add(this.else);
        this.else.reshape(this.a.do, n - 24, 70 * this.d, 24);
        this.else.a();
        final int n3 = do1.size().width - this.a.do - 70 * this.d;
        do1.add(this.long = new d(n3));
        this.long.reshape(this.a.do + 70 * this.d, n - 24, n3, 24);
    }
    
    public void do() {
        this.char = null;
        if (this.else != null) {
            this.else.if();
            this.else = null;
        }
        if (this.a != null) {
            this.a.a();
            this.a = null;
        }
        this.do = null;
    }
    
    public boolean if() {
        return this.else != null && !this.else.isShowing();
    }
    
    public boolean int() {
        return this.a != null && this.a.if();
    }
    
    public int for() {
        return this.int;
    }
    
    public void new() {
        this.int = 0;
        if (this.else.if) {
            this.int = 2;
        }
        if (this.else != null) {
            this.else.for();
        }
    }
    
    public void a() {
        if (this.else != null) {
            this.else.show();
        }
    }
    
    public void if(final int n) {
        if (this.else != null) {
            this.else.do(n);
        }
    }
    
    public void if(final int n, final int n2) {
        if (this.else != null) {
            this.else.if(n, n2);
        }
    }
    
    public void do(final int n) {
        if (this.else != null) {
            this.else.for(n);
        }
    }
    
    void a(final int int1) {
        this.int = int1;
        this.do.if(int1);
    }
    
    void a(final int n, final int n2) {
        switch (n) {
            case 0: {
                if (n2 == 2) {
                    this.do.new();
                }
                this.do.requestFocus();
                break;
            }
            case 1: {
                if (n2 == 3) {
                    this.do.for();
                    break;
                }
                if (n2 == 4) {
                    this.do.if();
                    break;
                }
                break;
            }
            case 2: {
                if (n2 == 3) {
                    this.do.byte();
                    break;
                }
                if (n2 == 4) {
                    this.do.long();
                    break;
                }
                break;
            }
            case 3: {
                this.do.try();
                this.do.requestFocus();
                break;
            }
        }
    }
    
    void a(final String s) {
        this.char.showStatus(s);
    }
}
