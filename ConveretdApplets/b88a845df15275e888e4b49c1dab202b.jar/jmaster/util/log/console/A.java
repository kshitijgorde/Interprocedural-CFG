// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.log.console;

import java.io.PrintStream;
import jmaster.util.log.D;
import java.util.Date;
import java.text.SimpleDateFormat;
import jmaster.util.log.F;

public class A extends F implements jmaster.util.log.A
{
    protected int C;
    protected SimpleDateFormat B;
    
    public A() {
        this.C = 2;
        this.B = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
    }
    
    public A(final int n) {
        this.C = 2;
        this.B = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        this.A(n);
    }
    
    public int C() {
        return this.C;
    }
    
    public void A(final int c) {
        this.C = c;
    }
    
    protected String D() {
        String format = null;
        if (this.B != null) {
            format = this.B.format(new Date());
        }
        return format;
    }
    
    protected synchronized void B(final int n, final Object o, final Throwable t) {
        if (this.C <= n) {
            final PrintStream out = System.out;
            synchronized (out) {
                if (o != null) {
                    out.print(this.D());
                    out.print(" - ");
                    out.print(D.A(n));
                    out.print(" - ");
                    out.print(Thread.currentThread().getName());
                    out.print(" - ");
                    out.println(o);
                }
                if (t != null) {
                    out.print(this.D());
                    out.print(" - ");
                    out.print(D.A(n));
                    out.print(" - ");
                    out.print(Thread.currentThread().getName());
                    out.print(" - ");
                    t.printStackTrace(out);
                }
            }
        }
    }
    
    public boolean B() {
        return this.C <= 1;
    }
    
    public boolean A() {
        return this.C <= 0;
    }
    
    public void D(final Object o) {
        if (this.B()) {
            super.D(o);
            this.B(1, o, null);
        }
    }
    
    public void C(final Object o, final Throwable t) {
        if (this.B()) {
            super.C(o, t);
            this.B(1, o, t);
        }
    }
    
    public void E(final Object o) {
        super.E(o);
        this.B(3, o, null);
    }
    
    public void E(final Object o, final Throwable t) {
        super.E(o, t);
        this.B(3, o, t);
    }
    
    public void A(final Object o) {
        super.A(o);
        this.B(4, o, null);
    }
    
    public void D(final Object o, final Throwable t) {
        super.D(o, t);
        this.B(1, o, t);
    }
    
    public void B(final Object o) {
        if (this.A()) {
            super.B(o);
            this.B(0, o, null);
        }
    }
    
    public void B(final Object o, final Throwable t) {
        if (this.A()) {
            super.B(o, t);
            this.B(0, o, t);
        }
    }
    
    public void C(final Object o) {
        super.C(o);
        this.B(2, o, null);
    }
    
    public void A(final Object o, final Throwable t) {
        super.A(o, t);
        this.B(2, o, t);
    }
}
