import java.util.Date;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class aa
{
    String if;
    Vector a;
    
    aa(final String if1) {
        this.a = new Vector();
        this.if = if1;
    }
    
    public int a() {
        return this.a.size();
    }
    
    n a(final JavaWatch javaWatch, final String s, final Date date, final int n, final int n2) {
        final n n3 = new n(javaWatch, this, s, date, n, n2);
        this.a.add(n3);
        n3.if().a(n3);
        return n3;
    }
    
    public String toString() {
        return this.if;
    }
}
