// 
// Decompiled by Procyon v0.5.30
// 

package org.a.c;

import java.io.PrintStream;

public class e implements f
{
    private PrintStream a;
    private int b;
    
    public e() {
        this(Integer.MAX_VALUE, null);
    }
    
    public e(final int n, final PrintStream printStream) {
        this.a(printStream);
        this.a(n);
    }
    
    public void a(final Object o) {
        if (this.b <= 10000) {
            this.a(o.toString());
        }
    }
    
    public void a(final Object o, final Throwable t) {
        if (this.b <= 10000) {
            this.a(o.toString());
            this.a(t);
        }
    }
    
    public void b(final Object o, final Throwable t) {
        if (this.b <= 20000) {
            this.a(o.toString());
            this.a(t);
        }
    }
    
    public void b(final Object o) {
        if (this.b <= 30000) {
            this.a(o.toString());
        }
    }
    
    public void c(final Object o, final Throwable t) {
        if (this.b <= 30000) {
            this.a(o.toString());
            this.a(t);
        }
    }
    
    public void c(final Object o) {
        if (this.b <= 40000) {
            this.a(o.toString());
        }
    }
    
    public void d(final Object o, final Throwable t) {
        if (this.b <= 40000) {
            this.a(o.toString());
            this.a(t);
        }
    }
    
    public void d(final Object o) {
        if (this.b <= 50000) {
            this.a(o.toString());
        }
    }
    
    public void e(final Object o, final Throwable t) {
        if (this.b <= 50000) {
            this.a(o.toString());
            this.a(t);
        }
    }
    
    public void a(final int b) {
        this.b = b;
    }
    
    protected void a(final String s) {
        if (this.a != null) {
            this.a.println(s);
        }
    }
    
    protected void a(final Throwable t) {
        if (this.a != null) {
            t.printStackTrace(this.a);
        }
    }
    
    public void a() {
        if (this.a != null) {
            this.a.flush();
        }
    }
    
    public void finalize() throws Throwable {
        this.a();
        super.finalize();
    }
    
    public void a(final PrintStream a) {
        this.a = a;
    }
}
