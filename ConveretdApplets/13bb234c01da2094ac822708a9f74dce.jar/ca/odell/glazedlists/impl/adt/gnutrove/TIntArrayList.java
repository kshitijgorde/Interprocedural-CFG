// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists.impl.adt.gnutrove;

public class TIntArrayList implements Cloneable
{
    protected transient int[] a;
    protected transient int b;
    
    public TIntArrayList() {
        this(10);
    }
    
    public TIntArrayList(final int n) {
        this.a = new int[n];
        this.b = 0;
    }
    
    public void a(final int n) {
        if (n > this.a.length) {
            final int[] a = new int[Math.max(this.a.length << 1, n)];
            System.arraycopy(this.a, 0, a, 0, this.a.length);
            this.a = a;
        }
    }
    
    public int a() {
        return this.b;
    }
    
    public boolean b() {
        return this.b == 0;
    }
    
    public void b(final int n) {
        this.a(this.b + 1);
        this.a[this.b++] = n;
    }
    
    public int c(final int n) {
        if (n >= this.b) {
            throw new ArrayIndexOutOfBoundsException(n);
        }
        return this.a[n];
    }
    
    public void a(final int n, final int n2) {
        if (n >= this.b) {
            throw new ArrayIndexOutOfBoundsException(n);
        }
        this.a[n] = n2;
    }
    
    public void c() {
        this.d(10);
    }
    
    public void d(final int n) {
        this.a = new int[n];
        this.b = 0;
    }
    
    public Object clone() {
        TIntArrayList list = null;
        try {
            list = (TIntArrayList)super.clone();
            list.a = this.a.clone();
        }
        catch (CloneNotSupportedException ex) {}
        return list;
    }
    
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof TIntArrayList)) {
            return false;
        }
        final TIntArrayList list = (TIntArrayList)o;
        if (list.a() != this.a()) {
            return false;
        }
        int b = this.b;
        while (b-- > 0) {
            if (this.a[b] != list.a[b]) {
                return false;
            }
        }
        return true;
    }
    
    public int hashCode() {
        int n = 0;
        int b = this.b;
        while (b-- > 0) {
            n += this.a[b];
        }
        return n;
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer("{");
        for (int i = 0; i < this.b - 1; ++i) {
            sb.append(this.a[i]);
            sb.append(", ");
        }
        if (this.a() > 0) {
            sb.append(this.a[this.b - 1]);
        }
        sb.append("}");
        return sb.toString();
    }
}
