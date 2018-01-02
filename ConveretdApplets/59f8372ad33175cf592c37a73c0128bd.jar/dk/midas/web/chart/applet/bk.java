// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

import java.util.Vector;

public class bk
{
    public static final Float do;
    private Vector if;
    private bk for;
    private bk a;
    private bk int;
    
    public bk() {
        this.if = new Vector();
    }
    
    public bk(final int n) {
        this.if = new Vector(n);
    }
    
    public bk(final int n, final int n2) {
        this.if = new Vector(n, n2);
    }
    
    public synchronized float if(final int n) {
        if (n >= this.if.size()) {
            System.out.println("Problem !!! index = " + n + " size = " + this.if.size());
        }
        return this.if.elementAt(n);
    }
    
    public synchronized void a(final float n) {
        this.if.addElement(new Float(n));
        this.if();
    }
    
    public synchronized void a(final float[] array) {
        for (int i = 0; i < array.length; ++i) {
            this.if.addElement(new Float(array[i]));
        }
        this.if();
    }
    
    public synchronized void a(final double[] array) {
        for (int i = 0; i < array.length; ++i) {
            this.if.addElement(this.a(array[i]));
        }
        this.if();
    }
    
    public synchronized void a(final int n, final double[] array, final int n2) {
        if (n > 0 && array != null && array.length > 0) {
            final int case1 = this.case();
            final int min = Math.min(n, array.length);
            this.a(n2);
            for (int i = 0; i < min; ++i) {
                this.if.setElementAt(this.a(array[i]), case1 - min + i);
            }
        }
        this.if();
    }
    
    public synchronized void if(final double[] array) {
        if (array != null && array.length > 0) {
            this.if.setElementAt(this.a(array[0]), this.if.size() - 1);
        }
        this.if();
    }
    
    public synchronized void new() {
        this.if.removeAllElements();
        this.if();
    }
    
    public synchronized void a(final float n, final int n2) {
        this.if.setElementAt(new Float(n), n2);
    }
    
    public synchronized int case() {
        return this.if.size();
    }
    
    public synchronized int try() {
        return this.if.capacity();
    }
    
    public synchronized float a(final int n, final int n2) {
        if (n >= this.if.size()) {
            return 0.0f;
        }
        float n3 = this.if(n, n2);
        int n4 = 1;
        for (int i = n; i <= n2; ++i) {
            final float if1 = this.if(i);
            if (if1 != Float.MIN_VALUE) {
                if (n4 != 0) {
                    n4 = 0;
                    n3 = if1;
                }
                else {
                    n3 = Math.min(n3, if1);
                }
            }
        }
        return n3;
    }
    
    public synchronized float if(final int n, final int n2) {
        if (n >= this.if.size()) {
            return 0.0f;
        }
        float n3 = this.if(n);
        for (int i = n + 1; i <= n2; ++i) {
            n3 = Math.max(n3, this.if(i));
        }
        return n3;
    }
    
    public synchronized float else() {
        return this.if(this.for());
    }
    
    public synchronized float char() {
        return this.if(this.byte());
    }
    
    public synchronized bk a() {
        if (this.for == null) {
            (this.for = new bk(this.if.size())).do(this);
        }
        return this.for;
    }
    
    public synchronized bk do() {
        if (this.a == null) {
            (this.a = new bk(this.if.size())).do(this);
        }
        return this.a;
    }
    
    public synchronized bk int() {
        if (this.int == null) {
            (this.int = new bk(this.if.size())).a(this);
        }
        return this.int;
    }
    
    protected synchronized void if() {
        if (this.for != null) {
            this.for.do(this);
        }
        if (this.a != null) {
            this.a.if(this);
        }
        if (this.int != null) {
            this.int.a(this);
        }
    }
    
    private synchronized void do(final bk bk) {
        this.new();
        if (bk.case() > 0) {
            final int for1 = bk.for();
            final int byte1 = bk.byte();
            final Float n = new Float(bk.if(for1));
            final Float n2 = new Float(bk.if(byte1));
            for (int i = 0; i < for1; ++i) {
                this.if.addElement(n);
            }
            float floatValue = n;
            int n3 = 0;
            float n4 = 0.0f;
            for (int j = for1; j <= byte1; ++j) {
                float if1 = bk.if(j);
                if (if1 == Float.MIN_VALUE) {
                    if (n3 == 0) {
                        final int do1 = bk.do(j, byte1);
                        n4 = (bk.if(do1) - floatValue) / (do1 - j + 1);
                    }
                    floatValue = (if1 = floatValue + n4);
                    ++n3;
                }
                else {
                    floatValue = if1;
                    n3 = 0;
                }
                this.if.addElement(new Float(if1));
            }
            for (int k = byte1 + 1; k < bk.case(); ++k) {
                this.if.addElement(n2);
            }
        }
        this.if();
    }
    
    private synchronized void if(final bk bk) {
        this.new();
        if (bk.case() > 0) {
            final float else1 = bk.else();
            for (int i = 0; i < bk.case(); ++i) {
                final float if1 = bk.if(i);
                this.if.addElement(new Float((if1 == Float.MIN_VALUE) ? if1 : ((1.0f + (if1 - else1) / else1) * 100.0f)));
            }
        }
        this.if();
    }
    
    private synchronized void a(final bk bk) {
        this.if.removeAllElements();
        if (bk.case() > 0) {
            final float else1 = bk.else();
            for (int i = 0; i < bk.case(); ++i) {
                final float if1 = bk.if(i);
                this.if.addElement(new Float((if1 == Float.MIN_VALUE) ? if1 : ((if1 - else1) / else1 * 100.0f)));
            }
        }
        this.if();
    }
    
    private int for() {
        final int n = this.case() - 1;
        for (int i = 0; i <= n; ++i) {
            if (this.if(i) != Float.MIN_VALUE) {
                return i;
            }
        }
        return n;
    }
    
    private int byte() {
        for (int i = this.case() - 1; i >= 0; --i) {
            if (this.if(i) != Float.MIN_VALUE) {
                return i;
            }
        }
        return 0;
    }
    
    private int do(final int n, final int n2) {
        for (int i = n; i <= n2; ++i) {
            if (this.if(i) != Float.MIN_VALUE) {
                return i;
            }
        }
        return n2;
    }
    
    private Float a(final double n) {
        return (n == Double.MIN_NORMAL) ? bk.do : new Float(n);
    }
    
    private void a(final int n) {
        for (int i = 0; i < this.if.size() - n; ++i) {
            this.if.setElementAt(this.if.elementAt(i + n), i);
        }
    }
    
    static {
        do = new Float(Float.MIN_VALUE);
    }
}
