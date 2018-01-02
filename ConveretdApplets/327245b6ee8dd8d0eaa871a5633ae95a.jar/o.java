import java.util.Enumeration;
import java.util.Hashtable;
import java.io.Serializable;

// 
// Decompiled by Procyon v0.5.30
// 

public class o implements Serializable
{
    Hashtable c;
    Hashtable m;
    Hashtable p;
    
    o() {
        this.c = new Hashtable();
        this.m = new Hashtable();
        this.p = new Hashtable();
    }
    
    String gc(final String s) {
        return this.c.get(s);
    }
    
    String gpc(final String s) {
        final Enumeration<String> keys = this.c.keys();
        while (keys.hasMoreElements()) {
            final String s2 = keys.nextElement();
            if (this.c.get(s2).equals(s)) {
                return s2;
            }
        }
        return null;
    }
    
    String gm(final String s, final String s2, final String[] array) {
        final String s3 = this.m.get(this.c(s, s2, array));
        if (s3 == null) {
            return s2;
        }
        return s3;
    }
    
    String gp(final String s, final String s2, final String[] array) {
        final String s3 = this.p.get(this.c(s, s2, array));
        if (s3 == null) {
            return s2;
        }
        return s3;
    }
    
    String c(final String s, final String s2, final String[] array) {
        String s3 = s + "." + s2;
        for (int i = 0; i < array.length; ++i) {
            s3 = s3 + "." + array[i];
        }
        return s3;
    }
}
