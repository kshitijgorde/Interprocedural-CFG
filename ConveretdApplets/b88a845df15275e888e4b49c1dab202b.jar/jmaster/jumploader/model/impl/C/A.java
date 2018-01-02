// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.model.impl.C;

import jmaster.jumploader.model.api.common.ITransferProgress;

public class A implements ITransferProgress
{
    private long F;
    private long C;
    private long B;
    private long A;
    private long D;
    private int G;
    private int[] I;
    private int H;
    private boolean E;
    
    public A() {
        this.G = 10;
        this.H = 0;
        this.E = false;
    }
    
    public long getBytesTotal() {
        return this.C;
    }
    
    public void D(final long c) {
        this.C = c;
    }
    
    public long getBytesTransferred() {
        return this.F;
    }
    
    public void A(final long f) {
        this.F = f;
    }
    
    public int getRate() {
        final int n = this.E ? this.I.length : this.H;
        if (n > 0) {
            int n2 = 0;
            for (int i = 0; i < n; ++i) {
                n2 += this.I[i];
            }
            return n2 / n;
        }
        return 0;
    }
    
    public void A(final int n) {
        this.I[this.H++] = n;
        if (this.H == this.I.length) {
            this.H = 0;
            this.E = true;
        }
    }
    
    public long A() {
        return this.B;
    }
    
    public void E(final long b) {
        this.B = b;
    }
    
    public long D() {
        return this.D;
    }
    
    public void B(final long d) {
        this.D = d;
    }
    
    public long B() {
        return this.A;
    }
    
    public void C(final long a) {
        this.A = a;
    }
    
    public int C() {
        return this.G;
    }
    
    public void B(final int g) {
        this.G = g;
        this.I = new int[this.G];
    }
    
    public long getBytesLeft() {
        return this.C - this.F;
    }
    
    public double getCompletion() {
        return (this.C > 0L) ? (this.F / this.C) : 0.0;
    }
    
    public double getCompletionPercent() {
        return this.getCompletion() * 100.0;
    }
    
    public long getTimeLeft() {
        long n = -1L;
        final int rate = this.getRate();
        if (rate > 0) {
            n = 1000L * this.getBytesLeft() / rate;
        }
        return n;
    }
}
