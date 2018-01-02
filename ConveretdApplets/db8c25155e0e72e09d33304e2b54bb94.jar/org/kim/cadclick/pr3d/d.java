// 
// Decompiled by Procyon v0.5.30
// 

package org.kim.cadclick.pr3d;

class d implements o
{
    protected int cc;
    protected String b5;
    protected n cf;
    protected int cs;
    protected int ch;
    protected int[] b3;
    protected String b7;
    protected String[] cv;
    protected boolean cj;
    protected float ci;
    protected float[] ck;
    protected double cm;
    protected n b6;
    protected n[] cx;
    
    protected d(final n cf, final int cs, final int cc) {
        this.b5 = null;
        this.cf = null;
        this.cf = cf;
        this.cs = cs;
        this.b5 = o.p[cs];
        this.cc = cc;
        cf.a(this.b5, this);
    }
    
    public synchronized void do(final float ci) throws IllegalArgumentException {
        if (this.cc != 6) {
            throw new IllegalArgumentException();
        }
        this.ci = ci;
        this.cf.for();
    }
    
    public synchronized void a(final int n, int length, final float[] array) throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
        if (this.cc != 4 && this.cc != 12 && this.cc != 16 && this.cc != 18 && this.cc != 5 && this.cc != 7 && this.cc != 13 && this.cc != 17 && this.cc != 19) {
            throw new IllegalArgumentException();
        }
        if (length == -1) {
            length = array.length;
            if (n == 0 && this.ck.length != length) {
                this.ck = new float[length];
            }
        }
        if (length + n > this.ck.length) {
            final float[] ck = new float[length + n];
            System.arraycopy(this.ck, 0, ck, 0, this.ck.length);
            this.ck = ck;
        }
        System.arraycopy(array, 0, this.ck, n, length);
        this.cf.for();
    }
}
