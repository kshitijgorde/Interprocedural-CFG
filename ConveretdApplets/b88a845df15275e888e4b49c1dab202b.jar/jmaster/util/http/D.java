// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.http;

import java.io.File;

public class D
{
    protected String D;
    protected String B;
    protected String A;
    protected String F;
    protected long E;
    protected long C;
    
    public D() {
        this.E = 0L;
        this.C = -1L;
    }
    
    public String G() {
        return this.A;
    }
    
    public void D(final String a) {
        this.A = a;
    }
    
    public String E() {
        return this.B;
    }
    
    public void B(final String b) {
        this.B = b;
    }
    
    public String C() {
        return this.F;
    }
    
    public void A(final String f) {
        this.F = f;
    }
    
    public long D() {
        return this.C;
    }
    
    public void B(final long c) {
        this.C = c;
    }
    
    public long A() {
        return this.E;
    }
    
    public void A(final long e) {
        this.E = e;
    }
    
    public String F() {
        return this.D;
    }
    
    public void C(final String d) {
        this.D = d;
    }
    
    public long B() {
        long c = new File(this.D).length() - this.E;
        if (this.C < c && this.C > 0L) {
            c = this.C;
        }
        return c;
    }
}
