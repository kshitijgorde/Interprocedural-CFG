// 
// Decompiled by Procyon v0.5.30
// 

package a.b.n;

import org.a.c.f;
import java.io.InputStream;

public class b implements d
{
    private String a;
    
    public static boolean a(final InputStream inputStream) {
        return org.a.c.b.a() && org.a.c.b.a(inputStream);
    }
    
    public static boolean a(final String s) {
        return org.a.c.b.a() && org.a.c.b.a(s);
    }
    
    public b() {
    }
    
    public b(final String s) {
        a(s);
    }
    
    public void b(final String a) {
        this.a = a;
    }
    
    public f a() {
        if (this.a == null) {
            return new org.a.c.b();
        }
        return new org.a.c.b(this.a);
    }
    
    public f a(final Object o) {
        if (o == null) {
            return this.a();
        }
        String s;
        if (o instanceof Class) {
            s = ((Class)o).getName();
        }
        else {
            s = o.toString();
        }
        if (this.a == null) {
            return new org.a.c.b(s);
        }
        return new org.a.c.b(new String(this.a + s));
    }
}
