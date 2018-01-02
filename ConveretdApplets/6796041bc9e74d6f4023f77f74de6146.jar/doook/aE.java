// 
// Decompiled by Procyon v0.5.30
// 

package doook;

public class aE
{
    private int g;
    private int h;
    private int i;
    private Object[] a;
    
    public synchronized boolean a() {
        return this.g == this.h;
    }
    
    public synchronized Object b() {
        if (this.g == this.h) {
            throw new ArrayIndexOutOfBoundsException("Queue is empty");
        }
        final Object o = this.a[this.g];
        this.a[this.g] = null;
        this.g = (this.g + 1) % this.a.length;
        --this.i;
        return o;
    }
    
    public synchronized void a(final Object o) {
        if ((this.h + 1) % this.a.length == this.g) {
            final Object[] a = new Object[2 * (this.a.length - 1) + 1];
            System.arraycopy(this.a, this.g, a, 0, this.a.length - this.g);
            if (this.g != 0) {
                System.arraycopy(this.a, 0, a, this.a.length - this.g, this.g - 1);
            }
            this.g = 0;
            this.h = this.i;
            this.a = a;
        }
        this.a[this.h] = o;
        this.h = (this.h + 1) % this.a.length;
        ++this.i;
    }
    
    public synchronized void d() {
        for (int i = 0; i < this.a.length; ++i) {
            this.a[i] = null;
        }
        final boolean b = false;
        this.h = (b ? 1 : 0);
        this.g = (b ? 1 : 0);
        this.i = 0;
    }
    
    public aE() {
        this(10);
    }
    
    public aE(final int n) {
        this.g = 0;
        this.h = 0;
        this.i = 0;
        this.a = new Object[n + 1];
    }
}
