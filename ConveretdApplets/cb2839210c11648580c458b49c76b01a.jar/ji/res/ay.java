// 
// Decompiled by Procyon v0.5.30
// 

package ji.res;

import ji.util.d;
import ji.util.e;

public class ay
{
    public static final String a() {
        if (e.u()) {
            if (d.ei()) {
                return String.valueOf(String.valueOf(c())).concat(" .Net");
            }
            return c();
        }
        else {
            if (d.ei()) {
                return String.valueOf(String.valueOf(b())).concat(" .Net");
            }
            return b();
        }
    }
    
    public static final String a(final String s) {
        if (s != null) {
            return s;
        }
        return s;
    }
    
    public static final String b() {
        return s.c("ViewONE");
    }
    
    public static final String c() {
        return s.c("ViewONE Pro");
    }
    
    public static final String d() {
        return s.c("Daeja");
    }
}
