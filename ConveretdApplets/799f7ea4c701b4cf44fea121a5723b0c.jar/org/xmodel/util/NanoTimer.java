// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.util;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class NanoTimer
{
    long G;
    long J;
    long A;
    long B;
    long I;
    long D;
    int H;
    String E;
    static long C;
    static long F;
    
    public NanoTimer() {
        if (NanoTimer.F == 0L) {
            A(this);
        }
        this.A = 0L;
        this.B = 0L;
        this.D = 0L;
        this.J = 0L;
        this.H = 0;
    }
    
    public NanoTimer(final long n) {
        this();
        this.I = n * 1000L;
    }
    
    public NanoTimer(final String e, final long n) {
        this();
        this.E = e;
        this.I = n * 1000L;
    }
    
    public void start() {
        this.G = System.nanoTime();
    }
    
    public long stop() {
        final long n = System.nanoTime() - this.G;
        this.J = n - NanoTimer.F;
        this.B += this.J;
        this.A += n - NanoTimer.C;
        this.D += this.J;
        ++this.H;
        if (this.I > 0L && this.D > this.I) {
            this.D = 0L;
            System.out.println(this.toString());
        }
        return n - (int)NanoTimer.F;
    }
    
    public long minimum() {
        return this.A;
    }
    
    public long estimated() {
        return this.B;
    }
    
    protected String toString(final String s, final String s2, final int n) {
        final StringBuilder sb = new StringBuilder();
        sb.append((this.E == null) ? "timer: " : this.E);
        sb.append('[');
        sb.append(n);
        sb.append("] ");
        sb.append(s2);
        sb.append(" (");
        sb.append(s);
        sb.append(')');
        return sb.toString();
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        format(sb, this.B);
        final String string = sb.toString();
        sb.setLength(0);
        format(sb, this.J);
        return this.toString(string, sb.toString(), this.H);
    }
    
    public static void format(final StringBuilder sb, final float n) {
        if (n < 10000.0f) {
            sb.append(n);
            sb.append("ns");
        }
        else if (n < 1000000.0f) {
            sb.append(n / 1000.0f);
            sb.append("us");
        }
        else if (n < 1.0E9f) {
            sb.append(n / 1000000.0f);
            sb.append("ms");
        }
        else {
            sb.append(n / 1.0E9f);
            sb.append("s");
        }
    }
    
    private static void A(final NanoTimer nanoTimer) {
        NanoTimer.C = Long.MAX_VALUE;
        long n = 0L;
        final int n2 = 200000;
        for (int i = 0; i < n2; ++i) {
            nanoTimer.start();
            final long stop = nanoTimer.stop();
            if (stop < NanoTimer.C) {
                NanoTimer.C = stop;
            }
            n += stop;
        }
        NanoTimer.F = n / n2;
    }
    
    public static void main(final String[] array) {
        final NanoTimer nanoTimer = new NanoTimer(1L);
        for (int i = 0; i < 100000; ++i) {
            nanoTimer.start();
            nanoTimer.stop();
        }
    }
}
