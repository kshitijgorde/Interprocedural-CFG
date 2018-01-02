import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class c3 extends c4
{
    public String[] a;
    public Hashtable b;
    
    public c3(final int n, final String[] a) {
        super(n);
        this.a = a;
    }
    
    public String[] b() {
        return this.a;
    }
    
    public final Hashtable c() {
        if (this.b == null) {
            this.b = new Hashtable();
            if (this.a != null) {
                for (int i = 0; i < this.a.length; ++i) {
                    this.b.put(this.a[i], this.a[i]);
                }
            }
        }
        if (this.b.size() > 0) {
            return this.b;
        }
        return null;
    }
}
