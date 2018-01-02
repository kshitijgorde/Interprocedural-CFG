import java.util.Enumeration;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class c5 extends c3
{
    private int a;
    private int b;
    
    public c5(final int n, final String[] array, final int a, final int b) {
        super(n, array);
        this.a = -1;
        this.b = -1;
        this.a = a;
        this.b = b;
    }
    
    public final int a() {
        return this.a;
    }
    
    public final Hashtable a(final c2 c2) {
        if (super.a == null) {
            return null;
        }
        final Hashtable<String, String> hashtable = new Hashtable<String, String>();
        final Hashtable hashtable2 = (c2 != null) ? c2.c() : null;
        if (hashtable2 == null) {
            for (int i = 0; i < super.a.length; ++i) {
                hashtable.put(super.a[i], super.a[i]);
            }
        }
        else {
            for (int j = 0; j < super.a.length; ++j) {
                if (hashtable2.containsKey(super.a[j])) {
                    hashtable.put(super.a[j], super.a[j]);
                }
            }
            if (hashtable.size() == hashtable2.size()) {
                return null;
            }
        }
        return hashtable;
    }
    
    public String toString() {
        return "UpdateEvent{" + this.a + "," + this.b + ", " + this.e() + "," + this.g() + "}";
    }
    
    public final String g() {
        final Hashtable c = this.c();
        final StringBuffer sb = new StringBuffer();
        if (c != null) {
            final Enumeration<String> keys = c.keys();
            while (keys.hasMoreElements()) {
                sb.append(keys.nextElement() + "  ");
            }
        }
        return sb.toString();
    }
}
