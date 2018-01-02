// 
// Decompiled by Procyon v0.5.30
// 

public class c2 extends c3
{
    private int[] a;
    
    public c2(final int n, final String[] array, final int[] a) {
        super(n, array);
        this.a = a;
    }
    
    public c2(final int n, final String[] array, final int n2) {
        super(n, array);
        this.a = new int[] { n2 };
    }
    
    public c2(final int n, final String[] array) {
        this(n, array, null);
    }
    
    public c2(final int n) {
        this(n, null, null);
    }
    
    public c2() {
        this(510, null, null);
    }
    
    public int[] a() {
        return this.a;
    }
    
    public String toString() {
        return "SubscriptionFilter{" + this.g() + ";" + this.f() + ";" + this.c() + "}";
    }
    
    private String g() {
        if (this.a == null) {
            return "";
        }
        final StringBuffer sb = new StringBuffer(this.a.length);
        for (int i = 0; i < this.a.length; ++i) {
            sb.append(this.a[i] + ",");
        }
        return sb.toString();
    }
}
