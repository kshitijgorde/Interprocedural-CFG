// 
// Decompiled by Procyon v0.5.30
// 

package doook;

public class e
{
    private int e;
    private int f;
    private int a;
    private Object[] a;
    
    public synchronized boolean a() {
        return this.e == this.f;
    }
    
    public synchronized Object a() {
        if (this.e == this.f) {
            throw new ArrayIndexOutOfBoundsException("Queue is empty");
        }
        final Object o = this.a[this.e];
        this.a[this.e] = null;
        this.e = (this.e + 1) % this.a.length;
        --this.a;
        return o;
    }
    
    public synchronized void a(final Object o) {
        if ((this.f + 1) % this.a.length == this.e) {
            final Object[] a = new Object[2 * (this.a.length - 1) + 1];
            System.arraycopy(this.a, this.e, a, 0, this.a.length - this.e);
            if (this.e != 0) {
                System.arraycopy(this.a, 0, a, this.a.length - this.e, this.e - 1);
            }
            this.e = 0;
            this.f = this.a;
            this.a = a;
        }
        this.a[this.f] = o;
        this.f = (this.f + 1) % this.a.length;
        ++this.a;
    }
    
    public synchronized void b() {
        for (int i = 0; i < this.a.length; ++i) {
            this.a[i] = null;
        }
        final boolean b = false;
        this.f = (b ? 1 : 0);
        this.e = (b ? 1 : 0);
        this.a = 0;
    }
    
    public e() {
        this(10);
    }
    
    public e(final int n) {
        this.e = 0;
        this.f = 0;
        this.a = 0;
        this.a = new Object[n + 1];
    }
}
