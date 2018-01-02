// 
// Decompiled by Procyon v0.5.30
// 

package chat;

public final class as
{
    private int a;
    private int b;
    private int c;
    private Object[] a;
    
    public final synchronized boolean a() {
        return this.a == this.b;
    }
    
    public final synchronized Object a() {
        if (this.a == this.b) {
            throw new ArrayIndexOutOfBoundsException("Queue is empty");
        }
        final Object o = this.a[this.a];
        this.a[this.a] = null;
        this.a = (this.a + 1) % this.a.length;
        --this.c;
        return o;
    }
    
    public final synchronized void a(final Object o) {
        if ((this.b + 1) % this.a.length == this.a) {
            final Object[] a = new Object[2 * (this.a.length - 1) + 1];
            System.arraycopy(this.a, this.a, a, 0, this.a.length - this.a);
            if (this.a != 0) {
                System.arraycopy(this.a, 0, a, this.a.length - this.a, this.a - 1);
            }
            this.a = 0;
            this.b = this.c;
            this.a = a;
        }
        this.a[this.b] = o;
        this.b = (this.b + 1) % this.a.length;
        ++this.c;
    }
    
    public final synchronized void a() {
        for (int i = 0; i < this.a.length; ++i) {
            this.a[i] = null;
        }
        final boolean b = false;
        this.b = (b ? 1 : 0);
        this.a = (b ? 1 : 0);
        this.c = 0;
    }
    
    public as(final byte b) {
        this();
    }
    
    private as() {
        this.a = 0;
        this.b = 0;
        this.c = 0;
        this.a = new Object[11];
    }
}
