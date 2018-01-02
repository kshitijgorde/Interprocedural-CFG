// 
// Decompiled by Procyon v0.5.30
// 

package y;

public final class cy
{
    private Object[] a;
    private int a;
    private Class a;
    private int b;
    
    public cy(Object o2) {
        this.b = 0;
        this.a = o.getClass();
        (this.a = new Object[8])[0] = o;
        for (o2 = 1; o2 < 8; ++o2) {
            try {
                this.a[o2] = this.a.newInstance();
            }
            catch (Exception ex) {
                ex.printStackTrace();
                System.exit(1);
            }
        }
        this.a = 8;
    }
    
    public final synchronized Object a() {
        ++this.b;
        if (this.a == 0) {
            try {
                return this.a.newInstance();
            }
            catch (Exception ex) {
                ex.printStackTrace();
                System.exit(1);
                return null;
            }
        }
        --this.a;
        return this.a[this.a];
    }
    
    public final synchronized void a(final Object o) {
        --this.b;
        if (this.b < 0) {
            System.err.println("assertion failed deallocate twice");
            Thread.dumpStack();
            System.exit(1);
        }
        if (this.a == this.a.length) {
            final Object[] a = new Object[this.a.length << 1];
            System.arraycopy(this.a, 0, a, 0, this.a);
            this.a = a;
        }
        this.a[this.a] = o;
        ++this.a;
    }
}
