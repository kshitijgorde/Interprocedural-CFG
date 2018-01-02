// 
// Decompiled by Procyon v0.5.30
// 

package a.b.f;

import java.util.Random;

public class l implements h
{
    private static Random a;
    private static Random b;
    private int c;
    
    public l() {
        this(null);
    }
    
    public l(final Random random) {
        this.c = 1;
        this.a(random);
    }
    
    public int available() {
        return this.c;
    }
    
    public byte[] a(final int n) {
        final byte[] array = new byte[n];
        l.b.nextBytes(array);
        return array;
    }
    
    public void a(final Random b) {
        if (b == null) {
            l.b = new Random();
        }
        else {
            l.b = b;
        }
    }
    
    static {
        l.a = new Random();
    }
}
