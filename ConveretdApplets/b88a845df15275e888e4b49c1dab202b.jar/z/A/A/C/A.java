// 
// Decompiled by Procyon v0.5.30
// 

package z.A.A.C;

import java.io.Serializable;

public class A extends Number implements Serializable
{
    private final int B;
    private final int C;
    private int A;
    
    public A(final int b, final int c) {
        this.A = 1000;
        this.B = b;
        this.C = c;
    }
    
    public double doubleValue() {
        return this.B / this.C;
    }
    
    public float floatValue() {
        return this.B / this.C;
    }
    
    public final byte byteValue() {
        return (byte)this.doubleValue();
    }
    
    public final int intValue() {
        return (int)this.doubleValue();
    }
    
    public final long longValue() {
        return (long)this.doubleValue();
    }
    
    public final short shortValue() {
        return (short)this.doubleValue();
    }
    
    public final int B() {
        return this.C;
    }
    
    public final int D() {
        return this.B;
    }
    
    public A C() {
        return new A(this.C, this.B);
    }
    
    public boolean A() {
        return this.C == 1 || (this.C != 0 && this.B % this.C == 0) || (this.C == 0 && this.B == 0);
    }
    
    public String toString() {
        return this.B + "/" + this.C;
    }
    
    public String A(final boolean b) {
        if (this.C == 0 && this.B != 0) {
            return this.toString();
        }
        if (this.A()) {
            return Integer.toString(this.intValue());
        }
        if (this.B != 1 && this.C % this.B == 0) {
            return new A(1, this.C / this.B).A(b);
        }
        final A e = this.E();
        if (b) {
            final String string = Double.toString(e.doubleValue());
            if (string.length() < 5) {
                return string;
            }
        }
        return e.toString();
    }
    
    private boolean F() {
        return (Math.min(this.C, this.B) - 1) / 5.0 + 2.0 > this.A;
    }
    
    public boolean equals(final Object o) {
        return o instanceof A && this.doubleValue() == ((A)o).doubleValue();
    }
    
    public A E() {
        if (this.F()) {
            return this;
        }
        for (int i = 2; i <= Math.min(this.C, this.B); ++i) {
            if (i % 2 != 0 || i <= 2) {
                if (i % 5 != 0 || i <= 5) {
                    if (this.C % i == 0 && this.B % i == 0) {
                        return new A(this.B / i, this.C / i);
                    }
                }
            }
        }
        return this;
    }
}
