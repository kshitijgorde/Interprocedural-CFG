// 
// Decompiled by Procyon v0.5.30
// 

package ji.ext;

import ji.io.p;
import ji.util.d;
import ji.io.h;

public class jiJNIUtil
{
    private String a;
    private final String b = "EXTERNAL DLL: ";
    private Object c;
    
    public jiJNIUtil(final String a, final Object c) {
        this.c = null;
        this.a = a;
        this.c = c;
    }
    
    public final Class classForName(String replace) {
        replace = replace.replace('/', '.');
        Class<?> forName = null;
        try {
            forName = Class.forName(replace);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return forName;
    }
    
    public final void logText(final String s) {
        h.d(this.a, "EXTERNAL DLL: ".concat(String.valueOf(String.valueOf(s))));
    }
    
    public final void releaseResources() {
        this.a = null;
        this.c = null;
    }
    
    public final String strRes(final int n) {
        return d.b(n, this.a);
    }
    
    public final void clearPref() {
        try {
            new p(this.a).ao(false, this.c);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
