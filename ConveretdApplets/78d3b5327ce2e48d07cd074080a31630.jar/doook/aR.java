// 
// Decompiled by Procyon v0.5.30
// 

package doook;

public class aR
{
    private int c;
    private int t;
    private int g;
    private Object[] a;
    
    public synchronized boolean b() {
        return this.c == this.t;
    }
    
    public synchronized Object a() {
        if (this.c == this.t) {
            throw new ArrayIndexOutOfBoundsException("Queue is empty");
        }
        final Object o = this.a[this.c];
        this.a[this.c] = null;
        this.c = (this.c + 1) % this.a.length;
        --this.g;
        return o;
    }
    
    public synchronized void a(final Object o) {
        if ((this.t + 1) % this.a.length == this.c) {
            final Object[] a = new Object[2 * (this.a.length - 1) + 1];
            System.arraycopy(this.a, this.c, a, 0, this.a.length - this.c);
            if (this.c != 0) {
                System.arraycopy(this.a, 0, a, this.a.length - this.c, this.c - 1);
            }
            this.c = 0;
            this.t = this.g;
            this.a = a;
        }
        this.a[this.t] = o;
        this.t = (this.t + 1) % this.a.length;
        ++this.g;
    }
    
    public synchronized void g() {
        for (int i = 0; i < this.a.length; ++i) {
            this.a[i] = null;
        }
        final boolean b = false;
        this.t = (b ? 1 : 0);
        this.c = (b ? 1 : 0);
        this.g = 0;
    }
    
    public aR() {
        this(10);
    }
    
    public aR(final int n) {
        this.c = 0;
        this.t = 0;
        this.g = 0;
        this.a = new Object[n + 1];
    }
}
